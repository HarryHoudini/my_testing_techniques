package test.e2e.widgets.nettree;

import main.java.constants.DependingConstants;
import main.java.elements.modal.networkTree.DeviceType;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.VisualMode;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.data.DeviceAttributes;
import main.java.utility.utils.config.TestPrecondition;
import main.java.xml.MyXmlCreator;
import main.java.xml.OControlRunner;
import main.java.xml.node.XmlDevice;
import main.java.xml.node.XmlGroup;
import main.java.xml.node.XmlRoot;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static main.java.elements.widgets.tree.FieldOfSort.ADDITIONAL_FIELD;
import static main.java.elements.widgets.tree.FieldOfSort.MAIN_FIELD;
import static main.java.elements.widgets.tree.VisualMode.*;
import static org.testng.Assert.*;

/**
 * This class test Tree of nodes
 * Tree of nodes should is empty before runFromCommandFile tests
 * Estimated execution time - 70 seconds
 */


public class ActionsWithNetworkTree {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private GroupOfNetworkTree group1;
    private int countAddDevice = 0;
    private final String EMPTY_STRING = "";
    private final DeviceAttributes DEVICE_ATTR_1 = new DeviceAttributes("PreTest Device 1",
        "PreTestDevice1", true, "255.255.255.255");
    private final DeviceAttributes DEVICE_ATTR_2 = new DeviceAttributes("APreTest Device 2",
        "PreTestDevice2", true, "1.1.1.1");
    private final DeviceAttributes DEVICE_ATTR_3 = new DeviceAttributes("ZZPreTest Device 3",
        "APreTestDevice3", true, "11.11.11.11");
    private final DeviceAttributes DEVICE_ATTR_4 = new DeviceAttributes("ZPreTest Device 4",
        "ZPreTestDevice4", true, "111.111.111.111");
    private final DeviceAttributes DEVICE_ATTR_5 = new DeviceAttributes("5PreTest Device 5",
        "5PreTestDevice5", true, "255.0.0.255");
    private final DeviceAttributes DEVICE_ATTR_6 = new DeviceAttributes("PreTest Device 6",
        DeviceType.DOMAIN_CONTROLLER, "PreTestDevice6", true, "222.222.222.2",
        "FF-FF-FF-FF-FF-FE", "Description");
    private final String GROUP_CAPTION = "New Group 1";

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        TestPrecondition.startOnNetworkTreePanel();
        addNodesByOcontrol();
        TestPrecondition.setContext(context);
    }

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.networkTreeWidget.shouldBeVisible();
        this.rootTree = networkTreeWidget.getRoot();

        this.networkTreeWidget
            .addNewDevice(this.DEVICE_ATTR_6);
        ++this.countAddDevice;

        this.group1 = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(this.GROUP_CAPTION);
    }

    private void addNodesByGui() {

        //precondition simpleSearch() method
        this.rootTree.select();
        this.networkTreeWidget.addNewDevice(this.DEVICE_ATTR_1);
        ++this.countAddDevice;

        //sorting precondition
        this.networkTreeWidget.addNewDevice(this.DEVICE_ATTR_2);
        ++this.countAddDevice;
        this.networkTreeWidget.addNewDevice(this.DEVICE_ATTR_3);
        ++this.countAddDevice;
        this.networkTreeWidget.addNewDevice(this.DEVICE_ATTR_4);
        ++this.countAddDevice;
        this.networkTreeWidget.addNewDevice(this.DEVICE_ATTR_5);
        ++this.countAddDevice;

        //visual precondition
        this.networkTreeWidget
                .addNewDevice(this.DEVICE_ATTR_6);
        ++this.countAddDevice;
    }

    private void addNodesByOcontrol() throws JAXBException, IOException, InterruptedException {
        ArrayList<Object> xmlDevices = new ArrayList<>();

        XmlDevice xmlDevice1 = new XmlDevice();
        xmlDevice1.setAllAttribures(this.DEVICE_ATTR_1);
        xmlDevice1.setId(71);
        xmlDevices.add(xmlDevice1);
        ++this.countAddDevice;

        XmlDevice xmlDevice2 = new XmlDevice();
        xmlDevice2.setAllAttribures(this.DEVICE_ATTR_2);
        xmlDevice2.setId(72);
        xmlDevices.add(xmlDevice2);
        ++this.countAddDevice;

        XmlDevice xmlDevice3 = new XmlDevice();
        xmlDevice3.setAllAttribures(this.DEVICE_ATTR_3);
        xmlDevice3.setId(73);
        xmlDevices.add(xmlDevice3);
        ++this.countAddDevice;

        XmlDevice xmlDevice4 = new XmlDevice();
        xmlDevice4.setAllAttribures(this.DEVICE_ATTR_4);
        xmlDevice4.setId(74);
        xmlDevices.add(xmlDevice4);
        ++this.countAddDevice;


        XmlDevice xmlDevice5 = new XmlDevice();
        xmlDevice5.setAllAttribures(this.DEVICE_ATTR_5);
        xmlDevice5.setId(75);
        xmlDevices.add(xmlDevice5);
        ++this.countAddDevice;

        XmlGroup xmlGroup1 = new XmlGroup();
        xmlGroup1.setCaption(this.GROUP_CAPTION);
        xmlGroup1.setId(81);
        xmlDevices.add(xmlGroup1);

        XmlRoot xmlRoot = new XmlRoot();
        xmlRoot.addSubitems(Arrays.asList(
            xmlDevice1.getId(),
            xmlDevice2.getId(),
            xmlDevice3.getId(),
            xmlDevice4.getId(),
            xmlDevice5.getId(),
            xmlGroup1.getId()
        ));
        xmlDevices.add(xmlRoot);

        MyXmlCreator xmlCreatorGroups = new MyXmlCreator(this.getClass());
        xmlCreatorGroups.marshall(xmlDevices);
        OControlRunner oControlRunnerGroups = new OControlRunner();
        oControlRunnerGroups.addUpdateXml(this.getClass(), xmlCreatorGroups.getXmls());
        oControlRunnerGroups.runFromCommandFile();
    }

//     Add nodes tests
    @Test
    public void addDeviceToRootUsingButtonShortForm(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 1",
            "TestDevice1", true, "192.168.1.1");
        this.rootTree.select();
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        this.networkTreeWidget.addNewDevice(DEVICE_ATTR);
        ++this.countAddDevice;

        // THEN
        final DeviceOfNetworkTree device = this.networkTreeWidget
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.rootTree, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    @Test
    public void addDeviceToGroupUsingButtonShortForm() {

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 2",
            "TestDevice2", true, "192.168.1.2");


        // WHEN
        this.group1.select();
        this.networkTreeWidget.addNewDevice(DEVICE_ATTR);
        ++this.countAddDevice;

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.group1, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    @Test
    public void addDeviceToRootUsingButtonLongForm(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("Test Device 3",
            DeviceType.DOMAIN_CONTROLLER, "TestDevice3", true, "192.168.1.3",
            "FF-FF-FF-FF-FF-FF", "Description");
        this.rootTree.select();
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        this.networkTreeWidget
            .addNewDevice(DEVICE_ATTR);
        ++this.countAddDevice;

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.rootTree, device);
        device.shoudHasAttributes(DEVICE_ATTR);
    }

    // Search tests
    @Test
    public void simpleSearch(){

        // GIVEN
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        this.networkTreeWidget
            .searching(DEVICE_ATTR_1.getDeviceCaption());

        // THEN
        this.networkTreeWidget.getAllDevicesOfTree().shouldHaveSize(1);
        this.networkTreeWidget.clearSearchString();
    }

    // Sorting tests
    @Test
    public void sortingByMainFieldNotIp(){

        // GIVEN
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_CAPTION);

        // WHEN
        this.networkTreeWidget.sortBy(MAIN_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(this.networkTreeWidget.isMainFieldSorted(false));
    }

    @Test
    public void sortingByAdditionalFieldNotIp(){

        // GIVEN
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.ADD_CAPTION);

        // WHEN
        this.networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(this.networkTreeWidget.isAdditionalFieldSorted(false));
    }

    @Test
    public void sortingByMainFieldIp(){

        // GIVEN
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(MAIN_IP_ADRESS);

        // WHEN
        this.networkTreeWidget
            .sortBy(MAIN_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(this.networkTreeWidget.isMainFieldSorted(false));
    }


    @Test
    public void sortingByAdditionalFieldIp(){

        // GIVEN
        this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(ADD_IP_ADRESS);

        // WHEN
        this.networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(false);

        // THEN
        assertTrue(this.networkTreeWidget.isAdditionalFieldSorted(false));
    }

    @Test
    public void reverseSortingByMainField(){

        // GIVEN;
        this.networkTreeWidget.setVisualMode(MAIN_HOSTNAME);
        // WHEN
        this.networkTreeWidget
            .sortBy(MAIN_FIELD)
            .setReverseSorting(true);
        // THEN
        assertTrue(this.networkTreeWidget.isMainFieldSorted(true));
    }

    @Test
    public void reverseSortingByAdditionalField(){

        // GIVEN
        this.networkTreeWidget.setVisualMode(ADD_CAPTION);
        // WHEN
        this.networkTreeWidget
            .sortBy(ADDITIONAL_FIELD)
            .setReverseSorting(true);
        // THEN
        assertTrue(this.networkTreeWidget.isAdditionalFieldSorted(true));

    }

    // Visualisation mode tests

    @Test
    public void selectHostnameMainField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(MAIN_HOSTNAME)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_6.getDeviceHostname());
    }

    @Test
    public void selectIpMainField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(MAIN_IP_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_6.getDeviceIP());
    }

    @Test
    public void selectMacMainField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(MAIN_MAC_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_6.getDeviceMacAddress());
    }

    @Test
    public void selectCaptionMainField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(MAIN_CAPTION)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithMainFieldText(DEVICE_ATTR_6.getDeviceCaption());
    }

    @Test
    public void selectIpAdditionalField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(ADD_IP_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_6.getDeviceIP());
    }

    @Test
    public void selectMacAdditionalField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(ADD_MAC_ADRESS)
            .expandAllGroup()

        // THEN
            .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_6.getDeviceMacAddress());
        }

    @Test
    public void selectCaptionAdditionalField(){

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .setVisualMode(ADD_CAPTION)
            .expandAllGroup()

        // THEN
        .shoudHasDeviceWithAdditionalFieldText(DEVICE_ATTR_6.getDeviceCaption());
    }

    @Test
    public void selectNoneAdditionalField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(MAIN_CAPTION)

        // WHEN
            .setVisualMode(ADD_WITHOUT_FIELD)
            .expandAllGroup()

        // THEN
        .shoudHasDeviceWithAdditionalFieldText(this.EMPTY_STRING);
    }

    @Test
    public void selectIpMainSameAdditionalField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(ADD_IP_ADRESS)

        // WHEN
            .setVisualMode(MAIN_IP_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceIP());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_6.getDeviceMacAddress());
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(ADD_IP_ADRESS));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(MAIN_IP_ADRESS));
    }

    @Test
    public void selectMacMainSameAdditionalField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(ADD_MAC_ADRESS)

        // WHEN
            .setVisualMode(MAIN_MAC_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceMacAddress());

        assertEquals(device.getAdditionalFieldText(), this.EMPTY_STRING);
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(ADD_MAC_ADRESS));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(MAIN_MAC_ADRESS));
    }

    @Test
    public void selectCaptionMainSameAdditionalField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(ADD_CAPTION)

        // WHEN
            .setVisualMode(MAIN_CAPTION)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceCaption());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_6.getDeviceMacAddress());
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(ADD_CAPTION));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(MAIN_CAPTION));
    }

    @Test
    public void selectIpAdditionalSameMainField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(MAIN_IP_ADRESS)

        // WHEN
            .setVisualMode(ADD_IP_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_6.getDeviceIP());
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(MAIN_IP_ADRESS));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(ADD_IP_ADRESS));
    }

    @Test
    public void selectMacAdditionalSameMainField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(MAIN_MAC_ADRESS)

        // WHEN
            .setVisualMode(ADD_MAC_ADRESS)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_6.getDeviceMacAddress());
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(MAIN_MAC_ADRESS));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(ADD_MAC_ADRESS));
    }

    @Test
    public void selectCaptionAdditionalSameMainField(){

        // GIVEN
        this.networkTreeWidget
            .setVisualMode(MAIN_CAPTION)

        // WHEN
            .setVisualMode(ADD_CAPTION)
            .expandAllGroup();

        // THEN
        DeviceOfNetworkTree device1 = this.networkTreeWidget.getDeviceByMainField(DEVICE_ATTR_6.getDeviceHostname());

        assertEquals(device1.getAdditionalFieldText(), DEVICE_ATTR_6.getDeviceCaption());
        assertFalse(this.networkTreeWidget.isCheckedVisualMode(MAIN_CAPTION));
        assertTrue(this.networkTreeWidget.isCheckedVisualMode(ADD_CAPTION));
    }

    // Collapse/expande folders tree test
    @Test
    public void collapseAllGroup(){

        // GIVEN

        // WHEN
        this.networkTreeWidget.collapseAllGroup();

        // THEN
        this.networkTreeWidget.getAllDevicesOfTree().shouldHaveSize(0);
    }

    @Test
    public void expandAllGroup(){

        // GIVEN

        // WHEN
        this.networkTreeWidget.expandAllGroup();

        // THEN
        this.networkTreeWidget.getAllDevicesOfTree().shouldHaveSize(this.countAddDevice);
    }
}
