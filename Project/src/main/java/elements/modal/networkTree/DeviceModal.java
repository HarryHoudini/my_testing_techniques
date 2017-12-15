package main.java.elements.modal.networkTree;

import com.codeborne.selenide.SelenideElement;
import main.java.elements.modal.ModalButton;
import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.ModalType;
import main.java.data.DeviceAttributes;
import main.java.elements.simple_elements.Checkbox;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static main.java.elements.modal.ModalTextedit.IP_ADRESS;

public class DeviceModal extends GroupModal {

    public DeviceModal(ModalType type){
        super(type);
    }

    private static final String EMPTY_STRING = "";

    public static DeviceModal deviceModal(ModalType type){
        return new DeviceModal(type);
    }

    private Checkbox staticIpCheckbox =
        new Checkbox(this.rootElement.$((byAttribute("data-control-name", "staticip"))));
    private SelenideElement deviceTypeDropDown =
        this.rootElement.$(byAttribute("data-qa-id", "devicetype"));

    public DeviceModal selectConfigureNode(){
        // O-0008
        this.rootElement.$(byTitle("Configure properties")).click();
        return this;
    }

    public DeviceModal selectConfigureNodeCredentials(){
        // O-0008
        this.rootElement.$(byTitle("Configure credentials")).click();
        return this;
    }

    public String getHeaderCaption(){
        return $(byClassName("ol_modal_header")).text();
    }


    // deviceTypeDropDown methods

    private DeviceModal selectDeviceType(DeviceType deviceType) {
        this.deviceTypeDropDown.click();
        this.deviceTypeDropDown.$(deviceType.getLocator()).click();
        return this;
    }

    /**
    * return null, if if nothing is selected in the Select "Device type"
    * throw exeption IllegalArgumentException, if the expectedCaption is not in the list
    */
    public Optional<DeviceType> getDeviceType() throws IllegalArgumentException{
       this.deviceTypeDropDown.getValue();
       return DeviceType.typeOf(this.deviceTypeDropDown.getText());
    }

    // staticIPCheckbox methods

    private DeviceModal clickStaticIP(){
       this.staticIpCheckbox.click();
       return this;
    }

    public boolean isStaticIP(){
        return this.staticIpCheckbox.isChecked();
    }

    public final void setAll(DeviceAttributes newAttributes){
        if(!newAttributes.getDeviceCaption().equals("")){
            setTexteditValue(ModalTextedit.CAPTION, newAttributes.getDeviceCaption());
        }

        if(!newAttributes.getDeviceHostname().equals("")){
            setTexteditValue(ModalTextedit.HOSTNAME, newAttributes.getDeviceHostname());
        }

        if(!newAttributes.getDeviceIP().equals("")){
            final boolean isEnabled = isEnabledTextedit(IP_ADRESS);
            if (!isEnabled) {
                clickStaticIP();
            }
            setTexteditValue(ModalTextedit.IP_ADRESS,  newAttributes.getDeviceIP());
            if (!isEnabled){
                clickStaticIP();
            }
        }

        if(!newAttributes.getDeviceMacAddress().equals("")){
            setTexteditValue(ModalTextedit.MAC_ADRESS, newAttributes.getDeviceMacAddress());
        }

        if(!newAttributes.getDeviceDescription().equals("")){
            setDescription(newAttributes.getDeviceDescription());
        }

        newAttributes
            .getDeviceStaticIP()
            .filter(staticIP -> staticIP != isStaticIP())
            .ifPresent( x -> clickStaticIP());

        newAttributes
            .getDeviceType()
            .ifPresent(this::selectDeviceType);
        clickCloseModalButton(ModalButton.SAVE);
    }
}
