package com.aruistar.benchmark;

import com.aruistar.benchmark.sql.SimpleSqlBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class RootBench {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SimpleSqlBenchmark.class.getSimpleName())
                .forks(2)
                .warmupIterations(3)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }

}
