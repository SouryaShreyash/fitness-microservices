package com.fitness.activityservice.repository;

import com.fitness.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Activityrepository  extends MongoRepository<Activity,String> {

    List<Activity> findByUserId(String userId);
}
