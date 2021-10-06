// Delwin Rosa
// Lab Test 2
// This version of the program requires the first string input to be that of a file name.
// Once the file name is given, the file will be opened and each line will be read via getline
// The read string will be saved into the string array, counted for each vowel and consonant, then calculates a ratio.
// The ratios are then saved into a float array, then once the end of file is reached, the program will
// print out the list of strings that have an above average ratio.

#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

int main() {
    const int MAX_NUM = 10000;
    string str[MAX_NUM];
    float flt[MAX_NUM] = {};
    int numVowels = 0;
    int numConsonants = 0;
    int counter = 0;
    int i = 0;
    float totalRatio = 0.0;
    float trueRatio = 0.0;
    float cvRatio = 0.0;
    string fileName;
    string placeholder;

    cout << "Please give the name of the file you wish to use.";
    cin >> fileName;
    ifstream fin;
    fin.open(fileName, ios::in | ios::binary);
    while(!fin.eof()){
        getline(cin, placeholder);
        str[i] = placeholder;
        for(int k = 0;  placeholder[k] != '\0'; k++){
            if(placeholder[k]=='a' || placeholder[k]=='e' || placeholder[k]=='i' ||
               placeholder[k]=='o' || placeholder[k]=='u' || placeholder[k]=='A' ||
               placeholder[k]=='E' || placeholder[k]=='I' || placeholder[k]=='O' ||
               placeholder[k]=='U'){
                numVowels++;
            }else if((placeholder[k]>='a'&& placeholder[k]<='z') || (placeholder[k]>='A'&& placeholder[k]<='Z')){
                numConsonants++;
            }
            if(numVowels != 0){
                cvRatio = (float)numConsonants/(float)numVowels;
            } else{
                cvRatio = 0.0;
            }
        }
        flt[i] = cvRatio;
        counter++;
        totalRatio = totalRatio + cvRatio;
        i++;
    }
    trueRatio = totalRatio / (float)counter;
    for(int j = 0; j < MAX_NUM; j++){
        if(flt[j] > trueRatio){
            stringstream ss;
            ss << str[j] << " " << flt[j] << endl;
            cout << ss.str();
        }
    }
    return 0;
}



