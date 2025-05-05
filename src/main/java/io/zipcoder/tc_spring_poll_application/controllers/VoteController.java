package io.zipcoder.tc_spring_poll_application.controllers;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.controllers
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
@RestController
public class VoteController {
    private VoteRepository voteRepository;

    @Autowired
    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<Vote> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);

        URI newVoteUri;
        try {
            newVoteUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(vote.getId())
                    .toUri();

        } catch (IllegalStateException ise) {
            newVoteUri = ServletUriComponentsBuilder
                    .fromUriString("http://roflmao:69/polls")
                    .path("/{id}")
                    .buildAndExpand(vote.getId())
                    .toUri();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(newVoteUri);

        return new ResponseEntity<>(vote, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vote>> getVotes() {
        return new ResponseEntity<>(voteRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}/votes/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vote>> getVotesForPoll(@PathVariable Long pollId) {
        return new ResponseEntity<>(voteRepository.findVotesByPoll(pollId), HttpStatus.OK);
    }
}
