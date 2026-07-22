package com.aavartix.engine_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chit_groups")
public class ChitGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "total_pool_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPoolAmount;

    @Column(name = "monthly_contribution", nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyContribution;

    @Column(name = "total_months", nullable = false)
    private Integer totalMonths;

    @Column(name = "current_month")
    private Integer currentMonth = 1;

    @Column(name = "max_discount_allowed", precision = 5, scale = 2)
    private BigDecimal maxDiscountAllowed;

    @Column(name = "status")
    private String status = "FORMING"; // FORMING, ACTIVE, COMPLETED

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public BigDecimal getTotalPoolAmount() { return totalPoolAmount; }
    public void setTotalPoolAmount(BigDecimal totalPoolAmount) { this.totalPoolAmount = totalPoolAmount; }

    public BigDecimal getMonthlyContribution() { return monthlyContribution; }
    public void setMonthlyContribution(BigDecimal monthlyContribution) { this.monthlyContribution = monthlyContribution; }

    public Integer getTotalMonths() { return totalMonths; }
    public void setTotalMonths(Integer totalMonths) { this.totalMonths = totalMonths; }

    public Integer getCurrentMonth() { return currentMonth; }
    public void setCurrentMonth(Integer currentMonth) { this.currentMonth = currentMonth; }

    public BigDecimal getMaxDiscountAllowed() { return maxDiscountAllowed; }
    public void setMaxDiscountAllowed(BigDecimal maxDiscountAllowed) { this.maxDiscountAllowed = maxDiscountAllowed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}