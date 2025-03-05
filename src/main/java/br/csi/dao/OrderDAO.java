package br.csi.dao;

import br.csi.entity.Client;
import br.csi.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDAO {
    private final EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) { em.persist(order); }

    public void findById(int id) { em.find(Order.class, id); }

    public List<Order> findAll() {
        String query = "select o from Order o";
        return em.createQuery(query, Order.class).getResultList();
    }

    public void update(Order order) { em.merge(order); }

    public void delete(Order order) { em.remove(order); }

}
