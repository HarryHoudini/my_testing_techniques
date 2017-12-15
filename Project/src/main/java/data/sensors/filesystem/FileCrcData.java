package main.java.data.sensors.filesystem;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class FileCrcData extends SensorData {
    private final Optional<String>         caption;
    private final Optional<String>         path;
    private final Optional<EqualsOperator> condition;
    private final Optional<String>         expectedValue;

    public FileCrcData(Builder builder){
        this.caption       = builder.caption;
        this.path          = builder.path;
        this.condition     = builder.condition;
        this.expectedValue = builder.expectedValue;
    }

    public static class Builder{
        private Optional<String>         caption       = Optional.empty();
        private Optional<String>         path          = Optional.empty();
        private Optional<EqualsOperator> condition     = Optional.empty();
        private Optional<String>         expectedValue = Optional.empty();

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

        public Builder setPath(String path){
            this.path = Optional.of(path);
            return this;
        }

        public Builder setCondition(EqualsOperator condition){
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

        FileCrcData expectedData = (FileCrcData) o;

        return
            OptionalUtils.optionalEquals(this.caption,       expectedData.caption)   &&
            OptionalUtils.optionalEquals(this.path,          expectedData.path)      &&
            OptionalUtils.optionalEquals(this.condition,     expectedData.condition) &&
            OptionalUtils.optionalEquals(this.expectedValue, expectedData.expectedValue);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.path);
        result = 31 * result + OptionalUtils.optionalHashCode(this.condition);
        result = 31 * result + OptionalUtils.optionalHashCode(this.expectedValue);
        return result;
    }

    @Override
    public String toString() {
        return "FileCrcData{" +
            "caption="         + OptionalUtils.optionalToString(this.caption)       +
            ", path="          + OptionalUtils.optionalToString(this.path)          +
            ", condition="     + OptionalUtils.optionalToString(this.condition)     +
            ", expectedValue=" + OptionalUtils.optionalToString(this.expectedValue) +
            '}';
    }
}



