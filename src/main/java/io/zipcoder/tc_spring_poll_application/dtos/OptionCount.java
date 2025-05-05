package io.zipcoder.tc_spring_poll_application.dtos;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.dtos
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
public class OptionCount {
    private Long optionId;
    private int count;

    public OptionCount(Long optionId) {
        this.optionId = optionId;
        count = 0;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
