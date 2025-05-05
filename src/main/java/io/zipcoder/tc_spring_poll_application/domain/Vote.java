package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Column(name = "VOTE_ID")
    @GeneratedValue
    @Id
    private Long id;

    @JoinColumn(name = "OPTION_ID")
    @ManyToOne
    private Option option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
