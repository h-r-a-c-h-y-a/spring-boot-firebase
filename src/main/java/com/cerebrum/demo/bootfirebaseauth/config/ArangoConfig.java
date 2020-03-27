package com.cerebrum.demo.bootfirebaseauth.config;

import com.arangodb.ArangoDB;
import com.arangodb.Protocol;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com/cerebrum/demo/bootfirebaseauth"})
public class ArangoConfig implements ArangoConfiguration {

//    @Override
//    public ArangoDB.Builder arango() {
//        InputStream in = ArangoConfig.class.getResourceAsStream("arangodb.properties");
//        return new ArangoDB.Builder().loadProperties(in);
//
//    }

    @Override
    public ArangoDB.Builder arango() {
        ArangoDB.Builder arango = new ArangoDB.Builder()
                .host("127.0.0.1", 8529)
                .useProtocol(Protocol.HTTP_JSON)
                .user("root")
                .password("root");
        return arango;
    }

    @Override
    public String database() {
        return "shop_place";
    }
}
