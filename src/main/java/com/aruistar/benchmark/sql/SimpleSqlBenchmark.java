package com.aruistar.benchmark.sql;

import com.alibaba.druid.pool.DruidDataSource;
import groovy.sql.Sql;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Threads;

import java.sql.SQLException;
import java.util.Map;

public class SimpleSqlBenchmark {

    static DruidDataSource datasourceInDocker = new DruidDataSource();
    static DruidDataSource datasourceInHost = new DruidDataSource();

    static {
        datasourceInDocker.setUrl("jdbc:postgresql://192.168.1.10:54321/benchmark");
        datasourceInDocker.setUsername("postgres");
        datasourceInDocker.setPassword("");
        datasourceInDocker.setDriverClassName("org.postgresql.Driver");

        datasourceInHost.setUrl("jdbc:postgresql://192.168.1.10:5432/benchmark");
        datasourceInHost.setUsername("postgres");
        datasourceInHost.setPassword("");
        datasourceInHost.setDriverClassName("org.postgresql.Driver");
    }

    @Benchmark
    @Threads(4)
    public void testPostgresInDocer() throws SQLException, ClassNotFoundException {

        Sql sql = new Sql(datasourceInDocker);

        Map row = sql.firstRow("with cte as ( select *\n" +
                "FROM\n" +
                "  (\n" +
                "    VALUES (uuid_generate_v4(), 'xiaoming',10, '语文'),\n" +
                "      (uuid_generate_v4(), 'xiaohong',12, '数学'),\n" +
                "      (uuid_generate_v4(), 'xiaoli',11, '英语'),\n" +
                "      (uuid_generate_v4(), 'xiaozhi',11, '英语'),\n" +
                "      (uuid_generate_v4(), 'xiaoxin',11, '英语')\n" +
                "  )\n" +
                "    AS tmp (id, name,age, fav))\n" +
                "\n" +
                "select array_to_json(array_agg(row_to_json(cte)))\n" +
                "from cte;");

        sql.close();
    }

    @Benchmark
    @Threads(4)
    public void testPostgresInHost() throws SQLException, ClassNotFoundException {

        Sql sql = new Sql(datasourceInHost);

        Map row = sql.firstRow("with cte as ( select *\n" +
                "FROM\n" +
                "  (\n" +
                "    VALUES (uuid_generate_v4(), 'xiaoming',10, '语文'),\n" +
                "      (uuid_generate_v4(), 'xiaohong',12, '数学'),\n" +
                "      (uuid_generate_v4(), 'xiaoli',11, '英语'),\n" +
                "      (uuid_generate_v4(), 'xiaozhi',11, '英语'),\n" +
                "      (uuid_generate_v4(), 'xiaoxin',11, '英语')\n" +
                "  )\n" +
                "    AS tmp (id, name,age, fav))\n" +
                "\n" +
                "select array_to_json(array_agg(row_to_json(cte)))\n" +
                "from cte;");

        sql.close();
    }

}
