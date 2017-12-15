package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.PingData;
import main.java.data.sensors.netbase.PingData.Builder;
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
 * 65 tests; time = 90 sec
 */

public class EditingPingSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Ping sensor";
    private final PingData SENSOR_DATA = new PingData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.PING;

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
    public StreamEx<PingData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new PingData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .flatMap(num -> StreamEx.of(
                    new PingData(Builder.of().setPacketCount(num)),
                    new PingData(Builder.of().setPacketSize(num)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new PingData(Builder.of().setTimeout(num)))
        ).append(
            StreamEx.of(DataConstants.BYTE_POSITIVE_NUMS)
                .map(ttl -> new PingData(Builder.of().setTtl(ttl)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new PingData(Builder.of().setPacketCountString(string)),
                        new PingData(Builder.of().setPacketSizeString(string)),
                        new PingData(Builder.of().setTimeoutString(string)),
                        new PingData(Builder.of().setTtlString(string)))
                    )
        );
    }

    @DataSupplier
    public StreamEx<PingData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new PingData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .flatMap(num -> StreamEx.of(
                    new PingData(Builder.of().setPacketCount(num)),
                    new PingData(Builder.of().setPacketSize(num)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(num -> new PingData(Builder.of().setTimeout(num)))
        ).append(
            StreamEx.of(DataConstants.BYTE_NEGATIVE_NUMS)
                .map(ttl -> new PingData(Builder.of().setTtl(ttl)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new PingData(Builder.of().setPacketCountString(string)),
                    new PingData(Builder.of().setPacketSizeString(string)),
                    new PingData(Builder.of().setTimeoutString(string)),
                    new PingData(Builder.of().setTtlString(string)))
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditPing(final PingData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditPing(final PingData data){

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
