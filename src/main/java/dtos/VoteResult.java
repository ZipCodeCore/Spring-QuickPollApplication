package dtos;

import io.zipcoder.tc_spring_poll_application.domain.Option;

import java.util.Collection;

public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> results;

    public int getTotalVotes() {
        int count = 0;
        for(OptionCount optionCount : results){
            count = count + optionCount.getCount();
        }
        totalVotes = count;
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
