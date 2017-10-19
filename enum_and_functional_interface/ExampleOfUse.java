package test.network_tree_winget;

import main.elements.widgets.node_tree.NetworkTreeWidget;
import main.elements.widgets.node_tree.nodes.GroupOfNetworkTree;
import main.elements.wizards.sensor_wizards.SelectWizardSensor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExampleOfUse {
    private NetworkTreeWidget networkTreeWidget;
    private GroupOfNetworkTree group;

    private final String GROUP_NAME = "Test Group";

    @BeforeClass
    public void setUp(){
        networkTreeWidget = new NetworkTreeWidget();
        group = networkTreeWidget.getGroup(GROUP_NAME);
    }

    @Test()
    public void addHttpSensor(){

        // GIVEN
        final String NAME_SENSOR = "HTTP Content Sensor";

        // WHEN
        this.group.addDefaultSensor(SelectWizardSensor.HTTP_CONTENT, NAME_SENSOR);

        // THEN
        this.group.shoudHasSensor(NAME_SENSOR);
    }
}
