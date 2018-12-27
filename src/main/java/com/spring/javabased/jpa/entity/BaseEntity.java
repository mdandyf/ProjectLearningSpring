package com.spring.javabased.jpa.entity;

import com.spring.javabased.jpa.configuration.EntityColumnPositionerCustomizer;
import com.spring.javabased.jpa.configuration.ColumnPosition;
import org.eclipse.persistence.annotations.Customizer;

import javax.persistence.*;
import java.util.Calendar;

@MappedSuperclass
@Customizer(EntityColumnPositionerCustomizer.class)
public class BaseEntity {

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnPosition(position = 90)
    private Calendar createdDate;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnPosition(position = 91)
    private Calendar updatedDate;

    public Calendar getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }
    public Calendar getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }
}
