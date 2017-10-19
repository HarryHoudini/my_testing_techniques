package test.network_tree_winget;

import main.data.DeviceAttributes;
import main.elements.modal_windows.networkTree.DeviceType;
import main.elements.widgets.node_tree.NetworkTreeWidget;
import main.elements.widgets.node_tree.VisualMode;
import main.elements.widgets.node_tree.nodes.DeviceOfNetworkTree;
import main.elements.widgets.node_tree.nodes.GroupOfNetworkTree;
import main.elements.widgets.node_tree.nodes.RootOfNetworkTree;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static main.elements.widgets.node_tree.FieldOfSort.ADDITIONAL_FIELD;
import static main.elements.widgets.node_tree.FieldOfSort.MAIN_FIELD;
import static main.elements.widgets.node_tree.VisualMode.*;
import static org.testng.Assert.*;

/**
 * This class test Tree of nodes
 * Tree of nodes should is empty before run tests
 * Estimated execution time - 70 seconds
 */


public class ActionsWithNetworkTree2 {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private GroupOfNetworkTree group;
    private int countAddDevice = 0;
    private final String EMPTY_STRING = "";
    private final DeviceAttributes DEVICE_ATTR_CONST = new DeviceAttributes("PreTest Device 6",
        DeviceType.DOMAIN_CONTROLLER, "PreTestDevice6", true, "222.222.222.2",
        "FF-FF-FF-FF-FF-FE", "Description");

    @BeforeClass
    public void setUp() throws InterruptedException, JAXBException, IOException {
        // precondition setup
        // initial of networkTreeWidget, rootTree, group
    }

//     Add nodes tests
    @Test
    public void addDeviceToRootUsingButtonShortForm(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 1",
            "TestDevice1", true, "192.168.1.1");
        rootTree.select();
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        networkTreeWidget.addNewDevice(DEVICE_ATTR);
        ++countAddDevice;

        // THEN
        final DeviceOfNetworkTree device = networkTreeWidget
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        networkTreeWidget.shoudBeParent(rootTree, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    @Test
    public void addDeviceToGroupUsingButtonShortForm() {

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 2",
            "TestDevice2", true, "192.168.1.2");


        // WHEN
        group.select();
        networkTreeWidget.addNewDevice(DEVICE_ATTR);
        ++countAddDevice;

        // THEN
        DeviceOfNetworkTree device = networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        networkTreeWidget.shoudBeParent(group, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    @Test
    public void addDeviceToRootUsingButtonLongForm(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 3",
            DeviceType.DOMAIN_CONTROLLER, "TestDevice3", true, "192.168.1.3",
            "FF-FF-FF-FF-FF-FF", "Description");
        rootTree.select();
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        networkTreeWidget
            .addNewDevice(DEVICE_ATTR);
        ++countAddDevice;

        // THEN
        DeviceOfNetworkTree device = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        networkTreeWidget.shoudBeParent(rootTree, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    // Sorting tests
    @Test
    public void sortingByMainFieldNotIp(){

        // GIVEN
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        networkTreeWidget.sortBy(MAIN_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(networkTreeWidget.isMainFieldSorted(false));
    }

    @Test
    public void sortingByAdditionalFieldNotIp(){

        // GIVEN
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.ADD_CAPTION);

        // WHEN
        networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(networkTreeWidget.isAdditionalFieldSorted(false));
    }

    @Test
    public void sortingByMainFieldIp(){

        // GIVEN
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_IP_ADRESS);

        // WHEN
        networkTreeWidget
            .sortBy(MAIN_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(networkTreeWidget.isMainFieldSorted(false));
    }


    @Test
    public void sortingByAdditionalFieldIp(){

        // GIVEN
        networkTreeWidget
            .expandAllGroup()
            .setVisualMode(ADD_IP_ADRESS);

        // WHEN
        networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(networkTreeWidget.isAdditionalFieldSorted(false));
    }

    @Test
    public void reverseSortingByMainField(){

        // GIVEN;
        networkTreeWidget.setVisualMode(MAIN_HOSTNAME);
        // WHEN
        networkTreeWidget
            .sortBy(MAIN_FIELD)
            .setReverseSorting(true);
        // THEN
        assertTrue(networkTreeWidget.isMainFieldSorted(true));
    }

    @Test
    public void reverseSortingByAdditionalField(){

        // GIVEN
        networkTreeWidget.setVisualMode(ADD_CAPTION);
        // WHEN
        networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(true);
        // THEN
        assertTrue(networkTreeWidget.isAdditionalFieldSorted(true));

    }

    // Visualisation mode tests

    @Test
    public void selectHostnameMainField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(MAIN_HOSTNAME)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_CONST.getDeviceHostname());
    }

    @Test
    public void selectIpMainField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(MAIN_IP_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_CONST.getDeviceIP());
    }

    @Test
    public void selectMacMainField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(MAIN_MAC_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_CONST.getDeviceMacAddress());
    }

    @Test
    public void selectCaptionMainField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(MAIN_CAPTION)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_CONST.getDeviceCaption());
    }

    @Test
    public void selectIpAdditionalField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(ADD_IP_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_CONST.getDeviceIP());
    }

    @Test
    public void selectMacAdditionalField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(ADD_MAC_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_CONST.getDeviceMacAddress());
        }

    @Test
    public void selectCaptionAdditionalField(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .setVisualMode(ADD_CAPTION)
            .expandAllGroup()

        // THEN
        .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_CONST.getDeviceCaption());
    }

    @Test
    public void selectNoneAdditionalField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(MAIN_CAPTION)

        // WHEN
            .setVisualMode(ADD_WITHOUT_FIELD)
            .expandAllGroup()

        // THEN
        .shoudHasDeviceWithAdditionalFieldText(EMPTY_STRING);
    }

    @Test
    public void selectIpMainSameAdditionalField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(ADD_IP_ADRESS)

        // WHEN
            .setVisualMode(MAIN_IP_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceIP());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_CONST.getDeviceMacAddress());
        assertFalse(networkTreeWidget.isCheckedVisualMode(ADD_IP_ADRESS));
        assertTrue(networkTreeWidget.isCheckedVisualMode(MAIN_IP_ADRESS));
    }

    @Test
    public void selectMacMainSameAdditionalField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(ADD_MAC_ADRESS)

        // WHEN
            .setVisualMode(MAIN_MAC_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceMacAddress());

        assertEquals(device.getAdditionalFieldText(), EMPTY_STRING);
        assertFalse(networkTreeWidget.isCheckedVisualMode(ADD_MAC_ADRESS));
        assertTrue(networkTreeWidget.isCheckedVisualMode(MAIN_MAC_ADRESS));
    }

    @Test
    public void selectCaptionMainSameAdditionalField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(ADD_CAPTION)

        // WHEN
            .setVisualMode(MAIN_CAPTION)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceCaption());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_CONST.getDeviceMacAddress());
        assertFalse(networkTreeWidget.isCheckedVisualMode(ADD_CAPTION));
        assertTrue(networkTreeWidget.isCheckedVisualMode(MAIN_CAPTION));
    }

    @Test
    public void selectIpAdditionalSameMainField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(MAIN_IP_ADRESS)

        // WHEN
            .setVisualMode(ADD_IP_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_CONST.getDeviceIP());
        assertFalse(networkTreeWidget.isCheckedVisualMode(MAIN_IP_ADRESS));
        assertTrue(networkTreeWidget.isCheckedVisualMode(ADD_IP_ADRESS));
    }

    @Test
    public void selectMacAdditionalSameMainField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(MAIN_MAC_ADRESS)

        // WHEN
            .setVisualMode(ADD_MAC_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_CONST.getDeviceMacAddress());
        assertFalse(networkTreeWidget.isCheckedVisualMode(MAIN_MAC_ADRESS));
        assertTrue(networkTreeWidget.isCheckedVisualMode(ADD_MAC_ADRESS));
    }

    @Test
    public void selectCaptionAdditionalSameMainField(){

        // GIVEN
        networkTreeWidget
            .setVisualMode(MAIN_CAPTION)

        // WHEN
            .setVisualMode(ADD_CAPTION)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_CONST.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_CONST.getDeviceCaption());
        assertFalse(networkTreeWidget.isCheckedVisualMode(MAIN_CAPTION));
        assertTrue(networkTreeWidget.isCheckedVisualMode(ADD_CAPTION));
    }

    // Collapse/expande folders tree test
    @Test
    public void collapseAllGroup(){

        // GIVEN

        // WHEN
        networkTreeWidget.collapseAllGroup();

        // THEN
        networkTreeWidget.getAllDevicesOfTree().shouldHaveSize(0);
    }

    @Test
    public void expandAllGroup(){

        // GIVEN

        // WHEN
        networkTreeWidget.expandAllGroup();

        // THEN
        networkTreeWidget.getAllDevicesOfTree().shouldHaveSize(countAddDevice);
    }
}
