package com.example.springgrafanaprometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringGrafanaPrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGrafanaPrometheusApplication.class, args);
    }
    @Scheduled(fixedDelay = 5000)
    public void test() throws InterruptedException {
        System.out.println("grafana - thread counting");
        Thread.sleep(3000);
    }
}
