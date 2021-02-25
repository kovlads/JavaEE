package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private final Map<Long, Product> productMap = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public String addToCart(Product product) {
        productMap.put(product.getId(), product);
        return "/cart.xhtml?faces-redirect-true";
    }

    public void removeFromCart(Product product) {
        productMap.remove(product.getId());
    }
}
