package pl.edu.pjatk.tau;

/**
 * Created by tp on 20.03.17.
 */
public interface Cart {
    void addProduct(Product p);
    double getSummaryPrice();
    void delProduct(Product p);
}
