package com.codetreat.sample;


import com.codetreat.sample.controller.SampleController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

 
@SpringBootApplication
public class SampleApplication {

  @Autowired
  SampleController controller;

  
  public static void main(String[] args) {
    SpringApplication.run(SampleApplication.class, args);
  }


  @Bean
  public ServletServerContainerFactoryBean createWebSocketContainer() {
    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
    container.setMaxBinaryMessageBufferSize(1024000);
    return container;
  }

}
