package com.aavartix.engine_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "discount_bid_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountBidAmount; // Priority value for Max-Heap

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt; // Crucial for FCFS tie-breaking!

    @Column(name = "status", nullable = false)
    private String status = "PENDING"; // PENDING, WON, LOST, REJECTED

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now(); // Captures the exact millisecond the bid hits the DB
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Auction getAuction() { return auction; }
    public void setAuction(Auction auction) { this.auction = auction; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BigDecimal getDiscountBidAmount() { return discountBidAmount; }
    public void setDiscountBidAmount(BigDecimal discountBidAmount) { this.discountBidAmount = discountBidAmount; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}