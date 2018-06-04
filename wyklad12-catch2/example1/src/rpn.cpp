#include "rpn.hpp"

#include <map>
#include <functional>
#include <vector>
#include <regex>
#include <list>
#include <iostream>

double rpn_count(std::vector < std::string > & formula_tokens) {
	
	auto pop = [](std::list < double > &stack){
		auto ret = stack.back();
		stack.pop_back();
		return ret;
	};
	
	std::map < std::string, std::function < void(std::list < double >&) > > 
		commands = {
			{"+",[pop](std::list < double > &stack){
				stack.push_back(pop(stack)+pop(stack));
			}},
			{"*",[pop](std::list < double > &stack){
				stack.push_back(pop(stack)*pop(stack));
			}},
			{"/",[pop](std::list < double > &stack){
				auto a = pop(stack), b = pop(stack);
				stack.push_back(b/a);
			}},
			{"-",[pop](std::list < double > &stack){
				auto a = pop(stack), b = pop(stack);
				stack.push_back(b-a);
			}}
		};
	
	std::list < double > stack; // stos
	for (auto &token : formula_tokens) {
		if (commands.count(token)) {
			commands[token](stack);
		} else {
			try {
				stack.push_back(std::stod(token));
			} catch (...) {
				throw std::invalid_argument(std::string("unrecognized token ") + token);
			}
		}
	}
	return stack.back();
}

double rpn_count(const std::string &program) {
	std::regex re(" ");
    std::sregex_token_iterator first{program.begin(), program.end(), re, -1}, last;
    std::vector<std::string> program_tokens {first, last};
    return rpn_count(program_tokens);
}

