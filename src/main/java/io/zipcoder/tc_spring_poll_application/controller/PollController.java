package io.zipcoder.tc_spring_poll_application.controller;
import io.zipcoder.tc_spring_poll_application.domain.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import javax.validation.Valid;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
public class PollController {
   private PollRepository pollRepository;


   @Autowired
    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(Pageable P) {
        Iterable<Poll> allPolls = pollRepository.findAll((Iterable<Long>) P);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody @Valid Poll poll) {
        poll = pollRepository.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(poll.getId()).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Poll p = pollRepository.findOne(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody @Valid Poll poll, @PathVariable Long pollId) {
        // Save the entity
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollRepository.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void verifyPoll(Long pollId){
        if(!pollRepository.exists(pollId)) {
            throw new ResourceNotFoundException();
        }
    }






}
