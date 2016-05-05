package test.filter;

import com.deltixlab.SignalFilter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class CodeAnalyzer {

    @State(Scope.Benchmark)  
    public static class BenchmarkFilter {
        SignalFilter signalFilter = new SignalFilter(3);
    }

    @State(Scope.Thread) 
    public static class ThreadFilter {
        SignalFilter signalFilter = new SignalFilter(3);
    }

    @Benchmark
    @Warmup(iterations = 500 )
    @Measurement(iterations = 500)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedFilter( ThreadFilter filter ) {
        filter.signalFilter.isSignalAllowed();
    }

    @Benchmark
    @Warmup(iterations = 500 )
    @Measurement(iterations = 500)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForSharedFilter( BenchmarkFilter filter) {
       filter.signalFilter.isSignalAllowed();
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
