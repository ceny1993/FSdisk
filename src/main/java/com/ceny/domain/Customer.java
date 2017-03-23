package com.ceny.domain;

import javax.persistence.*;

/**
 * Created by chensongkui on 2017/3/21.
 */
@Deprecated
@Entity
@Table(name = "mytable")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String last_ame;

    @Column(length = 1000000)
    private String test_content;

    @Lob
    private String long_text;

    //not happy
    @Transient
    private TmpClass tmpClass;

    protected Customer() {}

    public Customer(String firstName, String lastName, TmpClass tmpClass) {
        this.first_name = firstName;
        this.last_ame = lastName;
        this.tmpClass = tmpClass;
        this.test_content = "test";
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, first_name, last_ame);
    }
}
