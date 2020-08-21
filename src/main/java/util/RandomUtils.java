package util;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static Random rd = new Random();

    public static int getRandomNumerBetween(int from, int to){
        int delta = to - from + 1;
        return rd.nextInt(delta) + from;
    }
    public static int getRandomNumer(int bound){
        return rd.nextInt(bound);
    }

    public static int getRandomNumberInArray(int[] array) {
        int index = rd.nextInt(array.length);
        return array[index];
    }
    public static int getRandomNumberInList(List<Integer> array) {
        int index = rd.nextInt(array.size());
        return array.get(index);
    }
    public static String getRandomStringInList(List<String> array) {
        int index = rd.nextInt(array.size());
        return array.get(index);
    }
}
