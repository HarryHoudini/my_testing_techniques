package main.java.elements.modal.networkTree;

import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import org.openqa.selenium.By;

import java.util.Optional;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byAttribute;

@Getter
public enum DeviceType implements MyLocatorInterface {
    WINDOWS_STATION     ("win",                  "\uE94F Windows stantion"),
    WINDOWS_DESKTOP     ("win-desktop",          "\uE950 Windows desktop"),
    WINDOWS_LAPTOP      ("win-laptop",           "\uE951 Windows laptop"),
    WINDOWS_NETTOP      ("win-nettop",           "\uE952 Windows nettop"),
    WINDOWS_SERVER      ("win-server",           "\uE953 Windows server"),
    DEVICES             ("devices",              "\uE915 Devices"),
    DOMAIN_CONTROLLER   ("domain-controller",    "\uE917 Domain controller"),
    DSL_MODEM           ("dsl-modem",            "\uE918 Dsl modem"),
    DSL_ROUTE           ("dsl-router",           "\uE919 Dsl router"),
    ESX_I_SERVER        ("esx-i-server",         "\uE91B ESX I-server"),
    ESX_SERVER          ("esx-server",           "\uE91C ESX server"),
    FIREWALL            ("firewall",             "\uE91E Firewal"),
    FREE_BSD_SERVER     ("free-bsd-server",      "\uE921 BSD server"),
    GENERIC_DEVICE      ("generic-device",       "\uE922 Generic device"),
    HYPER_V_SERVER      ("hyper-v-server",       "\uE923 Hyper-V server"),
    I_MAC               ("i-mac",                "\uE924 I mac"),
    IP_CAMERA           ("ip-camera",            "\uE925 Ip-camera"),
    IP_PHONE            ("ip-phone",             "\uE926 Ip-phone"),
    LINUX_DESKTOP       ("linux-desktop",        "\uE928 Linux desktop"),
    LINUX_SERVER        ("linux-server",         "\uE929 Linux-server"),
    MAC                 ("mac",                  "\uE92C Mac"),
    MAC_BOOK            ("mac-book",             "\uE92D Mac book"),
    MAC_MINI            ("mac-mini",             "\uE92E Mac mini"),
    MAC_PRO             ("mac-pro",              "\uE92F Mac pro"),
    MODEM               ("modem",                "\uE932 Modem"),
    NAS                 ("nas",                  "\uE935 NAS"),
    NIX                 ("nix",                  "\uE936 NIX"),
    ORACLE_VM_SERVER    ("oracle-vm-server",     "\uE93B Oracle Vm-server"),
    PRINT_SERVER        ("print-server",         "\uE93F Print-server"),
    ROUTER              ("router",               "\uE941 Router"),
    SWITCH              ("switch",               "\uE94B Switch"),
    VM_VARE             ("vm-vare",              "\uE94E Vm-vare"),
    WIRELESS_ACCES_POINT("wireless-acces-point", "\uE954 Wireless acces-point");

    DeviceType(String qaId, String caption) {
        this.qaId = qaId;
        this.caption = caption;
    }

    private final String qaId;
    private final String caption;


    public By getLocator(){
        return byAttribute("data-qa-id", this.qaId);
    }

    /**
     * return null, if if nothing is selected in the Select "Device type"
     * throw exeption IllegalArgumentException, if the expectedCaption is not in the list
     */
    public static Optional<DeviceType> typeOf(final String expectedCaption) throws IllegalArgumentException{

        if (expectedCaption.trim().equals("")){
            return Optional.empty();
        }

        Optional<DeviceType> type = Stream.of(DeviceType.values())
            .filter(val -> val.getCaption().equals(expectedCaption))
            .findFirst();

        if (type.isPresent()){
            return type;
        }

        throw new IllegalArgumentException(
                "Unable to find matching device type for specified caption: " + expectedCaption
            );
    }
}