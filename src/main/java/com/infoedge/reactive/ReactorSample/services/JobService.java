package com.infoedge.reactive.ReactorSample.services;

import java.time.Duration;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infoedge.reactive.ReactorSample.dto.JobDTO;
import com.infoedge.reactive.ReactorSample.model.Job;
import com.infoedge.reactive.ReactorSample.repositories.JobRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JobService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobRepository jobRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

	public void create(JobDTO jobDTO) {
		LOGGER.info("Inside Job Service create method");
		Job job = modelMapper.map(jobDTO, Job.class);
		jobRepository.save(job).subscribe();
	}

	public Mono<JobDTO> findById(String id) {
		Mono<Job> job = jobRepository.findById(id);
		return modelMapper.map(job, new TypeToken<List<JobDTO>>() {
		}.getType());
	}

	public Flux<JobDTO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<JobDTO> findAll() {
		Flux<Job> jobs = jobRepository.findAll();
		Flux<JobDTO> jobsDto =  modelMapper.map(jobs, new TypeToken<Flux<JobDTO>>() {
		}.getType());
		jobsDto = jobsDto.delayElements(Duration.ofMillis(2000));
		return jobsDto;
	}

	public Mono<JobDTO> update(JobDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<JobDTO> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
