package test.filter;

import com.deltixlab.SignalFilter;
import com.deltixlab.TestFIlter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class CodeAnalyzer {

    @State(Scope.Benchmark)  
    public static class BenchmarkFilter {
        SignalFilter signalFilter = new SignalFilter(30);
        TestFIlter testFIlter = new TestFIlter(30);
    }

    @State(Scope.Thread) 
    public static class ThreadFilter {
        SignalFilter signalFilter = new SignalFilter(30);
        TestFIlter testFIlter = new TestFIlter(30);
    }


    @Benchmark
    @Warmup(iterations = 500, batchSize = 50)
    @Measurement(iterations = 500, batchSize = 50)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedSignalFilter( ThreadFilter filter ) {
        filter.signalFilter.isSignalAllowed();
    }


    @Benchmark
    @Warmup(iterations = 500, batchSize = 50)
    @Measurement(iterations = 500, batchSize = 50)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedTestFilter( ThreadFilter filter ) {
        filter.testFIlter.isSignalAllowed();
    }


    @Benchmark
    @Warmup(iterations = 500, batchSize = 50)
    @Measurement(iterations = 500, batchSize = 50)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForSharedSignalFilter( BenchmarkFilter filter) {
       filter.signalFilter.isSignalAllowed();
    }


    @Benchmark
    @Warmup(iterations = 500, batchSize = 50)
    @Measurement(iterations = 500, batchSize = 50)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForSharedTestFilter( BenchmarkFilter filter) {
        filter.testFIlter.isSignalAllowed();
    }





    public static void main(String ... args) throws RunnerException{
        Options opt = new OptionsBuilder()
                .include(CodeAnalyzer.class.getSimpleName())
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}
