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
    private long id;

    @Column(name = "QUESTION")
    @NotEmpty
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6)
    private Set<Option> options;

    public Poll(){
        this.id = id;
        this.question = question;
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Set<Option> getOptions() {
        return options;
    }
    public void setId(long id1){
        this.id = id1;
    }
    public void setQuestion(String question1){
        this.question = question1;
    }
    public void setOptions(Set<Option> options1){
        this.options = options1;
    }
}
