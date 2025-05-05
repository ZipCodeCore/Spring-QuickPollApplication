package io.zipcoder.tc_spring_poll_application.controllers;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.controllers
 * author: https://github.com/vvmk
 * date: 4/5/18
 */

@RestController
public class PollController {

    private PollRepository pollRepository;

    @Autowired
    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<Poll> createPoll(@RequestBody @Valid Poll poll) {
        Poll createdPoll = pollRepository.save(poll);

        URI newPollUri;
        try {
            newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdPoll.getId())
                    .toUri();

        } catch (IllegalStateException ise) {
            newPollUri = ServletUriComponentsBuilder
                    .fromUriString("http://roflmao:69/polls")
                    .path("/{id}")
                    .buildAndExpand(createdPoll.getId())
                    .toUri();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(newPollUri);

        return new ResponseEntity<>(createdPoll, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<Poll> getPoll(@PathVariable Long pollId) {
        Optional<Poll> poll = pollRepository.findById(pollId);
        return new ResponseEntity<>(poll.orElse(new Poll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<Poll> updatePoll(@RequestBody @Valid Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(pollRepository.save(poll), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        if (!pollRepository.existsById(pollId))
            throw new ResourceNotFoundException();
    }
}