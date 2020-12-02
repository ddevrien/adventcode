package be.devriendt.advent;

import be.devriendt.advent.s2020.Day1ReportRepair;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@State(Scope.Benchmark)
public class BenchmarkRunner {

    private Integer[] records;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(BenchmarkRunner.class.getSimpleName())
                .include(Day1ReportRepair.class.getSimpleName())
                .addProfiler(StackProfiler.class)
                .build();
        new Runner(opt).run();
//        org.openjdk.jmh.Main.main(args);
    }

    @Setup(Level.Invocation)
    public void setUp() throws IOException, URISyntaxException {
        List<String> lines = Util.getContent("/s2020/day1_expensereport.txt");
        records = lines.stream()
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    @Benchmark
    public int expenseReportArraySolution() {
        return Day1ReportRepair.expensesToTwoEntrySolution(records);
    }

    @Benchmark
    public int expenseReportStreamSolution()  {
        return Day1ReportRepair.expensesToTwoEntryStreamSolution(records);
    }
}
