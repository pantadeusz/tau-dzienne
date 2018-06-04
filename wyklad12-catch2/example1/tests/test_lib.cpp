#include "catch.hpp"

#include <rpn.hpp>

using Catch::Matchers::EndsWith; // see "matchers"

TEST_CASE("Some test 2")
{
    REQUIRE((10 + 2) == 12);
}

SCENARIO("Simple scenario")
{
    GIVEN("there are numbers 10 and 20")
    {
        int x = 10, y = 20;
        WHEN("we add them")
        {
            int z = x + y;
            THEN("the result should be 30")
            {
                REQUIRE(z == 30);
            }
        }
    }
}
