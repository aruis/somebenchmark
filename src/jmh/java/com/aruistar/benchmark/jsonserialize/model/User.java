package com.aruistar.benchmark.jsonserialize.model;


import io.vertx.core.json.JsonObject;

public class User extends JsonObject {


    public User(String name, String username, int age, String title, boolean bool) {
        put("name", name);
        put("age", age);
        put("title", title);
        put("bool", bool);
    }


}
