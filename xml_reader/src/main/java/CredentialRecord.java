import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@ToString
@Setter
@XmlRootElement(name = "credentialrecord")
public class CredentialRecord {

    @XmlAttribute
    private String caption = "default_caption";

    @XmlAttribute
    private String crtype = "windows";

    @XmlAttribute(name = "default")
    private String defaultCredential = "0";

    @XmlAttribute
    private String password = "j84sTuh8pmLb2YhVTChcmg==";

    @XmlAttribute
    private String username = "admin";

    @XmlAttribute
    private String id = "";
}