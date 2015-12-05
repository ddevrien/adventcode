package be.devriendt.advent;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dennis on 5/12/2015.
 */
public class AdventCodeTest {

    @Test
    public void day4_md5() throws Exception {
        Assert.assertEquals(609043, Day4md5.findLowestPossibleSolution("abcdef", 5));
        Assert.assertEquals(1048970, Day4md5.findLowestPossibleSolution("pqrstuv", 5));

        long time = System.currentTimeMillis();
        int solution = Day4md5.findLowestPossibleSolution("bgvyzdsv", 5);
        Assert.assertEquals(254575, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4: " + solution + " [" + DigestUtils.md5Hex("bgvyzdsv" + solution) + " [" + time + "ms]");

        time = System.currentTimeMillis();
        solution = Day4md5.findLowestPossibleSolution("bgvyzdsv", 6);
        Assert.assertEquals(1038736, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 4 extra: " + solution + " [" + DigestUtils.md5Hex("bgvyzdsv" + solution) + " [" + time + "ms]");
    }
}