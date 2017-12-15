package main.java.xml.node;

import lombok.Getter;
import lombok.Setter;
import main.java.data.DeviceAttributes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement (name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDevice {
    @XmlAttribute private String caption = "XML Device";
    @XmlAttribute private String hostname = "";
    @XmlAttribute private int id = 1000;
    @XmlAttribute private String ip = "";
    @XmlAttribute private String type = "device";

    public void setAllAttribures(DeviceAttributes attribures){
        setCaption(attribures.getDeviceCaption());
        setHostname(attribures.getDeviceHostname());
        setIp(attribures.getDeviceIP());
    }
}
