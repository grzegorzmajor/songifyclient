package ovh.major.songifyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SongifyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongifyClientApplication.class, args);
    }

}
