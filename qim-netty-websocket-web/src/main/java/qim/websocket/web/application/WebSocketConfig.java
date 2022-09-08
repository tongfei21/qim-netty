package qim.websocket.web.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qim.netty.server.websocket.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        System.out.println("----------------------start-------------------");
        return new ServerEndpointExporter();
    }
}
