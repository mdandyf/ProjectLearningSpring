package com.spring.javabased.configuration.jpa.entity;

import com.spring.javabased.configuration.jpa.configuration.ColumnPosition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Staff")
@Inheritance( strategy = InheritanceType.JOINED )
public class Staff extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ColumnPosition(position = 1)
    @Column(name="staff_id")
    private int sid;

    @ColumnPosition(position = 2)
    @Column(name="staff_name")
    private String sname;

    public Staff(){}

    public Staff(int sid, String sname) {
        super();
        this.sid = sid;
        this.sname = sname;
    }

    public void setSid(int sid) { this.sid = sid; }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

}
