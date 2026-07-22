package com.aavartix.engine_core.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private ChitGroup group;

    @Column(name = "month_number", nullable = false)
    private Integer monthNumber;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    private String status = "SCHEDULED"; // SCHEDULED, LIVE, CALCULATING, CLOSED

    @Column(name = "winning_bid_id")
    private UUID winningBidId; // Will be null until the FCFS/Max-Heap calculates the winner

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public ChitGroup getGroup() { return group; }
    public void setGroup(ChitGroup group) { this.group = group; }

    public Integer getMonthNumber() { return monthNumber; }
    public void setMonthNumber(Integer monthNumber) { this.monthNumber = monthNumber; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public UUID getWinningBidId() { return winningBidId; }
    public void setWinningBidId(UUID winningBidId) { this.winningBidId = winningBidId; }
}