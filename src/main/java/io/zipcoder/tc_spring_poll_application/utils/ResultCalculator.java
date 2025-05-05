package io.zipcoder.tc_spring_poll_application.utils;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import io.zipcoder.tc_spring_poll_application.dtos.OptionCount;
import io.zipcoder.tc_spring_poll_application.dtos.VoteResult;

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.utils
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
public class ResultCalculator extends AbstractCollection<OptionCount> {

    private HashMap<Long, OptionCount> optionCounts;
    private int size;

    public ResultCalculator() {
        optionCounts = new HashMap<>();
    }

    public void add(Option option) {
        size++;
        Long oId = option.getId();

        Optional<OptionCount> check = Optional.ofNullable(getOptionCountById(oId));
        OptionCount workingCount = check.orElse(new OptionCount(oId));

        //an exercise in restraint to maintain the integrity of OptionCount as a pure DTO
        workingCount.setCount(workingCount.getCount() + 1);

        optionCounts.put(oId, workingCount);
    }

    public VoteResult calculate() {
        return new VoteResult(size(), optionCounts.values());
    }

    public OptionCount getOptionCountById(Long id) {
        return optionCounts.get(id);
    }

    @Override
    public Iterator<OptionCount> iterator() {
        return optionCounts.values().iterator();
    }

    @Override
    public int size() {
        return size;
    }
}
