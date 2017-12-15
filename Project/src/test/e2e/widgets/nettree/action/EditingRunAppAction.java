package test.e2e.widgets.nettree.action;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.actions.RunApplicationData;
import main.java.data.actions.RunApplicationData.Builder;
import main.java.elements.widgets.tree.NetworkTreeFooter.NetworkTreeFooterCheckbox;
import main.java.elements.widgets.tree.NetworkTreeWidget;
import main.java.elements.widgets.tree.nodes.RootOfNetworkTree;
import main.java.elements.widgets.tree.nodes.actioninstance.ActionOfNode;
import main.java.elements.wizards.action.ActionType;
import main.java.elements.wizards.action.config.ConfigActionsWizard;
import main.java.utility.utils.config.TestPrecondition;
import one.util.streamex.StreamEx;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * 56 tests; time = 105 sec
 */

public class EditingRunAppAction {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private ActionOfNode      actionNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String ACTION_CAPTION = "Run Application action";
    private final RunApplicationData ACTION_DATA = new RunApplicationData(Builder.of()
        .setCaption    (ACTION_CAPTION)
        .setApplicationName("App Name")
    );
    private final ActionType ACTION_TYPE = ActionType.RUN_APP;

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
            .addAction(ACTION_TYPE, ACTION_DATA)
            .shouldHasAction(ACTION_CAPTION);
        this.actionNode = this.rootTree.getAction(ACTION_CAPTION, ACTION_TYPE);
    }

    @DataSupplier
    public StreamEx<RunApplicationData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.POSITIVE_PATHS)
                .flatMap(path ->
                    StreamEx.of(
                        new RunApplicationData(Builder.of().setApplicationName(path)),
                        new RunApplicationData(Builder.of().setApplicationDir (path))
                    )
                )
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string ->
                    new RunApplicationData(Builder.of().setCaption(string))
                )
        ).append(
            StreamEx.of(DataConstants.NO_REQ_POSITIVE_STRS)
                .map(string ->
                    new RunApplicationData(Builder.of().setApplicationParams(string))
                )
        ).append(
            StreamEx.of(true, false)
                .map(bool -> new RunApplicationData(Builder.of().setWaitForCompletion(bool)))
        );
    }

    @DataSupplier
    public StreamEx<RunApplicationData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.NEGATIVE_PATHS)
                .flatMap(path ->
                    StreamEx.of(
                        new RunApplicationData(Builder.of().setApplicationName(path)),
                        new RunApplicationData(Builder.of().setApplicationDir (path))
                    )
                )
        ).append(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string ->
                    new RunApplicationData(Builder.of().setCaption(string))
                )
        ).append(
            StreamEx.of(DataConstants.NO_REQ_NEGATIVE_STRS)
                .map(string ->
                    new RunApplicationData(Builder.of().setApplicationParams(string))
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditRunApp(final RunApplicationData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditRunApp(final RunApplicationData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editWrongData(data);
        ConfigActionsWizard wizard = this.actionNode
            .getConfigWizards();

        // THEN
        wizard.shouldHasError();
        wizard.close();
    }
}
