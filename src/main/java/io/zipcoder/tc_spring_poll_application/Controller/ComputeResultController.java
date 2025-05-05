package io.zipcoder.tc_spring_poll_application.Controller;

import dtos.OptionCount;
import dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.Domain.Vote;
import io.zipcoder.tc_spring_poll_application.Repositories.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {
    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);

        //TODO: Implement algorithm to count votes

        int counter = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>(); // create new hashmap that contains the long id and option count that contains long id and count
        for (Vote v: allVotes) { // for every vote add to the counter
            counter++;
            OptionCount optionCount = tempMap.get(v.getOption().getId()); // assign the option count to the id of the vote's option
            if(optionCount == null){
                optionCount = new OptionCount(); // if we dont have that option then create it
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1); // then set the count
        }
        voteResult.setTotalVotes(counter); // set the total votes to the counter we created
        voteResult.setResults(tempMap.values()); // get the values of the results
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
