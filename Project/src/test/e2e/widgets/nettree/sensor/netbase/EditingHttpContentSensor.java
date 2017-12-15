package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.HttpContentData;
import main.java.data.sensors.netbase.HttpContentData.Builder;
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
 * 70 tests; time = 135 sec
 */

public class EditingHttpContentSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Http Content sensor";
    private final HttpContentData SENSOR_DATA = new HttpContentData(Builder.of()
        .setCaption(SENSOR_CAPTION)
        .setUrlPath(DefaultValueSensorsConstant.URL_PATH_CONST)
    );
    private final SensorType SENSOR_TYPE = SensorType.HTTP_CONTENT;

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
    public StreamEx<HttpContentData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new HttpContentData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(true, false)
                .flatMap(bool -> StreamEx.of(
                    new HttpContentData(Builder.of().setSecureConnection(bool)),
                    new HttpContentData(Builder.of().setConditional(bool)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .map(port -> new HttpContentData(Builder.of().setPort(port)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new HttpContentData(Builder.of().setPortString(string)),
                        new HttpContentData(Builder.of().setTimeoutString(string)))
                    )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new HttpContentData(Builder.of().setTimeout(num)))
        ).append(
            StreamEx.of(DataConstants.POSITIVE_URLS)
                .map(url -> new HttpContentData(Builder.of().setUrlPath(url)))
        ).append(
            StreamEx.of(DataConstants.NO_REQ_512_POSITIVE_STRS)
                .map(string -> new HttpContentData(Builder.of().setTargetValue(string)))
        );
    }

    @DataSupplier
    public StreamEx<HttpContentData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new HttpContentData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .map(port -> new HttpContentData(Builder.of().setPort(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new HttpContentData(Builder.of().setPortString(string)),
                    new HttpContentData(Builder.of().setTimeoutString(string)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(port -> new HttpContentData(Builder.of().setTimeout(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_URLS)
                .map(url -> new HttpContentData(Builder.of().setUrlPath(url)))
        ).append(
            StreamEx.of(DataConstants.NO_REQ_512_NEGATIVE_STRS)
                .map(string -> new HttpContentData(Builder.of().setTargetValue(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditHttpContent(final HttpContentData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditHttpContent(final HttpContentData data){

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
