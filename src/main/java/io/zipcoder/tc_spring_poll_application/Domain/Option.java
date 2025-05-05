package io.zipcoder.tc_spring_poll_application.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {

    @Id
    @GeneratedValue
    @Column(name = "OPTION_ID")
    private long id;

    @Column(name = "OPTION_VALUE")
    private String value;


    public Option(){
        this.id = id;
        this.value = value;
    }

    public long getId(){
        return this.id;
    }
    public String getValue(){
        return this.value;
    }
    public void setId(long id1){
        this.id = id1;
    }
    public void setValue(String value1){
        this.value = value1;
    }
}
