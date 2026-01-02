package com.ecomm.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDebugRunner implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private static final Logger log =
            LoggerFactory.getLogger(MongoDebugRunner.class);
    public MongoDebugRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        log.info("🔥 Mongo DB Name = " + mongoTemplate.getDb().getName());
    }
}
