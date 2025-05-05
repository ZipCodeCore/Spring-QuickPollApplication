package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Poll {

        @Id
        @GeneratedValue
        @Column(name  = "POLL_ID")
        private Long id;

        @Column(name = "QUESTION")
        private String question;


        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "POLL_ID")
        @OrderBy
        private Set<Option> options;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Option> getOption() {
        return options;
    }

    public void setOption(Set<Option> option) {
        this.options = option;
    }
}


