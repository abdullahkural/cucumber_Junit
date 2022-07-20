package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1- Create the object of the Properties class
    //the reason that we made Properties object private and static is: we want to make this logic
    //1- we want it to be running before everything else
    //2- we have the static block here. We have the static block, and we can not write the code directly into the java class
    private static Properties properties = new Properties();

    static{

        try{
            //2- We need to open the file in java memory
            FileInputStream file = new FileInputStream("configuration.properties");

            //3- Load the properties object using FileInputStream object
            properties.load(file);

            //close the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class!");
            e.printStackTrace();
        }


    }

    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }

}

/*Let’s create our utility method
        Reading one time is good, but we need to create a re-usable utility method, so we can read from our ".properties" file whenever we want.
        1. Create a new class: "ConfigurationReader"
        2. Copy/paste our ".properties" file reading logic
        3. Edit if needed
        4. Create a new utility method to "re-use" our logic
        5. Method should accept “key” as a String, and return “value” as String
        */