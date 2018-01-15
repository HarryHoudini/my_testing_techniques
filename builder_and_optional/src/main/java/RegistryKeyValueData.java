import lombok.Getter;

import java.util.Optional;

@Getter
public class RegistryKeyValueData {
    private final Optional<String>        caption;
    private final Optional<String>        key;
    private final Optional<String>        valueName;
    private final Optional<String>        valueContent;
    private final Optional<ValueType>     exectedType;
    private final Optional<ConditionType> condition;
    private final Optional<String>        expectedValue;

    static enum ValueType {
        STRING,
        EXPANDABLE_STRING,
        MULTISTRING,
        BINARY,
        DWORD,
        QWORD
    }

    static enum ConditionType {
        EQUALS,
        NOT_EQUALS,
        CONTAIN,
        DOES_NOT_CONTAIN,
        GREATER,
        LESS
    }

    public RegistryKeyValueData(Builder builder){
        this.caption       = builder.caption;
        this.key           = builder.key;
        this.valueName     = builder.valueName;
        this.valueContent  = builder.valueContent;
        this.exectedType   = builder.expectedType;
        this.condition     = builder.condition;
        this.expectedValue = builder.expectedValue;
    }

    static class Builder{
        private Optional<String>        caption       = Optional.empty();
        private Optional<String>        key           = Optional.empty();
        private Optional<String>        valueName     = Optional.empty();
        private Optional<String>        valueContent  = Optional.empty();
        private Optional<ValueType>     expectedType  = Optional.empty();
        private Optional<ConditionType> condition     = Optional.empty();
        private Optional<String>        expectedValue = Optional.empty();

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

        public Builder setValueContent(String valueContent){
            this.valueContent = Optional.of(valueContent);
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
}




