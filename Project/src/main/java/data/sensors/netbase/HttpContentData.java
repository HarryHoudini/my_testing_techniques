package main.java.data.sensors.netbase;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class HttpContentData extends SensorData {
    private final Optional<String>  caption;
    private final Optional<Boolean> secureConnection;
    private final Optional<String>  port;
    private final Optional<String>  timeout;
    private final Optional<String>  urlPath;
    private final Optional<String>  targetValue;
    private final Optional<Boolean> conditional;

    public HttpContentData(Builder builder){
        this.caption          = builder.caption;
        this.secureConnection = builder.secureConnection;
        this.port             = builder.port;
        this.timeout          = builder.timeout;
        this.urlPath          = builder.urlPath;
        this.targetValue      = builder.targetValue;
        this.conditional      = builder.conditional;
    }

    public static class Builder{
        private Optional<String>  caption          = Optional.empty();
        private Optional<Boolean> secureConnection = Optional.empty();
        private Optional<String>  port             = Optional.empty();
        private Optional<String>  timeout          = Optional.empty();
        private Optional<String>  urlPath          = Optional.empty();
        private Optional<String>  targetValue      = Optional.empty();
        private Optional<Boolean> conditional      = Optional.empty();

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

        public Builder setSecureConnection(Boolean secureConnection){
            this.secureConnection = Optional.of(secureConnection);
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

        public Builder setUrlPath(String urlPath){
            this.urlPath = Optional.of(urlPath);
            return this;
        }

        public Builder setTargetValue(String targetValue){
            this.targetValue = Optional.of(targetValue);
            return this;
        }

        public Builder setConditional (Boolean conditional){
            this.conditional = Optional.of(conditional);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpContentData expectedData = (HttpContentData) o;

        return
            OptionalUtils.optionalEquals(this.caption,          expectedData.caption)          &&
            OptionalUtils.optionalEquals(this.secureConnection, expectedData.secureConnection) &&
            OptionalUtils.optionalEquals(this.port,             expectedData.port)             &&
            OptionalUtils.optionalEquals(this.timeout,          expectedData.timeout)          &&
            OptionalUtils.optionalEquals(this.urlPath,          expectedData.urlPath)          &&
            OptionalUtils.optionalEquals(this.targetValue,      expectedData.targetValue)      &&
            OptionalUtils.optionalEquals(this.conditional,      expectedData.conditional);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.secureConnection);
        result = 31 * result + OptionalUtils.optionalHashCode(this.port);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        result = 31 * result + OptionalUtils.optionalHashCode(this.urlPath);
        result = 31 * result + OptionalUtils.optionalHashCode(this.targetValue);
        result = 31 * result + OptionalUtils.optionalHashCode(this.conditional);
        return result;
    }

    @Override
    public String toString() {
        return "HttpContentData{" +
            "caption="            + OptionalUtils.optionalToString(this.caption)          +
            ", secureConnection=" + OptionalUtils.optionalToString(this.secureConnection) +
            ", port="             + OptionalUtils.optionalToString(this.port)             +
            ", timeout="          + OptionalUtils.optionalToString(this.timeout)          +
            ", urlPath="          + OptionalUtils.optionalToString(this.urlPath)          +
            ", targetValue="      + OptionalUtils.optionalToString(this.targetValue)      +
            ", conditional="      + OptionalUtils.optionalToString(this.conditional)      +
            '}';
    }
}



