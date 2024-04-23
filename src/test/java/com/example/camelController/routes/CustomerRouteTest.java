package com.example.camelController.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.CamelSpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(
        properties = { "camel.springboot.name=customName" }
)
public class CustomerRouteTest {
    @Autowired
    ProducerTemplate producerTemplate;

    @EndpointInject("mock:test")
    MockEndpoint mockEndpoint;

    @Configuration
    static class TestConfig {

        @Bean
        RoutesBuilder route() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:test").to("mock:test");
                }
            };
        }
    }

    @Test
    public void shouldAutowireProducerTemplate() {
        assertNotNull(producerTemplate);
    }

    @Test
    public void shouldSetCustomName() {
        assertEquals("customName", producerTemplate.getCamelContext().getName());
    }

    @Test
    public void shouldInjectEndpoint() throws InterruptedException {
        mockEndpoint.setExpectedMessageCount(1);
        producerTemplate.sendBody("direct:test", "msg");
        mockEndpoint.assertIsSatisfied();
    }

}
