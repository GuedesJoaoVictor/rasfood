package br.csi.service.test;

import br.csi.dao.AddressDAO;
import br.csi.dao.ClientDAO;
import br.csi.dao.MenuDAO;
import br.csi.dao.OrderDAO;
import br.csi.entity.Address;
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
        AddressDAO addressDAO = new AddressDAO(em);

        Address address = new Address("970431920", "Ferndandes Vieira", "81", "RS", "SM");
        addressDAO.save(address);
        Client guedes = new Client("Guedes", "05265294059", address);
        clientDAO.save(guedes);
        Order order = new Order(guedes);
        order.setOrder(new OrderMenu(menuDAO.findById(1), 2));
        orderDAO.save(order);
        System.out.println(order);
        em.getTransaction().commit();
        em.close();
    }
}
