package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController {
    public PollRepository pollRepository;

    @Autowired
    public PollController(PollRepository pollRepository){
        this.pollRepository = pollRepository;
    }

    @GetMapping(value="/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(Pageable P) {
        Page<Poll> allPolls = pollRepository.findAll(P);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @PostMapping(value="/polls")
    public ResponseEntity<?> createPoll(@RequestBody @Valid Poll poll) {
        poll = pollRepository.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        HttpHeaders hh = new HttpHeaders();
        hh.setLocation(newPollUri);
        return new ResponseEntity<>(hh, HttpStatus.CREATED);
    }

    @GetMapping(value="/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @PutMapping(value="/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody @Valid Poll poll, @PathVariable Long pollId) {
        // Save the entity
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollRepository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyPoll(Long pollId){
        if(!pollRepository.exists(pollId)){
            throw new ResourceNotFoundException();
        }
    }

}
