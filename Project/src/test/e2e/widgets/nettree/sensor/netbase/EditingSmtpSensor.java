package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.SmtpData;
import main.java.data.sensors.netbase.SmtpData.Builder;
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

public class EditingSmtpSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Smtp sensor";
    private final SmtpData SENSOR_DATA = new SmtpData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.SMTP;

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
    public StreamEx<SmtpData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new SmtpData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(true, false)
                .flatMap(bool -> StreamEx.of(
                    new SmtpData(Builder.of().setSecureConnection(bool)),
                    new SmtpData(Builder.of().setAuthorizationRequired(bool)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .map(port -> new SmtpData(Builder.of().setPort(port)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new SmtpData(Builder.of().setPortString(string)),
                        new SmtpData(Builder.of().setTimeoutString(string)))
                    )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new SmtpData(Builder.of().setTimeout(num)))
        ).append(
                StreamEx.of(DataConstants.NO_REQ_POSITIVE_STRS)
                    .map(string -> new SmtpData(Builder.of().setUsername(string)))
        );
    }

    @DataSupplier
    public StreamEx<SmtpData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new SmtpData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .map(port -> new SmtpData(Builder.of().setPort(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new SmtpData(Builder.of().setPortString(string)),
                    new SmtpData(Builder.of().setTimeoutString(string)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(port -> new SmtpData(Builder.of().setTimeout(port)))
        ).append(
            StreamEx.of(DataConstants.NO_REQ_NEGATIVE_STRS)
                .flatMap(string -> StreamEx.of(
                    new SmtpData(Builder.of().setUsername(string)),
                    new SmtpData(Builder.of().setPassword(string)))
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditSmtp(final SmtpData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditSmtp(final SmtpData data){

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
