package test.filter;

import com.deltixlab.CircularBuffer;
import com.deltixlab.SignalFilter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class CodeAnalyzer {

    @State(Scope.Benchmark)  //общий на все потоки
    public static class BenchmarkFilter {
        SignalFilter signalFilter = new SignalFilter(3);
    }

    @State(Scope.Thread)  //экземпл€р дл€ каждого потока
    public static class ThreadFilter {
        SignalFilter signalFilter = new SignalFilter(3);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime) //среднее врем€ работы.
    //@BenchmarkMode(Mode.Throughput)   //число операций в секунду
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedFilter( ThreadFilter filter ) {
        filter.signalFilter.isSignalAllowed();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForSharedFilter( BenchmarkFilter filter) {
       filter.signalFilter.isSignalAllowed();
    }

    @State(Scope.Thread)
    public static class ThreadBuffer {
        CircularBuffer circularBuffer = new CircularBuffer(3);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedBufferAdd( ThreadBuffer buffer, ThreadLongValues values ) {
        buffer.circularBuffer.add(values.timeNow);
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureForUnsharedBufferPeek( ThreadBuffer buffer ) {
        buffer.circularBuffer.peek();
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureGetTime() {
        System.currentTimeMillis();
    }

    @State(Scope.Thread)
    public static class ThreadLongValues {
        long timeNow = System.currentTimeMillis();
        long timeBefore = System.currentTimeMillis();
        long result;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    //@BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureCalculation( ThreadLongValues values) {
       values.result = values.timeNow - values.timeBefore;
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
