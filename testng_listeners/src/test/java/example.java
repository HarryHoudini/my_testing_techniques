import com.codeborne.selenide.WebDriverRunner;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class example {
    @BeforeClass
    public void setUp(ITestContext context){
        // something precondition
        context.setAttribute("baseUrl", WebDriverRunner.url());
    }

    // something test
}
