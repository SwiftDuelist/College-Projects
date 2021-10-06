// Delwin Rosa
// LabTest 3
// 04/27/2020

#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
#include <map>
using namespace std;
class gasPrices{
private:
    int month;
    int day;
    int year;
    int placeHolder = 0;
    float price;
    char separatorOne = '-';
    char separatorTwo = ':';

public:
    gasPrices(int a, int b, int c, float d){
        month = a;
        day = b;
        year = c;
        price = d;
    }
    // Set variables
    void setMonth(int a)
    {
        month = a;
    }
    void setDay(int b)
    {
        day = b;
    }
    void setYear(int c)
    {
        year = c;
    }
    void setPrice(float d)
    {
        price = d;
    }
    // Get Variables
    int getMonth()
    {
        return month;
    }
    float getPrice()
    {
        return price;
    }
    string toString(){
        stringstream ss;
        if(month < 10 && day < 10){
            ss << placeHolder << month << separatorOne << placeHolder << day << separatorOne << year << separatorTwo << price << endl;
        }
        else if(month < 10 && day >= 10){
            ss << placeHolder << month << separatorOne << day << separatorOne << year << separatorTwo << price << endl;
        }
        else if(month >= 10 && day < 10){
            ss << month << separatorOne << placeHolder << day << separatorOne << year << separatorTwo << price << endl;
        }
        else{
            ss << month << separatorOne << day << separatorOne << year << separatorTwo << price << endl;
        }
        return ss.str();
    }
};
int main() {
    int month = 0;
    int day = 0;
    int year = 0;
    int counter = 0;
    int bufferOne = 0;
    int bufferTwo = 0;

    float bufferThree = 0.0;
    float total = 0.0;
    float average = 0.0;
    float price = 0.0;
    float highest = 0.0;
    float lowest = 0.0;

    char separatorOne = '-';
    char separatorTwo = ':';

    string inFileName = "GasPrices.txt";
    string outFileName = "ListOfPrices.txt";

    vector<int> yearKey;
    vector<int> monthKey;

    map<int, float> averagePerYear;
    map<int, float> averagePerMonth;
    map<int, float> highestPerYear;
    map<int, float> lowestPerYear;
    map<int, float>::iterator it;

    multimap<int, gasPrices>::iterator iter;
    multimap<int, float> prices;
    multimap<int, gasPrices> data;

    ifstream inputFile;
    ofstream outputFile;

    inputFile.open(inFileName);
    outputFile.open(outFileName);

    while(!inputFile.eof()){
        inputFile >> month;
        if (bufferOne != month)
        {
            monthKey.push_back(month);
            bufferOne = month;
        }
        inputFile.get(separatorOne);
        inputFile >> day;
        inputFile.get(separatorOne);

        inputFile >> year;
        if (bufferTwo != year)
        {
            yearKey.push_back(year);
            bufferTwo = year;
        }

        inputFile.get(separatorTwo);
        inputFile >> price;
        prices.insert({year, price});

        gasPrices Object(month, day, year, price);
        data.emplace(pair <int, gasPrices> (year, Object));
    }

    for(int i = 0; i < yearKey.size(); i++) {
        auto range = prices.equal_range(yearKey[i]);
        for (it = range.first; it != range.second; it++) {
            cout << it->first << " " << it->second << endl;
            total += it->second;
            counter++;
        }
        average = (total/(float)counter);
        averagePerYear.insert(pair<int, double>(yearKey[i], average));
        counter = 0;
        total = 0;
        average = 0;
    }

    cout << "\nAverage price per year is: " << endl;
    for (it = averagePerYear.begin(); it != averagePerYear.end(); ++it)
    {
        cout << (*it).first << " " << (*it).second << endl;
    }

    for (int i = 0; i < monthKey.size(); i++)
    {
        for (iter = data.begin(); iter != data.end(); ++iter)
        {
            if ((*iter).second.getMonth() == monthKey[i])
            {
                total += (*iter).second.getPrice();
                counter++;
            }
        }
        average = (total/(float)counter);
        averagePerMonth.insert(pair<int, double>(monthKey[i], average));
        total = 0;
        counter = 0;
        average = 0;
    }

    cout << "\nAverage price per month is: " << endl;
    for (it = averagePerMonth.begin(); it != averagePerMonth.end(); ++it)
    {
        cout << (*it).first << " " << (*it).second << endl;
    }

    // Searches for the Highest price per year given
    for(int i = 0; i < yearKey.size(); i++)
    {
        auto range = data.equal_range(yearKey[i]);

        for(iter = range.first; iter != range.second; ++iter)
        {
            bufferThree = (*iter).second.getPrice();
            if (bufferThree > highest)
            {
                highest = bufferThree;
            }
        }
        highestPerYear.insert(pair<int, double>(yearKey[i], highest));
        highest = 0;
        bufferThree = 0.0;
    }

    cout << "\nHighest price per year is: " << endl;
    for(it = highestPerYear.begin(); it != highestPerYear.end(); ++it)
    {
        cout << (*it).first << " " << (*it).second << endl;
    }

    // Searches for the lowest Price per year given
    for(int i = 0; i < yearKey.size(); i++)
    {
        auto range = data.equal_range(yearKey[i]);
        lowest = 500000;
        for(iter = range.first; iter != range.second; ++iter)
        {
            bufferThree = (*iter).second.getPrice();
            if (bufferThree < lowest)
            {
                lowest = bufferThree;
            }
        }
        lowestPerYear.insert(pair<int, double>(yearKey[i], lowest));
    }

    cout << "\nThe lowest price for each year: " << endl;
    for(it = lowestPerYear.begin(); it != lowestPerYear.end(); ++it)
    {
        cout << (*it).first << " " << (*it).second << endl;
    }

    // Outputs the given data from the input file to a separate file
    for(auto iter = data.rbegin(); iter != data.rend(); ++iter)
    {
        outputFile << iter->second.toString();
    }

    //Close files
    inputFile.close();
    outputFile.close();

    return 0;
}
