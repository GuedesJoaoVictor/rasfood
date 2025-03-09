package br.csi.dao;

import br.csi.entity.MenuCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class MenuCategoryDAO {
    private final EntityManager entityManager;

    public MenuCategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(MenuCategory category) {
        entityManager.persist(category);
    }

    public MenuCategory findById(int id) {
        return entityManager.find(MenuCategory.class, id);
    }

    public List<MenuCategory> findAll() {
        String query = "select mc from MenuCategory mc";
        return entityManager.createQuery(query, MenuCategory.class).getResultList();
    }

    public void update(MenuCategory category) {
        entityManager.merge(category);
    }

    public void delete(MenuCategory category) {
        entityManager.remove(category);
    }

}
