package main.java.utility.utils;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyComparators {

    private static long getIpFromString(String ipString){
        final List<Long> ipOctets = Arrays.stream(ipString.split("\\."))
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return ipOctets.get(3) +
                ipOctets.get(2) * 256 +
                ipOctets.get(1) * 256 * 256 +
                ipOctets.get(0) * 256 * 256 * 256;
    }

    public static int ipComparator(String s1, String s2){
        return Long.compare(getIpFromString(s1), getIpFromString(s2));
    }
}
