import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;

public class DeviceModal {
    private SelenideElement rootElement;

    public DeviceModal(SelenideElement root){
        this.rootElement = root;
    }

    private SelenideElement deviceTypeDropDown =
        rootElement.$(byAttribute("data-qa-id", "devicetype"));

    public DeviceModal selectDeviceType(DeviceType deviceType) {
        this.deviceTypeDropDown.click();
        this.deviceTypeDropDown.$(deviceType.getLocator()).click();
        return this;
    }
}
