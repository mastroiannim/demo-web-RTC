package com.codetreat.sample.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.codetreat.sample.entity.SampleEntity;
import com.codetreat.sample.service.SampleService;
 
@RestController
public class SampleController {
 
    @Autowired
    SampleService sampleService;

    @RequestMapping("/home")
	public String home() {
	  return "Hello Docker World";
	}
 
    @RequestMapping(value = "/sample")
        public List<SampleEntity> sample() {
        return sampleService.getAll();
    }
}