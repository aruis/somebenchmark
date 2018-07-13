package com.aruistar.benchmark;

import com.aruistar.benchmark.model.User;
import com.jsoniter.output.JsonStream;
import groovy.json.JsonBuilder;
import groovy.json.JsonOutput;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class JsonToStringBenchmark {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JsonToStringBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }


    //    @Benchmark
    public void testJsonObjectToBuffer() {
        new User("Hello, World!", "tomcat", 10, "angular", true).toBuffer();
    }

    @Benchmark
    public void testJsonObjectToString() {
        new User("Hello, World!", "tomcat", 10, "angular", true).toString();
    }


    @Benchmark
    public void testJsonBuilder() {
        new JsonBuilder(new User("Hello, World!", "tomcat", 10, "angular", true).getMap()).toString();
    }

    @Benchmark
    public void testJsonOutput() {
        JsonOutput.toJson(new User("Hello, World!", "tomcat", 10, "angular", true).getMap());
    }

    @Benchmark
    public void testJsoniter() {
        JsonStream.serialize(new User("Hello, World!", "tomcat", 10, "angular", true).getMap());
    }


}
