package main.java.xml;

import lombok.Getter;
import main.java.utility.utils.MyUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class MyXmlCreator {
    private String xmlsFolderPath;
    private int currentCountFile;
    private Optional<List<File>> xmls;

    public MyXmlCreator(Class currentTestClass){
        File testClassDir = MyUtils.getDirByClass(currentTestClass);
        this.xmlsFolderPath =  testClassDir.getPath() + "\\xml\\";
        this.currentCountFile = 1000;
    }

    public void marshall(List<Object> objects) throws JAXBException {
        File xmlsDir = new File(this.xmlsFolderPath);
        MyUtils.deleteDirectory(xmlsDir);
        xmlsDir.mkdir();
        for (Object object : objects) {
            File file = new File(this.xmlsFolderPath + currentCountFile + ".xml");
            currentCountFile++;
            JAXBContext jaxbContext;
            jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(object, file);
        }
        Optional<File[]> xmlsFiles = Optional.ofNullable(xmlsDir.listFiles());
        this.xmls = xmlsFiles.map(Arrays::asList);
    }

    public List<File> getXmls(){
        return this.xmls.orElse(Collections.emptyList());
    }
}
