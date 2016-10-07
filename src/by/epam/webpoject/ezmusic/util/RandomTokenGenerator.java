package by.epam.webpoject.ezmusic.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Антон on 11.09.2016.
 */
public class RandomTokenGenerator {
    public static String nextToken() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
