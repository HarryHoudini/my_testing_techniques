package main.java.utility.utils;

import java.util.Objects;
import java.util.Optional;

public final class OptionalUtils {

    private OptionalUtils(){}

    /**
     * WARNING - if at least one is equal Optional.empty() - return true
     */
    public static <T> boolean optionalEquals(Optional<T> thisObjectOp, Optional<T> expectedObjectOp){
        if (!expectedObjectOp.isPresent() || !thisObjectOp.isPresent()){
            return true;
        }
        T thisObject     = thisObjectOp.get();
        T expectedObject = expectedObjectOp.get();
        if (thisObject.getClass() == String.class && expectedObject.getClass() == String.class){
            String thisString     = (String) thisObject;
            String expectedString = (String) expectedObject;
            return thisString.trim().equals(expectedString.trim());
        }
        else{
            return Objects.equals(thisObjectOp.get(), expectedObjectOp.get());
        }
    }

    public static <T> int optionalHashCode(Optional<T> optionalObj){
        return optionalObj.map(Object::hashCode).orElse(0);
    }

    public static <T> String optionalToString(Optional<T> optional){
        return optional.map(Object::toString).orElse("none");
    }
}
