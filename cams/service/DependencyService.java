package cams.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DependencyService {

    private String filePath;
    private Map<String, Object> instanceMap;
    private Set<String> currentlyCreating;

    public DependencyService() {
        this.filePath = "cams/data/dependencies.csv";
        this.instanceMap = new HashMap<>();
        this.currentlyCreating = new HashSet<>();
    }

    public Object getInstance(String className) {

        if (className.equals("DependencyService")) { 
            return this;
        }

        if (instanceMap.containsKey(className)) {
            return instanceMap.get(className);
        } else {
            if (currentlyCreating.contains(className)) {
                // Circular dependency detected
                System.out.println("Circular dependency detected for class: " + className);
                return null;
            }

            currentlyCreating.add(className);
            Object instance = createInstanceFromCsv(className);
            currentlyCreating.remove(className);

            if (instance != null) {
                instanceMap.put(className, instance);
            }
            return instance;
        }
    }

    public void clearCurrentlyCreating() {
        currentlyCreating.clear();
    }

    private Object createInstanceFromCsv(String className) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(className)) {
                    return createInstance(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createInstance(String[] dependencyParts) {
        try {
            String className = dependencyParts[0].trim();
            Class<?> clazz = Class.forName(getFullClassName(className));
            Object[] parameters = new Object[dependencyParts.length - 1];

            for (int i = 1; i < dependencyParts.length; i++) {
                String dependencyClassName = dependencyParts[i].trim();
                Object dependencyInstance = getInstance(dependencyClassName);
                parameters[i - 1] = dependencyInstance;
            }

            Constructor<?> compatibleConstructor = findCompatibleConstructor(clazz, parameters);
            if (compatibleConstructor == null) {
                throw new NoSuchMethodException("No compatible constructor found for " + className);
            } else {
                System.out.println("Found a compatible constructor " + compatibleConstructor);
            }

            return compatibleConstructor.newInstance(parameters);
        } catch (ReflectiveOperationException e) {
            System.out.println("Create instance unsuccessful for " + dependencyParts[0].trim());
            e.printStackTrace();
            // KARLE_TODO: Show user/developer dependecy is not available
        }
        return null;
    }

    private String getFullClassName(String className) {
        // Determine the package based on the class name
        if (className.endsWith("View")) {
            return "cams.view." + className;
        } else if (className.endsWith("Options")) {
            return "cams.option." + className;
        } else if (className.endsWith("UI")) {  
            return "cams.ui." + className;
        } else if (className.endsWith("Manager")) {
            return "cams.manager." + className;
        } else if (className.endsWith("Service")) {
            return "cams.service." + className;
        } else {
            return "cams." + className;
        }
    }

    private Constructor<?> findCompatibleConstructor(Class<?> clazz, Object[] parameters) {
        // For each constructor in the class
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] paramTypes = constructor.getParameterTypes();

            // If the parameter length doesn't match, continue to the next constructor
            if (paramTypes.length != parameters.length) {
                continue;
            }

            boolean compatible = true;
            // Check if each parameter is assignable from the given parameter instances
            for (int i = 0; i < paramTypes.length; i++) {
                if (!paramTypes[i].isAssignableFrom(parameters[i].getClass())) {
                    compatible = false;
                    break;
                }
            }

            // If all parameters are compatible, return this constructor
            if (compatible) {
                return constructor;
            }
        }

        // No compatible constructor found
        return null;
    }
}
