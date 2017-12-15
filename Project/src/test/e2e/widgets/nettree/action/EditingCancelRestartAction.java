package test.e2e.widgets.nettree.action;

import com.codeborne.selenide.WebDriverRunner;
import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.actions.CancelRestartData;
import main.java.data.actions.CancelRestartData.Builder;
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
 * 8 tests; time = 24 sec
 */

public class EditingCancelRestartAction {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private ActionOfNode      actionNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String ACTION_CAPTION = "Cancel Restart action";
    private final CancelRestartData ACTION_DATA = new CancelRestartData(Builder.of()
        .setCaption(ACTION_CAPTION)
    );
    private final ActionType ACTION_TYPE = ActionType.CANCEL_RESTART;

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
    public StreamEx<CancelRestartData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .map(string -> new CancelRestartData(Builder.of().setCaption(string)))
        );
    }

    @DataSupplier
    public StreamEx<CancelRestartData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .map(string -> new CancelRestartData(Builder.of().setCaption(string)))

        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditCancelRestart(final CancelRestartData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditCancelRestart(final CancelRestartData data){

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
