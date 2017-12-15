package main.java.data.actions;

import lombok.Getter;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public final class RunApplicationData extends ActionData {
    private final Optional<String>  caption;
    private final Optional<String>  applicationName;
    private final Optional<String>  applicationParams;
    private final Optional<String>  applicationDir;
    private final Optional<Boolean> waitForCompletion;

    public RunApplicationData(Builder builder){
        this.caption           = builder.caption;
        this.applicationName   = builder.applicationName;
        this.applicationParams = builder.applicationParams;
        this.applicationDir    = builder.applicationDir;
        this.waitForCompletion = builder.waitForCompletion;
    }

    public static class Builder{
        private Optional<String>  caption           = Optional.empty();
        private Optional<String>  applicationName   = Optional.empty();
        private Optional<String>  applicationParams = Optional.empty();
        private Optional<String>  applicationDir    = Optional.empty();
        private Optional<Boolean> waitForCompletion = Optional.empty();

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

        public Builder setApplicationName(String applicationName){
            this.applicationName = Optional.of(applicationName);
            return this;
        }

        public Builder setApplicationParams(String applicationParams){
            this.applicationParams = Optional.of(applicationParams);
            return this;
        }

        public Builder setApplicationDir(String applicationDir){
            this.applicationDir = Optional.of(applicationDir);
            return this;
        }

        public Builder setWaitForCompletion(Boolean waitForCompletion){
            this.waitForCompletion = Optional.of(waitForCompletion);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunApplicationData expectedData = (RunApplicationData) o;

        return
            OptionalUtils.optionalEquals(this.caption,           expectedData.caption)           &&
            OptionalUtils.optionalEquals(this.applicationName,   expectedData.applicationName)   &&
            OptionalUtils.optionalEquals(this.applicationParams, expectedData.applicationParams) &&
            OptionalUtils.optionalEquals(this.applicationDir,    expectedData.applicationDir)    &&
            OptionalUtils.optionalEquals(this.waitForCompletion, expectedData.waitForCompletion);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.applicationName);
        result = 31 * result + OptionalUtils.optionalHashCode(this.applicationParams);
        result = 31 * result + OptionalUtils.optionalHashCode(this.applicationDir);
        result = 31 * result + OptionalUtils.optionalHashCode(this.waitForCompletion);
        return result;
    }

    @Override
    public String toString() {
        return "RunApplicationData{" +
            "caption="             + OptionalUtils.optionalToString(this.caption)           +
            ", applicationName="   + OptionalUtils.optionalToString(this.applicationName)   +
            ", applicationParams=" + OptionalUtils.optionalToString(this.applicationParams) +
            ", applicationDir="    + OptionalUtils.optionalToString(this.applicationDir)    +
            ", waitForCompletion=" + OptionalUtils.optionalToString(this.waitForCompletion) +
            '}';
    }
}
