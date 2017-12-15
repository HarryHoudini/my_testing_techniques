package main.java.data.sensors.netbase;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class TelnetData extends SensorData {
    private final Optional<String> caption;
    private final Optional<String> port;
    private final Optional<String> timeout;

    public TelnetData(Builder builder){
        this.caption = builder.caption;
        this.port    = builder.port;
        this.timeout = builder.timeout;
    }

    public static class Builder{
        private Optional<String> caption = Optional.empty();
        private Optional<String> port    = Optional.empty();
        private Optional<String> timeout = Optional.empty();

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

        public Builder setPort(Long port){
            this.port = Optional.of(port.toString());
            return this;
        }

        public Builder setPortString(String port){
            this.port = Optional.of(port);
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TelnetData expectedData = (TelnetData) o;

        return
            OptionalUtils.optionalEquals(this.caption,     expectedData.caption) &&
                OptionalUtils.optionalEquals(this.port,    expectedData.port)    &&
                OptionalUtils.optionalEquals(this.timeout, expectedData.timeout);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.port);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        return result;
    }

    @Override
    public String toString() {
        return "TelnetData{" +
            "caption="   + OptionalUtils.optionalToString(this.caption) +
            ", port="    + OptionalUtils.optionalToString(this.port)    +
            ", timeout=" + OptionalUtils.optionalToString(this.timeout) +
            '}';
    }
}
