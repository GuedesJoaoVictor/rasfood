package br.csi.dao;

import br.csi.entity.Address;
import br.csi.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class AddressDAO {
    private final EntityManager em;

    public AddressDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Address address) { em.persist(address); }

    public void findById(int id) { em.find(Address.class, id); }

    public List<Address> findAll() {
        String query = "select a from Address a";
        return em.createQuery(query, Address.class).getResultList();
    }

    public void update(Address address) { em.merge(address); }

    public void delete(Address address) { em.remove(address); }

}
