package enums_of_locator;

import interfaces.MyLocatorInterface;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;

@AllArgsConstructor
public enum ConfigSensorsWizardTextedit implements MyLocatorInterface {

    // Common
    SENSOR_CAPTION      ("caption"),
    FILE_PATH           ("path"),
    SIZE                ("size"),
    PORT                ("port"),
    TIMEOUT             ("timeout"),
    USERNAME            ("username"),
    PASSWORD            ("password"),
    URL_PATH            ("urlpath"),
    // File compare
    FIRST_FILE_PATH     ("firstfile"),
    SECOND_FILE_PATH    ("secondfile"),
    // File CRC
    EXPECTED_CRC32      ("targetvalue"),
    // ftp
    FTP_ARGUMENT        ("argument"),
    // httpcontent
    TARGET_VALUE        ("targetvalue"),
    // imap
    MAILBOX             ("mailbox"),
    SEARCH_PARAMETERS   ("argument"),
    // ping
    PACKET_SIZE         ("packetsize"),
    PACKET_COUNT        ("packetcount"),
    TTL                 ("ttl"),
    // pop
    MAILDROP_SIZE       ("maildropsize"),
    // CPU usage
    CPU_USAGE           ("expectedvalue"),
    // Physical memory
    PHYSICAL_MEMORY     ("expectedvalue"),
    // Process count
    PROCESS_COUNT       ("expectedvalue"),
    // Registry key value
    REGISTRY_KEY        ("key"),
    REGISTRY_VALUE_NAME ("valuename"),
    EXPECTED_VALUE      ("expectedvalue"),
    // User count
    USER_COUNT        ("expectedvalue"),
    // Virtual memory
    VIRTUAL_MEMORY    ("expectedvalue"),
    // Service status
    SERVICE_NAME      ("servicename");

    String nameValue;

    public By getLocator(){
        return byName(this.nameValue);
    }

    public String getCaption(){
        return this.name().toLowerCase();
    }
}
