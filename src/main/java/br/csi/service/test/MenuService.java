package br.csi.service.test;

import br.csi.dao.MenuDAO;
import br.csi.utils.ChargeDataUtil;
import br.csi.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MenuService {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerRasFood();
        em.getTransaction().begin();
        ChargeDataUtil.registerCategory(em);
        ChargeDataUtil.registerProductMenu(em);
        MenuDAO menuDAO = new MenuDAO(em);
        System.out.println("\nLista de produtos por valor: " + menuDAO.findByPrice(BigDecimal.valueOf(25.00)) + "\n");
        //menuDAO.findByPrice(BigDecimal.valueOf(25.00), BigDecimal.valueOf(35.00)).forEach(System.out::println);
        System.out.println("\nLista de produtos por valor min max: " + menuDAO.findByPrice(BigDecimal.valueOf(25.00), BigDecimal.valueOf(35.00)) + "\n");
        System.out.println("\nProduto pesquisado por nome:" + menuDAO.findByName("feijoada"));
        em.close();
    }
}
