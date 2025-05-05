package io.zipcoder.tc_spring_poll_application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // this means that a class can be mapped to a table. Just is a marker like Serializable
public class Option {

    @Id // specifies primary key of entity (primary key is a special column)
    @GeneratedValue // configures the way of increment of the specified column (field)
    @Column(name = "OPTION_ID")// specifies mapped column for a persistence property. Without this annotation the framework assumes the field's variable-name is the persistent property
    private Long id;

    @Column(name = "OPTION_VALUE")// specifies mapped column for persistence value
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
