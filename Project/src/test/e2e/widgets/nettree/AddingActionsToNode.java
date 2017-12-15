package test.e2e.widgets.nettree;

import main.java.constants.DependingConstants;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.VisualMode;
import main.java.elements.widgets.tree.nodes.DeviceOfNetworkTree;
import main.java.elements.widgets.tree.nodes.GroupOfNetworkTree;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.wizards.action.ActionType;
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


public class AddingActionsToNode {
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
    public void addCancelRestartAction() {

        // GIVEN
        final String NAME_ACTION = "Cancel Restart Action";

        // WHEN
        this.rootTree.addDefaultAction(ActionType.CANCEL_RESTART, NAME_ACTION);

        // THEN
        this.rootTree.shouldHasAction(NAME_ACTION);
    }

    @Test()
    public void addDelayAction() {

        // GIVEN
        final String NAME_ACTION = "Delay Action";

        // WHEN
        this.group.addDefaultAction(ActionType.DELAY, NAME_ACTION);

        // THEN
        this.group.shouldHasAction(NAME_ACTION);
    }

    @Test()
    public void addExecuteScriptAction() {

        // GIVEN
        final String NAME_ACTION = "Execute Script Action";

        // WHEN
        this.device.addDefaultAction(ActionType.EXECUTE_SCRIPT, NAME_ACTION);

        // THEN
        this.device.shouldHasAction(NAME_ACTION);
    }

    @Test()
    public void addRestartComputerAction() {

        // GIVEN
        final String NAME_ACTION = "Restart Computer Action";

        // WHEN
        this.rootTree.addDefaultAction(ActionType.RESTART_COMPUTER, NAME_ACTION);

        // THEN
        this.rootTree.shouldHasAction(NAME_ACTION);
    }

    @Test()
    public void addRunAppAction() {

        // GIVEN
        final String NAME_ACTION = "Run App Action";

        // WHEN
        this.group.addDefaultAction(ActionType.RUN_APP, NAME_ACTION);

        // THEN
        this.group.shouldHasAction(NAME_ACTION);
    }

    @Test()
    public void addServiceControlAction() {

        // GIVEN
        final String NAME_ACTION = "Service Control Action";

        // WHEN
        this.device.addDefaultAction(ActionType.SERVICE_CONTROL, NAME_ACTION);

        // THEN
        this.device.shouldHasAction(NAME_ACTION);
    }


}
