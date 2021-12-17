package com.codetreat.sample;


import com.baeldung.SocketHandler;
import com.codetreat.sample.controller.SampleController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@SpringBootApplication
public class SampleApplication implements WebSocketConfigurer{

  @Autowired
  SampleController controller;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(new SocketHandler(), "/socket").setAllowedOrigins("*");
  }

  public static void main(String[] args) {
    SpringApplication.run(SampleApplication.class, args);
  }


}
