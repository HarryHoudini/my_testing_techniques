package main.java.elements.wizards.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.elements.wizards.WizardType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.filesystem.*;
import main.java.elements.wizards.sensor.config.netbase.*;
import main.java.elements.wizards.sensor.config.system.ServiceStatusConfigSensorsWizard;
import main.java.elements.wizards.sensor.config.winbase.*;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.utils.MyStreamMethods;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.function.Function;

import static com.codeborne.selenide.Selectors.byXpath;
import static main.java.elements.wizards.sensor.SelectWizardTab.*;

@Getter
@AllArgsConstructor
public enum SensorType implements MyLocatorInterface {
    PING                  ("netbase.ping",               NETBASE,    PingConfigSensorsWizard           ::new),
    TCP                   ("netbase.tcp",                NETBASE,    TcpConfigSensorsWizard            ::new),
    FTP                   ("netbase.ftp",                NETBASE,    FtpConfigSensorsWizard            ::new),
    HTTP                  ("netbase.http",               NETBASE,    HttpConfigSensorsWizard           ::new),
    HTTP_CONTENT          ("netbase.httpcontent",        NETBASE,    HttpContentConfigSensorsWizard    ::new),
    IMAP                  ("netbase.imap",               NETBASE,    ImapConfigSensorsWizard           ::new),
    POP                   ("netbase.pop",                NETBASE,    PopConfigSensorsWizard            ::new),
    SMTP                  ("netbase.smtp",               NETBASE,    SmtpConfigSensorsWizard           ::new),
    TELNET                ("netbase.telnet",             NETBASE,    TelnetConfigSensorsWizard         ::new),

    FILE_EXISTENCE        ("filesystem.fileexistence",   FILESYSTEM, FileExistenceConfigSensorsWizard  ::new),
    FILE_SIZE             ("filesystem.filesize",        FILESYSTEM, FileSizeConfigSensorsWizard       ::new),
    FOLDER_SIZE           ("filesystem.foldersize",      FILESYSTEM, FolderSizeConfigSensorsWizard     ::new),
    FOLDER_EXISTENCE      ("filesystem.folderexistence", FILESYSTEM, FolderExistenceConfigSensorsWizard::new),
    FREE_FOLDER_SIZE      ("filesystem.freefoldersize",  FILESYSTEM, FreeFolderSizeConfigSensorsWizard ::new),
    FILE_CRC              ("filesystem.filecrc",         FILESYSTEM, FileCrcConfigSensorsWizard        ::new),
    FILE_COMPARE          ("filesystem.filecompare",     FILESYSTEM, FileCompareConfigSensorsWizard    ::new),


    REGISTRY_KEY          ("winbase.registrykeyvalue",   WINBASE,    RegistryKeyConfigSensorsWizard    ::new),
    CPU_USAGE             ("winbase.cpuusage",           WINBASE,    CpuUsageConfigSensorsWizard       ::new),
    PHYSICAL_MEMORY       ("winbase.physicalmemory",     WINBASE,    PhysicalMemoryConfigSensorsWizard ::new),
    VIRTUAL_MEMORY        ("winbase.virtualmemory",      WINBASE,    VirtualMemoryConfigSensorsWizard  ::new),
    PROCESS_COUNT         ("winbase.processcount",       WINBASE,    ProcessCountConfigSensorsWizard   ::new),
    USER_COUNT            ("winbase.usercount",          WINBASE,    UserCountConfigSensorsWizard      ::new),

    SYSTEM_SERVICE_STATUS ("system.servicestatus",       SYSTEM,     ServiceStatusConfigSensorsWizard  ::new);

    final String caption;
    final SelectWizardTab type;
    final Function<WizardType, ? extends ConfigSensorsWizard> configWizardConstructor;

    // O-0018
    public By getLocator(){
        final String xpath = String.format(".//*[@data-ol-accord-item = '%s']", this.caption);
        return byXpath(xpath);
    }

    public String getCaption(){
        return MyStreamMethods.getLast(
            Arrays.asList(this.caption.split("\\."))
        ).orElse("None name");
    }
}
