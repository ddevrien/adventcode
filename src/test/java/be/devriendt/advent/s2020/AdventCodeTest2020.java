package be.devriendt.advent.s2020;

import org.junit.Assert;
import org.junit.Test;

public class AdventCodeTest2020 {

    @Test
    public void day1_reportRepair() throws Exception {
        Assert.assertEquals(514579, Day1ReportRepair.expensesToTwoEntrySolution(1721, 979, 366, 299, 675, 1456));

        long time = System.currentTimeMillis();
        int solution = Day1ReportRepair.expensesToTwoEntrySolution("/s2020/day1_expensereport.txt");
        Assert.assertEquals(974304, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1: " + solution + " [" + time + "ms]");

        Assert.assertEquals(241861950, Day1ReportRepair.expensesToThreeEntrySolution(1721, 979, 366, 299, 675, 1456));

        time = System.currentTimeMillis();
        solution = Day1ReportRepair.expensesToThreeEntrySolution("/s2020/day1_expensereport.txt");
        Assert.assertEquals(236430480, solution);
        time = System.currentTimeMillis() - time;
        System.out.println("DAY 1 extra: " + solution + " [" + time + "ms]");
    }

}