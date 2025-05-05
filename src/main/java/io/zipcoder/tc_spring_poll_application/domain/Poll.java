package io.zipcoder.tc_spring_poll_application.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll {

    @Column(name = "POLL_ID")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "QUESTION")
    @NotEmpty
    private String question;

    @OrderBy
    @JoinColumn(name = "OPTION_ID")
    @OneToMany(cascade = CascadeType.ALL)
    @Size(min=2, max=6)
    private Set<Option> options;

    public Long getId() {
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

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
