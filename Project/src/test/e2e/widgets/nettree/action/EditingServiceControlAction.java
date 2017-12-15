package test.e2e.widgets.nettree.action;

import io.github.sskorol.core.DataSupplier;
import main.java.constants.DataConstants;
import main.java.constants.DependingConstants;
import main.java.data.Credential;
import main.java.data.actions.ServiceControlData;
import main.java.data.actions.ServiceControlData.Builder;
import main.java.data.actions.ServiceControlData.ServiceAction;
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

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * 25 tests; time = 60 sec
 */

public class EditingServiceControlAction {
    private NetworkTreeWidget networkTreeWidget;
    private RootOfNetworkTree rootTree;
    private ActionOfNode      actionNode;

    private final Credential CREDENTIAL = new Credential("Caption", "Username", "Password");
    private final String ACTION_CAPTION = "Service Control action";
    private final ServiceControlData ACTION_DATA = new ServiceControlData(Builder.of()
        .setCaption    (ACTION_CAPTION)
        .setServiceName("Service Name")
    );
    private final ActionType ACTION_TYPE = ActionType.SERVICE_CONTROL;

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
    public StreamEx<ServiceControlData> getPositiveData(){
        return StreamEx.of(
            StreamEx.of(ServiceAction.values())
                .map(action -> new ServiceControlData(Builder.of().setServiceAction(action)))
        ).append(
            StreamEx.of(DataConstants.REQ_POSITIVE_STRS)
                .flatMap(string ->
                    StreamEx.of(
                        new ServiceControlData(Builder.of().setCaption(string)),
                        new ServiceControlData(Builder.of().setServiceName(string))
                    )
                )
        ).append(
            StreamEx.of(true, false)
                .map(local -> new ServiceControlData(Builder.of().setLocalAction(local)))
        );
    }

    @DataSupplier
    public StreamEx<ServiceControlData> getNegativeData(){
        return StreamEx.of(
            StreamEx.of(DataConstants.REQ_NEGATIVE_STRS)
                .flatMap(string ->
                    StreamEx.of(
                        new ServiceControlData(Builder.of().setCaption(string)),
                        new ServiceControlData(Builder.of().setServiceName(string))
                    )
                )
        );
    }

    @Test(dataProvider = "getPositiveData")
    public void shouldEditServiceControl(final ServiceControlData data){

        // GIVEN

        // WHEN
        this.actionNode
            .editCorrectData(data)

        // THEN
            .shouldHasData(data);
    }

    @Test(dataProvider = "getNegativeData")
    public void shouldNotEditServiceControl(final ServiceControlData data){

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
