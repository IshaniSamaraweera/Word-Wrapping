/**
 *
 * @author Ishani SA
 * 18/06/15.
 * Copyright (c) 2014 Ishani SA. All rights reserved.
 *
 */

import java.util.*;
import java.io.*;

public class WordWrapping
{
	private static int M, noOfWords;
	private static int[] wordLen;
	private static String[] splited;
	private static int matrix[][]; 
	private static int[] costArray;
	private static Integer myInf = Integer.MAX_VALUE;
	private static int[] lineBreak;
	private static String wrappedText;
	
	//Method to split the input paragraph
	public static void splitParagraph(String paragraphName)
	{
		try
		{
			splited = paragraphName.split(" ");
	        
	        //assign the value of M
			String m = splited[0];
			
	        M = Integer.parseInt(m);
		}
		
		catch(NumberFormatException e)
		{
			System.out.println("M value has not inputed");
		}
	}
	
	//Method to get the length of each word in the input paragraph
	public static void getWordLength(String[] arrayName)
	{
		noOfWords = arrayName.length-1;//get the number of words in the paragraph
        
        	//get the length of each word to an array
        	wordLen = new int[noOfWords];
        	for(int i=0; i<noOfWords; i++)
        	{	        	
        		wordLen[i] = (arrayName[i+1]).length();
        	
        	}
	}
	
	//Method to calculate the spaces remaining and fill the matrix
	public static void addValues(int[][] matrixName, int r, int c, int[] lengthArray, int maxLength )
	{
	//add values to the matrix
        
        for(int i=0; i<r; i++)
        {
        	for(int j=i; j< c; j++)
        	{
	        	int value = j-i; //add the number of spaces between words
	        	for(int k=i; k<=j; k++)
	        	{
	        		value += lengthArray[k];		        			
	        	}
	        		
	        	if(value <= maxLength)
	        	{
	        		matrixName[i][j] =  (int) Math.pow(maxLength-value,3);
	        	}
	        	else
	        	{
	        		
	        		matrixName[i][j]=myInf;
	        	}
        	}
        }
        
        
	}
	
	//Method to print the matrix
	public static void printMatrix(int[][] matrixName, int r, int c)
	{
		//print the matrix
		
		for(int n=0; n<c; n++)
		{
			System.out.format("\t%d",n);
		}
        for(int o=0; o<r; o++)
        {
        	System.out.print("\n");
        	System.out.format("%d\t",o);
        	for(int p=0; p<c; p++)
        	{
        		System.out.print((matrixName[o][p]==Integer.MAX_VALUE)?"INF":matrixName[o][p]);
        		System.out.print("\t");
        	}
        	
        	System.out.print("\n");
        }
	}
	
	//Method to get values from the matrix
	private static int getVal(int i, int j)
	{
		int k = matrix[i][j];
		return(k);
	}
	
	//Method to fill the minimum cost array
	private static void fillCostArray(int[][] matrixName, int wordCount)
	{
		try
		{
			costArray = new int[wordCount+1];
			lineBreak = new int[wordCount+1];
			int i = 0;
			int j = 0;
			int minCost = myInf;
			
			for(i =0; i<wordCount; i++)
			{
				j=0;
				minCost = myInf;
				if(getVal(j,i) != myInf)
				{
					costArray[i] = getVal(j,i);
					lineBreak[i]= j;
				}
				
				else
				{
					for(j=1; j<= i; j++)
					{
						if(getVal(j,i) != myInf)
						{
							int Temp = getVal(j,i) + costArray[j-1];
							if(Temp<= minCost)
							{
								minCost = Temp;
								lineBreak[i]= j;
							}
						}
						costArray[i]= minCost;	
					}
					
				}
			}
		}
		
		catch(StackOverflowError e)
		{
			System.out.println("StackOverFlowError");
		}
		
	}

	//Method to print the wrapped text	
	private static void printWrap (int[] breakArrayName, String[] wordArrayName)
	{
		
		wrappedText = "";
		int count = breakArrayName[0];
		
		for(int i=0; i<wordArrayName.length-1; i++)
		{
			if(count==breakArrayName[i+1] || count==breakArrayName[i+1]-1)
			{
				
				wrappedText += wordArrayName[i+1];
				wrappedText += " ";
				count=breakArrayName[i+1];
			}
			else
			{
				wrappedText += wordArrayName[i+1];
				wrappedText+="\n";
				
				count=breakArrayName[i+1];
			}
		}
	}
	
	//Main
	public static void main(String[] args)
	{
		try
		{
			//getting the input and split the paragraph by spaces
			Scanner input = new Scanner(System.in);
			
			//System.out.println("Input the paragraph as M<space>Paragraph:");
			String paragraph=input.nextLine();
			
			splitParagraph(paragraph);
			getWordLength(splited);
			
			if(noOfWords==0)
			{
				System.out.println("A paragraph has not inputed");
			}
			if(M>100)
			{
				System.out.println("M value is too large");
			}
			
			if(noOfWords>30000)
			{
				System.out.println("Paragraph is too big");

			}
			
			
			//create new matrix
			matrix= new int[noOfWords][noOfWords];
			
			//Add values to the matrix
			addValues(matrix, noOfWords, noOfWords, wordLen, M  );
			//printMatrix(matrix, noOfWords, noOfWords);

			//Fill the minimum cost array
			fillCostArray(matrix, noOfWords);
			
			//Print the wrapped text
			printWrap(lineBreak,splited);
			System.out.print(wrappedText);
			
			//Print error value
			System.out.format("%d",costArray[noOfWords-2]);

			/*for(int k=0; k<lineBreak.length; k++)
			{
				System.out.format("line//////break = %d\n",lineBreak[k]);
			}
			  */
	      }
		
		catch(ArrayIndexOutOfBoundsException ex)
		{
			System.out.println("Array index out");
		}
        
	}
}
