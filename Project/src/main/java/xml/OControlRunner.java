package main.java.xml;

import main.java.utility.utils.MyUtils;
import main.java.constants.DependingConstants;

import java.io.*;
import java.util.List;

public class OControlRunner {
    private File commandFile;

    public static OControlRunner of(){
        return new OControlRunner();
    }

    public void addUpdateXml(Class currentTestClass, List<File> xmlFiles) {
        File testsDir = MyUtils.getDirByClass(currentTestClass);
        String xmlsFilePath =  testsDir.getPath() + "\\xml.xml";
        this.commandFile = new File(xmlsFilePath);
        if (this.commandFile.exists()){
            this.commandFile.delete();
        }
        try {
            this.commandFile.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException("Can't create " + this.commandFile);
        }
        try (FileOutputStream stream = new FileOutputStream(this.commandFile)) {
            for (File xml : xmlFiles) {
                String oControlString = "UPDATE " + xml.getPath() + "\n";
                stream.write(oControlString.getBytes());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find " + this.commandFile);
        } catch (IOException e) {
            throw new IllegalStateException("Reading error in " + this.commandFile);
        }
    }

    public void runFromCommandFile(){
        ProcessBuilder builder = new ProcessBuilder(DependingConstants.OCONTROL_PATH);
        builder.redirectInput(commandFile);
        run(builder);
    }

    public void resetData(){
        ProcessBuilder builder = new ProcessBuilder(DependingConstants.OCONTROL_PATH, "reset");
        run(builder);
    }


    private void run(final ProcessBuilder builder) {
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            throw new IllegalStateException("Can't start oControl.exe");
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Can't interrupt oControl.exe");
        }
    }
}
