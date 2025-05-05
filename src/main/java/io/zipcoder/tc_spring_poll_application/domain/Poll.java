package io.zipcoder.tc_spring_poll_application.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long id;

    @NotEmpty
    @Column(name = "QUESTION")
    private String question;

    @Size(min=2, max = 6)
    @OneToMany(cascade = CascadeType.ALL)// indicates that a Poll instance can contain zero or more Option instances. The CascadeType.All indicates that any database operations such as persist, remove, or merge on a Poll instance needs to be propagated to all related Option instances
    @JoinColumn(name = "POLL_ID")// indicates this entity is the owner of the relationship. It has a key to the column with options
    @OrderBy// orders by ASC default
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
