package com.yqq.classification;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.prediction.Event;
import io.prediction.EventClient;
import org.joda.time.DateTime;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by yqq on 2019/11/11.
 */
public class DataDao {

    private static final String API_KEY = "MBa6XCZyuus6I1srdC1VOh1e7JQJ9v6MYayeWQX1HyUmmxPOv0hjvQ2fLAVsCgTc";
    private static final String EVENT_URL = "http://localhost:7070";

    private EventClient eventClient;

    public DataDao() {
        this.eventClient = new EventClient(API_KEY, EVENT_URL);
    }

    public DataDao(String accessKey) {
        this.eventClient = new EventClient(accessKey);
    }

    public void addEvents() throws InterruptedException, ExecutionException, IOException {

        //addUserEvents();

        //addItemEvents();

        //addViewEvents();

        addEventsDemo();

        eventClient.close();

    }

    private void addEventsDemo() throws InterruptedException, ExecutionException, IOException {

        List<Event> events = Lists.newArrayList();
        Map<String,Object> map1 = new HashMap();
        map1.put("attr0",66);
        map1.put("attr1",34);
        map1.put("attr2",25);
        map1.put("attr3",1);
        map1.put("plan",5);
        Event event1 = new Event()
                .entityId("user_5")
                .entityType("user")
                .event("$set").properties(map1).eventTime(new DateTime());
        events.add(event1);

        Map<String,Object> map2 = new HashMap();
        map2.put("attr0",64);
        map2.put("attr1",35);
        map2.put("attr2",25);
        map2.put("attr3",2);
        map2.put("plan",5);
        Event event2 = new Event()
                .entityId("user_6")
                .entityType("user")
                .event("$set").properties(map2).eventTime(new DateTime());
        events.add(event2);

        // id 集合
        List<String> result = eventClient.createEvents(events);

        System.out.println("result--->"+result);

    }

    public void addUserEvents() throws InterruptedException, ExecutionException, IOException {

        for (int i = 0; i < 100; i++) {

            List<Event> events = Lists.newArrayList();

            events.add(new Event().entityId("user"+ i).entityType("user").event("$set"));

            List<String> result = eventClient.createEvents(events);

            System.out.println(result);

        }

    }

    public void addItemEvents() throws InterruptedException, ExecutionException, IOException {

        List<Event> events = Lists.newArrayList();

        events.add(new Event().entityId("item1").entityType("item").event("$set").property("categories", ImmutableList.of("machine-learning")));

        events.add(new Event().entityId("item2").entityType("item").event("$set").property("categories", ImmutableList.of("javascript")));

        events.add(new Event().entityId("item3").entityType("item").event("$set").property("categories", ImmutableList.of("scala")));

        events.add(new Event().entityId("item4").entityType("item").event("$set").property("categories", ImmutableList.of("artificial-intelligence")));

        events.add(new Event().entityId("item5").entityType("item").event("$set").property("categories", ImmutableList.of("statistics")));

        events.add(new Event().entityId("item6").entityType("item").event("$set").property("categories", ImmutableList.of("python")));

        events.add(new Event().entityId("item7").entityType("item").event("$set").property("categories", ImmutableList.of("web-development")));

        events.add(new Event().entityId("item8").entityType("item").event("$set").property("categories", ImmutableList.of("security")));

        events.add(new Event().entityId("item9").entityType("item").event("$set").property("categories", ImmutableList.of("ruby")));

        events.add(new Event().entityId("item10").entityType("item").event("$set").property("categories", ImmutableList.of("openshift")));

        List<String> result = eventClient.createEvents(events);

        System.out.println(result);

    }

    public void addViewEvents() throws InterruptedException, ExecutionException, IOException {

        for (int i = 0; i < 100; i++) {

            List<Event> events = Lists.newArrayList();

            events.add(new Event().entityId("user"+ i).entityType("user").event("view").targetEntityType("item").targetEntityId("item1"));

            events.add(new Event().entityId("user"+ i).entityType("user").event("view").targetEntityType("item").targetEntityId("item4"));

            Random random = new Random();

            int id = random.nextInt(9) + 1;

            events.add(new Event().entityId("user" + i).entityType("user").event("view").targetEntityType("item").targetEntityId("item" + id));

            List<String> result = eventClient.createEvents(events);

            System.out.println(result);

        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        DataDao itemDataDao = new DataDao();

        itemDataDao.addEvents();

    }
}
