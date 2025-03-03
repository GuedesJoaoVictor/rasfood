package br.csi.dao;

import br.csi.entity.Menu;
import br.csi.entity.MenuCategory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MenuDAO {
    private final EntityManager entityManager;

    public MenuDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Menu menu) {
        this.entityManager.persist(menu);
        System.out.println("Entidade cadastrada: " + menu);
    }

    public Menu findById(int id) {
        return this.entityManager.find(Menu.class, id);
    }

    public List<Menu> findAll() {
        String query = "select m from Menu m";
        return entityManager.createQuery(query, Menu.class).getResultList();
    }

    public List<Menu> findByPrice(BigDecimal price) {
        String query = "select m from Menu m where m.price = :price";
        return entityManager.createQuery(query, Menu.class).setParameter("price", price).getResultList();
    }

    public List<Menu> findByPrice(BigDecimal min, BigDecimal max) {
        String query = "select m from Menu m where m.price >= :min and m.price <= :max";
        return entityManager.createQuery(query, Menu.class).setParameter("min", min).setParameter("max", max).getResultList();
    }

    public void update(Menu menu) {
        this.entityManager.merge(menu);
    }

    public void delete(Menu menu) {
        this.entityManager.remove(menu);
    }
}
