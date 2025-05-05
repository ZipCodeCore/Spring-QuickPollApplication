package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController// marks entity as a controller following REST specs
public class PollController {

    private PollRepository pollRepository;

    @Autowired //The @Autowired annotation allows you to skip configurations elsewhere of what to inject and just does it for you
    public PollController(PollRepository pollRepository){
        this.pollRepository = pollRepository;
    }

    @RequestMapping(value="/polls", method= RequestMethod.GET)//maps web requests to entities with the @RequestMapping
    public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable); //essentially pages the data
        return new ResponseEntity<>(allPolls, HttpStatus.OK);// returns paged data
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) { //@RequestBody tells Spring that the entire request body needs to be converted to an instance of Poll
        poll = pollRepository.save(poll);
        HttpHeaders newHeaders = new HttpHeaders();

        URI newPollUri = ServletUriComponentsBuilder //allows the client to have a way to know the uri of the created poll
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
                newHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(newHeaders, HttpStatus.CREATED);
    }

    @Valid
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @Valid
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyPoll(Long pollId){
        Poll poll = pollRepository.findOne(pollId);
        if(poll == null){
            throw new ResourceNotFoundException("The given poll id " + pollId + " does not exist!");
        }
    }

}
