package br.csi.dao;

import br.csi.entity.Menu;

import javax.persistence.EntityManager;
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

    public void update(Menu menu) {
        this.entityManager.merge(menu);
    }

    public void delete(Menu menu) {
        this.entityManager.remove(menu);
    }
}
