import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.io.StringReader;

public class JSONSerializer {

    public static <T> String serialize(T object){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final String result;
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Can't serialize " + object, e);
        }
        return result;
    }

    public static <T> T deserialize(String json, Class<T> className){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        final StringReader reader = new StringReader(json);
        try {
            return mapper.readValue(reader, className);
        } catch (IOException e) {
            throw new IllegalStateException("Can't deserialize " + json, e);
        }
    }
}
