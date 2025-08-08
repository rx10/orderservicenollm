package com.technumen.orderservicenollm.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Table(name = "ordtable")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long custId;
    private String ItemName;
    private Double ItemPrice;
    private int ItemQty;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {
    }

    public Order(Long custId, String ItemName, Double ItemPrice, int ItemQty) {
        this.custId = custId;
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemQty = ItemQty;
    }

    public Long getId() {
        return id;
    }

    public Long getCustId() {
        return custId;
    }

    public String getItemName() {
        return ItemName;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public int getItemQty() {
        return ItemQty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public void setItemQty(int itemQty) {
        ItemQty = itemQty;
    }
}
