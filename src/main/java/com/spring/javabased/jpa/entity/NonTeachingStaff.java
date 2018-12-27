package com.spring.javabased.jpa.entity;

import com.spring.javabased.jpa.configuration.ColumnPosition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name="NonTeachingStaff")
@PrimaryKeyJoinColumn(referencedColumnName = "staff_id")
public class NonTeachingStaff extends Staff {

    @ColumnPosition(position = 2)
    @Column(name="staff_area_expertise")
    private String areaexpertise;

    public NonTeachingStaff(){}

    public NonTeachingStaff(int sid, String sname, String areaexpertise) {
        super(sid, sname);
        this.areaexpertise = areaexpertise;
        this.setCreatedDate(Calendar.getInstance());
        this.setUpdatedDate(Calendar.getInstance());
    }

    public String getAreaexpertise() {
        return areaexpertise;
    }

    public void setAreaexpertise(String areaexpertise) {
        this.areaexpertise = areaexpertise;
    }
}
