package com.aavartix.engine_core.dto;

import java.math.BigDecimal;

public class CreateGroupRequest {

    private String groupName;
    private BigDecimal totalPoolAmount;
    private Integer totalMonths;
    private BigDecimal maxDiscountAllowed; // e.g., 30.00 for 30% max bid

    // Getters and Setters
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public BigDecimal getTotalPoolAmount() { return totalPoolAmount; }
    public void setTotalPoolAmount(BigDecimal totalPoolAmount) { this.totalPoolAmount = totalPoolAmount; }

    public Integer getTotalMonths() { return totalMonths; }
    public void setTotalMonths(Integer totalMonths) { this.totalMonths = totalMonths; }

    public BigDecimal getMaxDiscountAllowed() { return maxDiscountAllowed; }
    public void setMaxDiscountAllowed(BigDecimal maxDiscountAllowed) { this.maxDiscountAllowed = maxDiscountAllowed; }
}