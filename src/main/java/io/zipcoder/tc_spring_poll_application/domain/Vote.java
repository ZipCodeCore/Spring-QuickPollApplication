package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "VOTE_ID")
    Long Id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    Option options;

    public Vote() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Option getOptions() {
        return options;
    }

    public void setOptions(Option options) {
        this.options = options;
    }
}
