// Esse projeto é Academico - Realizado nos cursos da Data Sciencie Academy
// https://www.datascienceacademy.com.br/

import org.apache.kafka.clients.producer.*;

import java.text.*;
import java.util.*;

public class KafkaProducerApp {

    public static void main(String[] args){

        // Cria a classe Properties para instanciar o Producer com as configurações desejadas:
        Properties props = new Properties();

        props.put("acks", "1");
        props.put("buffer.memory", 33554432);
        props.put("compression.type", "none");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("client.id", "");
        props.put("linger.ms", 0);
        props.put("max.block.ms", 60000);
        props.put("retry.backoff.ms", 5);


        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);

        DateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        String topic = "topico3";

        // Número de registros para enviar
        int numberOfRecords = 1000;


}

