package ovh.major.songifyclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import ovh.major.songifyclient.client.SongifyProxy;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class SongifyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongifyClientApplication.class, args);
    }

}
