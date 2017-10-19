package test.network_tree_winget;

import com.codeborne.selenide.Configuration;
import io.vavr.Tuple2;

import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class MyConditions {
    private MyConditions(){}
    
    public static <T, S> boolean waitingBy(BiFunction<T, S, Boolean> function, Tuple2<T, S> funcParametr){
        final long timeout = Configuration.timeout;
        final long startTime = System.currentTimeMillis();
        do{
            try {
                if (function.apply(funcParametr._1, funcParametr._2)) {
                    return true;
                }
            }
            catch(NoSuchElementException e){
                continue;
            }
        } while(System.currentTimeMillis() - startTime < timeout);
        return false;
    }
}
