package br.csi.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_menu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @Column(name = "register_value")
    private BigDecimal registerValue;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderMenu() {}

    public OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.registerValue = menu.getPrice();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public BigDecimal getRegisterValue() {
        return registerValue;
    }

    public void setRegisterValue(BigDecimal registerValue) {
        this.registerValue = registerValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "id=" + id +
                ", menu=" + menu +
                ", registerValue=" + registerValue +
                ", quantity=" + quantity +
                '}';
    }
}
