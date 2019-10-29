package orf.kafka.practice.kafkapractice;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SslConfigs;

public class TestKafkaSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");

		// configure the following three settings for SSL Encryption
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
		props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
				"/Users/vijendrasingh/kafka.client.truststore.jks");
		props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "test1234");

		// configure the following three settings for SSL Authentication
		props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
				"/Users/vijendrasingh/kafka.client.keystore.jks");
		props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "test1234");
		props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "test1234");

		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (long i = 0; i < 2; i++) {
			ProducerRecord<String, String> data = new ProducerRecord<String, String>("test", "key-" + i,
					"message-" + i);
			producer.send(data, new Callback() {

				public void onCompletion(RecordMetadata metadata, Exception exception) {
					System.out.println("Callback -- " + metadata.offset() + " : " + metadata.topic() + " : "
							+ metadata.toString());

				}
			});
		}

		producer.close();
	}
}
