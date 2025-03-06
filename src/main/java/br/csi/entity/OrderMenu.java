package br.csi.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_menu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Menu menu;
    private BigDecimal price;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderMenu() {}

    public OrderMenu(Menu menu, int price) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = menu.getPrice().multiply(BigDecimal.valueOf(price));
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
