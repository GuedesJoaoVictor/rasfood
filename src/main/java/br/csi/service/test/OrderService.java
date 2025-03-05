package br.csi.service.test;

import br.csi.dao.ClientDAO;
import br.csi.dao.MenuDAO;
import br.csi.dao.OrderDAO;
import br.csi.entity.Client;
import br.csi.entity.Order;
import br.csi.entity.OrderMenu;
import br.csi.utils.ChargeDataUtil;
import br.csi.utils.JPAUtil;

import javax.persistence.EntityManager;

public class OrderService {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerRasFood();
        em.getTransaction().begin();
        ChargeDataUtil.registerCategory(em);
        ChargeDataUtil.registerProductMenu(em);

        MenuDAO menuDAO = new MenuDAO(em);
        ClientDAO clientDAO = new ClientDAO(em);
        OrderDAO orderDAO = new OrderDAO(em);

        Client guedes = new Client("Guedes", "05265294059", "970431920");
        Order order = new Order(guedes);
        order.setOrder(new OrderMenu(order, menuDAO.findById(1), 2));
        clientDAO.save(guedes);
        orderDAO.save(order);
        em.close();
    }
}
