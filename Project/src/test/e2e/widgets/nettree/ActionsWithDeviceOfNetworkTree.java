package test.e2e.widgets.nettree;

import com.codeborne.selenide.CollectionCondition;
import com.epam.reportportal.service.ReportPortal;
import main.java.constants.DependingConstants;
import main.java.data.DeviceAttributes;
import main.java.elements.modal.ModalTextedit;
import main.java.elements.modal.networkTree.DeviceType;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.VisualMode;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.wizards.action.ActionType;
import main.java.elements.wizards.sensor.SensorType;
import main.java.utility.utils.config.TestPrecondition;
import main.java.xml.MyXmlCreator;
import main.java.xml.OControlRunner;
import main.java.xml.node.XmlDevice;
import main.java.xml.node.XmlRoot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rp.com.google.common.io.BaseEncoding;
import rp.com.google.common.io.Resources;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static com.codeborne.selenide.Selectors.byId;
import static org.testng.Assert.assertTrue;

/**
 * Created by Saveg on 9/24/2017.
 * Estimated execution time - 30 seconds
 */


public class ActionsWithDeviceOfNetworkTree {
    private static Logger logger = LogManager.getLogger(ActionsWithDeviceOfNetworkTree.class);
    private NetworkTreeWidget   networkTreeWidget;
    private RootOfNetworkTree   rootTree;
    private DeviceOfNetworkTree device1;

    private final DeviceAttributes DEVICE_ATTRIBUTES = new DeviceAttributes("New Device 1",
                                                                            "NewDevice1");

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.networkTreeWidget.shouldBeVisible();
        this.rootTree = networkTreeWidget.getRoot();

        this.device1 = this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTRIBUTES.getDeviceCaption());
    }

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        File file = new File("C:\\TEMP\\1.png");
        logger.info("RP_MESSAGE#BASE64#{}#{}",
            BaseEncoding.base64().encode(Files.readAllBytes(file.toPath())),
            "I'm logging content via BASE64");
        TestPrecondition.startOnNetworkTreePanel();

        addNodesByOcontrol();

        // OLYMPUS-1508 DoD #4
        networkTreeSetUp();
        TestPrecondition.setContext(context);
    }


    private void addNodesByOcontrol() throws JAXBException, IOException, InterruptedException {
        ArrayList<Object> xmlDevices = new ArrayList<>();

        XmlDevice xmlDevice1 = new XmlDevice();
        xmlDevice1.setId(71);
        xmlDevice1.setCaption(DEVICE_ATTRIBUTES.getDeviceCaption());
        xmlDevice1.setHostname(DEVICE_ATTRIBUTES.getDeviceHostname());
        xmlDevices.add(xmlDevice1);

        XmlRoot xmlRoot = new XmlRoot();
        xmlRoot.addSubitems(Collections.singletonList(
            xmlDevice1.getId()
        ));
        xmlDevices.add(xmlRoot);

        MyXmlCreator xmlCreatorGroups = new MyXmlCreator(this.getClass());
        xmlCreatorGroups.marshall(xmlDevices);
        OControlRunner oControlRunnerGroups = new OControlRunner();
        oControlRunnerGroups.addUpdateXml(this.getClass(), xmlCreatorGroups.getXmls());
        oControlRunnerGroups.runFromCommandFile();
    }

    @Test()
    public void editDeviceCaption(){

        // GIVEN
        final String NEW_DEVICE_CAPTION = "New Caption Device";
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editCaption(NEW_DEVICE_CAPTION);

        // THEN
        this.device1.shouldHasCaption(NEW_DEVICE_CAPTION);
    }

    @Test()
    public void editDeviceHostname(){

        // GIVEN
        final String NEW_HOSTNAME = "NewCaptionDevice";
        final DeviceAttributes newAttributes = DeviceAttributes.deviceAttributesByHostname(NEW_HOSTNAME);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(newAttributes);

        // THEN
        this.device1.shoudHasTextEditAttr(ModalTextedit.HOSTNAME, NEW_HOSTNAME);
    }

    @Test()
    public void editDeviceIp(){

        // GIVEN
        final String NEW_IP = "192.168.0.1";
        final DeviceAttributes newAttributes = DeviceAttributes.deviceAttributesByIp(NEW_IP);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(newAttributes);

        // THEN
        this.device1.shoudHasTextEditAttr(ModalTextedit.IP_ADRESS, NEW_IP);
    }

    @Test()
    public void editDeviceMac(){

        // GIVEN
        final String NEW_MAC = "FF-FF-FF-FF-FF-FF";
        final DeviceAttributes newAttributes = DeviceAttributes.deviceAttributesByMac(NEW_MAC);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(newAttributes);

        // THEN
        this.device1.shoudHasTextEditAttr(ModalTextedit.MAC_ADRESS, NEW_MAC);
    }

    @Test()
    public void editDeviceDescription(){

        // GIVEN
        final String NEW_DESCRIPRION = "New Descriprion";
        final DeviceAttributes newAttributes = DeviceAttributes.deviceAttributesByDescription(NEW_DESCRIPRION);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(newAttributes);

        // THEN
        this.device1.shouldHasDescription(NEW_DESCRIPRION);
    }

    @Test()
    public void editDeviceType(){

        // GIVEN
        final DeviceType NEW_TYPE = DeviceType.DOMAIN_CONTROLLER;
        final DeviceAttributes newAttributes = DeviceAttributes.deviceAttributesByType(NEW_TYPE);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(newAttributes);

        // THEN
        this.device1.shoudHasType(NEW_TYPE);
    }

    @Test()
    public void editAllFieldOfDevice(){

        // GIVEN
        final String NEW_CAPTION = "New Caption Device 1";
        final DeviceType NEW_TYPE = DeviceType.DSL_MODEM;
        final String NEW_HOSTNAME = "NewCaptionDevice1";
        final String NEW_IP = "192.168.0.2";
        final String NEW_MAC = "FF-FF-FF-FF-FF-FE";
        final String NEW_DESCRIPRION = "New Descriprion 1";
        final Boolean IS_STATIC_IP = true;
        final DeviceAttributes NEW_ATTRIBUTES = new DeviceAttributes(NEW_CAPTION, NEW_TYPE, NEW_HOSTNAME,
            IS_STATIC_IP, NEW_IP, NEW_MAC,NEW_DESCRIPRION);
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.editAttributes(NEW_ATTRIBUTES);

        // THEN
        this.device1.shoudHasAttributes(NEW_ATTRIBUTES);
    }

    @Test()
    public void selectDefaultCredentialForDevice(){

        // GIVEN
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.setDefaultCredential();

        // THEN
        assertTrue(this.device1.isDefaultCredentialSelected());
    }

    @Test()
    public void addSensorForDeviceWithoutCredential(){

        // GIVEN
        final String SENSOR_NAME = "New Sensor";
        this.networkTreeWidget.expandAllGroup();
        this.device1
            .setInheritCredential()

        // WHEN
            .addDefaultSensor(SensorType.HTTP, SENSOR_NAME);

        // THEN
        this.device1.shouldHasSensor(SENSOR_NAME);
    }

    @Test()
    public void addActionForDeviceWithoutCredential(){

        // GIVEN
        final String ACTION_NAME = "New Action";
        this.networkTreeWidget.expandAllGroup();
        this.device1
            .setInheritCredential()

            // WHEN
            .addDefaultAction(ActionType.DELAY, ACTION_NAME);

        // THEN
        this.device1.shouldHasAction(ACTION_NAME);
    }

    @Test(priority = 1)
    public void deleteDevice(){

        // GIVEN
        String idGroup = this.device1.getId();
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.device1.deleteDevice();

        // THEN
        this.networkTreeWidget.getRootElement().$$(byId(idGroup)).shouldHave(CollectionCondition.empty);
    }
}
