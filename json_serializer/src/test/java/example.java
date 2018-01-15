public class example {
    public static void main(String[] args) {
        final CredentialRecordJSON credentialRecordJSON = CredentialRecord.builder()
            .caption("new_credential")
            .username("new_username")
            .password("aabbcc==")
            .build()
            .getCredentialRecordJSON();
        final String json = JSONSerializer.serialize(credentialRecordJSON);
        System.out.println(json);
    }
}
