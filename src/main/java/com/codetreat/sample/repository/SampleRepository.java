package com.codetreat.sample.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.codetreat.sample.entity.SampleEntity;
 
@Repository
public interface SampleRepository extends CrudRepository<SampleEntity, Long> {
 
    @Query("FROM SampleEntity")
    public List<SampleEntity> getAll();
}