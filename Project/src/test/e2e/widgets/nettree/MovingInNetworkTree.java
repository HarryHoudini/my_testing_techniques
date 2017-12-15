package test.e2e.widgets.nettree;

import main.java.constants.DependingConstants;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
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
import java.util.Collections;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static main.java.elements.widgets.tree.VisualMode.MAIN_CAPTION;

/**
 * This class test moving in Network Tree
 * Tree of nodes should is empty before runFromCommandFile tests
 * After precondition Tree have to be:
 * Root
 *     Group 1
 *         Group 1.1
 *             Group 1.1.1
 *             Device 1.1.1
 *             Device 1.1.2
 *             Device 1.1.3
 *         Group 1.2
 *         Group 1.3
 *             Group 1.3.1
 *         Device 1.1
 *         Device 1.2
 *         Device 1.3
 *         Device 1.4
 *     Group 2
 *         Group 2.1
 *             Group 2.1.1
 *         Device 2.1
 *         Device 2.2
 *     Group 3
 *         Device 3.1
 *     Group 4
 *     Group 5
 *         Device 5.1
 *     Device 1
 *     Device 2
 *     Device 3
 *     Device 4
 * Estimated execution time - 131 seconds
 */

public class MovingInNetworkTree {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private GroupOfNetworkTree group1, group2, group3, group4, group5;
    private GroupOfNetworkTree group11, group12, group13, group21;
    private GroupOfNetworkTree group111, group131, group211;
    private DeviceOfNetworkTree device1, device2, device3, device4;
    private DeviceOfNetworkTree device11, device12, device13, device14, device21, device22, device31, device51;
    private DeviceOfNetworkTree device111, device112, device113;
    private final DeviceAttributes DEVICE_ATTR_1 = new DeviceAttributes("Move Test Device 1",
                                                                        "MoveTestDevice1");
    private final DeviceAttributes DEVICE_ATTR_2 = new DeviceAttributes("Move Test Device 2",
                                                                        "MoveTestDevice2");
    private final DeviceAttributes DEVICE_ATTR_3 = new DeviceAttributes("Move Test Device 3",
                                                                        "MoveTestDevice3");
    private final DeviceAttributes DEVICE_ATTR_4 = new DeviceAttributes("Move Test Device 4",
                                                                        "MoveTestDevice4");
    private final DeviceAttributes DEVICE_ATTR_11 = new DeviceAttributes("Move Test Device 1.1",
                                                                         "MoveTestDevice1.1");
    private final DeviceAttributes DEVICE_ATTR_12 = new DeviceAttributes("Move Test Device 1.2",
                                                                        "MoveTestDevice1.2");
    private final DeviceAttributes DEVICE_ATTR_13 = new DeviceAttributes("Move Test Device 1.3",
                                                                        "MoveTestDevice1.3");
    private final DeviceAttributes DEVICE_ATTR_14 = new DeviceAttributes("Move Test Device 1.4",
                                                                        "MoveTestDevice1.4");
    private final DeviceAttributes DEVICE_ATTR_21 = new DeviceAttributes("Move Test Device 2.1",
                                                                        "MoveTestDevice2.1");

    private final DeviceAttributes DEVICE_ATTR_22 = new DeviceAttributes("Move Test Device 2.2",
                                                                         "MoveTestDevice2.2");
    private final DeviceAttributes DEVICE_ATTR_31 = new DeviceAttributes("Move Test Device 3.1",
                                                                         "MoveTestDevice3.1");
    private final DeviceAttributes DEVICE_ATTR_51 = new DeviceAttributes("Move Test Device 5.1",
                                                                         "MoveTestDevice5.1");
    private final DeviceAttributes DEVICE_ATTR_111 = new DeviceAttributes("Move Test Device 1.1.1",
                                                                         "MoveTestDevice1.1.1");
    private final DeviceAttributes DEVICE_ATTR_112 = new DeviceAttributes("Move Test Device 1.1.2",
                                                                         "MoveTestDevice1.1.2");
    private final DeviceAttributes DEVICE_ATTR_113 = new DeviceAttributes("Move Test Device 1.1.3",
                                                                         "MoveTestDevice1.1.3");

    private final String GROUP_CAPTION_1 = "Move Test Group 1";
    private final String GROUP_CAPTION_2 = "Move Test Group 2";
    private final String GROUP_CAPTION_3 = "Move Test Group 3";
    private final String GROUP_CAPTION_4 = "Move Test Group 4";
    private final String GROUP_CAPTION_5 = "Move Test Group 5";
    private final String GROUP_CAPTION_11 = "Move Test Group 1.1";
    private final String GROUP_CAPTION_12 = "Move Test Group 1.2";
    private final String GROUP_CAPTION_13 = "Move Test Group 1.3";
    private final String GROUP_CAPTION_21 = "Move Test Group 2.1";
    private final String GROUP_CAPTION_111 = "Move Test Group 1.1.1";
    private final String GROUP_CAPTION_211 = "Move Test Group 2.1.1";
    private final String GROUP_CAPTION_131 = "Move Test Group 1.3.1";


    private void addNodesByOcontrol() throws JAXBException, IOException, InterruptedException {
        ArrayList<Object> xmlDevices = new ArrayList<>();
        ArrayList<Object> xmlGroups = new ArrayList<>();

        XmlDevice xmlDevice1 = new XmlDevice();
        xmlDevice1.setAllAttribures(this.DEVICE_ATTR_1);
        xmlDevice1.setId(71);
        xmlDevices.add(xmlDevice1);

        XmlDevice xmlDevice2 = new XmlDevice();
        xmlDevice2.setAllAttribures(this.DEVICE_ATTR_2);
        xmlDevice2.setId(72);
        xmlDevices.add(xmlDevice2);

        XmlDevice xmlDevice3 = new XmlDevice();
        xmlDevice3.setAllAttribures(this.DEVICE_ATTR_3);
        xmlDevice3.setId(73);
        xmlDevices.add(xmlDevice3);

        XmlDevice xmlDevice4 = new XmlDevice();
        xmlDevice4.setAllAttribures(this.DEVICE_ATTR_4);
        xmlDevice4.setId(74);
        xmlDevices.add(xmlDevice4);

        XmlDevice xmlDevice11 = new XmlDevice();
        xmlDevice11.setAllAttribures(this.DEVICE_ATTR_11);
        xmlDevice11.setId(711);
        xmlDevices.add(xmlDevice11);

        XmlDevice xmlDevice12 = new XmlDevice();
        xmlDevice12.setAllAttribures(this.DEVICE_ATTR_12);
        xmlDevice12.setId(712);
        xmlDevices.add(xmlDevice12);

        XmlDevice xmlDevice13 = new XmlDevice();
        xmlDevice13.setAllAttribures(this.DEVICE_ATTR_13);
        xmlDevice13.setId(713);
        xmlDevices.add(xmlDevice13);

        XmlDevice xmlDevice14 = new XmlDevice();
        xmlDevice14.setAllAttribures(this.DEVICE_ATTR_14);
        xmlDevice14.setId(714);
        xmlDevices.add(xmlDevice14);

        XmlDevice xmlDevice21 = new XmlDevice();
        xmlDevice21.setAllAttribures(this.DEVICE_ATTR_21);
        xmlDevice21.setId(721);
        xmlDevices.add(xmlDevice21);

        XmlDevice xmlDevice22 = new XmlDevice();
        xmlDevice22.setAllAttribures(this.DEVICE_ATTR_22);
        xmlDevice22.setId(722);
        xmlDevices.add(xmlDevice22);

        XmlDevice xmlDevice31 = new XmlDevice();
        xmlDevice31.setAllAttribures(this.DEVICE_ATTR_31);
        xmlDevice31.setId(731);
        xmlDevices.add(xmlDevice31);

        XmlDevice xmlDevice51 = new XmlDevice();
        xmlDevice51.setAllAttribures(this.DEVICE_ATTR_51);
        xmlDevice51.setId(751);
        xmlDevices.add(xmlDevice51);

        XmlDevice xmlDevice111 = new XmlDevice();
        xmlDevice111.setAllAttribures(this.DEVICE_ATTR_111);
        xmlDevice111.setId(7111);
        xmlDevices.add(xmlDevice111);

        XmlDevice xmlDevice112 = new XmlDevice();
        xmlDevice112.setAllAttribures(this.DEVICE_ATTR_112);
        xmlDevice112.setId(7112);
        xmlDevices.add(xmlDevice112);

        MyXmlCreator xmlCreatorDevices = new MyXmlCreator(this.getClass());
        xmlCreatorDevices.marshall(xmlDevices);
        OControlRunner oControlRunnerDevices = new OControlRunner();
        oControlRunnerDevices.addUpdateXml(this.getClass(), xmlCreatorDevices.getXmls());
        oControlRunnerDevices.runFromCommandFile();

        XmlDevice xmlDevice113 = new XmlDevice();
        xmlDevice113.setAllAttribures(this.DEVICE_ATTR_113);
        xmlDevice113.setId(7113);
        xmlGroups.add(xmlDevice113);

        XmlGroup xmlGroup111 = new XmlGroup();
        xmlGroup111.setId(8111);
        xmlGroup111.setCaption(this.GROUP_CAPTION_111);
        xmlGroups.add(xmlGroup111);

        XmlGroup xmlGroup211 = new XmlGroup();
        xmlGroup211.setId(8211);
        xmlGroup211.setCaption(this.GROUP_CAPTION_211);
        xmlGroups.add(xmlGroup211);

        XmlGroup xmlGroup131 = new XmlGroup();
        xmlGroup131.setId(8131);
        xmlGroup131.setCaption(this.GROUP_CAPTION_131);
        xmlGroups.add(xmlGroup131);

        XmlGroup xmlGroup11 = new XmlGroup();
        xmlGroup11.setId(811);
        xmlGroup11.setCaption(this.GROUP_CAPTION_11);
        xmlGroup11.addSubitems(Arrays.asList(
            xmlDevice111.getId(),
            xmlDevice112.getId(),
            xmlDevice113.getId(),
            xmlGroup111.getId()
        ));
        xmlGroups.add(xmlGroup11);

        XmlGroup xmlGroup12 = new XmlGroup();
        xmlGroup12.setId(812);
        xmlGroup12.setCaption(this.GROUP_CAPTION_12);
        xmlGroups.add(xmlGroup12);

        XmlGroup xmlGroup13 = new XmlGroup();
        xmlGroup13.setId(813);
        xmlGroup13.setCaption(this.GROUP_CAPTION_13);
        xmlGroup13.addSubitems(Collections.singletonList(
            xmlGroup131.getId()
        ));
        xmlGroups.add(xmlGroup13);

        XmlGroup xmlGroup21 = new XmlGroup();
        xmlGroup21.setId(821);
        xmlGroup21.setCaption(this.GROUP_CAPTION_21);
        xmlGroup21.addSubitems(Collections.singletonList(
            xmlGroup211.getId()
        ));
        xmlGroups.add(xmlGroup21);

        XmlGroup xmlGroup1 = new XmlGroup();
        xmlGroup1.setId(81);
        xmlGroup1.setCaption(this.GROUP_CAPTION_1);
        xmlGroup1.addSubitems(Arrays.asList(
            xmlDevice11.getId(),
            xmlDevice12.getId(),
            xmlDevice13.getId(),
            xmlDevice14.getId(),
            xmlGroup11.getId(),
            xmlGroup12.getId(),
            xmlGroup13.getId()
        ));
        xmlGroups.add(xmlGroup1);

        XmlGroup xmlGroup2 = new XmlGroup();
        xmlGroup2.setId(82);
        xmlGroup2.setCaption(this.GROUP_CAPTION_2);
        xmlGroup2.addSubitems(Arrays.asList(
            xmlDevice21.getId(),
            xmlDevice22.getId(),
            xmlGroup21.getId()
        ));
        xmlGroups.add(xmlGroup2);

        XmlGroup xmlGroup3 = new XmlGroup();
        xmlGroup3.setId(83);
        xmlGroup3.setCaption(this.GROUP_CAPTION_3);
        xmlGroup3.addSubitems(Collections.singletonList(
            xmlDevice31.getId()
        ));
        xmlGroups.add(xmlGroup3);

        XmlGroup xmlGroup4 = new XmlGroup();
        xmlGroup4.setId(84);
        xmlGroup4.setCaption(this.GROUP_CAPTION_4);
        xmlGroups.add(xmlGroup4);

        XmlGroup xmlGroup5 = new XmlGroup();
        xmlGroup5.setId(85);
        xmlGroup5.setCaption(this.GROUP_CAPTION_5);
        xmlGroup5.addSubitems(Collections.singletonList(
            xmlDevice51.getId()
        ));
        xmlGroups.add(xmlGroup5);

        XmlRoot xmlRoot = new XmlRoot();
        xmlRoot.addSubitems(Arrays.asList(
            xmlDevice1.getId(),
            xmlDevice2.getId(),
            xmlDevice3.getId(),
            xmlDevice4.getId(),
            xmlGroup1.getId(),
            xmlGroup2.getId(),
            xmlGroup3.getId(),
            xmlGroup4.getId(),
            xmlGroup5.getId()
        ));
        xmlGroups.add(xmlRoot);

        MyXmlCreator xmlCreatorGroups = new MyXmlCreator(this.getClass());
        xmlCreatorGroups.marshall(xmlGroups);
        OControlRunner oControlRunnerGroups = new OControlRunner();
        oControlRunnerGroups.addUpdateXml(this.getClass(), xmlCreatorGroups.getXmls());
        oControlRunnerGroups.runFromCommandFile();
    }

    private void initNodes() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.networkTreeWidget.shouldBeVisible();
        this.rootTree = this.networkTreeWidget.getRoot();

        this.networkTreeWidget
            .setVisualMode(MAIN_CAPTION)
            .expandAllGroup();

        this.group1 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_1);
        this.group2 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_2);
        this.group3 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_3);
        this.group4 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_4);
        this.group5 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_5);
        this.group11 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_11);
        this.group12 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_12);
        this.group13 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_13);
        this.group21 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_21);
        this.group111 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_111);
        this.group131 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_131);
        this.group211 = this.networkTreeWidget.getGroup(this.GROUP_CAPTION_211);

        this.device1 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_1.getDeviceCaption());
        this.device2 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_2.getDeviceCaption());
        this.device3 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_3.getDeviceCaption());
        this.device4 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_4.getDeviceCaption());
        this.device11 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_11.getDeviceCaption());
        this.device12 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_12.getDeviceCaption());
        this.device13 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_13.getDeviceCaption());
        this.device14 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_14.getDeviceCaption());
        this.device21 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_21.getDeviceCaption());
        this.device22 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_22.getDeviceCaption());
        this.device31 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_31.getDeviceCaption());
        this.device51 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_51.getDeviceCaption());
        this.device111 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_111.getDeviceCaption());
        this.device112 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_112.getDeviceCaption());
        this.device113 = this.networkTreeWidget.getDeviceByMainField(this.DEVICE_ATTR_113.getDeviceCaption());

    }

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        TestPrecondition.startOnNetworkTreePanel();

        addNodesByOcontrol();
        initNodes();
        TestPrecondition.setContext(context);
    }

    @Test (groups = "moving")
    public void moveDeviceFromRootToGroup() throws IOException {

        // GIVEN

        // WHEN
        this.networkTreeWidget
            .moveTo(this.device1, this.group1)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group1.renew(), this.device1.renew());
    }

    @Test (groups = "moving")
    public void moveDeviceFromGroupToRoot(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(this.device11, this.rootTree)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.rootTree, this.device11.renew());

    }

    @Test (groups = "moving")
    public void moveDeviceFrom2DepthTo3Depth(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(this.device21, this.group11)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group11.renew(), this.device21.renew());
    }

    @Test (groups = "moving")
    public void moveDeviceFrom3DepthTo2Depth(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(this.device111, this.group2)
            .expandAllGroup()

            // THEN
            .shoudBeParent(this.group2.renew(), this.device111.renew());
    }

    @Test (groups = "moving")
    public void moveSiblingsDevices(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(Arrays.asList(this.device2, this.device3), this.group11)
            .expandAllGroup()

            // THEN
            .shoudBeParent(this.group11.renew(), this.device2.renew())
            .shoudBeParent(this.group11.renew(), this.device3.renew());
    }

    @Test (groups = "moving")
    public void moveDevicesDifferentDepths(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(Arrays.asList(this.device12, this.device112), this.group2)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group2.renew(), this.device12.renew())
            .shoudBeParent(this.group2.renew(), this.device112.renew());
    }

    @Test (groups = "moving")
    public void moveEmptyGroup(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(this.group111, this.rootTree)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.rootTree, this.group111.renew());
    }

    @Test (groups = "moving")
    public void moveNoEmptyGroup(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(this.group3, this.group2)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group2.renew(), this.group3.renew())
            .shoudBeParent(this.group3.renew(), this.device31.renew());
    }

    @Test (groups = "moving")
    public void moveSiblingsDeviceAndGroup(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(Arrays.asList(this.group12, this.device13), this.group11)
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group11.renew(), this.group12.renew())
            .shoudBeParent(this.group11.renew(), this.device13.renew());
    }

    @Test (groups = "moving")
    public void moveDeviceAndGroupDifferentDepths(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(Arrays.asList(this.group4, this.device113), this.group1)
            .expandAllGroup()

            // THEN
            .shoudBeParent(this.group1.renew(), this.group4.renew())
            .shoudBeParent(this.group1.renew(), this.device113.renew());
    }

    @Test (dependsOnGroups = "moving")
    public void moveComplexStructure(){

        // GIVEN

        // WHEN
        networkTreeWidget
            .moveTo(Arrays.asList(
                this.device4, this.device14, this.group13, this.group211, this.device22, this.group5
                ),
                this.group11
            )
            .expandAllGroup()

        // THEN
            .shoudBeParent(this.group11.renew(), this.group5.renew())
            .shoudBeParent(this.group11.renew(), this.group13.renew())
            .shoudBeParent(this.group11.renew(), this.group211.renew())
            .shoudBeParent(this.group11.renew(), this.device14.renew())
            .shoudBeParent(this.group11.renew(), this.device22.renew())
            .shoudBeParent(this.group13.renew(), this.group131.renew())
            .shoudBeParent(this.group5.renew(), this.device51.renew());
    }
}
