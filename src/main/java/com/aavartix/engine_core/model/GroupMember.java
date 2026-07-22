package com.aavartix.engine_core.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "group_members")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private ChitGroup group;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "has_won_pot")
    private Boolean hasWonPot = false;

    @Column(name = "won_month")
    private Integer wonMonth;

    @Column(name = "total_paid_in", precision = 12, scale = 2)
    private BigDecimal totalPaidIn = BigDecimal.ZERO;

    @Column(name = "round_robin_position")
    private Integer roundRobinPosition; // Crucial for our Round Robin starvation-prevention algorithm

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public ChitGroup getGroup() { return group; }
    public void setGroup(ChitGroup group) { this.group = group; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Boolean getHasWonPot() { return hasWonPot; }
    public void setHasWonPot(Boolean hasWonPot) { this.hasWonPot = hasWonPot; }

    public Integer getWonMonth() { return wonMonth; }
    public void setWonMonth(Integer wonMonth) { this.wonMonth = wonMonth; }

    public BigDecimal getTotalPaidIn() { return totalPaidIn; }
    public void setTotalPaidIn(BigDecimal totalPaidIn) { this.totalPaidIn = totalPaidIn; }

    public Integer getRoundRobinPosition() { return roundRobinPosition; }
    public void setRoundRobinPosition(Integer roundRobinPosition) { this.roundRobinPosition = roundRobinPosition; }
}