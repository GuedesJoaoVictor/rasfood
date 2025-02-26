package br.csi.service.test;

import br.csi.entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class DishService {
    public static void main(String[] args) {
        Dish risoto = new Dish();
        risoto.setName("Risoto de frutos do mar");
        risoto.setDescription("Risoto acompanhado de lula, polvo e mariscos");
        risoto.setStatus(true);
        risoto.setPrice(BigDecimal.valueOf(88.50));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rasfood");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(risoto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
