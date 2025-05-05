package io.zipcoder.tc_spring_poll_application.dto;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.domain.Vote;

import java.util.*;

public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> results;

    public VoteResult() {
        this.results = new ArrayList<>();
        this.totalVotes = 0;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void addTotalVotes(int votes) {
        this.totalVotes += votes;
    }

    public Collection<OptionCount> getResults() {
        return results;
    }

    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }

    public void addResult(OptionCount optionCount){
        this.results.add(optionCount);
        this.addTotalVotes(optionCount.getCount());
    }

    public void mapVotes(Iterable<Vote> votes) {
        Map<Option, Integer> map = new HashMap<>();
        for (Vote vote : votes) {
            if (map.containsKey(vote.getOption())) map.put(vote.getOption(), map.get(vote.getOption()) + 1);
            else map.put(vote.getOption(), 1);
        }
        for (Option option : map.keySet()) this.addResult(new OptionCount(option.getId(), map.get(option)));
//        return getResults();
    }
}
