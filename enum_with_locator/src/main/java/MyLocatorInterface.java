import org.openqa.selenium.By;

public interface MyLocatorInterface {
    By getLocator();

    default String getCaption(){
        return "None";
    }
}
