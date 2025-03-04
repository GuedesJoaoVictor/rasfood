package br.csi.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "final_price")
    private BigDecimal finalPrice;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    private Client client;

    public Order() {}

    public Order(BigDecimal finalPrice, LocalDateTime createdAt, Client client) {
        this.finalPrice = finalPrice;
        this.createdAt = createdAt;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", finalPrice=" + finalPrice +
                ", createdAt=" + createdAt +
                ", client=" + client +
                '}';
    }
}
