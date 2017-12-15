package test.e2e.widgets.nettree.sensor.filesystem;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.sensors.filesystem.FileSizeData;
import main.java.data.sensors.filesystem.FileSizeData.Builder;
import main.java.data.sensors.filesystem.SizeCondition;
import main.java.data.sensors.filesystem.UnitSize;
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
 * 47 tests; time = 87 sec
 */

public class EditingFileSizeSensor {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private SensorOfNode      sensorNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String SENSOR_CAPTION = "File Size sensor";
    private final FileSizeData SENSOR_DATA = new FileSizeData(Builder.of()
        .setCaption   (SENSOR_CAPTION)
        .setPath (DefaultValueSensorsConstant.FIRST_FILE_PATH_CONST)
    );
    private final SensorType SENSOR_TYPE = SensorType.FILE_SIZE;

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
    public StreamEx<FileSizeData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new FileSizeData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.NOT_DOS_POSITIVE_PATHS)
                .map(path -> new FileSizeData(Builder.of().setPath(path)))
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new FileSizeData(Builder.of().setSizeLong(num)))
        ).append(
            StreamEx.of(DataConstants.EIGHT_BYTE_POSITIVE_STRS)
                .map(string -> new FileSizeData(Builder.of().setSizeString(string)))
        ).append(
            StreamEx.of(SizeCondition.values())
                .map(condition -> new FileSizeData(Builder.of().setCondition(condition)))
        ).append(
            StreamEx.of(UnitSize.values())
                .map(unit -> new FileSizeData(Builder.of().setUnitOfMeasure(unit)))
        );
    }

    @DataSupplier
    public StreamEx<FileSizeData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new FileSizeData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.NOT_DOS_NEGATIVE_PATHS)
                .map(path -> new FileSizeData(Builder.of().setPath(path)))
        ).append(
            StreamEx.of(DataConstants.EIGHT_BYTE_NEGATIVE_NUMS)
                .map(num -> new FileSizeData(Builder.of().setSizeLong(num)))
        ).append(
            StreamEx.of(DataConstants.EIGHT_BYTE_NEGATIVE_STRS)
                .map(string -> new FileSizeData(Builder.of().setSizeString(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditFolderSize(final FileSizeData data){

        // GIVEN

        // WHEN
        this.sensorNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditFolderSize(final FileSizeData data){

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
