package main.java.data.sensors.filesystem;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class FileExistenceData extends SensorData {
    private final Optional<String> caption;
    private final Optional<String> path;

    public FileExistenceData(Builder builder){
        this.caption = builder.caption;
        this.path    = builder.path;
    }

    public static class Builder{
        private Optional<String> caption = Optional.empty();
        private Optional<String> path    = Optional.empty();

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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileExistenceData expectedData = (FileExistenceData) o;

        return
            OptionalUtils.optionalEquals(this.caption, expectedData.caption) &&
                OptionalUtils.optionalEquals(this.path,    expectedData.path);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.path);
        return result;
    }

    @Override
    public String toString() {
        return "FileExistenceData{" +
            "caption=" + OptionalUtils.optionalToString(this.caption) +
            ", path="  + OptionalUtils.optionalToString(this.path)    +
            '}';
    }
}



