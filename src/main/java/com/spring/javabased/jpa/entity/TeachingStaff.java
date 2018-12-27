package com.spring.javabased.jpa.entity;


import com.spring.javabased.jpa.configuration.ColumnPosition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name="TeachingStaff")
@PrimaryKeyJoinColumn(referencedColumnName = "staff_id")
public class TeachingStaff extends Staff {

    @ColumnPosition(position = 2)
    @Column(name="staff_qualification")
    private String qualification;

    @ColumnPosition(position = 3)
    @Column(name="staff_subject_expertise")
    private String subjectexpertise;

    public TeachingStaff(){}

    public TeachingStaff(int sid, String sname, String qualification, String subjectexpertise) {
        super(sid, sname);
        this.qualification = qualification;
        this.subjectexpertise = subjectexpertise;
        this.setCreatedDate(Calendar.getInstance());
        this.setUpdatedDate(Calendar.getInstance());
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubjectexpertise() {
        return subjectexpertise;
    }

    public void setSubjectexpertise(String subjectexpertise) {
        this.subjectexpertise = subjectexpertise;
    }
}
