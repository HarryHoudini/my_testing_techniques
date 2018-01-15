import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "credentialrecord")
public class CredentialRecord {

    @Builder.Default
    @JsonProperty
    final private String caption = "default_captionвыавыаы";

    @Builder.Default
    @JsonProperty
    final private String crtype = "none";

    @Builder.Default
    @JsonSerialize(using = BooleanJSONSerializer.class)
    @JsonProperty("default")
    final private boolean defaultCredential = false;

    @Builder.Default
    @JsonProperty
    final private String password = "j84sTuh8pmLb2YhVTChcmg==";

    @Builder.Default
    @JsonProperty
    final private String username = "admin";

    @Builder.Default
    @Getter
    @JsonSerialize
    @JsonProperty
    final private String id = "";

    @JsonIgnore
    public CredentialRecordJSON getCredentialRecordJSON(){
        return new CredentialRecordJSON(this);
    }
}