package com.labtrack.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "test_type")
public class TestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testTypeID")
    private Long testTypeID;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "unit", nullable = false, length = 30)
    private String unit;

    @Column(name = "reference_min", nullable = false, precision = 8, scale = 2)
    private BigDecimal referenceMin;

    @Column(name = "reference_max", nullable = false, precision = 8, scale = 2)
    private BigDecimal referenceMax;

    // Getters and Setters
    public Long getTestTypeID() { return testTypeID; }
    public void setTestTypeID(Long testTypeID) { this.testTypeID = testTypeID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getReferenceMin() { return referenceMin; }
    public void setReferenceMin(BigDecimal referenceMin) { this.referenceMin = referenceMin; }

    public BigDecimal getReferenceMax() { return referenceMax; }
    public void setReferenceMax(BigDecimal referenceMax) { this.referenceMax = referenceMax; }
}