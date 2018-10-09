#include <iostream>
#include <pthread.h>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <vector>
#include <algorithm>

using namespace std;

struct threePartmessage {
    string section2, section3;
    int section1;
};

void *sifterT(void *);
void *decoderT(void *);
void *fenceT(void *);
void *hillT(void *);
void *valleyT(void *);
bool inputValidator(string message);
int mod(int m, int n);
int modInverse(int a, int m);
threePartmessage messageCutter(string message);
bool threePartMessageValidator(threePartmessage message);
string vectorMul(string msg, int *tokens);
string vectorMul2(string msg, int *tokens);
void tokenize(string m, int &toki);

string part1, part2, part3;

int main() {
    pthread_t sifter;
    string input;
    //Sifter thread
    if (pthread_create(&sifter, nullptr, sifterT, &input)) {
        cout << "Error creating sifter thread";
        return 0;
    }
    if (pthread_join(sifter, nullptr)) {
        cout << "Error joining sifter thread";
        return 0;
    }
    return 0;
}

//Sifter Thread
void *sifterT(void *in) {
    string *input = (string *) (in);
    string message;
    string tries;
    for (int i = 0; i <= 3 && message != "exit"; i++) {
        cout << "Enter your message: ";
        getline(cin, message);
        if (message == "exit") return EXIT_SUCCESS;
        part1.clear();
        part2.clear();
        part3.clear();

        if (inputValidator(message)) {
            //make decoder thread here
            //reset counter on valid input
            i = 0;
            cout << "Valid input received, starting Decoder thread." << endl;
            pthread_t decoder;

            if (pthread_create(&decoder, nullptr, decoderT, &message)) {
                cout << "Error creating decoder thread";
                return 0;
            }

            if (pthread_join(decoder, nullptr)) {
                cout << "Error joining decoder thread";
                return 0;
            }
            //break;
        } else {
            if (i == 0) tries = "twice";
            if (i == 1) {
                cout << "This is your last chance to enter a correct message: " << endl << "-->";
            } else {
                cout << "Invalid input, you may try " << tries << " again: " << endl;
                cout << "-->";
            }
            message.clear();
            //getline(cin, message);
        }
    }

    pthread_exit(0);
    return EXIT_SUCCESS;
}

bool inputValidator(string message) {
    bool valid = true;
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
   // cout << "Section 1: " << part1 << endl << "Section 2: " << part2 << endl << "Section 3: " << part3 << endl;

    if (valid && grabbed1 && grabbed2 && grabbed3) return true;
    else return false;
}

//Decoder Thread
void *decoderT(void *in) {
    void *threadPointer;
    pthread_t fence, hill, valley;
    if (pthread_create(&fence, nullptr, fenceT, &part1)) {
        cout << "Error creating Fence thread";
        return 0;
    }
    if (pthread_join(fence, nullptr)) {
        cout << "Error joining Fence thread";
        return 0;
    }
    if (pthread_create(&hill, nullptr, hillT, &part2)) {
        cout << "Error creating Hill thread";
        return 0;
    }

    if (pthread_join(hill, nullptr)) {
        cout << "Error joining Hill thread" << threadPointer;
        return 0;
    }

        if (pthread_create(&valley, nullptr, valleyT, &part3)) {
            cout << "Error creating Valley thread";
            return 0;
        }

        if (pthread_join(valley, nullptr)) {
            cout << "Error joining Valley thread";
            return 0;
        }

        pthread_exit(0);
    return EXIT_SUCCESS;
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

    while (message[i] == ' ')i++; //ignore leading whitespace
    //Check for numbers using the ascii codes.
    //grab the leading numbers
    while (message[i] >= 48 && message[i] <= 57) {
        section1 = section1 + message[i];
        i++;
    }

    // We didn't find any numbers at the beginning
    if (section1.size() == 0)
        return 0;

    // Gather the rest of the characters
    while (i < message.size()) {
        section2 = section2 + message[i];
        i++;
    }

    //now find repeated numerical char positions
    bool fencenotfound = true;
    for (int q = 0; q < section1.size() && fencenotfound; q++) {
        for (int p = q + 2; p < section1.size() & fencenotfound; p++) {
            if (section1[q] == section1[p]) {
                for (int k = 0; k <= p - 1; k++)
                    Key = Key + section1[k];
                fencenotfound = false;
            }
        }
    }

    string Keyb;

    //Pulls out characters up to p-1
    for (int q = 1, p = 0; q < Key.size(); q++, p++) {
        if (Key[q] == Key[p]) {
            for (int j = 0; j < p; j++) Keyb = Keyb + Key[j];
        }
    }

    // If q = p + 1 we'll use this instead
    if (Keyb.size() > 0) {
        Key.clear();
        for (int i = 0; i < Keyb.size(); i++) {
            Key = Key + Keyb[i];
        }
    }

    // Check the validity  of the key using gauss's formula
    int correctSum = (Key.size() * (Key.size() + 1)) / 2;
    int keySum = 0;
    for (int i = 0; i < Key.size(); i++) keySum = keySum + (Key[i] - 48);

    if (keySum != correctSum) {
        cout << "Invalid Key sum." << endl;
        return 0;
    }

    int groupeSize;
    int element;
    string decrypted;
    //Extract text from encrypted string using pointer arithmetic concept
    //No need for matrix.
    if (!(section2.size() % Key.size())) {
        groupeSize = section2.size() / Key.size();
        for (int i = 0; i < groupeSize; i++) {
            for (int j = 0; j < Key.size(); j++) {
                element = ((groupeSize) * (Key[j] - 48)) - groupeSize + i;
                decrypted += section2[element];
            }
        }
        //uppercase for output
        for(int i = 0; i < decrypted.length(); i++){
            decrypted[i] = toupper(decrypted[i]);
        }
        cout << "<-FENCE---------------------->" << endl;
        cout << decrypted << endl;
    } else {
        cout << " Invalid Message length. Exiting... " << endl;
    }
    return EXIT_SUCCESS;
    //pthread_exit((void *) "finished fence");
}

//Algorithm #2: Hill Algorithm
void *hillT(void *in) {
    string *input = (string *) (in);
    string message = *input;

    int i = 0;
    threePartmessage messagetxt;

    messagetxt = messageCutter(message);
    if (!threePartMessageValidator(messagetxt)) {
        pthread_exit(0);
        return EXIT_SUCCESS;
    }

    //convert to uppercase
    for (int k = 0; k < messagetxt.section2.length(); k++)
        messagetxt.section2[k] = toupper(messagetxt.section2[k]);

    //Tokenize
    int *tokens = new int[messagetxt.section3.length()];
    char *cstr = new char[messagetxt.section3.length() + 1];

    strcpy(cstr, messagetxt.section3.c_str());
    char *toks = strtok(cstr, " ");
    i = 0;
    while (toks != 0) {
        tokens[i] = atoi(toks);
        i++;
        toks = strtok(nullptr, " ");
    }
    delete[] cstr;


    //Encrypt
    string encrypted;

    if(messagetxt.section1 == 3) {
        encrypted = vectorMul(messagetxt.section2, tokens);
    }

    if(messagetxt.section1 == 2){
        encrypted = vectorMul2(messagetxt.section2, tokens);
    }

    delete[] tokens;
    cout << "<-HILL----------------------->" << endl;
    cout << encrypted << endl;

    pthread_exit(0);
    return EXIT_SUCCESS;
}

//Algorithm #3: Valley Algorithm
void *valleyT(void *in) {
    string *input = (string *) (in);
    string message = *input;
    int Kinverse[] = {0,0,0,0,0,0,0,0,0};
    int determinant;
    int h;
    int m = 26;

    threePartmessage messagetxt;
    messagetxt = messageCutter(message);
    if(!threePartMessageValidator(messagetxt)) {
        pthread_exit(0);
        return EXIT_SUCCESS;
    }

    for (int k = 0; k < messagetxt.section2.length(); k++)
        messagetxt.section2[k] = toupper(messagetxt.section2[k]);

    //Tokenize
    int *tokens = new int[messagetxt.section3.length()];
    char *cstr = new char[messagetxt.section3.length() + 1];

    strcpy(cstr, messagetxt.section3.c_str());
    char *toks = strtok(cstr, " ");
    int i = 0;
    while (toks != 0) {
        tokens[i] = atoi(toks);
        i++;
        toks = strtok(nullptr, " ");
    }
    delete[] cstr;

    string decrypted;

    if(messagetxt.section1 == 2){
        determinant = tokens[0] * tokens[3] - tokens[1] * tokens[2];
        h = modInverse(mod(determinant, m),m);
        if (h == m){
            cout << "Not relatively prime, cannot compute inverse." << endl;
            pthread_exit(0);
            return EXIT_SUCCESS;
        }

        Kinverse[3] = mod(h * tokens[0],m);
        Kinverse[1] = mod(-h * tokens[1],m);
        Kinverse[2] = mod(-h * tokens[2],m);
        Kinverse[0] = mod(h * tokens[3],m);

        decrypted = vectorMul2(messagetxt.section2, Kinverse);
    }
    if(messagetxt.section1 == 3){
        determinant = tokens[0]*(tokens[4] * tokens[8] - tokens[7]*tokens[5]) -
                tokens[1]* (tokens[3] * tokens[8] - tokens[6]*tokens[5]) +
                tokens[2] * (tokens[3] * tokens[7] - tokens[6]*tokens[4]);
        if (modInverse(mod(determinant, 26),26) == 26){
            cout << "Not relatively prime, cannot compute inverse." << endl;
            pthread_exit(0);
            return EXIT_SUCCESS;
        }
        h = modInverse(mod(determinant, 26),26);

        // Calculate K inverse
        Kinverse[0] = mod(h * (tokens[4] * tokens[8] - tokens[7]*tokens[5]), m);
        Kinverse[1] = mod(-h * (tokens[1] * tokens[8] - tokens[7]*tokens[2]), m);
        Kinverse[2] = mod(h * (tokens[1] * tokens[5] - tokens[4]*tokens[2]), m);
        Kinverse[3] = mod(-h * (tokens[3] * tokens[8] - tokens[6]*tokens[5]), m);
        Kinverse[4] = mod(h * (tokens[0] * tokens[8] - tokens[6]*tokens[2]), m);
        Kinverse[5] = mod(-h * (tokens[0] * tokens[5] - tokens[3]*tokens[2]), m);
        Kinverse[6] = mod(h * (tokens[3] * tokens[7] - tokens[6]*tokens[4]), m);
        Kinverse[7] = mod(-h * (tokens[0] * tokens[7] - tokens[6]*tokens[1]), m);
        Kinverse[8] = mod(h * (tokens[0] * tokens[4] - tokens[3]*tokens[1]), m);

        decrypted = vectorMul(messagetxt.section2, Kinverse);

    }

    cout << "<-VALLEY--------------------->" << endl;
    cout << decrypted << endl;

    return EXIT_SUCCESS;
}

threePartmessage messageCutter(string message){
    threePartmessage messageTxt_s;

    int i = 0;
    while (message[i] == ' ')i++;
    if (message[i] == '3' || message[i] == '2')
        messageTxt_s.section1 = message[i] - 48;
    else {
        cout << "invalid section 1,  not 2 or 3" << endl;
        pthread_exit(0);
    }

    // Get message section 2 and 3
    i++;
    while (message[i] == ' ')i++;
    for (int j = i; j < message.length(); j++) {
        if (message[j] == ' ')
            continue;
        if (isalpha(message[j])) {
            messageTxt_s.section2 += message[j];
        }
        if (isdigit(message[j])) {
            messageTxt_s.section3 = message.substr(j, string::npos);
            break;
        }
    }

    return messageTxt_s;
}

bool threePartMessageValidator(threePartmessage message){
    // Validating input
    if (message.section1 == 2 && message.section2.length() % 2 != 0) {
        cout << "section2 has wrong length. Should be multiplicative 2 " << endl;
        return false;
    }

    if (message.section1 == 3 && message.section2.length() % 3 != 0) {
        cout << "section2 has wrong length. Should be multiplicative 3 " << endl;
        return false;
    }

    for (int k = 0; k < message.section3.length(); k++) {
        if (message.section3[k] == ' ') continue;
        if (!isdigit(message.section3[k])) {
            cout << "invalid section3 contains non numerical chars. " << endl;
            return false;
        }
    }

    return true;
}

void tokenize(string m, int &toki){
    //convert to uppercase
    for (int k = 0; k < m.length(); k++)
        m[k] = toupper(m[k]);

    //Tokenize
    int *tokens = new int[m.length()];
    char *cstr = new char[m.length() + 1];

    strcpy(cstr, m.c_str());
    char *toks = strtok(cstr, " ");
    int i = 0;
    while (toks != 0) {
        tokens[i] = atoi(toks);
        i++;
        toks = strtok(nullptr, " ");
    }
    delete[] cstr;
}

int mod(int m, int n){
    return ((m % n) < 0)? (m % n) + n : m % n;
}

int modInverse(int a, int m)
{
    a = mod(a,m);
    for (int x=1; x<m; x++)
        if ((a*x) % m == 1)
            return x;
}

string vectorMul(string msg, int *tokens) {
    string encrypted;
    for (int k = 0, m = 0; k < msg.length(); k += 3, m++) {
        encrypted += (char) (((((msg[k]) - 65) * tokens[0] +
                               (msg[k + 1] - 65) * tokens[3] +
                               (msg[k + 2] - 65) * tokens[6]) % 26) + 65);

        encrypted += (char) (((((msg[k]) - 65) * tokens[1] +
                               (msg[k + 1] - 65) * tokens[4] +
                               (msg[k + 2] - 65) * tokens[7]) % 26) + 65);

        encrypted += (char) (((((msg[k]) - 65) * tokens[2] +
                               (msg[k + 1] - 65) * tokens[5] +
                               (msg[k + 2] - 65) * tokens[8]) % 26) + 65);
    }

    return encrypted;
}

string vectorMul2(string msg, int *tokens){
    string decrypted;
    for (int k = 0, m = 0; k < msg.length(); k += 2, m++) {
        decrypted += (char) (((((msg[k]) - 65) * tokens[0] +
                               (msg[k + 1] - 65) * tokens[2]) % 26) + 65);

        decrypted += (char) (((((msg[k]) - 65) * tokens[1] +
                               (msg[k + 1] - 65) * tokens[3]) % 26) + 65);
    }
    return decrypted;
}