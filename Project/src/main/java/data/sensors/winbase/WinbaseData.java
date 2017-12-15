package main.java.data.sensors.winbase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import main.java.utility.utils.OptionalUtils;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.byValue;

@Getter
public class WinbaseData extends SensorData {
    private final Optional<String> caption;
    private final Optional<String> expectedValue;
    private final Optional<ConditionType> operator;

    @AllArgsConstructor
    @Getter
    public enum ConditionType implements MyLocatorInterface, MySelectOptionsInterface {
        EQUALS    ("string:equal"),
        NOT_EQUALS("string:nonequal"),
        GREATER   ("string:greater"),
        LESS      ("string:less");

        private String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public WinbaseData(Builder builder){
        this.caption       = builder.caption;
        this.expectedValue = builder.expectedValue;
        this.operator      = builder.operator;
    }

    public static class Builder{
        private Optional<String> caption              = Optional.empty();
        private Optional<String> expectedValue        = Optional.empty();
        private Optional<ConditionType> operator = Optional.empty();

        public static Builder of(){
            return new Builder();
        }

        public static Builder of(String caption){
            return Builder.of().setCaption(caption);
        }

        public Builder setCaption(String caption){
            this.caption = Optional.of(caption);
            return this;
        }

        public Builder setExpectedValue(Long expectedValue){
            this.expectedValue = Optional.of(expectedValue.toString());
            return this;
        }

        public Builder setExpectedValueDouble(Double expectedValue){
            this.expectedValue = Optional.of(expectedValue.toString());
            return this;
        }

        public Builder setExpectedValueString(String expectedValue){
            this.expectedValue = Optional.of(expectedValue);
            return this;
        }

        public Builder setOperator(ConditionType operator){
            this.operator = Optional.of(operator);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinbaseData expectedData = (WinbaseData) o;

        return
            OptionalUtils.optionalEquals(this.caption,       expectedData.caption)       &&
            OptionalUtils.optionalEquals(this.expectedValue, expectedData.expectedValue) &&
            OptionalUtils.optionalEquals(this.operator,      expectedData.operator);
}

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.expectedValue);
        result = 31 * result + OptionalUtils.optionalHashCode(this.operator);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
            "{" +
            "caption="         + OptionalUtils.optionalToString(this.caption)       +
            ", expectedValue=" + OptionalUtils.optionalToString(this.expectedValue) +
            ", operator="      + OptionalUtils.optionalToString(this.operator)      +
            '}';
    }
}
