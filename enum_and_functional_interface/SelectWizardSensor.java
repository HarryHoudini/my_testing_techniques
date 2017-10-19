package test.network_tree_winget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.elements.wizards.sensor_wizards.SelectWizardTab;
import main.elements.wizards.sensor_wizards.config_wizards.ConfigSensorsWizard;
import main.elements.wizards.sensor_wizards.config_wizards.filesystem_wizards.*;
import main.elements.wizards.sensor_wizards.config_wizards.netbase_wizards.*;
import main.elements.wizards.sensor_wizards.config_wizards.system_wizards.ServiceStatusConfigSensorsWizard;
import main.elements.wizards.sensor_wizards.config_wizards.winbase_wizards.*;
import main.utility.my_interface.MyLocatorInterface;
import main.utility.utils.MyStreamMethods;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.codeborne.selenide.Selectors.byXpath;
import static main.elements.wizards.sensor_wizards.SelectWizardTab.*;

@Getter
@AllArgsConstructor
public enum SelectWizardSensor implements MyLocatorInterface {
    PING                  ("netbase.ping",               PingConfigSensorsWizard           ::new),
    TCP                   ("netbase.tcp",                TcpConfigSensorsWizard            ::new),
    FTP                   ("netbase.ftp",                FtpConfigSensorsWizard            ::new),
    HTTP                  ("netbase.http",               HttpConfigSensorsWizard           ::new),
    HTTP_CONTENT          ("netbase.httpcontent",        HttpContentConfigSensorsWizard    ::new),
    IMAP                  ("netbase.imap",               ImapConfigSensorsWizard           ::new),
    POP                   ("netbase.pop",                PopConfigSensorsWizard            ::new),
    SMTP                  ("netbase.smtp",               SmtpConfigSensorsWizard           ::new),
    TELNET                ("netbase.telnet",             TelnetConfigSensorsWizard         ::new),

    FILE_EXISTENCE        ("filesystem.fileexistence",   FileExistenceConfigSensorsWizard  ::new),
    FILE_SIZE             ("filesystem.filesize",        FileSizeConfigSensorsWizard       ::new),
    FOLDER_SIZE           ("filesystem.foldersize",      FolderSizeConfigSensorsWizard     ::new),
    FOLDER_EXISTENCE      ("filesystem.folderexistence", FolderExistenceConfigSensorsWizard::new),
    FREE_FOLDER_SIZE      ("filesystem.freefoldersize",  FreeFolderSizeConfigSensorsWizard ::new),
    FILE_CRC              ("filesystem.filecrc",         FileCrcConfigSensorsWizard        ::new),
    FILE_COMPARE          ("filesystem.filecompare",     FileCompareConfigSensorsWizard    ::new),


    REGISTRY_KEY          ("winbase.registrykeyvalue",   RegistryKeyConfigSensorsWizard    ::new),
    CPU_USAGE             ("winbase.cpuusage",           CpuUsageConfigSensorsWizard       ::new),
    PHYSICAL_MEMORY       ("winbase.physicalmemory",     PhysicalMemoryConfigSensorsWizard ::new),
    VIRTUAL_MEMORY        ("winbase.virtualmemory",      VirtualMemoryConfigSensorsWizard  ::new),
    PROCESS_COUNT         ("winbase.processcount",       ProcessCountConfigSensorsWizard   ::new),
    USER_COUNT            ("winbase.usercount",          UserCountConfigSensorsWizard      ::new),

    SYSTEM_SERVICE_STATUS ("system.servicestatus",       ServiceStatusConfigSensorsWizard  ::new);

    final String caption;
    final Supplier<? extends ConfigSensorsWizard> configWizardConstructor;

    public By getLocator(){
        final String xpath = String.format(".//*[@data-ol-accord-item = '%s']", this.caption);
        return byXpath(xpath);
    }
}
