package br.com.javanei.retrocenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAsync
@SpringBootApplication
public class RetroCenter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RetroCenter.class, args);
    }
}
