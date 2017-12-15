package main.java.data.sensors.netbase;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class FtpData extends SensorData {
    private final Optional<String>  caption;
    private final Optional<Boolean> secureConnection;
    private final Optional<String>  port;
    private final Optional<String>  timeout;
    private final Optional<String>  username;
    private final Optional<String>  password;
    private final Optional<Boolean> enabledActiveMode;
    private final Optional<String>  argument;

    public FtpData(Builder builder){
        this.caption           = builder.caption;
        this.secureConnection  = builder.secureConnection;
        this.port              = builder.port;
        this.timeout           = builder.timeout;
        this.username          = builder.username;
        this.password          = builder.password;
        this.enabledActiveMode = builder.active;
        this.argument          = builder.argument;
    }

    public static class Builder{
        private Optional<String>  caption          = Optional.empty();
        private Optional<Boolean> secureConnection = Optional.empty();
        private Optional<String>  port             = Optional.empty();
        private Optional<String>  timeout          = Optional.empty();
        private Optional<String>  username         = Optional.empty();
        private Optional<String>  password         = Optional.empty();
        private Optional<Boolean> active           = Optional.empty();
        private Optional<String>  argument         = Optional.empty();

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

        public Builder setUsername(String username){
            this.username = Optional.of(username);
            return this;
        }

        public Builder setPassword(String password){
            this.password = Optional.of(password);
            return this;
        }

        public Builder setEnabledActiveMode(Boolean active){
            this.active = Optional.of(active);
            return this;
        }

        public Builder setArgument(String argument){
            this.argument = Optional.of(argument);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FtpData expectedData = (FtpData) o;

        return
            OptionalUtils.optionalEquals(this.caption,           expectedData.caption)           &&
                OptionalUtils.optionalEquals(this.secureConnection,  expectedData.secureConnection)  &&
                OptionalUtils.optionalEquals(this.port,              expectedData.port)              &&
                OptionalUtils.optionalEquals(this.timeout,           expectedData.timeout)           &&
                OptionalUtils.optionalEquals(this.username,          expectedData.username)          &&
                OptionalUtils.optionalEquals(this.password,          expectedData.password)          &&
                OptionalUtils.optionalEquals(this.enabledActiveMode, expectedData.enabledActiveMode) &&
                OptionalUtils.optionalEquals(this.argument,          expectedData.argument);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.secureConnection);
        result = 31 * result + OptionalUtils.optionalHashCode(this.port);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        result = 31 * result + OptionalUtils.optionalHashCode(this.username);
        result = 31 * result + OptionalUtils.optionalHashCode(this.password);
        result = 31 * result + OptionalUtils.optionalHashCode(this.enabledActiveMode);
        result = 31 * result + OptionalUtils.optionalHashCode(this.argument);
        return result;
    }

    @Override
    public String toString() {
        return "FtpData{" +
            "caption="             + OptionalUtils.optionalToString(this.caption)           +
            ", secureConnection="  + OptionalUtils.optionalToString(this.secureConnection)  +
            ", port="              + OptionalUtils.optionalToString(this.port)              +
            ", timeout="           + OptionalUtils.optionalToString(this.timeout)           +
            ", username="          + OptionalUtils.optionalToString(this.username)          +
            ", password="          + OptionalUtils.optionalToString(this.password)          +
            ", enabledActiveMode=" + OptionalUtils.optionalToString(this.enabledActiveMode) +
            ", argument="          + OptionalUtils.optionalToString(this.argument)          +
            '}';
    }
}


