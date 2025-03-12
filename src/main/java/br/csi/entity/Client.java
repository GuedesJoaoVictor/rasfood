package br.csi.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cpf;
    @Embedded
    private Contact contact;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    public Client() {}

    public Client(String name, String cpf, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.addressList.add(address);
        address.setClient(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddress(Address address) {
        address.setClient(this);
        this.addressList.add(address);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", contact=" + contact +
                ", addressList=" + addressList +
                '}';
    }
}
