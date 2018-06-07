package ness;

import ness.consumer.Consumer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class ConsumerApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        Consumer consumer = context.getBean("MQConsumer", Consumer.class);

        while (true){
            consumer.consume();
        }
    }
}
