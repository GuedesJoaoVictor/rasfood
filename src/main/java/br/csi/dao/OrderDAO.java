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

    public List<Object[]> findItensMoreSelled() {
        String query = "select m.name, sum(om.quantity) from Order o join OrderMenu om on o.id = om.order.id join Menu m on m.id = om.menu.id group by m.name";
        return em.createQuery(query, Object[].class).getResultList();
    }

    public void update(Order order) { em.merge(order); }

    public void delete(Order order) { em.remove(order); }

}
