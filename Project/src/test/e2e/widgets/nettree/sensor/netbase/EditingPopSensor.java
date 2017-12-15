package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.PopData;
import main.java.data.sensors.netbase.PopData.Builder;
import main.java.data.sensors.netbase.PopData.ComparisonOperator;
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
 * ? tests; time = ? sec
 */

public class EditingPopSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Pop sensor";
    private final PopData SENSOR_DATA = new PopData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.POP;

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
    public StreamEx<PopData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new PopData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(true, false)
                .flatMap(bool -> StreamEx.of(
                    new PopData(Builder.of().setSecureConnection(bool)),
                    new PopData(Builder.of().setAuthorizationRequired(bool)),
                    new PopData(Builder.of().setCheckMailDropSize(bool)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .map(port -> new PopData(Builder.of().setPort(port)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new PopData(Builder.of().setPortString(string)),
                        new PopData(Builder.of().setTimeoutString(string)),
                        new PopData(Builder.of().setMailDropSizeString(string)))
                    )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new PopData(Builder.of().setTimeout(num)))
        ).append(
                StreamEx.of(DataConstants.NO_REQ_POSITIVE_STRS)
                    .map(string -> new PopData(Builder.of().setUsername(string)))
        ).append(
                StreamEx.of(ComparisonOperator.values())
                    .map(operator -> new PopData(Builder.of().setOperator(operator)))
        ).append(
                StreamEx.of(DataConstants.FOUR_BYTE_NIL_POSITIVE_NUMS)
                    .map(num -> new PopData(Builder.of().setMailDropSize(num)))
        );
    }

    @DataSupplier
    public StreamEx<PopData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new PopData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .flatMap(num -> StreamEx.of(
                    new PopData(Builder.of().setPort(num)),
                    new PopData(Builder.of().setTimeout(num)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .map(port -> new PopData(Builder.of().setPort(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new PopData(Builder.of().setPortString(string)),
                    new PopData(Builder.of().setTimeoutString(string)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(port -> new PopData(Builder.of().setTimeout(port)))
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NIL_NEGATIVE_NUMS)
                .map(num -> new PopData(Builder.of().setMailDropSize(num)))
        ).append(
            StreamEx.of(DataConstants.NIL_NEGATIVE_NUM_STRS)
                .map(string -> new PopData(Builder.of().setMailDropSizeString(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditPop(final PopData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditPop(final PopData data){

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
