package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController {

    PollRespository pollRespository;

    public PollController() {
    }

    @Autowired
    public PollController(PollRespository pollRespository) {
        this.pollRespository = pollRespository;
    }

    @Valid
    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = pollRespository.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        return new ResponseEntity<>(newPollUri, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRespository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRespository.findOne(pollId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @Valid
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        Poll p = pollRespository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRespository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyPoll(Long PollId) {
        if (pollRespository.findOne(PollId) == null){
            throw new ResourceNotFoundException("Not Found");
    }
    }
}

