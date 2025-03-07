package br.csi.dao;

import br.csi.entity.Address;
import br.csi.interfaces.ClientRequestStatus;
import br.csi.vo.ClientVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

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

    public List<ClientVo> findClient(String state, String city, String street) {
        String query = "select new br.csi.vo.ClientVo(a.client.cpf, a.client.name) from Address a where 1=1";
        query = verifyParamsToQuery(query, state, city, street);
        TypedQuery<ClientVo> tq = em.createQuery(query, ClientVo.class);
        addParamsToQuery(tq, state, city, street);
        return tq.getResultList();
    }

    private String verifyParamsToQuery(String query, String state, String city, String street) {
        if (Objects.nonNull(state)) {
            query += ClientRequestStatus.State;
        }
        if(Objects.nonNull(city)) {
            query += ClientRequestStatus.City;
        }
        if(Objects.nonNull(street)) {
            query += ClientRequestStatus.Street;
        }
        return query;
    }

    private TypedQuery<ClientVo> addParamsToQuery(TypedQuery<ClientVo> tq, String state, String city, String street) {
        if(Objects.nonNull(state)) {
            tq.setParameter("state", state);
        }
        if(Objects.nonNull(city)) {
            tq.setParameter("city", city);
        }
        if(Objects.nonNull(street)) {
            tq.setParameter("street", street);
        }
        return tq;
    }

    public void update(Address address) { em.merge(address); }

    public void delete(Address address) { em.remove(address); }

}
