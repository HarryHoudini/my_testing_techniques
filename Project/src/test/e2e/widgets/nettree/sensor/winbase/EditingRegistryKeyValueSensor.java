package test.e2e.widgets.nettree.sensor.winbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.winbase.RegistryKeyValueData;
import main.java.data.sensors.winbase.RegistryKeyValueData.Builder;
import main.java.data.sensors.winbase.RegistryKeyValueData.ConditionType;
import main.java.data.sensors.winbase.RegistryKeyValueData.ValueType;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.widgets.tree.nodes.actioninstance.SensorOfNode;
import main.java.elements.wizards.sensor.DefaultValueSensorsConstant;
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
 * 51 tests; time = 140 sec
 */

public class EditingRegistryKeyValueSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Registry Key Value sensor";
    private final RegistryKeyValueData SENSOR_DATA = new RegistryKeyValueData(Builder.of()
        .setCaption   (SENSOR_CAPTION)
        .setKey(DefaultValueSensorsConstant.REGISTRY_KEY_CONST)
    );
    private final SensorType SENSOR_TYPE = SensorType.REGISTRY_KEY;

    @BeforeClass
    public void setUp(ITestContext context) throws InterruptedException, JAXBException, IOException {
        TestPrecondition.startOnNetworkTreePanel();
        networkTreeSetUp();
        TestPrecondition.setContext(context);
    }

    private void networkTreeSetUp() {
        this.networkTreeWidget = new NetworkTreeWidget();
        this.networkTreeWidget
            .getFooter()
            .setCheckbox(NetworkTreeFooterCheckbox.UNDEFINED, true);
        this.rootTree = networkTreeWidget.getRoot();
        this.rootTree
            .createNewCredential(CREDENTIAL)
            .addSensor(SENSOR_TYPE, SENSOR_DATA)
            .shouldHasSensor(SENSOR_CAPTION);
        this.sensorNode = this.rootTree.getSensor(SENSOR_CAPTION, SENSOR_TYPE);
    }

    @DataSupplier
    public StreamEx<RegistryKeyValueData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new RegistryKeyValueData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(true, false)
                .map(bool -> new RegistryKeyValueData(Builder.of().setValueContent(bool)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_KEY_POSITIVE_STRS)
                .map(key -> new RegistryKeyValueData(Builder.of().setKey(key)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_VALUE_NAME_POSITIVE_STRS)
                .map(name -> new RegistryKeyValueData(Builder.of().setValueName(name)))
        ).append(
            StreamEx.of(ValueType.values())
                .map(type -> new RegistryKeyValueData(Builder.of().setExpextedType(type)))
        ).append(
            StreamEx.of(ConditionType.values())
                .map(condition -> new RegistryKeyValueData(Builder.of().setCondition(condition)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_VALUE_POSITIVE_STRS)
                .map(value -> new RegistryKeyValueData(Builder.of().setExpectedValue(value)))
        );
    }

    @DataSupplier
    public StreamEx<RegistryKeyValueData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new RegistryKeyValueData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_KEY_NEGATIVE_STRS)
                .map(key -> new RegistryKeyValueData(Builder.of().setKey(key)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_VALUE_NAME_NEGATIVE_STRS)
                .map(name -> new RegistryKeyValueData(Builder.of().setValueName(name)))
        ).append(
            StreamEx.of(DataConstants.REGISTRY_VALUE_NEGATIVE_STRS)
                .map(value -> new RegistryKeyValueData(Builder.of().setExpectedValue(value)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditRegistryKeyValue(final RegistryKeyValueData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditRegistryKeyValue(final RegistryKeyValueData data){

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
