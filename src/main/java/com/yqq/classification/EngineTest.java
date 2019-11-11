package com.yqq.classification;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import io.prediction.EngineClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by yqq on 2019/11/11.
 */
public class EngineTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        EngineClient engineClient = new EngineClient("http://localhost:8000");

        JsonObject response = engineClient.sendQuery(ImmutableMap.<String, Object>of(
                "attr0", 66,
                "attr1",  34,
                "attr2",25,
                "attr3",1
        ));
        System.out.println(response);
        engineClient.close();

    }
}
