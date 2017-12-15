package main.java.xml.node;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement (name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlGroup {
    @XmlAttribute private String caption = "";
    @XmlAttribute private int id = 1;
    @XmlAttribute private String type = "group";
    @XmlElement (name = "subitem")
    private ArrayList<XmlSubitemNode> subitems = new ArrayList<>();

    public void addSubitems(List<Integer> addonIps) {
        addonIps.forEach( i -> {
            XmlSubitemNode subitemNode = new XmlSubitemNode();
            subitemNode.setName(i);
            this.subitems.add(subitemNode);
        });
    }
}
