package facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Z先生 on 2016/10/31.
 */
@SpringBootApplication
@ImportResource("classpath:dubbo-producer.xml")
public class Start {
    public static void main(String[] args) {
         SpringApplication.run(Start.class, args);
    }
}
