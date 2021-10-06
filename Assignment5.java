import java.io.*;
import java.util.Scanner;

public class Assignment5 {

    static void lcs(String X, String Y, int m, int n)
    {
        int[][] matrix = new int[m+1][n+1];
   
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    matrix[i][j] = 0;
                else if (X.charAt(i-1) == Y.charAt(j-1))
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
            }
        }
        
        int index = matrix[m][n];
        int temp = index;
   
        // Character array that stores the LCS string
        char[] lcs = new char[index+1];
        lcs[index] = '\u0000'; // Terminating character
   
        // Storing the characters into the LCS array
        int i = m;
        int j = n;
        while (i > 0 && j > 0)
        {
            // If current character in X[] and Y are same, then
            // current character is part of LCS
            if (X.charAt(i-1) == Y.charAt(j-1))
            {
                // Put current character in result
                lcs[index-1] = X.charAt(i-1); 
                  
                // reduce values of i, j and index
                i--; 
                j--; 
                index--;     
            }
   
            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (matrix[i-1][j] > matrix[i][j-1])
                i--;
            else
                j--;
        }
   
        // Print the LCS between the two strings
        System.out.print("The LCS of "+X+" and "+Y+" is: ");
        for(int k=0;k<=temp;k++)
            System.out.print(lcs[k]);
    }
      
    // driver program
    public static void main (String[] args) 
    {
    	Scanner scan = new Scanner(System.in);
		// Ask user for sentence
		System.out.print("Please input the first sequence you wish to be compare: ");
        String X = scan.next();
        System.out.print("Please input the second sequence you wish to be compare: ");
        String Y = scan.next();
        scan.close();
        int m = X.length();
        int n = Y.length();
        lcs(X, Y, m, n);
    }


}
