package main.java.data;

import lombok.Getter;
import lombok.Setter;
import main.java.elements.modal.networkTree.DeviceType;

import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class DeviceAttributes {
    private String deviceCaption;
    private Optional<DeviceType> deviceType;
    private String deviceHostname;
    private Optional<Boolean> deviceStaticIP;
    private String deviceIP;
    private String deviceMacAddress;
    private String deviceDescription;

    public DeviceAttributes(String deviceCaption,  Optional <DeviceType> deviceType,
                            String deviceHostname, Boolean deviceStaticIP,
                            String deviceIP,       String deviceMacAddress,
                            String deviceDescription) {
        this.deviceCaption     = deviceCaption;
        this.deviceType        = deviceType;
        this.deviceHostname    = deviceHostname;
        this.deviceStaticIP    = Optional.ofNullable(deviceStaticIP);
        this.deviceIP          = deviceIP;
        this.deviceMacAddress  = deviceMacAddress;
        this.deviceDescription = deviceDescription;
    }

    public DeviceAttributes(String deviceCaption,  DeviceType deviceType,
                            String deviceHostname, Boolean deviceStaticIP,
                            String deviceIP,       String deviceMacAddress,
                            String deviceDescription) {
        this.deviceCaption     = deviceCaption;
        this.deviceType        = Optional.ofNullable(deviceType);
        this.deviceHostname    = deviceHostname;
        this.deviceStaticIP    = Optional.ofNullable(deviceStaticIP);
        this.deviceIP          = deviceIP;
        this.deviceMacAddress  = deviceMacAddress;
        this.deviceDescription = deviceDescription;
    }

    public DeviceAttributes(String deviceCaption,             String deviceHostname,
                            Boolean deviceStaticIP, String deviceIP) {
        this(deviceCaption, Optional.empty(), deviceHostname,
            deviceStaticIP, deviceIP, "", "");
    }

    public DeviceAttributes(String deviceCaption,   String deviceHostname) {
        this(deviceCaption, Optional.empty(), deviceHostname,
            null, "", "", "");
    }

    public DeviceAttributes(String deviceCaption) {
        this(deviceCaption, Optional.empty(), "",
            null, "", "", "");
    }

    public static DeviceAttributes deviceAttributesByCaption(String deviceCaption){
        return new DeviceAttributes(deviceCaption);
    }

    public static DeviceAttributes deviceAttributesByType (DeviceType type){
        return new DeviceAttributes("", type, "", null,
            "", "", "");
    }

    public static DeviceAttributes deviceAttributesByHostname(String deviceHostname){
        return new DeviceAttributes("", deviceHostname, null, "");
    }

    public static DeviceAttributes deviceAttributesByIp(String deviceIp){
        return new DeviceAttributes("", "", null, deviceIp);
    }

    public static DeviceAttributes deviceAttributesByMac (String mac){
        return new DeviceAttributes("", Optional.empty(), "", null,
            "", mac, "");
    }

    public static DeviceAttributes deviceAttributesByDescription (String description){
        return new DeviceAttributes("", Optional.empty(), "", null,
            "", "", description);
    }

    /**
     * @param expectedAttr - DeviceAttributes, attribute with which we compare
     *  if expectedAttr has deviceStaticIP == null or deviceType == null ->
     *  then checking these fields is not done
     */
    public boolean isEquals(DeviceAttributes expectedAttr){
        return Objects.equals(this.deviceCaption, expectedAttr.getDeviceCaption()) &&
            Objects.equals(this.deviceHostname, expectedAttr.getDeviceHostname()) &&
            Objects.equals(this.deviceIP, expectedAttr.getDeviceIP()) &&
            Objects.equals(this.deviceMacAddress, expectedAttr.getDeviceMacAddress()) &&
            (!expectedAttr.getDeviceStaticIP().isPresent() ||
                (this.deviceStaticIP.isPresent() &&
                    Objects.equals(this.deviceStaticIP.get(), expectedAttr.getDeviceStaticIP().get())
                )
            ) &&
            (!expectedAttr.getDeviceType().isPresent() ||
                (this.deviceType.isPresent() &&
                    Objects.equals(this.deviceType.get(), expectedAttr.getDeviceType().get())
                )
            );
    }
}
