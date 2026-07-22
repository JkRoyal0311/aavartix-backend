package com.aavartix.engine_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "wallet_balance", precision = 12, scale = 2)
    private BigDecimal walletBalance = BigDecimal.ZERO;

    @Column(name = "trust_score")
    private Integer trustScore = 100; // Starts at a default of 100

    @Column(name = "risk_category")
    private String riskCategory = "MEDIUM"; // LOW, MEDIUM, HIGH

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public BigDecimal getWalletBalance() { return walletBalance; }
    public void setWalletBalance(BigDecimal walletBalance) { this.walletBalance = walletBalance; }

    public Integer getTrustScore() { return trustScore; }
    public void setTrustScore(Integer trustScore) { this.trustScore = trustScore; }

    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}