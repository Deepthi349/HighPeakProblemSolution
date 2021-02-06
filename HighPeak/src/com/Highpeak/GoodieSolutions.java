package com.Highpeak;

import java.io.*;
import java.util.*;

class Gift 
{
	String name;
	int price;

	public Gift(String name, int price) 
	{
		this.name = name;
		this.price = price;
	}

	public String toString() 
	{ 
		return this.name + ": " + this.price;
	}
}

public class GoodieSolutions {
	public static void main(String[] args) throws Exception 
	{
		FileInputStream fis=new FileInputStream("input.txt");       
		Scanner sc=new Scanner(fis);
		int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
		

		ArrayList<Gift> goodies_items = new ArrayList<Gift>();

		while(sc.hasNextLine())  
		{
			String current[] = sc.nextLine().split(": ");
			goodies_items.add(new Gift(current[0], Integer.parseInt(current[1])));
		}
		sc.close();
		
		int n =  goodies_items.size();
		for (int i = 0; i < n -1; i++) 
		{
			for (int j =0 ; j < n-i-1 ;j++) {
				if (goodies_items.get(j).price > goodies_items.get(j+1).price) {

					Gift tmp = new Gift(goodies_items.get(j).name, goodies_items.get(j).price);
					Gift tmp1 = new Gift(goodies_items.get(j+1).name, goodies_items.get(j+1).price);

					goodies_items.set(j, tmp1);
					goodies_items.set(j+1, tmp);
				}
			}
		}

		int min_diff = goodies_items.get(goodies_items.size()-1).price;
		int min_index = 0;
		
		for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) 
		{
			int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

			if(diff<=min_diff) 
			{
				min_diff = diff;
				min_index = i;
			}
		}

		FileWriter fw = new FileWriter("output.txt");
		fw.write("The goodies selected for distribution are:\n\n");
		for(int i=min_index;i<min_index + number_of_employees; i++) 
		{
			fw.write(goodies_items.get(i).toString() + "\n");
		}
		fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
		fw.close();
	}
}

