package cams.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cams.components.option.Options;
import cams.interfaces.UI;
import cams.interfaces.View;

/**
 * NavigationManager is a class responsible for managing views for navigation purposes.
 * It uses a DependencyManager to create instances of views based on information stored in the views.csv file.
 *
 * @author Toh Kar Le
 */
public class NavigationService {
    
    private String filePath;
    private HashMap<String, View> viewMap;
    private DependencyService service;

    public NavigationService(DependencyService service) {
        this.service = service;
        this.filePath = "cams/data/views.csv";
        this.viewMap = new HashMap<>();
    }

    public View getView(String viewName) {
        if (viewMap.containsKey(viewName)) {
            return viewMap.get(viewName);
        } else {
            // If view not found, fetch dependencies and create instance
            View view = createViewInstanceFromCsv(viewName);
            if (view != null) {
                viewMap.put(viewName, view);
            }
            return view;
        }
    }

    public String getViewName(View view) {
        for (Map.Entry<String, View> entry : viewMap.entrySet()) {
            if (entry.getValue().equals(view)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private View createViewInstanceFromCsv(String viewName) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(viewName)) {
                    return createViewInstance(viewName, parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // KARLE_TODO: Show user/developer that the view is not available
        }
        return null;
    }

    private View createViewInstance(String viewName, String[] navigationParts) {

        ArrayList<String> optionNames = new ArrayList<>();
        ArrayList<String> inputFieldNames = new ArrayList<>();

        for (int i = 1; i < navigationParts.length; i++) {
            String fieldName = navigationParts[i].trim();
            if (fieldName.endsWith("Options")) {
                optionNames.add(fieldName);
            } else if (fieldName.endsWith("UI")) {
                inputFieldNames.add(fieldName);
            }
        }

        View view = (View) service.getInstance(viewName);

        if (!optionNames.isEmpty()) {
            view.setOptions(createOptionsInstance(optionNames));
        }
        
        if (!inputFieldNames.isEmpty()) {
            view.setUIs(creatUIInstance(inputFieldNames));
        }

        return view;
    }

    private HashMap<String, Options> createOptionsInstance(ArrayList<String> optionNames) {
        HashMap<String, Options> viewOptions = new HashMap<>();
        for (String optionName : optionNames) {
            Options options = (Options) service.getInstance(optionName);
            viewOptions.put(optionName, options);
        }
        return viewOptions;
    }

    private HashMap<String, UI> creatUIInstance(ArrayList<String> inputFieldNames) {
        HashMap<String, UI> viewInputFields = new HashMap<>();
        for (String inputFieldName : inputFieldNames) {
            UI ui = (UI) service.getInstance(inputFieldName);
            viewInputFields.put(inputFieldName, ui);
        }
        return viewInputFields;
    }
}
