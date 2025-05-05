package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.domain
 * author: https://github.com/vvmk
 * date: 4/5/18
 */
@Entity
public class Option {

    @Id
    @GeneratedValue
    @Column(name = "OPTION_ID")
    private Long Id;

    @Column(name = "OPTION_VALUE")
    private String value;

    public Option() {
        //y
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
