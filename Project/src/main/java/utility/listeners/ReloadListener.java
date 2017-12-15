package main.java.utility.listeners;

import com.codeborne.selenide.Condition;
import main.java.constants.DependingConstants;
import main.java.utility.utils.FailureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ReloadListener implements ITestListener {
    public static Logger logger = LogManager.getLogger(ReloadListener.class);
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        FailureUtils.attachScreenshot(FailureUtils.messageBuilder(result));
        String url = (String) result.getTestContext().getAttribute(DependingConstants.NAME_ATTR_URL);
        open(url);
        $(byId("ol_main")).shouldBe(Condition.visible);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
