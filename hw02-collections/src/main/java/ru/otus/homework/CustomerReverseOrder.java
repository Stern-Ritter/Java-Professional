package ru.otus.homework;

import java.util.LinkedList;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedList<Customer> customers;

    public CustomerReverseOrder() {
        this.customers = new LinkedList<>();
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        return customers.pollLast();
    }
}