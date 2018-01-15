import lombok.ToString;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@ToString
public class XMLReader {
    public static <T> T unmarshalling(Class<T> className, String xml){
        StringReader reader = new StringReader(xml);

        try {
            JAXBContext jc = JAXBContext.newInstance(className);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            @SuppressWarnings("unchecked")
            final T result = (T) unmarshaller.unmarshal(reader);
            return result;
        } catch (JAXBException e) {
            throw new IllegalStateException(String.format("Unmarshalling error for %s", className), e);
        }
    }
}
