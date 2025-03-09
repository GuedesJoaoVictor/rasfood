package br.csi.service.test;

import br.csi.dao.AddressDAO;
import br.csi.dao.ClientDAO;
import br.csi.dao.OrderDAO;
import br.csi.entity.Order;
import br.csi.utils.ChargeDataUtil;
import br.csi.utils.JPAUtil;

import javax.persistence.EntityManager;

public class OrderService {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerRasFood();
        em.getTransaction().begin();
        ChargeDataUtil.registerCategory(em);
        ChargeDataUtil.registerProductMenu(em);
        ChargeDataUtil.registerClients(em);
        ChargeDataUtil.registerOrdersClients(em);
        OrderDAO orderDAO = new OrderDAO(em);
        Order order = orderDAO.joinFetchClient(2);

        System.out.println(orderDAO.findItensMoreSelled());
        System.out.println(order.getClient().getName());

        ClientDAO clientDAO = new ClientDAO(em);
        AddressDAO addressDAO = new AddressDAO(em);
        System.out.println(clientDAO.findByName("João"));
        System.out.println(addressDAO.findClient("SP", null, null));

        em.getTransaction().commit();
        em.close();
    }
}
