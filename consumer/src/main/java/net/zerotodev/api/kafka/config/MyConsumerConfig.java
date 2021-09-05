package net.zerotodev.api.kafka.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.messaging.PollableChannel;
@Configuration
public class MyConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;
    @Value("${spring.kafka.topic}")
    private String topic;
    @Bean
    public PollableChannel channel(){
        return new QueueChannel();
    }
    @Bean
    public Map<String, Object> config(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "QUIZ-SHOW");
        return properties;
    }
    @Bean
    public ConsumerFactory<?, ?> factory(){
        return new DefaultKafkaConsumerFactory<>(config());
    }
    @Bean
    public ConcurrentMessageListenerContainer<String, String> container(){
        ContainerProperties properties = new ContainerProperties(topic);
        return (ConcurrentMessageListenerContainer<String, String>)
                new ConcurrentMessageListenerContainer<>(factory(), properties);
    }
    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String> adapter(){
        KafkaMessageDrivenChannelAdapter<String, String> adapter =
                new KafkaMessageDrivenChannelAdapter<>(container());
        adapter.setOutputChannel(channel());
        return adapter;
    }
}















