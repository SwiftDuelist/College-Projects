// Name: Delwin Rosa
// Date: 04/19/2020
// Program: Program 5, Encryption and Decryption
// Purpose: The purpose of this program is to receive the name of an encrypted file,
//          and the name of the file you wish to write the decrypted message to.
//          The program does this by double shifting the encrypted message back to its original state

#include <iostream>
#include <fstream>
using namespace std;

int main() {
    int shiftKey;
    int doubleShift = 0;
    int temp;
    const int SIZE = 255; // Array Size
    char inFileName[SIZE]; // Name of the input file
    char outFileName[SIZE]; // Name of the output file
    char data;              // Character placeholder

    ifstream inputFile;     // Input file stream object
    ofstream outputFile;    // Output file stream object

    cout << "Enter the name of the encrypted file:" << endl;
    cin.getline(inFileName, SIZE);

    cout << "Enter the name of the file you would like to save the decrypted data:" << endl;
    cin.getline(outFileName, SIZE);

    // Open the files
    inputFile.open(inFileName);
    outputFile.open(outFileName);

    inputFile >> shiftKey;  // Fetches the shiftkey from the input file and stores it for use
    inputFile.get(data);    // Fetches the first character in the input file

    while(!inputFile.eof()){
        // Encrypt the character
        data -= shiftKey;
        temp = shiftKey;
        while(shiftKey != 0){
            doubleShift = (doubleShift * 10) + (shiftKey%10);
            shiftKey = shiftKey/10;
        }
        shiftKey = temp;
        data -= doubleShift; // Encrypt the character a second time

        // Write the decrypted data

        outputFile.put(data);
        // Read the next character
        inputFile.get(data);
        doubleShift = 0;
    }

    cout << inFileName << " has been successfully decrypted and saved to " << outFileName << endl;

    return 0;
}
