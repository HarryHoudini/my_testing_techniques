package main.java.utility.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.vavr.Tuple2;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class MyConditions {
    public static Condition mainFieldDevice(String expectedValue){
        return new Condition("mainFieldDevice") {
            @Override
            public boolean apply(WebElement webElement) {
                return expectedValue.equals(DeviceOfNetworkTree.getMainFieldText(webElement));
            }
        };
    }
    public static Condition additionalFieldDevice(String expectedValue){
        return new Condition("additionalFieldDevice") {
            @Override
            public boolean apply(WebElement webElement) {
                return expectedValue.equals(DeviceOfNetworkTree.getAdditionalFieldText(webElement));
            }
        };
    }
    public static Condition nameGroup(String expectedValue){
        return new Condition("nameGroup") {
            @Override
            public boolean apply(WebElement webElement) {
                return expectedValue.equals(GroupOfNetworkTree.getCaption(webElement));
            }
        };
    }

    public static <T, S> boolean waitingBy(BiFunction<T, S, Boolean> function, Tuple2<T, S> funcParametr){
        final long timeout = Configuration.timeout;
        final long startTime = System.currentTimeMillis();
        do{
            try {
                if (function.apply(funcParametr._1, funcParametr._2)) {
                    return true;
                }
            }
            catch(NoSuchElementException | NullPointerException e){
                // Logger
            }
        } while(System.currentTimeMillis() - startTime < timeout);
        return false;
    }
}
