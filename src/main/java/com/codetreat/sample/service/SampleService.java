package com.codetreat.sample.service;
 
import java.util.List;

import com.codetreat.sample.entity.SampleEntity;
import com.codetreat.sample.repository.SampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@Service
@EnableJpaRepositories("com.codetreat.sample.repository") //to activate repositories
public class SampleService {
 
    @Autowired
    SampleRepository sampleRepo;
    
    @RequestMapping(value = "/sample")
    public List<SampleEntity> getAll() {
        return sampleRepo.getAll();
    }
}