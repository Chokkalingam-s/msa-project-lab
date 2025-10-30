package com.example.funds.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // NEFT or IMPS
    private BigDecimal amount;
    private String fromAcc;
    private String toAcc;
    private LocalDateTime created = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getFromAcc() { return fromAcc; }
    public void setFromAcc(String fromAcc) { this.fromAcc = fromAcc; }
    public String getToAcc() { return toAcc; }
    public void setToAcc(String toAcc) { this.toAcc = toAcc; }
    public LocalDateTime getCreated() { return created; }
}
