package main.java.data.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import main.java.utility.utils.OptionalUtils;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.byValue;

@Getter
public final class ServiceControlData extends ActionData {
    private final Optional<String>        caption;
    private final Optional<String>        serviceName;
    private final Optional<ServiceAction> serviceAction;
    private final Optional<Boolean>       localAction;

    @AllArgsConstructor
    @Getter
    public enum ServiceAction implements MyLocatorInterface, MySelectOptionsInterface {
        START    ("string:start"),
        PAUSE    ("string:pause"),
        CONTINUE ("string:continue"),
        RESTART  ("string:restart"),
        STOP     ("string:stop");

        private final String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public ServiceControlData(Builder builder){
        this.caption       = builder.caption;
        this.serviceName   = builder.serviceName;
        this.serviceAction = builder.serviceAction;
        this.localAction   = builder.localAction;
    }

    public static class Builder{
        private Optional<String>        caption       = Optional.empty();
        private Optional<String>        serviceName   = Optional.empty();
        private Optional<ServiceAction> serviceAction = Optional.empty();
        private Optional<Boolean>       localAction   = Optional.empty();

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

        public Builder setServiceName(String serviceName){
            this.serviceName = Optional.of(serviceName);
            return this;
        }

        public Builder setServiceAction(ServiceAction serviceAction){
            this.serviceAction = Optional.of(serviceAction);
            return this;
        }

        public Builder setLocalAction(Boolean localAction){
            this.localAction = Optional.of(localAction);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceControlData expectedData = (ServiceControlData) o;

        return
            OptionalUtils.optionalEquals(this.caption,       expectedData.caption)       &&
            OptionalUtils.optionalEquals(this.serviceName,   expectedData.serviceName)   &&
            OptionalUtils.optionalEquals(this.serviceAction, expectedData.serviceAction) &&
            OptionalUtils.optionalEquals(this.localAction,   expectedData.localAction);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.serviceName);
        result = 31 * result + OptionalUtils.optionalHashCode(this.serviceAction);
        result = 31 * result + OptionalUtils.optionalHashCode(this.localAction);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceControlData{" +
            "caption="         + OptionalUtils.optionalToString(this.caption)       +
            ", serviceName="   + OptionalUtils.optionalToString(this.serviceName)   +
            ", serviceAction=" + OptionalUtils.optionalToString(this.serviceAction) +
            ", localAction="   + OptionalUtils.optionalToString(this.localAction)   +
            '}';
    }
}




