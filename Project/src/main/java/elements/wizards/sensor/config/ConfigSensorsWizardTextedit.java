package main.java.elements.wizards.sensor.config;

import lombok.AllArgsConstructor;
import main.java.elements.simple_elements.EditlineType;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;

@AllArgsConstructor
public enum ConfigSensorsWizardTextedit implements MyLocatorInterface {

    // Common
    SENSOR_CAPTION      ("caption",       EditlineType.TEXT_EDIT),
    FILE_PATH           ("path",          EditlineType.TEXT_EDIT),
    SIZE                ("size",          EditlineType.NUMBER_EDIT),
    PORT                ("port",          EditlineType.NUMBER_EDIT),
    TIMEOUT             ("timeout",       EditlineType.NUMBER_EDIT),
    USERNAME            ("username",      EditlineType.TEXT_EDIT),
    PASSWORD            ("password",      EditlineType.TEXT_EDIT),
    URL_PATH            ("urlpath",       EditlineType.TEXT_EDIT),
    // File compare
    FIRST_FILE_PATH     ("firstfile",     EditlineType.TEXT_EDIT),
    SECOND_FILE_PATH    ("secondfile",    EditlineType.TEXT_EDIT),
    // File CRC
    EXPECTED_CRC32      ("targetvalue",   EditlineType.TEXT_EDIT),
    // FTP
    FTP_ARGUMENT("argument",      EditlineType.TEXT_EDIT),
    // HTTP_CONTENT
    TARGET_VALUE        ("targetvalue",   EditlineType.TEXT_AREA),
    // IMAP
    MAILBOX             ("mailbox",       EditlineType.TEXT_EDIT),
    SEARCH_PARAMETERS   ("argument",      EditlineType.TEXT_EDIT),
    // PING
    PACKET_SIZE         ("packetsize",    EditlineType.NUMBER_EDIT),
    PACKET_COUNT        ("packetcount",   EditlineType.NUMBER_EDIT),
    TTL                 ("ttl",           EditlineType.NUMBER_EDIT),
    // POP
    MAILDROP_SIZE       ("maildropsize",  EditlineType.NUMBER_EDIT),
    // CPU usage
    CPU_USAGE           ("expectedvalue", EditlineType.NUMBER_EDIT),
    // Physical memory
    PHYSICAL_MEMORY     ("expectedvalue", EditlineType.NUMBER_EDIT),
    // Process count
    PROCESS_COUNT       ("expectedvalue", EditlineType.NUMBER_EDIT),
    // Registry key value
    REGISTRY_KEY        ("key",           EditlineType.TEXT_EDIT),
    REGISTRY_VALUE_NAME ("valuename",     EditlineType.TEXT_EDIT),
    EXPECTED_VALUE      ("expectedvalue", EditlineType.TEXT_AREA),
    // User count
    USER_COUNT        ("expectedvalue", EditlineType.NUMBER_EDIT),
    // Virtual memory
    VIRTUAL_MEMORY    ("expectedvalue", EditlineType.NUMBER_EDIT),
    // Service status
    SERVICE_NAME      ("servicename",   EditlineType.TEXT_EDIT);

    String nameValue;
    EditlineType editlineType;

    public By getLocator(){
        By locator;
        if(editlineType == EditlineType.TEXT_EDIT){
            String xpath = String.format(".//*[@data-control-name='%s']", this.nameValue);
            locator = byXpath(xpath);
        }
        else{
            locator = byName(this.nameValue);
        }
        return locator;
    }

    public String getCaption(){
        return this.name().toLowerCase();
    }
}
