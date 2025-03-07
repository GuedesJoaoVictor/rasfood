package br.csi.dao;

import br.csi.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientDAO {
    private final EntityManager em;

    public ClientDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Client client) { em.persist(client); }

    public void findById(int id) { em.find(Client.class, id); }

    public List<Client> findAll() {
        String query = "select c from Client c";
        return em.createQuery(query, Client.class).getResultList();
    }

    public List<Client> findByName(String name) {
         String query = "select c from Client c where upper(c.name) like upper(:name)";
         return em.createQuery(query, Client.class).setParameter("name", "%"+name+"%").getResultList();
    }

    public void update(Client client) { em.merge(client); }

    public void delete(Client client) { em.remove(client); }

}
