//
// Created by Joshua on 9/27/2018.
//

#ifndef FENCEHILLVALLEY_INPUTMSG_H
#define FENCEHILLVALLEY_INPUTMSG_H

#include <string>
#include <vector>

using namespace std;
#endif //FENCEHILLVALLEY_INPUTMSG_H

class InputMsg
{
private:
    //input string for validation
    string m_input;
    string m_partOne;
    string m_partTwo;
    string m_partThree;

public:
    InputMsg();
    bool validate(std::string input);
    std::vector<std::string> split(std::string stringToBeSplitted, std::string delimeter);
};
