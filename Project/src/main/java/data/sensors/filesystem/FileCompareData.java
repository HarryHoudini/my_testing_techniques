package main.java.data.sensors.filesystem;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class FileCompareData extends SensorData {
    private final Optional<String>             caption;
    private final Optional<String>             firstfile;
    private final Optional<String>             secondfile;
    private final Optional<EqualsOperator>     condition;
    private final Optional<Boolean>            checkContent;

    public FileCompareData(Builder builder){
        this.caption      = builder.caption;
        this.firstfile    = builder.firstfile;
        this.secondfile   = builder.secondfile;
        this.condition    = builder.condition;
        this.checkContent = builder.checkContent;
    }

    public static class Builder{
        private Optional<String>             caption      = Optional.empty();
        private Optional<String>             firstfile    = Optional.empty();
        private Optional<String>             secondfile   = Optional.empty();
        private Optional<EqualsOperator>     condition    = Optional.empty();
        private Optional<Boolean>            checkContent = Optional.empty();

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

        public Builder setFirstfile(String firstfile){
            this.firstfile = Optional.of(firstfile);
            return this;
        }

        public Builder setSecondfile(String secondfile){
            this.secondfile = Optional.of(secondfile);
            return this;
        }

        public Builder setCondition(EqualsOperator condition){
            this.condition = Optional.of(condition);
            return this;
        }

        public Builder setCheckContent(Boolean checkContent){
            this.checkContent = Optional.of(checkContent);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileCompareData expectedData = (FileCompareData) o;

        return
            OptionalUtils.optionalEquals(this.caption,      expectedData.caption)    &&
            OptionalUtils.optionalEquals(this.firstfile,    expectedData.firstfile)  &&
            OptionalUtils.optionalEquals(this.secondfile,   expectedData.secondfile) &&
            OptionalUtils.optionalEquals(this.condition,    expectedData.condition)  &&
            OptionalUtils.optionalEquals(this.checkContent, expectedData.checkContent);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.firstfile);
        result = 31 * result + OptionalUtils.optionalHashCode(this.secondfile);
        result = 31 * result + OptionalUtils.optionalHashCode(this.condition);
        result = 31 * result + OptionalUtils.optionalHashCode(this.checkContent);
        return result;
    }

    @Override
    public String toString() {
        return "FileCompareData{" +
            "caption="        + OptionalUtils.optionalToString(this.caption)      +
            ", firstfile="    + OptionalUtils.optionalToString(this.firstfile)    +
            ", secondfile="   + OptionalUtils.optionalToString(this.secondfile)   +
            ", condition="    + OptionalUtils.optionalToString(this.condition)    +
            ", checkContent=" + OptionalUtils.optionalToString(this.checkContent) +
            '}';
    }
}



