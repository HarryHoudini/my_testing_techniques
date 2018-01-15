public class example {
    public static void main(String[] args) {
        final String xml = "<credentialrecord caption=\"c\" crtype=\"windows\" default=\"0\" password=\"p\" username=\"u\" id=\"1\" />\n";
        final CredentialRecord unmarshalling = XMLReader.unmarshalling(CredentialRecord.class, xml);
        System.out.println(unmarshalling);

    }
}
