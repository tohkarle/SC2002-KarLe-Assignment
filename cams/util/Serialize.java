package cams.util;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An object that reads and writes Serializable Object List into/ from a file
 */
public class Serialize
{
	/**
	 * Used to read from a file a Serializable Object into specific Class Object like ArrayList based on filename.
	 * @param filename The filename to be read (eg. save.dat). 
	 * Include Map in name to return a HashMap Object. (eg. userMap.sav)
	 * Include List in name to return a ArrayList Object. (eg. campList.sav)
	 * @return Object, still needs to be cast to specific ArrayList<Class>, HashMap<String, Class>
	 */
	public static Object load(String filename) {
		Object pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);

            // check file name for type of Object
            if (filename.contains("List")){
                pDetails = (ArrayList<?>) in.readObject();
            }
            else if (filename.contains("Map")){
                pDetails = (HashMap<?, ?>) in.readObject();
            }
            else{
                // base data type
                pDetails = in.readObject();
            }

            in.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	/**
	 * Used to write a Serializable Object into a file
	 * @param filename The filename to be writen into (eg. save.dat)
	 * @param obj The Object to be writen into the file
	 */
	public static void save(String filename, Object obj) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}