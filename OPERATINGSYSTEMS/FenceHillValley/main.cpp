#include <iostream>
#include <pthread.h>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <vector>

using namespace std;

void *sifterT(void *);

void *decoderT(void *);

void *fenceT(void *);

void *hillT(void *);

void *pinnacleT(void *);

bool isValidInput(string input);

bool inputValidator(string message);

string part1, part2, part3;

int main() {
   // string input = "*j098dlkdjfjlkjdsf*kmakmkdkajf0i3098ud*sajnfflkjdsa0lksdajkjfw";
    pthread_t sifter;

    //Ask user for input
    string input;
    //cout << "Enter your message: " << endl;
    //getline(cin, input);
   // if (input == "exit") return 0;

    //Sifter thread
    if (pthread_create(&sifter, NULL, sifterT, &input)) {
        cout << "Error creating sifter thread";
        return 0;
    }

    if (pthread_join(sifter, NULL)) {
        cout << "Error joining sifter thread";
        return 0;
    }

    return 0;
}

//Sifter Thread
void *sifterT(void *in) {
    string *input = (string *) (in);

    //string message;
    string message = "***3 rrlmwbkaspdh 17 17 5 21 18 21 2 2 19 12 *43125678812ttnaAptMTSUOaodwcoIXknLypETZ**3 GoodMorningJohn 3 2 1 20 15 4 10 22 3";

    cout << "Enter your message: " << endl;
   // getline(cin, message);

    if (message == "exit") return 0;

    for (int i = 0; i < 2 && message != "exit"; i++) {
        if (inputValidator(message)) {

            //make decoder thread here
            cout << "Valid input received, starting Decoder thread." << endl;
            pthread_t decoder;

            if (pthread_create(&decoder, nullptr, decoderT, &message))
            {
                cout << "Error creating decoder thread";
                return 0;
            }

            if (pthread_join(decoder, nullptr))
            {
                cout << "Error joining decoder thread";
                return 0;
            }
            break;
        } else {
            cout << "invalid input, you may try to input " << 2 - i << " more times: " << endl;
            cout << "-->";
            message.clear();
            getline(cin, message);
        }
    }
}

bool inputValidator(string message) {
    // cout << message << endl;
    bool valid = true;
    //string part1, part2, part3;
    //string parts[3];
    bool grabbed1 = false, grabbed2 = false, grabbed3 = false;

    int i = 0;
    while (i < message.size() - 1) {
        int asterCount = 0;
        while (message[i] == ' ')i++;
        if (message[i] == '*') asterCount = 1;
        if (message[i + 1] == '*') asterCount = 2;
        if (message[i + 2] == '*') asterCount = 3;


        switch (asterCount) {
            case 0 :
                if (asterCount == 0)
                    i = message.size() - 1;
                valid = false;
                break;
            case 1:
                i++;
                for (int j = i; message[j] != '*' && i <= message.size() - 1 && !grabbed1; j++, i++) {
                    part1 = part1 + message[j];
                    //cout << i << endl;
                }
                if (grabbed1) valid = false;
                grabbed1 = true;
                break;
            case 2:
                i = i + 2;
                for (int j = i; message[j] != '*' && i <= message.size() - 1 && !grabbed2; j++, i++) {
                    part2 = part2 + message[j];
                }
                if (grabbed2) valid = false;
                grabbed2 = true;
                break;
            case 3:
                i = i + 3;
                for (int j = i; message[j] != '*' && i <= message.size() - 1 && !grabbed3; j++, i++) {
                    part3 = part3 + message[j];
                }
                if (grabbed3) valid = false;
                grabbed3 = true;
                break;
        }

    }

   // parts[0] = part1;
    //parts[1] = part2;
   // parts[2] = part3;

    cout << "Section 1: " << part1 << endl << "Section 2: " << part2 << endl << "Section 3: " << part3 << endl;

    if (valid && grabbed1 && grabbed2 & grabbed3) return true;
    else return false;
}

//Decoder Thread
void *decoderT(void *in) {
        pthread_t fence, hill, pinnacle;

        if (pthread_create(&fence, NULL, fenceT, &part1)) {
            cout << "Error creating Fence thread";
            return 0;
        }

        if (pthread_join(fence, NULL)) {
            cout << "Error joining Fence thread";
            return 0;
        }

//        if (pthread_create(&hill, NULL, hillT, &part2)) {
//            cout << "Error creating Hill thread";
//            return 0;
//        }
//
//        if (pthread_join(hill, NULL)) {
//            cout << "Error joining Hill thread";
//            return 0;
//        }
//
//        if (pthread_create(&pinnacle, NULL, pinnacleT, &part3)) {
//            cout << "Error creating Pinnacle thread";
//            return 0;
//        }
//
//        if (pthread_join(pinnacle, NULL)) {
//            cout << "Error joining Pinnacle thread";
//            return 0;
//        }
}

//Algorithm #1: Rail Fence algorithm
void *fenceT(void *in) {
    string *input = (string *) (in);
    string message = *input;

    string section1, section2, Key;
    int i = 0;
    //find repeated occurrence of numerical chars only
    //at the beginning of the string otherwise return 0;

    //first find length of section 1

    while(message[i] == ' ')i++; //ignore leading whitespace
    //Check for numbers using the ascii codes.
    //grab the leading numbers
    while(message[i] >= 48 && message[i] <= 57) {
        section1 = section1 + message[i];
        i++;
    }

    // We didn't find any numbers at the beginning
    if(section1.size() == 0)
    return 0;

    // Gather the rest of the characters
    while(i < message.size() ){
        section2 = section2 + message[i];
        i++;
    }

    //now find repeated numerical char positions
    int numeric;
    bool fencenotfound = true;
    bool fencenotfound2 = true;
    for(int q = 0; q < section1.size() && fencenotfound; q++){
        numeric = section1[q];
        for(int p = q + 2; p < section1.size() & fencenotfound; p++){
            if (section1[q] == section1[p]){
                if(p - q != 1){
                    fencenotfound2 = false;
                }
                for(int k = 0; k <= p - 1 ; k++)
                    Key = Key + section1[k];
               // p = q = section1.size();
                fencenotfound = false;
            }
        }
    }

    string Keyb;

    //Pulls out characters up to p-1
    for(int q = 1, p = 0; q < Key.size() ; q++, p++ ){
        if(Key[q] == Key[p]){
            fencenotfound2 = false;
            for(int j = 0; j < p; j++) Keyb = Keyb + Key[j];
       }

    }

    cout << section1 << endl << section2 << endl << Key << endl << Keyb << endl;


    // If q = p + 1 we'll use this instead
    if(Keyb.size() > 0){
        Key.clear();
       for(int i = 0; i < Keyb.size(); i++){
            Key = Key + Keyb[i];
       }
    }

    cout << Key << " key here" << endl;

    //cout << section1 << endl << section2 << endl << Key << endl << Keyb << endl;


    // Check the validity  of the key using gauss's formula
    int correctSum = (Key.size() * ( Key.size() + 1))/2;
    int keySum = 0;
    for(int i = 0; i < Key.size(); i++)     keySum = keySum + (Key[i] - 48);

    if( keySum != correctSum) {
        cout << "Invalid Key sum." << endl;
        return 0;
    }
    else
        cout << "Correct Key sum." << endl;

    int groupeSize;
    int element;
    //Extract text from encrypted string using pointer arithmetic concept
    //No need for matrix.
    if ( !(section2.size() % Key.size())){
        groupeSize = section2.size() / Key.size();
        cout << "Decrypted text: " << endl;
        for(int i = 0; i < groupeSize; i++) {
            for (int j = 0; j < Key.size(); j++) {
                element = ((groupeSize) * (Key[j] - 48)) - groupeSize + i;
                cout << section2[element] ;
            }
        }
    }
    else {
     cout << " Invalid Message length. Exiting... " << endl;
    }


}

//Algorithm #2: Hill Algorithm
void *hillT(void *in) {
    string *input = (string *) (in);
    string message = *input;

    //Trim spaces at front if necessary
    size_t firstChar = message.find_first_of("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    string trimmedMessage = message.substr(firstChar, message.length());

    //Find first number and split into section1
    size_t firstNum = trimmedMessage.find_first_of("123456789");
    string section1 = trimmedMessage.substr(0, firstNum - 1);

    //Check if section1 is even length
    if ((section1.length() % 2) != 0) {
        cout << "Hill: Not an even length of characters." << endl;
    } else {
        size_t lastNum = trimmedMessage.find_last_of("0123456789");
        //Trim white space at the end of section2
        string section2 = trimmedMessage.substr(firstNum, lastNum - firstNum + 1);

        //Tokenize and break up section2 and put into vector
        vector<string> vsection2;
        int i = 0;
        string delim = " ";
        size_t pos = section2.find(delim);
        while (pos != string::npos) {
            vsection2.push_back(section2.substr(i, pos - i));
            i = ++pos;
            pos = section2.find(delim, pos);

            if (pos == string::npos) {
                vsection2.push_back(section2.substr(i, section2.length()));
            }
        }

        if (vsection2.size() != 4) {
            cout << "Hill: The digit section does not contain 4 digits" << endl;
        } else {
            //Put string into array
            char asection1[section1.length()];
            strcpy(asection1, section1.c_str());

            //Create a 2 index vector, loop and increment by 2 and convert/do matrix multiplication/reconvert
            int section1_2[2];
            string hill;
            for (int x = 0; x < section1.length(); x = x + 2) {
                if (isupper(asection1[x])) {
                    section1_2[0] = asection1[x] - 65;
                } else {
                    section1_2[0] = asection1[x] - 97;
                }
                if (isupper(asection1[x + 1])) {
                    section1_2[1] = asection1[x + 1] - 65;
                } else {
                    section1_2[1] = asection1[x + 1] - 97;
                }

                hill += (65 + (((atoi(vsection2.at(0).c_str()) * section1_2[0]
                                 + atoi(vsection2.at(1).c_str()) * section1_2[1]) % 26 + 26) % 26));
                hill += (65 + (((atoi(vsection2.at(2).c_str()) * section1_2[0]
                                 + atoi(vsection2.at(3).c_str()) * section1_2[1]) % 26 + 26) % 26));
            }

            cout << "Hill Thread result is " << hill << endl;
        }
    }
}

//Algorithm #3: Pinnacle Algorithm
void *pinnacleT(void *in) {
    string *input = (string *) (in);
    string message = *input;

    size_t firstChar = message.find_first_of("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    string trimmedMessage = message.substr(firstChar, message.length());

    //Find first number and split into section1
    size_t firstNum = trimmedMessage.find_first_of("123456789");
    string section1 = trimmedMessage.substr(0, firstNum - 1);

    //Check if section1 is divisible by 3
    if (section1.length() % 3 != 0) {
        cout << "Pinnacle: Character length is not divisible by 3." << endl;
    } else {
        size_t lastNum = trimmedMessage.find_last_of("0123456789");
        //Trim white space at the end of section2
        string section2 = trimmedMessage.substr(firstNum, lastNum - firstNum + 1);

        //Tokenize and break up section2 and put into vector
        vector<string> vsection2;
        int i = 0;
        string delim = " ";
        size_t pos = section2.find(delim);
        while (pos != string::npos) {
            vsection2.push_back(section2.substr(i, pos - i));
            i = ++pos;
            pos = section2.find(delim, pos);

            if (pos == string::npos) {
                vsection2.push_back(section2.substr(i, section2.length()));
            }
        }

        if (vsection2.size() != 9) {
            cout << "Pinnacle: The digit section does not contain 9 digits." << endl;
        } else {
            //Put string into array
            char asection1[section1.length()];
            strcpy(asection1, section1.c_str());
            int section1_2[3];
            string pinnacle;

            //Create a 3 index vector, loop and increment by 3 and convert/do matrix multiplication/reconvert
            for (int x = 0; x < section1.length(); x = x + 3) {
                if (isupper(asection1[x])) {
                    section1_2[0] = asection1[x] - 65;
                } else {
                    section1_2[0] = asection1[x] - 97;
                }
                if (isupper(asection1[x + 1])) {
                    section1_2[1] = asection1[x + 1] - 65;
                } else {
                    section1_2[1] = asection1[x + 1] - 97;
                }
                if (isupper(asection1[x + 2])) {
                    section1_2[2] = asection1[x + 2] - 65;
                } else {
                    section1_2[2] = asection1[x + 2] - 97;
                }

                pinnacle += (65 + (((atoi(vsection2.at(0).c_str()) * section1_2[0] +
                                     atoi(vsection2.at(1).c_str()) * section1_2[1]
                                     + atoi(vsection2.at(2).c_str()) * section1_2[2]) % 26 + 26) % 26));
                pinnacle += (65 + (((atoi(vsection2.at(3).c_str()) * section1_2[0] +
                                     atoi(vsection2.at(4).c_str()) * section1_2[1]
                                     + atoi(vsection2.at(5).c_str()) * section1_2[2]) % 26 + 26) % 26));
                pinnacle += (65 + (((atoi(vsection2.at(6).c_str()) * section1_2[0] +
                                     atoi(vsection2.at(7).c_str()) * section1_2[1]
                                     + atoi(vsection2.at(8).c_str()) * section1_2[2]) % 26 + 26) % 26));
            }
            cout << "Pinnacle Thread result is: " << pinnacle << endl;
        }
    }
}