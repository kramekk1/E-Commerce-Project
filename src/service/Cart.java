package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private List<Computer> computers;
    private List<Smartphone> smartphones;
    private List<Electronics> electronics;

    public Cart() {
        products = new ArrayList<>();
        computers = new ArrayList<>();
        smartphones = new ArrayList<>();
        electronics = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public List<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(List<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    public List<Electronics> getElectronics() {
        return electronics;
    }

    public void setElectronics(List<Electronics> electronics) {
        this.electronics = electronics;
    }

}
