package test.e2e.widgets.nettree.action;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.actions.RestartComputerData;
import main.java.data.actions.RestartComputerData.Builder;
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
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * 36 tests; time = 80 sec
 */

public class EditingRestartComputerAction {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private ActionOfNode      actionNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String ACTION_CAPTION = "Restart Computer action";
    private final RestartComputerData ACTION_DATA = new RestartComputerData(Builder.of()
        .setCaption    (ACTION_CAPTION)
        .setMessageText("Message Text")
    );
    private final ActionType ACTION_TYPE = ActionType.RESTART_COMPUTER;

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
    public StreamEx<RestartComputerData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .flatMap(string -> Stream.of(
                    new RestartComputerData(Builder.of().setCaption(string)),
                    new RestartComputerData(Builder.of().setMessageText(string))
                    )
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_POSITIVE_NUMS)
                .map(num -> new RestartComputerData(Builder.of().setTimeout(num)))
        ).append(
            StreamEx.of(DataConstants.POSITIVE_NUM_STRS)
                .map(string -> new RestartComputerData(Builder.of().setTimeoutString(string)))
        ).append(
            StreamEx.of(true, false)
                .flatMap(bool -> Stream.of(
                    new RestartComputerData(Builder.of().setForce(bool)),
                    new RestartComputerData(Builder.of().setShutdown(bool))
                    )
                )
        );
    }

    @DataSupplier
    public StreamEx<RestartComputerData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .flatMap(string -> Stream.of(
                    new RestartComputerData(Builder.of().setCaption(string)),
                    new RestartComputerData(Builder.of().setMessageText(string))
                    )
                )
        ).append(
            StreamEx.of(DataConstants.FOUR_BYTE_NEGATIVE_NUMS)
                .map(num -> new RestartComputerData(Builder.of().setTimeout(num)))
        ).append(
            StreamEx.of(DataConstants.NEGATIVE_NUM_STRS)
                .map(string -> new RestartComputerData(Builder.of().setTimeoutString(string)))
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditRestartComputer(final RestartComputerData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditRestartComputer(final RestartComputerData data){

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
