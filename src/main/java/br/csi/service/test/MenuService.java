package br.csi.service.test;

import br.csi.dao.MenuDAO;
import br.csi.entity.Menu;
import br.csi.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MenuService {
    public static void main(String[] args) {
        Menu risoto = new Menu();
        risoto.setName("Risoto de frutos do mar");
        risoto.setDescription("Risoto acompanhado de lula, polvo e mariscos");
        risoto.setStatus(true);
        risoto.setPrice(BigDecimal.valueOf(88.50));

        Menu salmao = new Menu();
        salmao.setName("Salmão ao molho de maracujá");
        salmao.setDescription("Salmão grelhado ao molho de maracujá");
        salmao.setStatus(true);
        salmao.setPrice(BigDecimal.valueOf(60.00));

        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        MenuDAO menuDAO = new MenuDAO(entityManager);
        entityManager.getTransaction().begin();
        menuDAO.save(risoto);
        entityManager.flush();
        menuDAO.save(salmao);
        entityManager.flush();
        System.out.println("O prato consultado foi: " + menuDAO.findById(1));
        menuDAO.delete(salmao);
        System.out.println("O prato consultado foi: " + menuDAO.findById(2));

        entityManager.clear();
        risoto.setPrice(BigDecimal.valueOf(75.50));
        menuDAO.update(risoto);
        System.out.println("O prato consultado foi: " + menuDAO.findById(1));
    }
}
