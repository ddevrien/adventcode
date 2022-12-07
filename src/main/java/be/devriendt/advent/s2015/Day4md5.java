package be.devriendt.advent.s2015;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Dennis on 5/12/2015.
 */
public class Day4md5 {

    public static int findLowestPossibleSolution(String input, int leadingZeroes) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        int number = 0;

        while (true) {
            md5.update((input + number).getBytes("UTF-8"));
            byte[] hash = md5.digest();

            if (leadingZeroesCheck(hash, leadingZeroes)) {
                return number;
            }
            number++;
        }
    }

    private static boolean leadingZeroesCheck(byte[] hash, int requiredZeroes) {
        int leadingZeroes = 0;
        int index = 0;

        while (leadingZeroes < requiredZeroes - 1) {
            if (hash[index] == 0) {
                leadingZeroes += 2;
                index++;
            } else {
                return false;
            }
        }

        return leadingZeroes == requiredZeroes || (hash[index] > 0 && hash[index] < 16);
    }

}
