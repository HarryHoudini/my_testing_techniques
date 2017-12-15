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
public class RegistryKeyValueData extends SensorData {
    private final Optional<String>        caption;
    private final Optional<String>        key;
    private final Optional<String>        valueName;
    private final Optional<Boolean>       checkValueContent;
    private final Optional<ValueType>     expectedType;
    private final Optional<ConditionType> condition;
    private final Optional<String>        expectedValue;

    @AllArgsConstructor
    @Getter
    public enum ValueType implements MyLocatorInterface, MySelectOptionsInterface {
        STRING           ("string:string"),
        EXPANDABLE_STRING("string:stringex"),
        MULTISTRING      ("string:multistring"),
        BINARY           ("string:binary"),
        DWORD            ("string:dword"),
        QWORD            ("string:qword");

        private final String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ConditionType implements MyLocatorInterface, MySelectOptionsInterface {
        EQUALS          ("string:equal"),
        NOT_EQUALS      ("string:nonequal"),
        CONTAIN         ("string:contain"),
        DOES_NOT_CONTAIN("string:doesnotcontain"),
        GREATER         ("string:greater"),
        LESS            ("string:less");

        private final String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public RegistryKeyValueData(Builder builder){
        this.caption           = builder.caption;
        this.key               = builder.key;
        this.valueName         = builder.valueName;
        this.checkValueContent = builder.checkValueContent;
        this.expectedType      = builder.expectedType;
        this.condition         = builder.condition;
        this.expectedValue     = builder.expectedValue;
    }

    public static class Builder{
        private Optional<String>        caption            = Optional.empty();
        private Optional<String>        key                = Optional.empty();
        private Optional<String>        valueName          = Optional.empty();
        private Optional<Boolean>       checkValueContent  = Optional.empty();
        private Optional<ValueType>     expectedType       = Optional.empty();
        private Optional<ConditionType> condition          = Optional.empty();
        private Optional<String>        expectedValue      = Optional.empty();

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

        public Builder setKey(String key){
            this.key = Optional.of(key);
            return this;
        }

        public Builder setValueName(String valueName){
            this.valueName = Optional.of(valueName);
            return this;
        }

        public Builder setValueContent(Boolean checkValueContent){
            this.checkValueContent = Optional.of(checkValueContent);
            return this;
        }

        public Builder setExpextedType(ValueType expextedType){
            this.expectedType = Optional.of(expextedType);
            return this;
        }

        public Builder setCondition(ConditionType condition){
            this.condition = Optional.of(condition);
            return this;
        }

        public Builder setExpectedValue(String expectedValue){
            this.expectedValue = Optional.of(expectedValue);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistryKeyValueData expectedData = (RegistryKeyValueData) o;

        return
            OptionalUtils.optionalEquals(this.caption,            expectedData.caption)           &&
            OptionalUtils.optionalEquals(this.key,                expectedData.key)               &&
            OptionalUtils.optionalEquals(this.valueName,          expectedData.valueName)         &&
            OptionalUtils.optionalEquals(this.checkValueContent,  expectedData.checkValueContent) &&
            OptionalUtils.optionalEquals(this.expectedType,       expectedData.expectedType)      &&
            OptionalUtils.optionalEquals(this.condition,          expectedData.condition)         &&
            OptionalUtils.optionalEquals(this.expectedValue,      expectedData.expectedValue);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.key);
        result = 31 * result + OptionalUtils.optionalHashCode(this.valueName);
        result = 31 * result + OptionalUtils.optionalHashCode(this.checkValueContent);
        result = 31 * result + OptionalUtils.optionalHashCode(this.expectedType);
        result = 31 * result + OptionalUtils.optionalHashCode(this.condition);
        result = 31 * result + OptionalUtils.optionalHashCode(this.expectedValue);
        return result;
    }

    @Override
    public String toString() {
        return "RegistryKeyValueData{" +
            "caption="              + OptionalUtils.optionalToString(caption)            +
            ", key="                + OptionalUtils.optionalToString(key)                +
            ", valueName="          + OptionalUtils.optionalToString(valueName)          +
            ", checkValueContent="  + OptionalUtils.optionalToString(checkValueContent)  +
            ", expectedType="       + OptionalUtils.optionalToString(expectedType)       +
            ", condition="          + OptionalUtils.optionalToString(condition)          +
            ", expectedValue="      + OptionalUtils.optionalToString(expectedValue)      +
            '}';
    }
}




