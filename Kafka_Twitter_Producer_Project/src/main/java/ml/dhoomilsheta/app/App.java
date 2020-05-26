package ml.dhoomilsheta.app;

import ml.dhoomilsheta.app.producer.TwitterKafkaProducer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("program Started!");
        TwitterKafkaProducer producer = new TwitterKafkaProducer();
        producer.run();
    }
}
