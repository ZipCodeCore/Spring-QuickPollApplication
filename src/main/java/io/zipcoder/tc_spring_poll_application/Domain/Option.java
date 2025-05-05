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
    Long id;
    @Column(name = "OPTION_VALUE")
    String value;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }






}
