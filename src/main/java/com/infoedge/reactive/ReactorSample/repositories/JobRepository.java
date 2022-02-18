package com.infoedge.reactive.ReactorSample.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.infoedge.reactive.ReactorSample.model.Job;

public interface JobRepository extends ReactiveMongoRepository<Job, String> {

}
