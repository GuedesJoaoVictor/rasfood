package br.csi.entity;

import javax.persistence.*;

@Entity
@Table(name = "menu_category")
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public MenuCategory() {}

    public MenuCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
