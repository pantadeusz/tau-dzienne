package pl.edu.pjatk.tau;

import java.util.ArrayList;

public class CartImpl implements Cart {

    ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void delProduct(Product p) {
        products.remove(products.indexOf(p));
    }

    public double getSummaryPrice() {
        double s = 0;
        for (Product p : products) {
            s = s + p.getPrice();
        }
        return s;
    }
}
