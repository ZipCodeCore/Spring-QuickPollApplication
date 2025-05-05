package io.zipcoder.tc_spring_poll_application.Domain;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    Long id;

    @Column(name = "QUESTION")
    @NotEmpty
    String question;

    @JoinColumn(name = "POLL_ID")
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy
    @Size(min=2, max = 6)
    Set<Option> options;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
