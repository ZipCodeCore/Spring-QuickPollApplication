package io.zipcoder.tc_spring_poll_application.Domain;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    Option option;

    public Long getId() {
        return id;
    }

    public Option getOption() {
        return option;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
