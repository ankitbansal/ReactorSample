package com.infoedge.reactive.ReactorSample.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infoedge.reactive.ReactorSample.dto.JobDTO;
import com.infoedge.reactive.ReactorSample.services.JobService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jobs")
public class JobResource {

	@Autowired
	private JobService jobService;

	private static final Logger LOGGER = LoggerFactory.getLogger(JobResource.class);

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody JobDTO jobDTO) {
		LOGGER.info("Inside Job Resource create method");
		jobService.create(jobDTO);
		LOGGER.info("Finished Job Resource create method");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mono<JobDTO>> findById(@PathVariable("id") String id) {
		Mono<JobDTO> e = jobService.findById(id);
		HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Mono<JobDTO>>(e, status);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public Flux<JobDTO> findByName(@PathVariable("name") String name) {
		return jobService.findByName(name);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<JobDTO> findAll() {
		Flux<JobDTO> emps = jobService.findAll();
		return emps;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Mono<JobDTO> update(@RequestBody JobDTO e) {
		return jobService.update(e);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) {
		jobService.delete(id).subscribe();
	}
}
