package main.java.xml.node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "subitem")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSubitemNode {
    @XmlAttribute private String name;

    public static XmlSubitemNode xmlSubitemNode(){
        return new XmlSubitemNode();
    }

    public void setName(int id){
        this.name = "node." + id;
    }

    public String getName(){
        return this.name;
    }

}
