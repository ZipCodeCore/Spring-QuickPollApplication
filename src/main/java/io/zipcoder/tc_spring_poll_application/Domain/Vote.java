package io.zipcoder.tc_spring_poll_application.Domain;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    public Vote(){
        this.id = id;
        this.option = option;
    }
    public long getId() {
        return id;
    }

    public Option getOption() {
        return option;
    }
    public void setId(long id1){
        this.id = id1;
    }
    public void setOption(Option option1){
        this.option = option1;
    }



}
