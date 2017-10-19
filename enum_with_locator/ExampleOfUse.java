package main.elements.modal_windows.networkTree;

import com.codeborne.selenide.SelenideElement;
import main.elements.modal_windows.ModalType;

import static com.codeborne.selenide.Selectors.byAttribute;

public class ExampleOfUse{
    private class DeviceModal extends GroupModal {

        public DeviceModal(ModalType type){
            super(type);
        }

        private SelenideElement deviceTypeDropDown =
            rootElement.$(byAttribute("data-qa-id", "devicetype"));

        private DeviceModal selectDeviceType(DeviceType deviceType) {
            this.deviceTypeDropDown.click();
            this.deviceTypeDropDown.$(deviceType.getLocator()).click();
            return this;
        }
    }
}

