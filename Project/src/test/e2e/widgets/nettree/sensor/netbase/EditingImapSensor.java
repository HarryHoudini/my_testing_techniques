package test.e2e.widgets.nettree.sensor.netbase;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.netbase.ImapData;
import main.java.data.sensors.netbase.ImapData.Builder;
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
 * 50 tests; time = 90 sec
 */

public class EditingImapSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "Imap sensor";
    private final ImapData SENSOR_DATA = new ImapData(Builder.of()
        .setCaption(SENSOR_CAPTION)
    );
    private final SensorType SENSOR_TYPE = SensorType.IMAP;

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
    public StreamEx<ImapData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .flatMap(string -> StreamEx.of(
                    new ImapData(Builder.of().setCaption(string)),
                    new ImapData(Builder.of().setMailbox(string)))
                )
        ).append(
            StreamEx.of(true, false)
                .flatMap(bool -> StreamEx.of(
                    new ImapData(Builder.of().setSecureConnection(bool)),
                    new ImapData(Builder.of().setAuthorizationRequired(bool)),
                    new ImapData(Builder.of().setNeedSearch(bool)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_POSITIVE_NUMS)
                .map(port -> new ImapData(Builder.of().setPort(port)))
        ).append(
                StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                    .flatMap(string -> StreamEx.of(
                        new ImapData(Builder.of().setPortString(string)),
                        new ImapData(Builder.of().setTimeoutString(string)))
                    )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new ImapData(Builder.of().setTimeout(num)))
        ).append(
                StreamEx.of(DataConstants.NO_REQ_POSITIVE_STRS)
                    .flatMap(string -> StreamEx.of(
                        new ImapData(Builder.of().setUsername(string)),
                        new ImapData(Builder.of().setSearchParameter(string)))
                    )
        );
    }

    @DataSupplier
    public StreamEx<ImapData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .flatMap(string -> StreamEx.of(
                    new ImapData(Builder.of().setCaption(string)),
                    new ImapData(Builder.of().setMailbox(string)))
                )
        ).append(
            StreamEx.of(DataConstants.TWO_BYTE_NEGATIVE_NUMS)
                .map(port -> new ImapData(Builder.of().setPort(port)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .flatMap(string -> StreamEx.of(
                    new ImapData(Builder.of().setPortString(string)),
                    new ImapData(Builder.of().setTimeoutString(string)))
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(port -> new ImapData(Builder.of().setTimeout(port)))
        ).append(
            StreamEx.of(DataConstants.NO_REQ_NEGATIVE_STRS)
                .flatMap(string -> StreamEx.of(
                    new ImapData(Builder.of().setUsername(string)),
                    new ImapData(Builder.of().setPassword(string)),
                    new ImapData(Builder.of().setSearchParameter(string)))
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditImap(final ImapData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditImap(final ImapData data){

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
