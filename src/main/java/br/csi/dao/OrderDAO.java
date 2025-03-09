package br.csi.dao;

import br.csi.entity.Order;
import br.csi.vo.PrimaryItensVo;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDAO {
    private final EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) { em.persist(order); }

    public Order findById(int id) { return em.find(Order.class, id); }

    public List<Order> findAll() {
        String query = "select o from Order o";
        return em.createQuery(query, Order.class).getResultList();
    }

    public List<PrimaryItensVo> findItensMoreSelled() {
        String query = "select new br.csi.vo.PrimaryItensVo(m.name, sum(om.quantity)) from Order o join OrderMenu om on o.id = om.order.id join Menu m on m.id = om.menu.id group by m.name";
        return em.createQuery(query, PrimaryItensVo.class).getResultList();
    }

    public Order joinFetchClient(int id) {
        String query = "select o from Order o join fetch o.client where o.id = :current";
        return em.createQuery(query, Order.class).setParameter("current", id).getSingleResult();
    }

    public void update(Order order) { em.merge(order); }

    public void delete(Order order) { em.remove(order); }

}
