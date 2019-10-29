package orf.kafka.practice.kafkapractice;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 60000);
        props.put("batch.size", 16384);
        props.put("linger.ms", 30000);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
        producer.close();

    }
}
