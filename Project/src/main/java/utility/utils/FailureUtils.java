package main.java.utility.utils;

import com.codeborne.selenide.Screenshots;
import com.epam.reportportal.service.ReportPortal;
import org.testng.ITestResult;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;

public class FailureUtils {
    private FailureUtils(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    public static void attachScreenshot(String message){
        final File file = Screenshots.takeScreenShotAsFile();
        ReportPortal.emitLog(message, "WARN", Calendar.getInstance().getTime(), file);
    }

    public static String messageBuilder(ITestResult result){
        String testName = result.getMethod().getMethodName();
        String testClass = result.getTestClass().getName();
        String testParameters = Arrays.toString(result.getParameters());
        return "Test class: " + testClass +
               "//n Method name: " + testName +
               "//n Test parameters: " + testParameters;
    }
}
