package ovh.major.songifyclient.client.domain.configurations;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}