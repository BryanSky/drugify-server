package ch.drugify.server.server;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@ComponentScan(basePackages = { "ch.drugify.server.*" })
@PropertySource(value="classpath:config.properties", ignoreResourceNotFound=true)
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        String db_host = env.getProperty("mongodb.host");
        String db_port = env.getProperty("mongodb.port");
        String db_name = env.getProperty("mongodb.db");

        MongoClient mongo = new MongoClient(db_host, Integer.valueOf(db_port));
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, db_name);
        return new MongoTemplate(mongoDbFactory);

    }
}
