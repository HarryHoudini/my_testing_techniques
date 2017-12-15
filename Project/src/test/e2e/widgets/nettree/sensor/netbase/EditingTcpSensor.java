package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.TcpData;
import main.java.data.sensors.netbase.TcpData.Builder;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
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
 * 37 tests; time = 55 sec
 */

public class EditingTcpSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Tcp sensor";
    private final TcpData SENSOR_DATA = new TcpData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.TCP;

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
    public StreamEx<TcpData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new TcpData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .map(port -> new TcpData(Builder.of().setPort(port)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new TcpData(Builder.of().setPortString(string)),
                        new TcpData(Builder.of().setTimeoutString(string)))
                    )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new TcpData(Builder.of().setTimeout(num)))
        );
    }

    @DataSupplier
    public StreamEx<TcpData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new TcpData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .map(port -> new TcpData(Builder.of().setPort(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new TcpData(Builder.of().setPortString(string)),
                    new TcpData(Builder.of().setTimeoutString(string)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(port -> new TcpData(Builder.of().setTimeout(port)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditTcp(final TcpData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditTcp(final TcpData data){

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
