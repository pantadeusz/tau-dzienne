cmake_minimum_required(VERSION 3.0.0 FATAL_ERROR)

project (testycpp CXX)

set (testycpp_VERSION_MAJOR 0)
set (testycpp_VERSION_MINOR 1)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

if(NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE Release)
endif()


set(CATCH_INCLUDE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/catch)
add_library(Catch INTERFACE)
target_include_directories(Catch INTERFACE ${CATCH_INCLUDE_DIR})


install(DIRECTORY src/ DESTINATION include/testycpp
        FILES_MATCHING PATTERN "*.hpp")


include_directories("${PROJECT_SOURCE_DIR}/src")

file(GLOB_RECURSE lib_SOURCES "src/*.cpp")
file(GLOB_RECURSE app_TESTS "tests/*.cpp")

#add_executable(app ${app_SOURCES})
add_executable(tests EXCLUDE_FROM_ALL ${app_TESTS} ) 
target_link_libraries(tests Catch)




enable_testing()

add_test( NAME tests 
          COMMAND "${CMAKE_BINARY_DIR}/tests "
          WORKING_DIRECTORY "${PROJECT_SOURCE_DIR}"  )

add_custom_target(check COMMAND ${CMAKE_CTEST_COMMAND}
                  DEPENDS tests)


