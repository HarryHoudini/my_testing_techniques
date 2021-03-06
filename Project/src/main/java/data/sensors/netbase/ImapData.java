package main.java.data.sensors.netbase;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class ImapData extends SensorData {
    private final Optional<String>  caption;
    private final Optional<Boolean> secureConnection;
    private final Optional<String>  port;
    private final Optional<String>  timeout;
    private final Optional<Boolean> authorizationRequired;
    private final Optional<String>  username;
    private final Optional<String>  password;
    private final Optional<Boolean> needSearch;
    private final Optional<String>  mailbox;
    private final Optional<String>  searchParameter;

    public ImapData(Builder builder){
        this.caption               = builder.caption;
        this.secureConnection      = builder.secureConnection;
        this.port                  = builder.port;
        this.timeout               = builder.timeout;
        this.authorizationRequired = builder.authorizationRequired;
        this.username              = builder.username;
        this.password              = builder.password;
        this.needSearch            = builder.needSearch;
        this.mailbox               = builder.mailbox;
        this.searchParameter       = builder.searchParameter;
    }

    public static class Builder{
        private Optional<String>  caption               = Optional.empty();
        private Optional<Boolean> secureConnection      = Optional.empty();
        private Optional<String>  port                  = Optional.empty();
        private Optional<String>  timeout               = Optional.empty();
        private Optional<Boolean> authorizationRequired = Optional.empty();
        private Optional<String>  username              = Optional.empty();
        private Optional<String>  password              = Optional.empty();
        private Optional<Boolean> needSearch            = Optional.empty();
        private Optional<String>  mailbox               = Optional.empty();
        private Optional<String>  searchParameter       = Optional.empty();

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

        public Builder setAuthorizationRequired(Boolean authorizationRequired){
            this.authorizationRequired = Optional.of(authorizationRequired);
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

        public Builder setNeedSearch (Boolean needSearch){
            this.needSearch = Optional.of(needSearch);
            return this;
        }

        public Builder setMailbox(String mailbox){
            this.mailbox = Optional.of(mailbox);
            return this;
        }

        public Builder setSearchParameter(String searchParameter){
            this.searchParameter = Optional.of(searchParameter);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImapData expectedData = (ImapData) o;

        return
            OptionalUtils.optionalEquals(this.caption,               expectedData.caption)               &&
            OptionalUtils.optionalEquals(this.secureConnection,      expectedData.secureConnection)      &&
            OptionalUtils.optionalEquals(this.port,                  expectedData.port)                  &&
            OptionalUtils.optionalEquals(this.timeout,               expectedData.timeout)               &&
            OptionalUtils.optionalEquals(this.authorizationRequired, expectedData.authorizationRequired) &&
            OptionalUtils.optionalEquals(this.username,              expectedData.username)              &&
            OptionalUtils.optionalEquals(this.password,              expectedData.password)              &&
            OptionalUtils.optionalEquals(this.needSearch,            expectedData.needSearch)            &&
            OptionalUtils.optionalEquals(this.mailbox,               expectedData.mailbox)               &&
            OptionalUtils.optionalEquals(this.searchParameter,       expectedData.searchParameter);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.secureConnection);
        result = 31 * result + OptionalUtils.optionalHashCode(this.port);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        result = 31 * result + OptionalUtils.optionalHashCode(this.authorizationRequired);
        result = 31 * result + OptionalUtils.optionalHashCode(this.username);
        result = 31 * result + OptionalUtils.optionalHashCode(this.password);
        result = 31 * result + OptionalUtils.optionalHashCode(this.needSearch);
        result = 31 * result + OptionalUtils.optionalHashCode(this.mailbox);
        result = 31 * result + OptionalUtils.optionalHashCode(this.searchParameter);
        return result;
    }

    @Override
    public String toString() {
        return "ImapData{" +
            "caption="                 + OptionalUtils.optionalToString(this.caption)               +
            ", secureConnection="      + OptionalUtils.optionalToString(this.secureConnection)      +
            ", port="                  + OptionalUtils.optionalToString(this.port)                  +
            ", timeout="               + OptionalUtils.optionalToString(this.timeout)               +
            ", authorizationRequired=" + OptionalUtils.optionalToString(this.authorizationRequired) +
            ", username="              + OptionalUtils.optionalToString(this.username)              +
            ", password="              + OptionalUtils.optionalToString(this.password)              +
            ", needSearch="            + OptionalUtils.optionalToString(this.needSearch)            +
            ", mailbox="               + OptionalUtils.optionalToString(this.mailbox)               +
            ", searchParameter="       + OptionalUtils.optionalToString(this.searchParameter)       +
            '}';
    }
}



