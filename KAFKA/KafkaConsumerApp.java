// Esse projeto é Academico - Realizado nos cursos da Data Sciencie Academy
// https://www.datascienceacademy.com.br/


import org.apache.kafka.common.*;
import org.apache.kafka.clients.consumer.*;

import java.util.*;

public class KafkaConsumerApp {
    public static void main(String[] args){

        // Cria a classe Properties para instanciar o Consumer com as configurações desejadas:
        Properties props = new Properties();

        
        props.put("fetch.min.bytes", 1);
        props.put("group.id", "");
        props.put("heartbeat.interval.ms", 3000);
        props.put("max.partition.fetch.bytes", 1048576);
        props.put("session.timeout.ms", 30000);

        props.put("metadata.max.age.ms", 300000);
        props.put("reconnect.backoff.ms", 50);
        props.put("retry.backoff.ms", 100);
        props.put("client.id", "");


        // Cria uma instância do KafkaConsumer e configura com propriedades.
        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<String, String>(props);

        // Cria uma lista de assinaturas de tópicos por partição:
        ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
        partitions.add(new TopicPartition("topico5", 0));
        partitions.add(new TopicPartition("topico5", 1));
        partitions.add(new TopicPartition("topico5", 2));

 
}
