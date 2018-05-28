#include "catch.hpp"
using Catch::Matchers::EndsWith; // or Catch::EndsWith
TEST_CASE("Some test") {
    std::string str = "testowanie aplikacji";
    REQUIRE_THAT( str, EndsWith( "aplikacji" ) ); 
}

TEST_CASE("Some test 2") {
    REQUIRE ( 1 == 1 );
}
