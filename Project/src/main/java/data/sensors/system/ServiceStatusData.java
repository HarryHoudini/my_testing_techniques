package main.java.data.sensors.system;

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
public class ServiceStatusData extends SensorData {
    private final Optional<String>        caption;
    private final Optional<String>        serviceName;
    private final Optional<ServiceStatus> status;

    @AllArgsConstructor
    @Getter
    public enum ServiceStatus implements MyLocatorInterface, MySelectOptionsInterface {
        STARTED("string:started"),
        STOPPED("string:stopped"),
        PAUSED ("string:paused");

        private final String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public ServiceStatusData(Builder builder){
        this.caption     = builder.caption;
        this.serviceName = builder.serviceName;
        this.status      = builder.status;
    }

    public static class Builder{
        private Optional<String>        caption     = Optional.empty();
        private Optional<String>        serviceName = Optional.empty();
        private Optional<ServiceStatus> status      = Optional.empty();

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

        public Builder setStatus(ServiceStatus status){
            this.status = Optional.of(status);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceStatusData expectedData = (ServiceStatusData) o;

        return
            OptionalUtils.optionalEquals(this.caption,     expectedData.caption)     &&
            OptionalUtils.optionalEquals(this.serviceName, expectedData.serviceName) &&
            OptionalUtils.optionalEquals(this.status,      expectedData.status);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.serviceName);
        result = 31 * result + OptionalUtils.optionalHashCode(this.status);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceStatusData{" +
            "caption="       + OptionalUtils.optionalToString(this.caption)     +
            ", serviceName=" + OptionalUtils.optionalToString(this.serviceName) +
            ", status="      + OptionalUtils.optionalToString(this.status)      +
            '}';
    }
}




