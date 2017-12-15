package test.e2e.widgets.nettree;

import com.codeborne.selenide.CollectionCondition;
import main.java.constants.DependingConstants;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.VisualMode;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.wizards.action.ActionType;
import main.java.elements.wizards.sensor.SensorType;
import main.java.data.DeviceAttributes;
import main.java.utility.utils.config.TestPrecondition;
import main.java.xml.MyXmlCreator;
import main.java.xml.OControlRunner;
import main.java.xml.node.XmlGroup;
import main.java.xml.node.XmlRoot;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;


public class ActionsWithGroupOfNetworkTree {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private GroupOfNetworkTree group1;
    private GroupOfNetworkTree group11;

    private final String MAIN_GROUP_CAPTION = "New Group 1";
    private final String SUB_GROUP_CAPTION  = "New Group 1.1";

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

        this.group1 = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(MAIN_GROUP_CAPTION);

        this.group11 = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(SUB_GROUP_CAPTION);
    }

    private void addNodesByOcontrol() throws JAXBException, IOException, InterruptedException {
        ArrayList<Object> xmlGroups = new ArrayList<>();

        XmlGroup xmlGroup11 = new XmlGroup();
        xmlGroup11.setId(811);
        xmlGroup11.setCaption(SUB_GROUP_CAPTION);
        xmlGroups.add(xmlGroup11);

        XmlGroup xmlGroup1 = new XmlGroup();
        xmlGroup1.setId(81);
        xmlGroup1.setCaption(MAIN_GROUP_CAPTION);
        xmlGroup1.addSubitems(Collections.singletonList(
            xmlGroup11.getId()
        ));
        xmlGroups.add(xmlGroup1);

        XmlRoot xmlRoot = new XmlRoot();
        xmlRoot.addSubitems(Collections.singletonList(
            xmlGroup1.getId()
        ));
        xmlGroups.add(xmlRoot);

        MyXmlCreator xmlCreatorGroups = new MyXmlCreator(this.getClass());
        xmlCreatorGroups.marshall(xmlGroups);
        OControlRunner oControlRunnerGroups = new OControlRunner();
        oControlRunnerGroups.addUpdateXml(this.getClass(), xmlCreatorGroups.getXmls());
        oControlRunnerGroups.runFromCommandFile();
    }

    @Test()
    public void editGroupCaption(){

        // GIVEN
        final String NEW_GROUP_CAPTION = "New Caption Group 1";

        // WHEN
        this.networkTreeWidget.expandAllGroup();
        this.group1.editCaption(NEW_GROUP_CAPTION);

        // THEN
        this.networkTreeWidget.shoudBeParent(this.rootTree, this.group1);
    }

    @Test()
    public void selectDefaultCredentialForGroup(){

        // GIVEN

        // WHEN
        this.group1.setDefaultCredential();

        // THEN
        assertTrue(this.group1.isDefaultCredentialSelected());
    }

    @Test()
    public void addGroupToGroupWithoutCredential(){

        // GIVEN
        final String GROUP_CAPTION = "New Group 1.2";
        this.group1.setInheritCredential();

        // WHEN
        this.group1.addGroup(GROUP_CAPTION);

        // THEN
        final GroupOfNetworkTree group = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(GROUP_CAPTION);
        this.networkTreeWidget.shoudBeParent(this.group1, group);
    }

    @Test()
    public void addGroupToGroupWithDefaultCredential(){


        // GIVEN
        final String GROUP_CAPTION = "New Group 1.3";
        this.group1.setDefaultCredential();

        // WHEN
        this.group1.addGroup(GROUP_CAPTION);

        // THEN
        final GroupOfNetworkTree group = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(GROUP_CAPTION);
        this.networkTreeWidget.shoudBeParent(this.group1, group);
    }

    @Test()
    public void addDeviceToGroupWithoutCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 1.1",
            "NewDevice1.1");
        this.group1
            .setInheritCredential()

            // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.group1, device);
    }

    @Test()
    public void addDeviceToGroupWithDefaultCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 1.2",
            "NewDevice1.2");
        this.group1
            .setDefaultCredential()

            // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .expandAllGroup()
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.group1, device);
    }

    @Test()
    public void addSensorForGroupWithDefaultCredential(){

        // GIVEN
        final String SENSOR_NAME = "New Sensor 1";
        this.group1
            .setDefaultCredential()

            // WHEN
            .addDefaultSensor(SensorType.FTP, SENSOR_NAME);
        // THEN
        this.group1.shouldHasSensor(SENSOR_NAME);
    }

    @Test()
    public void addActionForGroupWithDefaultCredential(){

        // GIVEN
        final String ACTION_NAME = "New Action 1";
        this.group1
            .setDefaultCredential()

            // WHEN
            .addDefaultAction(ActionType.DELAY, ACTION_NAME);

        // THEN
        this.group1.shouldHasAction(ACTION_NAME);
    }

    @Test(priority = 1)
    public void editSubGroupCaption(){

        // GIVEN
        final String NEW_GROUP_CAPTION = "New Caption Group 1.1";

        // WHEN
        this.networkTreeWidget.expandAllGroup();
        this.group11.editCaption(NEW_GROUP_CAPTION);

        // THEN
        this.networkTreeWidget.shoudBeParent(this.group1, this.group11);
    }

    @Test()
    public void selectDefaultCredentialForSubGroup(){

        // GIVEN

        // WHEN
        this.group11.setDefaultCredential();

        // THEN
        assertTrue(this.group11.isDefaultCredentialSelected());
    }

    @Test()
    public void addDeviceToSubGroupWithoutCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 1.1.1",
            "NewDevice1.1.1");
        this.group11
            .setInheritCredential()

            // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .expandAllGroup()
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        device.select();
        this.networkTreeWidget.shoudBeParent(this.group11, device);
    }

    @Test()
    public void addDeviceToSubGroupWithDefaultCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 1.1.2",
            "NewDevice112");
        this.group11
            .setDefaultCredential()

            // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .expandAllGroup()
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        device.select();
        this.networkTreeWidget.shoudBeParent(this.group11, device);
    }

    @Test()
    public void addSensorForSubGroupWithoutCredential(){

        // GIVEN
        final String SENSOR_NAME = "New Sensor 2";
        this.group11
            .setInheritCredential()

            // WHEN
            .addDefaultSensor(SensorType.TCP, SENSOR_NAME);
        // THEN
        this.group11.shouldHasSensor(SENSOR_NAME);
    }

    @Test()
    public void addActionForSubGroupWithoutCredential(){

        // GIVEN
        final String ACTION_NAME = "New Action 2";
        this.group11
            .setInheritCredential()

            // WHEN
            .addDefaultAction(ActionType.DELAY, ACTION_NAME);

        // THEN
        this.group11.shouldHasAction(ACTION_NAME);
    }

    @Test(priority = 2)
    public void deleteGroup(){

        // GIVEN
        String idGroup = this.group1.getId();
        this.networkTreeWidget.expandAllGroup();

        // WHEN
        this.group1.deleteGroup();

        // THEN
        this.networkTreeWidget.getRootElement().$$(byId(idGroup)).shouldHave(CollectionCondition.empty);
    }
}
