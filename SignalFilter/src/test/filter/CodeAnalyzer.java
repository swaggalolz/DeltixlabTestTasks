package test.filter;

import com.deltixlab.SignalFilter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class CodeAnalyzer {


    @State(Scope.Benchmark)
    public static class BenchmarkState {
        SignalFilter signalFilter = new SignalFilter(3);
    }
    @Benchmark
    public void measureShared(BenchmarkState state) {
        state.signalFilter.isSignalAllowed();
    }
    @Benchmark
    public void test() {
        SignalFilter signalFilter = new SignalFilter(3);
        signalFilter.isSignalAllowed();
        signalFilter.isSignalAllowed();
        signalFilter.isSignalAllowed();
        signalFilter.isSignalAllowed();
        signalFilter.isSignalAllowed();
    }

    public static void main(String ... args) throws RunnerException{
        Options opt = new OptionsBuilder()
                .include(CodeAnalyzer.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}
