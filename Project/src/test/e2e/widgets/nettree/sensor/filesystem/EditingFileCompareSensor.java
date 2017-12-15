package test.e2e.widgets.nettree.sensor.filesystem;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.filesystem.EqualsOperator;
import main.java.data.sensors.filesystem.FileCompareData;
import main.java.data.sensors.filesystem.FileCompareData.Builder;
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
 * 53 tests; time = 100 sec
 */

public class EditingFileCompareSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "File Compare sensor";
    private final FileCompareData SENSOR_DATA = new FileCompareData(Builder.of()
        .setCaption   (SENSOR_CAPTION)
        .setFirstfile ("C:\\folder\\file.ext")
        .setSecondfile("ADMIN$")
    );
    private final SensorType SENSOR_TYPE = SensorType.FILE_COMPARE;

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
    public StreamEx<FileCompareData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(EqualsOperator.values())
                .map(operator -> new FileCompareData(Builder.of().setCondition(operator)))
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new FileCompareData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.NOT_DOS_POSITIVE_PATHS)
                .flatMap(path ->
                    StreamEx.of(
                        new FileCompareData(Builder.of().setFirstfile(path)),
                        new FileCompareData(Builder.of().setSecondfile(path))
                    )
                )
        ).append(
            StreamEx.of(true, false)
                .map(bool -> new FileCompareData(Builder.of().setCheckContent(bool)))
        );
    }

    @DataSupplier
    public StreamEx<FileCompareData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new FileCompareData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.NOT_DOS_NEGATIVE_PATHS)
                .flatMap(path ->
                    StreamEx.of(
                        new FileCompareData(Builder.of().setFirstfile(path)),
                        new FileCompareData(Builder.of().setSecondfile(path))
                    )
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditFileCompare(final FileCompareData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditFileCompare(final FileCompareData data){

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
