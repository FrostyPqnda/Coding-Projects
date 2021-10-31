/**
 * Purpose of the program: Create a function, that when called, will 
 * return the estimated value of pi.
 */
#include <iostream>
#include <cmath>
using namespace std;

// Estimates the value of pi based on the number of random points
// generated n number of times.
// 
// The bigger n is, the more accurate the results.
double EstimatePi(unsigned long long int);

int main() 
{
    int numPoints;
    cout << "Enter the number of points: ";
    cin >> numPoints;

    double estimateOfPi = EstimatePi(numPoints);
    cout << "The estimated value of pi: " << estimateOfPi << endl;
}

double EstimatePi(unsigned long long int n) 
{
    // Set the seed number to random choice based on the computer's internal clock
    srand(time(NULL)); 

    // Number of points generated inside the circle
    int numPointsInCircle = 0; 

    // Number of points generated in total
    int numPointsTotal = 0; 

    // Loop to generate a random (x, y) point n times that will be used to calculate the estimated
    // value of pi.
    for(unsigned long long int i = 0; i < n; i++)
    {
        // Generate two random points from [0, 1] inclusive
        double x = (double)(rand()) / (double)(RAND_MAX);
        double y = (double)(rand()) / (double)(RAND_MAX);

        // Get the distance from the point (0, 0) to (x, y)        
        double distance = pow(x, 2) + pow(y, 2);

        // Increment 'numPointsInCircle' by 1
        // if the distance between the points
        // (0, 0) and (x, y) is less than or
        // equal to 1
        if(distance <= 1) 
        {
            numPointsInCircle++;
        }

        // Increment the total points generated
        numPointsTotal++;
    }

    // initialize a new variable named 'pi' to the estimated value to estimated value of pi
    double pi = 4.0 * ((double)(numPointsInCircle) / (double)(numPointsTotal));
    
    // Return the variable 'pi' storing the estimated value of pi
    return pi;
}