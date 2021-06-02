import org.apache.kafka.common.*;
import org.apache.kafka.clients.consumer.*;

import java.util.*;

public class KafkaConsumerApp {
    public static void main(String[] args){

        // Cria a classe Properties para instanciar o Consumer com as configurações desejadas:
        Properties props = new Properties();

        props.put("bootstrap.servers", "ec2-52-15-70-20.us-east-2.compute.amazonaws.com:9092, ec2-52-15-70-20.us-east-2.compute.amazonaws.com:9093");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("fetch.min.bytes", 1);
        props.put("group.id", "");
        props.put("heartbeat.interval.ms", 3000);
        props.put("max.partition.fetch.bytes", 1048576);
        props.put("session.timeout.ms", 30000);
        props.put("auto.offset.reset", "latest");
        props.put("connections.max.idle.ms", 540000);
        props.put("enable.auto.commit", true);
        props.put("exclude.internal.topics", true);
        props.put("max.poll.records", 2147483647);
        props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        props.put("request.timeout.ms", 40000);
        props.put("auto.commit.interval.ms", 5000);
        props.put("fetch.max.wait.ms", 500);
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

        // Atribui partições ao consumer:
        myConsumer.assign(partitions);

        // Recupera a lista de assinaturas do tópico do objeto interno SubscriptionState:
        Set<TopicPartition> assignedPartitions = myConsumer.assignment();

        // Imprima as atribuições da partição:
        printSet(assignedPartitions);

        // Comece a pesquisar por mensagens:
        try {
            while (true){
                ConsumerRecords records = myConsumer.poll(10);
                printRecords(records);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myConsumer.close();
        }

    }

    private static void printSet(Set<TopicPartition> collection){
        if (collection.isEmpty()) {
            System.out.println("Eu não tenho nenhuma partição atribuída ainda...");
        }
        else {
            System.out.println("Eu estou designado a seguir partições:");
            for (TopicPartition partition: collection){
                System.out.println(String.format("Partição: %s no Tópico: %s", Integer.toString(partition.partition()), partition.topic()));
            }
        }
    }

    private static void printRecords(ConsumerRecords<String, String> records)
    {
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(String.format("Tópico: %s, Partição: %d, Offset: %d, Key: %s, Value: %s", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
        }
    }
}
