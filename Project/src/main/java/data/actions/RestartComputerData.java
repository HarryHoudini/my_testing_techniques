package main.java.data.actions;

import lombok.Getter;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public final class RestartComputerData extends ActionData {

    private final Optional<String>  caption;
    private final Optional<String>  messageText;
    private final Optional<String>  timeout;
    private final Optional<Boolean> force;
    private final Optional<Boolean> shutdown;

    public RestartComputerData(Builder builder){
        this.caption     = builder.caption;
        this.messageText = builder.messageText;
        this.timeout     = builder.timeout;
        this.force       = builder.force;
        this.shutdown    = builder.shutdown;
    }

    public static class Builder{
        private Optional<String>  caption     = Optional.empty();
        private Optional<String>  messageText = Optional.empty();
        private Optional<String>  timeout     = Optional.empty();
        private Optional<Boolean> force       = Optional.empty();
        private Optional<Boolean> shutdown    = Optional.empty();

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

        public Builder setMessageText(String messageText){
            this.messageText = Optional.of(messageText);
            return this;
        }

        public Builder setTimeout(Long timeout){
            this.timeout = Optional.of(timeout.toString());
            return this;
        }

        public Builder setTimeoutString(String timeout){
            this.timeout = Optional.of(timeout);
            return this;
        }

        public Builder setForce(Boolean force){
            this.force = Optional.of(force);
            return this;
        }

        public Builder setShutdown(Boolean shutdown){
            this.shutdown = Optional.of(shutdown);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestartComputerData expectedData = (RestartComputerData) o;

        return
            OptionalUtils.optionalEquals(this.caption,     expectedData.caption)     &&
            OptionalUtils.optionalEquals(this.messageText, expectedData.messageText) &&
            OptionalUtils.optionalEquals(this.timeout,     expectedData.timeout)     &&
            OptionalUtils.optionalEquals(this.force,       expectedData.force)       &&
            OptionalUtils.optionalEquals(this.shutdown,    expectedData.shutdown);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.messageText);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        result = 31 * result + OptionalUtils.optionalHashCode(this.force);
        result = 31 * result + OptionalUtils.optionalHashCode(this.shutdown);
        return result;
    }

    @Override
    public String toString() {
        return "RestartComputerData{" +
            "caption="       + OptionalUtils.optionalToString(this.caption)     +
            ", messageText=" + OptionalUtils.optionalToString(this.messageText) +
            ", timeout="     + OptionalUtils.optionalToString(this.timeout)     +
            ", force="       + OptionalUtils.optionalToString(this.force)       +
            ", shutdown="    + OptionalUtils.optionalToString(this.shutdown)    +
            '}';
    }
}
