package br.csi.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @EmbeddedId
    private ClientId clientId;
    private String name;
    @Embedded
    private Contact contact;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    public Client() {}

    public Client(String name, String cpf, Address address, String email) {
        this.clientId = new ClientId(email, cpf);
        this.name = name;
        this.addressList.add(address);
        address.setClient(this);
    }

    public String getEmail() {
        return clientId.getEmail();
    }

    public void setEmail(String email) {
        clientId.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return clientId.getCpf();
    }

    public void setCpf(String cpf) {
        clientId.setCpf(cpf);
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
                ", name='" + name + '\'' +
                ", cpf='" + clientId.getCpf() + '\'' +
                ", contact=" + contact +
                ", addressList=" + addressList +
                ", email=" + clientId.getEmail() +
                '}';
    }
}
