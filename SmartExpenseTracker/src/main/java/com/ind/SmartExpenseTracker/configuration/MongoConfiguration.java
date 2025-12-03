package com.ind.SmartExpenseTracker.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {
    @Value("${spring.data.mongodb.uri}") private String uri;
    @Value("${spring.data.mongodb.database}") private String db;

    @Bean public MongoClient mongoClient() { return MongoClients.create(uri); }
    @Bean public MongoTemplate mongoTemplate() { return new MongoTemplate(mongoClient(), db); }
}

