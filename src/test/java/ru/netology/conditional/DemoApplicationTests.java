package ru.netology.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    public static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    public static GenericContainer<?> myappProb = new GenericContainer<>("probapp")
            .withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;


    @BeforeAll
    public static void setUp() {
        devapp.start();
        myappProb.start();
    }

    @Test
    void contextLoadsDevApp() {
        System.out.println("http://localhost:" + devapp.getMappedPort(8080)+"/profile");
        ResponseEntity<String> forEntity =
                restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080)+"/profile", String.class);
        Assertions.assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void contextLoadsProbApp() {
        System.out.println("http://localhost:" + myappProb.getMappedPort(8081)+"/profile");
        ResponseEntity<String> forEntity =
                restTemplate.getForEntity("http://localhost:" + myappProb.getMappedPort(8081)+"/profile", String.class);
        Assertions.assertEquals("Current profile is production", forEntity.getBody());
    }

}