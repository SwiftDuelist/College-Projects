// Name: Delwin Rosa
// Date: 04/19/2020
// Program: Program 5, Encryption and Decryption
// Purpose: The purpose of this program is to receive the name of a file that contains a message,
//          and the name of another file you wish to write the encrypted message to as well as a shift key.
//          The program does this by double shifting the message into a new message.

#include <iostream>
#include <sstream>
#include <fstream>
using namespace std;

int main(int argc, char** argv) {
    int shiftKey; // Amount to add to a character's ASCII code
    int doubleShift = 0;
    int temp = 0;
    char data;              // To hold a character from the file
    string inFileName; // Input fileName
    string outFileName;

    ifstream inputFile;     // Input file stream object
    ofstream outputFile;    // Output file stream object

    // Get the name of the input file
    cout << "Enter the name of the file you would like to encrypt: ";
    getline(cin, inFileName);

    cout << "Enter the name of the file you would like to save the encrypted data: ";
    getline(cin, outFileName);

    cout << "Please enter the shift key you would like to use for the encryption (between 1-255):" << endl;
    cin >> shiftKey;

    if(shiftKey < 1 || shiftKey > 255){
        do{
            cout << "Sorry, but the given value does not fit within the specified range (1-255)" << endl;
            cout << "Please enter the shift key you would like to use for the encryption: ";
            cin >> shiftKey;
        }while(shiftKey < 1 || shiftKey > 255);
    }

    // Open the files
    inputFile.open(inFileName);
    outputFile.open(outFileName);

    outputFile << shiftKey << endl;
    // Read the first character of the input file
    inputFile.get(data);

    while(!inputFile.eof())
    {
        data += shiftKey;
        temp = shiftKey;
        while(shiftKey != 0){
            doubleShift = (doubleShift * 10) + (shiftKey%10);
            shiftKey = shiftKey/10;
        }

        shiftKey = temp;
        data += doubleShift;
        outputFile.put(data);
        inputFile.get(data);
        doubleShift = 0;
    }

    // Close the files
    inputFile.close();
    outputFile.close();

    // Let the user know the encryption has finished
    cout << "The file " << inFileName << " was encrypted and saved to " << outFileName << endl;

    return 0;
}
