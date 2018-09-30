//
// Created by Joshua on 9/27/2018.
//

#include "InputMsg.h"
#include <string>
#include <regex>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <iostream>


using namespace std;

InputMsg::InputMsg(){
   // m_input = input;
   //cout << validate(input) << endl;
}

bool InputMsg::validate(std::string input) {

    // size_t found = m_input.find("***");
    string star3 = "***";
    vector<string> part1 = InputMsg::split(input, star3);
    vector<string> part2 = split(input, "**");
    vector<string> part3 = split(input, "*");

    if (part1.size() > 2 || part2.size() > 2 || part2.size() > 2)
        return false;
    if (part1.size() + part2.size() + part3.size() != 5)
        return false;

    return true;
}

std::vector<std::string> split(std::string stringToBeSplitted, std::string delimeter)
{
    std::vector<std::string> splittedString;
    int startIndex = 0;
    int  endIndex = 0;
    while( (endIndex = stringToBeSplitted.find(delimeter, startIndex)) < stringToBeSplitted.size() )
    {

        std::string val = stringToBeSplitted.substr(startIndex, endIndex - startIndex);
        splittedString.push_back(val);
        startIndex = endIndex + delimeter.size();

    }
    if(startIndex < stringToBeSplitted.size())
    {
        std::string val = stringToBeSplitted.substr(startIndex);
        splittedString.push_back(val);
    }
    return splittedString;

}