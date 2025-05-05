package io.zipcoder.tc_spring_poll_application.dtos;

import java.util.Collection;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.dtos
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> results;

    public VoteResult(int totalVotes, Collection<OptionCount> results) {
        this.totalVotes = totalVotes;
        this.results = results;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Collection<OptionCount> getResults() {
        return results;
    }

    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }
}
