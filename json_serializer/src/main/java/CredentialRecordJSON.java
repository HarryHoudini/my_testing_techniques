import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;

@JsonRootName("items")
@AllArgsConstructor
public class CredentialRecordJSON {

    @JsonProperty
    private CredentialRecord attrib;
}
