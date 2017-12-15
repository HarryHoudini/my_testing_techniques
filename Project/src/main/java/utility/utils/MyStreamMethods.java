package main.java.utility.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyStreamMethods {
    public static <T> List<T> dropRightUntil(List<T> inList, Predicate<T> predicate){
        int lastIdx = IntStream.range(0, inList.size())
            .filter(i -> predicate.test(inList.get(i)))
            .findFirst()
            .orElse(-1);

        return inList.subList(0, lastIdx + 1);
    }

    public static <T> Optional<T> getLast(Stream<T> inStream){
        return inStream.reduce((a, b) -> b);
    }

    public static <T> Optional<T> getLast(List<T> inList){
        return Optional.ofNullable(inList.get(inList.size() - 1));
    }

    public static <T> Optional<List<T>> drop(List<T> inList, int indexDrop){
        try{
            return Optional.of(inList.subList(indexDrop, inList.size()));
        }
        catch (IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }
}
