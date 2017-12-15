package main.java.data.sensors.netbase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import main.java.utility.utils.OptionalUtils;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.byValue;

@Getter
public class PopData extends SensorData {
    private final Optional<String>              caption;
    private final Optional<Boolean>             secureConnection;
    private final Optional<String>              port;
    private final Optional<String>              timeout;
    private final Optional<Boolean>             authorizationRequired;
    private final Optional<String>              username;
    private final Optional<String>              password;
    private final Optional<Boolean>             checkMailDropSize;
    private final Optional<ComparisonOperator>  operator;
    private final Optional<String>              mailDropSize;

    @AllArgsConstructor
    @Getter
    public enum ComparisonOperator implements MyLocatorInterface, MySelectOptionsInterface {
        EQUALS            ("string:="),
        NOT_EQUALS        ("string:<>"),
        GREATER           ("string:>"),
        GREATER_OR_EQUALS ("string:>="),
        LESS              ("string:<"),
        LESS_OR_EQUALS    ("string:<=");

        private String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public PopData(Builder builder){
        this.caption               = builder.caption;
        this.secureConnection      = builder.secureConnection;
        this.port                  = builder.port;
        this.timeout               = builder.timeout;
        this.authorizationRequired = builder.authorizationRequired;
        this.username              = builder.username;
        this.password              = builder.password;
        this.checkMailDropSize     = builder.checkMailDropSize;
        this.operator              = builder.operator;
        this.mailDropSize          = builder.mailDropSize;
    }

    public static class Builder{
        private Optional<String>             caption               = Optional.empty();
        private Optional<Boolean>            secureConnection      = Optional.empty();
        private Optional<String>             port                  = Optional.empty();
        private Optional<String>             timeout               = Optional.empty();
        private Optional<Boolean>            authorizationRequired = Optional.empty();
        private Optional<String>             username              = Optional.empty();
        private Optional<String>             password              = Optional.empty();
        private Optional<Boolean>            checkMailDropSize     = Optional.empty();
        private Optional<ComparisonOperator> operator              = Optional.empty();
        private Optional<String>             mailDropSize          = Optional.empty();

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

        public Builder setCheckMailDropSize(Boolean checkMailDropSize){
            this.checkMailDropSize = Optional.of(checkMailDropSize);
            return this;
        }

        public Builder setOperator(ComparisonOperator operator){
            this.operator = Optional.of(operator);
            return this;
        }

        public Builder setMailDropSize(Long mailDropSize){
            this.mailDropSize = Optional.of(mailDropSize.toString());
            return this;
        }

        public Builder setMailDropSizeString(String mailDropSize){
            this.mailDropSize = Optional.of(mailDropSize);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PopData expectedData = (PopData) o;

        return
            OptionalUtils.optionalEquals(this.caption,               expectedData.caption)               &&
            OptionalUtils.optionalEquals(this.secureConnection,      expectedData.secureConnection)      &&
            OptionalUtils.optionalEquals(this.port,                  expectedData.port)                  &&
            OptionalUtils.optionalEquals(this.timeout,               expectedData.timeout)               &&
            OptionalUtils.optionalEquals(this.authorizationRequired, expectedData.authorizationRequired) &&
            OptionalUtils.optionalEquals(this.username,              expectedData.username)              &&
            OptionalUtils.optionalEquals(this.password,              expectedData.password)              &&
            OptionalUtils.optionalEquals(this.checkMailDropSize,     expectedData.checkMailDropSize)     &&
            OptionalUtils.optionalEquals(this.operator,              expectedData.operator)              &&
            OptionalUtils.optionalEquals(this.mailDropSize,          expectedData.mailDropSize);
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
        result = 31 * result + OptionalUtils.optionalHashCode(this.checkMailDropSize);
        result = 31 * result + OptionalUtils.optionalHashCode(this.operator);
        result = 31 * result + OptionalUtils.optionalHashCode(this.mailDropSize);
        return result;
    }

    @Override
    public String toString() {
        return "PopData{" +
            "caption="                 + OptionalUtils.optionalToString(this.caption)               +
            ", secureConnection="      + OptionalUtils.optionalToString(this.secureConnection)      +
            ", port="                  + OptionalUtils.optionalToString(this.port)                  +
            ", timeout="               + OptionalUtils.optionalToString(this.timeout)               +
            ", authorizationRequired=" + OptionalUtils.optionalToString(this.authorizationRequired) +
            ", username="              + OptionalUtils.optionalToString(this.username)              +
            ", password="              + OptionalUtils.optionalToString(this.password)              +
            ", checkMailDropSize="     + OptionalUtils.optionalToString(this.checkMailDropSize)     +
            ", operator="              + OptionalUtils.optionalToString(this.operator)              +
            ", mailDropSize="          + OptionalUtils.optionalToString(this.mailDropSize)          +
            '}';
    }
}




