package br.csi.dao;

import br.csi.entity.Menu;
import br.csi.entity.MenuCategory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collections;
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
        try {
            return this.entityManager.find(Menu.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Menu> findAll() {
        try {
            String query = "select m from Menu m";
            return entityManager.createQuery(query, Menu.class).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Menu> findByPrice(BigDecimal price) {
        try {
            String query = "select m from Menu m where m.price = :price";
            return entityManager.createQuery(query, Menu.class).setParameter("price", price).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Menu> findByPrice(BigDecimal min, BigDecimal max) {
        try {
            String query = "select m from Menu m where m.price >= :min and m.price <= :max";
            return entityManager.createQuery(query, Menu.class).setParameter("min", min).setParameter("max", max).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Menu findByName(String name) {
        try {
            String query = "select m from Menu m where upper(m.name) = upper(:name)";
            return entityManager.createQuery(query, Menu.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void update(Menu menu) {
        try {
            this.entityManager.merge(menu);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Menu menu) {
        try {
            this.entityManager.remove(menu);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
