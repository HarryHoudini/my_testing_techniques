package main.java.utility.utils;

import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.actions;

public class MyUtils {
    public static void myDragAndDrop(SelenideElement source, SelenideElement target){
        actions()
            .clickAndHold(source)
            .moveByOffset(5, 5)
            .moveToElement(target)
            .release()
            .build()
            .perform();
    }

    public static boolean deleteDirectory(File path){
        if(path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return( path.delete() );
    }

    public static File getDirByClass(Class className){
        String[] classStrings = className.toString().split("\\.");
        String classString = MyStreamMethods.getLast(Arrays.asList(classStrings))
            .orElseThrow(() -> new IllegalArgumentException(className + "nas not class name"));

        String folderPath =  className
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .getPath() + classString + "\\";
        File folder = new File(folderPath);
        if (!folder.exists()){
            folder.mkdir();
        }
        return folder;
    }

}
