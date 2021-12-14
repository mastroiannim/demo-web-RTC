package com.codetreat.sample.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codetreat.sample.entity.SampleEntity;
import com.codetreat.sample.repository.SampleRepository;
 
@SpringBootApplication
@Service
public class SampleService {
 
    @Autowired
    SampleRepository sampleRepo;
    @RequestMapping(value = "/sample")
    public List<SampleEntity> getAll() {
        return sampleRepo.getAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleService.class, args);
      }
}