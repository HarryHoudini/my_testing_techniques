package main.java.data.sensors.filesystem;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class FreeFolderSizeData extends SensorData {
    private final Optional<String>        caption;
    private final Optional<String>        path;
    private final Optional<String>        size;
    private final Optional<SizeCondition> condition;
    private final Optional<UnitSize>      unitOfMeasure;
    private final Optional<Boolean>       forUser;

    public FreeFolderSizeData(Builder builder){
        this.caption               = builder.caption;
        this.path                  = builder.path;
        this.size                  = builder.size;
        this.condition             = builder.condition;
        this.unitOfMeasure         = builder.unitOfMeasure;
        this.forUser               = builder.forUser;
    }

    public static class Builder{
        private Optional<String>        caption       = Optional.empty();
        private Optional<String>        path          = Optional.empty();
        private Optional<String>        size          = Optional.empty();
        private Optional<SizeCondition> condition     = Optional.empty();
        private Optional<UnitSize>      unitOfMeasure = Optional.empty();
        private Optional<Boolean>       forUser       = Optional.empty();

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

        public Builder setSizeLong(Long size){
            this.size = Optional.of(size.toString());
            return this;
        }

        public Builder setSizeString(String size){
            this.size = Optional.of(size);
            return this;
        }

        public Builder setCondition(SizeCondition condition){
            this.condition = Optional.of(condition);
            return this;
        }

        public Builder setUnitOfMeasure(UnitSize unitOfMeasure){
            this.unitOfMeasure = Optional.of(unitOfMeasure);
            return this;
        }

        public Builder setForUser(Boolean forUser){
            this.forUser = Optional.of(forUser);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreeFolderSizeData expectedData = (FreeFolderSizeData) o;

        return
            OptionalUtils.optionalEquals(this.caption,           expectedData.caption)   &&
                OptionalUtils.optionalEquals(this.path,          expectedData.path)      &&
                OptionalUtils.optionalEquals(this.size,          expectedData.size)      &&
                OptionalUtils.optionalEquals(this.condition,     expectedData.condition) &&
                OptionalUtils.optionalEquals(this.unitOfMeasure, expectedData.unitOfMeasure) &&
                OptionalUtils.optionalEquals(this.forUser,       expectedData.forUser);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.path);
        result = 31 * result + OptionalUtils.optionalHashCode(this.size);
        result = 31 * result + OptionalUtils.optionalHashCode(this.condition);
        result = 31 * result + OptionalUtils.optionalHashCode(this.unitOfMeasure);
        result = 31 * result + OptionalUtils.optionalHashCode(this.forUser);
        return result;
    }

    @Override
    public String toString() {
        return "FreeFolderSizeData{" +
            "caption="         + OptionalUtils.optionalToString(this.caption)       +
            ", path="          + OptionalUtils.optionalToString(this.path)          +
            ", size="          + OptionalUtils.optionalToString(this.size)          +
            ", condition="     + OptionalUtils.optionalToString(this.condition)     +
            ", unitOfMeasure=" + OptionalUtils.optionalToString(this.unitOfMeasure) +
            ", forUser="       + OptionalUtils.optionalToString(this.forUser)       +
            '}';
    }
}



