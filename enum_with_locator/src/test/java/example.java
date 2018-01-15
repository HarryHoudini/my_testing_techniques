import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class example {
    public static void main(String[] args) {
        final String idDeviceModal = "id";
        final DeviceModal deviceModal = new DeviceModal($(byId(idDeviceModal)));

        // select GENERIC_DEVICE
        deviceModal.selectDeviceType(DeviceType.GENERIC_DEVICE);

        // select DSL_MODEM
        deviceModal.selectDeviceType(DeviceType.DSL_MODEM);
    }
}
