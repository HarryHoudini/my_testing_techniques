package test.e2e.widgets.nettree.sensor.system;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.system.ServiceStatusData;
import main.java.data.sensors.system.ServiceStatusData.Builder;
import main.java.data.sensors.system.ServiceStatusData.ServiceStatus;
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
 * 21 tests; time = 47 sec
 */

public class EditingServiceStatusSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Service Status sensor";
    private final ServiceStatusData SENSOR_DATA = new ServiceStatusData(Builder.of()
        .setCaption    (SENSOR_CAPTION)
        .setServiceName(DefaultValueSensorsConstant.SERVICE_NAME_CONST)
    );
    private final SensorType SENSOR_TYPE = SensorType.SYSTEM_SERVICE_STATUS;

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
    public StreamEx<ServiceStatusData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(ServiceStatus.values())
                .map(status -> new ServiceStatusData(Builder.of().setStatus(status)))
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .flatMap(string ->
                    StreamEx.of(
                        new ServiceStatusData(Builder.of().setCaption(string)),
                        new ServiceStatusData(Builder.of().setServiceName(string))
                    )
                )
        );
    }

    @DataSupplier
    public StreamEx<ServiceStatusData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .flatMap(string ->
                    StreamEx.of(
                        new ServiceStatusData(Builder.of().setCaption(string)),
                        new ServiceStatusData(Builder.of().setServiceName(string))
                    )
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditServiceStatus(final ServiceStatusData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditServiceStatus(final ServiceStatusData data){

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
