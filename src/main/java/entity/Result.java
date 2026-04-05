package com.labtrack.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultID")
    private Long resultID;

    @OneToOne
    @JoinColumn(name = "sampleID", nullable = false, unique = true)
    private com.labtrack.entity.Sample sample;

    @Column(name = "value", nullable = false, precision = 8, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "flag", nullable = false)
    private Flag flag;

    @Column(name = "entered_at")
    private LocalDateTime enteredAt;

    public enum Flag {
        HIGH, LOW, NORMAL
    }

    @PrePersist
    protected void onCreate() {
        enteredAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getResultID() { return resultID; }
    public void setResultID(Long resultID) { this.resultID = resultID; }

    public com.labtrack.entity.Sample getSample() { return sample; }
    public void setSample(com.labtrack.entity.Sample sample) { this.sample = sample; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public Flag getFlag() { return flag; }
    public void setFlag(Flag flag) { this.flag = flag; }

    public LocalDateTime getEnteredAt() { return enteredAt; }
    public void setEnteredAt(LocalDateTime enteredAt) { this.enteredAt = enteredAt; }
}