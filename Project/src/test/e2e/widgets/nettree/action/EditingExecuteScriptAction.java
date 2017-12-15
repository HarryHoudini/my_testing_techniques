package test.e2e.widgets.nettree.action;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.actions.ExecuteScriptData;
import main.java.data.actions.ExecuteScriptData.Builder;
import main.java.data.actions.ExecuteScriptData.ScriptType;
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
 * 24 tests; time = 60 sec
 */

public class EditingExecuteScriptAction {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private ActionOfNode      actionNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String ACTION_CAPTION = "Execute Script action";
    private final ExecuteScriptData ACTION_DATA = new ExecuteScriptData(Builder.of()
        .setCaption(ACTION_CAPTION)
        .setScript ("Script Content")
    );
    private final ActionType actionType = ActionType.EXECUTE_SCRIPT;

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
            .addAction(this.actionType, ACTION_DATA)
            .shouldHasAction(ACTION_CAPTION);
        this.actionNode = this.rootTree.getAction(ACTION_CAPTION, this.actionType);
    }

    @DataSupplier
    public StreamEx<ExecuteScriptData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(ScriptType.values())
                .map(type -> new ExecuteScriptData(Builder.of().setScriptType(type)))
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new ExecuteScriptData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_AREA_STRS)
                .map(string -> new ExecuteScriptData(Builder.of().setScript(string)))
        );
    }

    @DataSupplier
    public StreamEx<ExecuteScriptData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new ExecuteScriptData(Builder.of().setCaption(string)))
        ).append(
            StreamEx.of(DataConstants.REQ_NEGATIVE_AREA_STRS)
                .map(string -> new ExecuteScriptData(Builder.of().setScript(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditExecuteScript(final ExecuteScriptData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditExecuteScript(final ExecuteScriptData data){

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
