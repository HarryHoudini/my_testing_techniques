public class example {
    public static void main(String[] args) {
        final NodeOfNetworkTree nodeOfNetworkTree = new NodeOfNetworkTree();
        nodeOfNetworkTree.addDefaultSensor(SelectWizardSensor.HTTP_CONTENT, "Sensor HTTP Content");
        nodeOfNetworkTree.addDefaultSensor(SelectWizardSensor.SYSTEM_SERVICE_STATUS, "Sensor SSS");
    }
}
