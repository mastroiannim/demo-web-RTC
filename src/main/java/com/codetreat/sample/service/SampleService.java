package com.codetreat.sample.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.codetreat.sample.entity.SampleEntity;
import com.codetreat.sample.repository.SampleRepository;
 
@Service
public class SampleService {
 
    @Autowired
    SampleRepository sampleRepo;
 
        public List<SampleEntity> getAll() {
        return sampleRepo.getAll();
    }
}