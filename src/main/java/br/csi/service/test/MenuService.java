package br.csi.service.test;

import br.csi.dao.MenuCategoryDAO;
import br.csi.dao.MenuDAO;
import br.csi.entity.Menu;
import br.csi.entity.MenuCategory;
import br.csi.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MenuService {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerRasFood();
        registerItensMenu(em, registerCategory(em));
    }

    private static MenuCategory registerCategory(EntityManager em) {
        MenuCategoryDAO menuCategoryDAO = new MenuCategoryDAO(em);
        MenuCategory mainDish = new MenuCategory("Main Dish");
        em.getTransaction().begin();
        em.persist(mainDish);
        menuCategoryDAO.save(mainDish);
        em.getTransaction().commit();
        em.clear();

        return mainDish;
    }

    private static void registerItensMenu(EntityManager em, MenuCategory category) {
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

        MenuDAO menuDAO = new MenuDAO(em);
        em.getTransaction().begin();
        menuDAO.save(risoto);
        risoto.setMenuCategory(category);
        em.flush();
        menuDAO.save(salmao);
        salmao.setMenuCategory(category);
        em.flush();
        System.out.println("O prato consultado foi: " + menuDAO.findById(1));
        System.out.println("O prato consultado foi: " + menuDAO.findById(2));
        em.close();
    }
}
