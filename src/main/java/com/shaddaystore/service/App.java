package com.shaddaystore.service;
/* este paquete se creó dentro del java de la carpeta main */

import com.shaddaystore.service.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class App {
    public static void main (String[] args){
        SpringApplication.run(App.class, args);
    }
}
