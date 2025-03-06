package br.csi.entity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cep;
    private String street;
    private String complement;
    private String state;
    private String city;
    @ManyToOne
    private Client client;

    public Address(String cep, String street, String complement, String state, String city) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.state = state;
        this.city = city;
    }

    public Address() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", street='" + street + '\'' +
                ", complement='" + complement + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
