package test.e2e.widgets.nettree;

import main.java.constants.DependingConstants;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.VisualMode;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.wizards.sensor.SensorType;
import main.java.data.Credential;
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

public class AddingSensorsToNode {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private DeviceOfNetworkTree device;
    private GroupOfNetworkTree group;


    private final DeviceAttributes DEVICE_ATTRIBUTES = new DeviceAttributes("Test Device",
        "TestDevice");

    private final String GROUP_NAME = "Test Group";

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        TestPrecondition.startOnNetworkTreePanel();
        addNodesByOcontrol();
        networkTreeSetUp();
        TestPrecondition.setContext(context);
    }

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.networkTreeWidget.shouldBeVisible();
        this.rootTree = networkTreeWidget.getRoot();

        this.device = this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTRIBUTES.getDeviceCaption());
        this.group = this.networkTreeWidget.getGroup(GROUP_NAME);

        this.rootTree.createNewCredential(CREDENTIAL);
        this.group.selectCredential(CREDENTIAL);
        this.device.selectCredential(CREDENTIAL);
    }

    private void addNodesByOcontrol() throws JAXBException, IOException, InterruptedException {
        ArrayList<Object> xmlNodes = new ArrayList<>();

        XmlDevice xmlDevice1 = new XmlDevice();
        xmlDevice1.setId(71);
        xmlDevice1.setCaption(DEVICE_ATTRIBUTES.getDeviceCaption());
        xmlDevice1.setHostname(DEVICE_ATTRIBUTES.getDeviceHostname());
        xmlNodes.add(xmlDevice1);

        XmlGroup xmlGroup1 = new XmlGroup();
        xmlGroup1.setId(81);
        xmlGroup1.setCaption(GROUP_NAME);
        xmlNodes.add(xmlGroup1);

        XmlRoot xmlRoot = new XmlRoot();
        xmlRoot.addSubitems(Arrays.asList(
            xmlDevice1.getId(),
            xmlGroup1.getId()
        ));
        xmlNodes.add(xmlRoot);

        MyXmlCreator xmlCreatorGroups = new MyXmlCreator(this.getClass());
        xmlCreatorGroups.marshall(xmlNodes);
        OControlRunner oControlRunnerGroups = new OControlRunner();
        oControlRunnerGroups.addUpdateXml(this.getClass(), xmlCreatorGroups.getXmls());
        oControlRunnerGroups.runFromCommandFile();
    }

    @Test()
    public void addPingSensor(){

        // GIVEN
        final String NAME_SENSOR = "Ping Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.PING, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFtpSensor(){

        // GIVEN
        final String NAME_SENSOR = "FTP Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.FTP, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addHttpSensor(){

        // GIVEN
        final String NAME_SENSOR = "HTTP Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.HTTP, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }


    @Test()
    public void addHttpContentSensor(){

        // GIVEN
        final String NAME_SENSOR = "HTTP Content Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.HTTP_CONTENT, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addImapSensor(){

        // GIVEN
        final String NAME_SENSOR = "IMAP Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.IMAP, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addPopSensor(){

        // GIVEN
        final String NAME_SENSOR = "POP Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.POP, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addSmtpSensor(){

        // GIVEN
        final String NAME_SENSOR = "SMTP Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.SMTP, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addTcpSensor(){

        // GIVEN
        final String NAME_SENSOR = "TCP Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.TCP, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addTelnetSensor(){

        // GIVEN
        final String NAME_SENSOR = "Telnet Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.TELNET, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFileCompareSensor(){

        // GIVEN
        final String NAME_SENSOR = "File Compare Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.FILE_COMPARE, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFileCrcSensor(){

        // GIVEN
        final String NAME_SENSOR = "File CRC Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.FILE_CRC, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFileExistenceSensor(){

        // GIVEN
        final String NAME_SENSOR = "File Existence Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.FILE_EXISTENCE, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFolderExistenceSensor(){

        // GIVEN
        final String NAME_SENSOR = "Folder Existence Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.FOLDER_EXISTENCE, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFolderSizeSensor(){

        // GIVEN
        final String NAME_SENSOR = "Size Of Folder Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.FOLDER_SIZE, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addFreeFolderSizeSensor(){

        // GIVEN
        final String NAME_SENSOR = "Free Folder Size Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.FREE_FOLDER_SIZE, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addCpuUsageSensor(){

        // GIVEN
        final String NAME_SENSOR = "CPU Usage Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.CPU_USAGE, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addPhysicalMemorySensor(){

        // GIVEN
        final String NAME_SENSOR = "Physical Memory Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.PHYSICAL_MEMORY, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addProcessCountSensor(){

        // GIVEN
        final String NAME_SENSOR = "Proccess Count Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.PROCESS_COUNT, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addRegistryKeySensor(){

        // GIVEN
        final String NAME_SENSOR = "Registry Key Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.REGISTRY_KEY, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addUserCountSensor(){

        // GIVEN
        final String NAME_SENSOR = "User Count Sensor";

        // WHEN
        this.device.addDefaultSensor(SensorType.USER_COUNT, NAME_SENSOR);

        // THEN
        this.device.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addVirtualMemorySensor(){

        // GIVEN
        final String NAME_SENSOR = "Virtual Memory Sensor";

        // WHEN
        this.group.addDefaultSensor(SensorType.VIRTUAL_MEMORY, NAME_SENSOR);

        // THEN
        this.group.shouldHasSensor(NAME_SENSOR);
    }

    @Test()
    public void addServiceStatusSensor(){

        // GIVEN
        final String NAME_SENSOR = "Service Status Sensor";

        // WHEN
        this.rootTree.addDefaultSensor(SensorType.SYSTEM_SERVICE_STATUS, NAME_SENSOR);

        // THEN
        this.rootTree.shouldHasSensor(NAME_SENSOR);
    }
}
