package com.obs.mainwork;

import java.util.Scanner;

import com.obs.bean.AccountantBean;
import com.obs.dao.AccountantDAO;
import com.obs.dao.AccountantDAOimpl;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;

public class Main {
	static int count=10001;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome To Online Banking System");
		 
		System.out.println("1.Accountant 2.Customer");
		System.out.println("choose your option");
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Accountant-LOGIN");
			System.out.println("Enter username");
			String uname=sc.next();
			System.out.println("Enter Password");
			String pass=sc.next();
			
			AccountantDAO a=new AccountantDAOimpl();
			
			try {
				AccountantBean ab=a.LoginAccountant(uname, pass);
				//System.out.println(ab);
				System.out.println("Welcome "+ab.getEname());
				
				
				boolean y=true;
				
				while(y) {
					System.out.println("1. Adding new account for customer\r\n"
							+ "2. Edit existing available account\r\n"
							+ "3. Remove the account by account number\r\n"
							+ "4. Viewing particular account details by giving account number\r\n"
							+ "5. Viewing all the account details\r\n"
							+ "6. Taking care of deposit and withdrawal operations\r\n");
					
					int x=sc.nextInt();
					
					if(x==1) {
						String a1="213"+count;
						count++;
						System.out.println("Enter Name");
			 			String a2=sc.next();
			 			System.out.println("Enter Balance");
			 			int a3=sc.nextInt();
			 			System.out.println("Enter Email");
			 			String a4=sc.next();
			 			System.out.println("Enter Password");
			 			String a5=sc.next();
			 			System.out.println("Enter Mobile number");
			 			String a6=sc.next();
			 			System.out.println("Enter Address");
			 			String a7=sc.next();
			 			
			 			String s1=null;
						try {
							s1 = a.addCustomer(a1,a2,a3,a4,a5,a6,a7);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 			if(s1!=null) {
			 				System.out.println(s1);
			 			}
					}
				}
			
				
			} catch (AccountantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	
	}

}
