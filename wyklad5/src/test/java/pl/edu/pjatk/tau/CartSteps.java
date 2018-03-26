package pl.edu.pjatk.tau;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertTrue;

/**
 * To jest ta najwazniejsza klasa do testów behawioralnych ktora implementuje opowiesc (story)
 *
 * Zobacz, że ta jedna klasa może pasować do wielu opowieści.
 */


public class CartSteps {
    private Cart cart;
    Product product;
    int productCount;

    @Given("there is a product with price $price zl")
    public void fillCart(double price){
        product = new Product();
        product.setPrice(price);
        cart = new CartImpl();
    }

    @When("$products products are added to cart")
    public void productsAdded(int products) {
        for (int i = 0; i < products; i++)
            cart.addProduct(product);
        productCount = products;
    }

    @Then("the summary price should be $summaryPrice")
    public void priceCalculated(double summaryPrice) {
        assertTrue(cart.getSummaryPrice() == summaryPrice);
    }

}
