package br.com.estudo.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.testcontainers.containers.MariaDBContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseLifecycleTest implements QuarkusTestResourceLifecycleManager {

    @Rule
    private static MariaDBContainer<?> database = new MariaDBContainer<>("mariadb:10.6.1-focal");

    @Override
    public Map<String, String> start() {
        database.start();
        database.setPrivilegedMode(false);
        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.datasource.jdbc.url",database.getJdbcUrl());
        properties.put("quarkus.datasource.username",database.getUsername());
        properties.put("quarkus.datasource.password",database.getPassword());
        return properties;
    }

    @Override
    public void stop() {
        if (database != null) {
            database.stop();
        }
    }
    
}
