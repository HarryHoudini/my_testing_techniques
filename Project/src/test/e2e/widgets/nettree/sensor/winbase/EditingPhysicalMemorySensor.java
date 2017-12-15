package test.e2e.widgets.nettree.sensor.winbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.winbase.PhysicalMemoryData;
import main.java.data.sensors.winbase.WinbaseData.Builder;
import main.java.elements.widgets.tree.NetworkTreeFooter;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.widgets.tree.nodes.actioninstance.SensorOfNode;
import main.java.elements.wizards.sensor.SensorType;
import main.java.elements.wizards.sensor.config.ConfigSensorsWizard;
import main.java.utility.utils.config.TestPrecondition;
import one.util.streamex.StreamEx;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * 28 tests; time = 60 sec
 */

public class EditingPhysicalMemorySensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Physical Memory sensor";
    private final PhysicalMemoryData SENSOR_DATA = new PhysicalMemoryData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.PHYSICAL_MEMORY;

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        TestPrecondition.startOnNetworkTreePanel();
        networkTreeSetUp();
        TestPrecondition.setContext(context);
    }

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget.getFooter().setCheckbox(NetworkTreeFooter.NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.rootTree = networkTreeWidget.getRoot();
        this.rootTree
            .createNewCredential(CREDENTIAL)
            .addSensor(SENSOR_TYPE, SENSOR_DATA)
            .shouldHasSensor(SENSOR_CAPTION);
        this.sensorNode = this.rootTree.getSensor(SENSOR_CAPTION, SENSOR_TYPE);
    }

    @DataSupplier
    public StreamEx<PhysicalMemoryData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new PhysicalMemoryData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.PERCENT_POSITIVE_NUMS)
                .map(percent -> new PhysicalMemoryData(Builder.of().setExpectedValue(percent)))
        ).append(
            StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                .map(string -> new PhysicalMemoryData(Builder.of().setExpectedValueString(string)))
        ).append(
            StreamEx.of(PhysicalMemoryData.ConditionType.values())
                .map(operator -> new PhysicalMemoryData(Builder.of().setOperator(operator)))
        );
    }

    @DataSupplier
    public StreamEx<PhysicalMemoryData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new PhysicalMemoryData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.PERCENT_NEGATIVE_NUMS)
                .map(percent -> new PhysicalMemoryData(Builder.of().setExpectedValue(percent)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .map(string -> new PhysicalMemoryData(Builder.of().setExpectedValueString(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditPhysicalMemory(final PhysicalMemoryData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditPhysicalMemory(final PhysicalMemoryData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editWrongData(data);
        ConfigSensorsWizard wizard = this.sensorNode
            .getConfigWizards();

        // THEN
        wizard.shouldHasError();
        wizard.close();
    }


}
