
Scenario:  cart price for 2 products with the same price

Given there is a product with price 10.0 zl
When 2 products are added to cart
Then the summary price should be 20.0


Scenario:  cart price for 3 products with the same price

Given there is a product with price 15.0 zl
When 2 products are added to cart
Then the summary price should be 30.0


Scenario:  cart price for 3 products with the same price

Given there is a product with price <price> zl
When <count> products are added to cart
Then the summary price should be <summary>

Examples:
|price|count|summary|
|1.0|1|1.0|
|10.0|1|10.0|
|10.0|5|50.0|
