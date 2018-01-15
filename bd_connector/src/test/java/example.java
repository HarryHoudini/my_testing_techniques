public class example {
    public static void main(String[] args) {
        final int id = 1009;
        final String xmlData = BDReader.getXmlDataById(CredentialRecordEntity.class, id);
        System.out.println(xmlData);
    }
}
