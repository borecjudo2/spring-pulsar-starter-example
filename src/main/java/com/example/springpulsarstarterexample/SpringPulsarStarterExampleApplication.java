package com.example.springpulsarstarterexample;

import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.pulsar.core.PulsarTemplate;

@SpringBootApplication
public class SpringPulsarStarterExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringPulsarStarterExampleApplication.class, args);
  }

  @Bean
  ApplicationRunner runner(PulsarTemplate<String> pulsarTemplate) {
    //    PulsarTemplate<Foo> pulsarTemplate = new PulsarTemplate<>(producerFactory);
    //    pulsarTemplate.setSchema(Schema.JSON(Foo.class));

    return args -> {
      //      Foo foo = new Foo("HELLLLLoo");
      pulsarTemplate.send("hello-pulsar-partitioned", "HELLLLLoo");
    };
  }

  @PulsarListener(
      subscriptionName = "hello-pulsar-shared-subscription1",
      topics = "hello-pulsar-partitioned",
      //      schemaType = SchemaType.JSON,
      subscriptionType = SubscriptionType.Exclusive
  )
  public void listen1(String foo) {
    System.out.println("Message Received 1: " + foo);
  }

  @PulsarListener(
      subscriptionName = "hello-pulsar-shared-subscription2",
      topics = "hello-pulsar-partitioned",
      //      schemaType = SchemaType.JSON,
      subscriptionType = SubscriptionType.Shared
  )
  public void listen2(String foo) {
    System.out.println("Message Received 2: " + foo);
  }

  @PulsarListener(
      subscriptionName = "hello-pulsar-shared-subscription2",
      topics = "hello-pulsar-partitioned",
      //      schemaType = SchemaType.JSON,
      subscriptionType = SubscriptionType.Shared
  )
  public void listen3(String foo) {
    System.out.println("Message Received 3: " + foo);
  }

  @PulsarListener(
      subscriptionName = "hello-pulsar-shared-subscription2",
      topics = "hello-pulsar-partitioned",
      //      schemaType = SchemaType.JSON,
      subscriptionType = SubscriptionType.Shared
  )
  public void listen4(String foo) {
    System.out.println("Message Received 4: " + foo);
  }
}
