package br.csi.utils;

import br.csi.dao.*;
import br.csi.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
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
        Menu moqueca = new Menu("Moqueca", "Peixe branco, banana da terra, arroz e farofa", true, BigDecimal.valueOf(95.00), categories.get(2));
        Menu feijoada = new Menu("Feijoada", "Feijão preto com linguiça, carne seca, costelinha e acompanhamentos", true, BigDecimal.valueOf(85.00), categories.get(2));
        Menu boboDeCamarao = new Menu("Bobó de Camarão", "Camarão refogado com purê de mandioca e arroz", true, BigDecimal.valueOf(90.00), categories.get(2));
        Menu acaraje = new Menu("Acarajé", "Bolinho de feijão-fradinho recheado com vatapá e camarão", true, BigDecimal.valueOf(45.00), categories.get(2));

        // Saladas
        Menu caprese = new Menu("Salada Caprese", "Tomate, mussarela de búfala, manjericão e azeite", true, BigDecimal.valueOf(35.00), categories.get(0));
        Menu caesar = new Menu("Salada Caesar", "Alface romana, croutons, parmesão e molho Caesar", true, BigDecimal.valueOf(40.00), categories.get(0));

        // Entradas
        Menu bolinhoDeBacalhau = new Menu("Bolinho de Bacalhau", "Bolinho de bacalhau com molho tártaro", true, BigDecimal.valueOf(30.00), categories.get(1));
        Menu pastelDeQueijo = new Menu("Pastel de Queijo", "Pastel frito recheado com queijo derretido", true, BigDecimal.valueOf(25.00), categories.get(1));

        // Salvando os pratos no banco de dados
        for(Menu menu : Arrays.asList(moqueca, feijoada, boboDeCamarao, acaraje, caprese, caesar, bolinhoDeBacalhau, pastelDeQueijo)) {
            menuDAO.save(menu);
        }

        em.flush();
        em.clear();
    }

    public static void registerClients(EntityManager em) {
        ClientDAO clientDAO = new ClientDAO(em);
        AddressDAO addressDAO = new AddressDAO(em);

        // Endereços
        Address address1 = new Address("970431920", "Fernandes Vieira", "81", "RS", "Santa Maria");
        Address address2 = new Address("123456789", "Avenida Brasil", "100", "SP", "São Paulo");
        Address address3 = new Address("987654321", "Rua das Flores", "50", "RJ", "Rio de Janeiro");
        Address address4 = new Address("456789123", "Rua dos Coqueiros", "200", "BA", "Salvador");
        Address address5 = new Address("321654987", "Avenida Paulista", "1500", "SP", "São Paulo");

        // Clientes
        Client guedes = new Client("João Guedes", "05265294059", address1);
        Client maria = new Client("Maria Silva", "12345678901", address2);
        Client joao = new Client("João Oliveira", "98765432109", address3);
        Client ana = new Client("Ana Costa", "45678912345", address4);
        Client carlos = new Client("Carlos Souza", "32165498709", address5);

        // Associando endereços aos clientes
        guedes.setAddress(address1);
        maria.setAddress(address2);
        joao.setAddress(address3);
        ana.setAddress(address4);
        carlos.setAddress(address5);

        // Salvando endereços no banco de dados
        for (Address address : Arrays.asList(address1, address2, address3, address4, address5)) {
            addressDAO.save(address);
        }

        // Salvando clientes no banco de dados
        clientDAO.save(guedes);
        clientDAO.save(maria);
        clientDAO.save(joao);
        clientDAO.save(ana);
        clientDAO.save(carlos);

        em.flush();
        em.clear();
    }

    public static void registerOrdersClients(EntityManager em) {
        ClientDAO clientDAO = new ClientDAO(em);
        MenuDAO menuDAO = new MenuDAO(em);
        OrderDAO orderDAO = new OrderDAO(em);

        // Busca todos os clientes e menus cadastrados
        List<Client> clientList = clientDAO.findAll();
        List<Menu> menuList = menuDAO.findAll();

        // Pedido para o cliente Guedes
        Order orderGuedes = new Order(clientList.getFirst()); // Guedes
        orderGuedes.setOrder(new OrderMenu(menuList.get(0), 2)); // Moqueca, 2 unidades
        orderGuedes.setOrder(new OrderMenu(menuList.get(5), 3)); // Salada Caesar, 3 unidades
        orderDAO.save(orderGuedes);

        // Pedido para o cliente Maria
        Order orderMaria = new Order(clientList.get(1)); // Maria
        orderMaria.setOrder(new OrderMenu(menuList.get(1), 1)); // Feijoada, 1 unidade
        orderMaria.setOrder(new OrderMenu(menuList.get(6), 2)); // Bolinho de Bacalhau, 2 unidades
        orderDAO.save(orderMaria);

        // Pedido para o cliente João
        Order orderJoao = new Order(clientList.get(2)); // João
        orderJoao.setOrder(new OrderMenu(menuList.get(2), 2)); // Bobó de Camarão, 2 unidades
        orderJoao.setOrder(new OrderMenu(menuList.get(7), 4)); // Pastel de Queijo, 4 unidades
        orderDAO.save(orderJoao);

        // Pedido para o cliente Ana
        Order orderAna = new Order(clientList.get(3)); // Ana
        orderAna.setOrder(new OrderMenu(menuList.get(3), 1)); // Acarajé, 1 unidade
        orderAna.setOrder(new OrderMenu(menuList.get(4), 2)); // Salada Caprese, 2 unidades
        orderDAO.save(orderAna);

        // Pedido para o cliente Carlos
        Order orderCarlos = new Order(clientList.get(4)); // Carlos
        orderCarlos.setOrder(new OrderMenu(menuList.get(1), 3)); // Feijoada, 3 unidades
        orderCarlos.setOrder(new OrderMenu(menuList.get(2), 1)); // Bobó de Camarão, 1 unidade
        orderDAO.save(orderCarlos);

        em.flush();
        em.clear();
    }
}