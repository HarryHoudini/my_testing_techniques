package test.e2e.widgets.nettree;

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
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;


public class ActionsWithRootOfNetworkTree {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;

    @BeforeClass
    public void setUp(ITestContext context) {
        TestPrecondition.startOnNetworkTreePanel();
        TestPrecondition.setContext(context);
    }

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.networkTreeWidget.shouldBeVisible();
        this.rootTree = networkTreeWidget.getRoot();
    }

    @Test()
    public void editRootCaption() throws NoSuchElementException {

        // GIVEN
        final String NEW_ROOT_CAPTION = "New Caption";

        // WHEN
        this.rootTree.editCaption(NEW_ROOT_CAPTION);

        // THEN
        this.networkTreeWidget.getRoot().shouldHasCaption(NEW_ROOT_CAPTION);
    }

    @Test()
    public void selectDefaultCredentialForRoot(){

        // GIVEN

        // WHEN
        this.rootTree.setDefaultCredential();

        // THEN
        assertTrue(this.rootTree.isDefaultCredentialSelected());
    }

    @Test()
    public void addGroupToRootWithoutCredential(){

        // GIVEN
        final String GROUP_CAPTION = "New Group 1";
        this.rootTree.setInheritCredential();

        // WHEN
        this.rootTree.addGroup(GROUP_CAPTION);

        // THEN
        final GroupOfNetworkTree group = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(GROUP_CAPTION);
        this.networkTreeWidget.shoudBeParent(this.rootTree, group);
    }

    @Test()
    public void addGroupToRootWithDefaultCredential(){

        // GIVEN
        final String GROUP_CAPTION = "New Group 2";
        this.rootTree
            .setDefaultCredential()

        // WHEN
            .addGroup(GROUP_CAPTION);

        // THEN
        final GroupOfNetworkTree group = this.networkTreeWidget
            .expandAllGroup()
            .getGroup(GROUP_CAPTION);
        this.networkTreeWidget.shoudBeParent(this.rootTree, group);
    }

    @Test()
    public void addDeviceToRootWithoutCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 1",
                                                                  "NewDevice1");
        this.rootTree
            .setInheritCredential()

        // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        this.networkTreeWidget.shoudBeParent(this.rootTree, device);
    }


    @Test()
    public void addDeviceToRootWithDefaultCredential(){

        // GIVEN
        final DeviceAttributes DEVICE_ATTR = new DeviceAttributes("New Device 2",
            "NewDevice2");
        this.rootTree
            .setDefaultCredential()

            // WHEN
            .addNewDevice(DEVICE_ATTR);

        // THEN
        DeviceOfNetworkTree device = this.networkTreeWidget
            .setVisualMode(VisualMode.MAIN_CAPTION)
            .expandAllGroup()
            .getDeviceByMainField(DEVICE_ATTR.getDeviceCaption());
        device.select();
        this.networkTreeWidget.shoudBeParent(this.rootTree, device);
    }

    @Test()
    public void addSensorForRootWithoutCredential(){

        // GIVEN
        final String SENSOR_NAME = "New Sensor 1";
        this.rootTree
            .setInheritCredential()

        // WHEN
            .addDefaultSensor(SensorType.PING, SENSOR_NAME);
        // THEN
        this.rootTree.shouldHasSensor(SENSOR_NAME);
    }

    @Test()
    public void addActionForRootWithoutCredential(){

        // GIVEN
        final String ACTION_NAME = "New Action 1";
        this.rootTree
            .setInheritCredential()

        // WHEN
        .addDefaultAction(ActionType.DELAY, ACTION_NAME);

        // THEN
        this.rootTree.shouldHasAction(ACTION_NAME);
    }
}
