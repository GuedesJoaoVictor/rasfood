package br.csi.utils;

import br.csi.dao.MenuCategoryDAO;
import br.csi.dao.MenuDAO;
import br.csi.entity.Menu;
import br.csi.entity.MenuCategory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ChargeDataUtil {
    private ChargeDataUtil() {}

    public static void registerCategory(EntityManager em) {
        MenuCategory salad = new MenuCategory("Saladas");
        MenuCategory entry = new MenuCategory("Entradas");
        MenuCategory main = new MenuCategory("Pratos Principais");

        MenuCategoryDAO menuCategoryDAO = new MenuCategoryDAO(em);

        menuCategoryDAO.save(entry);
        em.flush();
        menuCategoryDAO.save(salad);
        em.flush();
        menuCategoryDAO.save(main);
        em.flush();
        em.clear();
    }

    public static void registerProductMenu(EntityManager em) {
        MenuDAO menuDAO = new MenuDAO(em);
        MenuCategoryDAO menuCategoryDAO = new MenuCategoryDAO(em);

        List<MenuCategory> categories = menuCategoryDAO.findAll();

        // Pratos Principais
        Menu moqueca = new Menu("Moqueca", "Peixe branco, banana da terra, arroz e farofa", true, BigDecimal.valueOf(95.00), LocalDateTime.now(), categories.get(2));
        Menu feijoada = new Menu("Feijoada", "Feijão preto com linguiça, carne seca, costelinha e acompanhamentos", true, BigDecimal.valueOf(85.00), LocalDateTime.now(), categories.get(2));
        Menu boboDeCamarao = new Menu("Bobó de Camarão", "Camarão refogado com purê de mandioca e arroz", true, BigDecimal.valueOf(90.00), LocalDateTime.now(), categories.get(2));
        Menu acaraje = new Menu("Acarajé", "Bolinho de feijão-fradinho recheado com vatapá e camarão", true, BigDecimal.valueOf(45.00), LocalDateTime.now(), categories.get(2));

        // Saladas
        Menu caprese = new Menu("Salada Caprese", "Tomate, mussarela de búfala, manjericão e azeite", true, BigDecimal.valueOf(35.00), LocalDateTime.now(), categories.get(0));
        Menu caesar = new Menu("Salada Caesar", "Alface romana, croutons, parmesão e molho Caesar", true, BigDecimal.valueOf(40.00), LocalDateTime.now(), categories.get(0));

        // Entradas
        Menu bolinhoDeBacalhau = new Menu("Bolinho de Bacalhau", "Bolinho de bacalhau com molho tártaro", true, BigDecimal.valueOf(30.00), LocalDateTime.now(), categories.get(1));
        Menu pastelDeQueijo = new Menu("Pastel de Queijo", "Pastel frito recheado com queijo derretido", true, BigDecimal.valueOf(25.00), LocalDateTime.now(), categories.get(1));

        // Salvando os pratos no banco de dados
        menuDAO.save(moqueca);
        menuDAO.save(feijoada);
        menuDAO.save(boboDeCamarao);
        menuDAO.save(acaraje);
        menuDAO.save(caprese);
        menuDAO.save(caesar);
        menuDAO.save(bolinhoDeBacalhau);
        menuDAO.save(pastelDeQueijo);

        em.getTransaction().commit();
        em.close();
    }
}