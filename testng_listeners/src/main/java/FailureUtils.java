import com.codeborne.selenide.Screenshots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FailureUtils {
    private static Logger logger = LogManager.getLogger(FailureUtils.class);
    private FailureUtils(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    public static void attachScreenshot(String message){
        File file = Screenshots.takeScreenShotAsFile();
        try {
            logger.warn("RP_MESSAGE#BASE64#{}#{}",
                            Files.readAllBytes(file.toPath()),
                            message);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read screenshot fom file " + file, e);
        }
    }

    public static String messageBuilder(ITestResult result){
        String testMethod = result.getMethod().getMethodName();
        String testClass = result.getTestClass().getName();
        String testParameters = Arrays.toString(result.getParameters());
        return "\nTest class: " + testClass +
               "\nMethod name: " + testMethod +
               "\nTest parameters: " + testParameters;
    }
}
