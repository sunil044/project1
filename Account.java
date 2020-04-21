package project1;

import java.math.BigDecimal;
import java.util.*;
import java.util.Scanner;

public class Account {
	
	public static void main(String[] args)
	{
		Map<Long, AccountDetails> accList = new HashMap<>();
		
		Scanner scanner = new Scanner(System.in);
		int userChoice;
		boolean quit = false;

		do {

			System.out.println("1. Create Account");
			System.out.println("2. Search Account");
			System.out.println("3. Deposit money");
			System.out.println("4. Withdraw money");
			System.out.println("5. Delete Account");
			System.out.print("Your choice, 0 to quit: ");
			userChoice = scanner.nextInt();

			switch (userChoice) {

			case 1: AccountDetails accDetails = new AccountDetails();
					accDetails=createAccount(scanner,accDetails);
					accList.put(accDetails.getAccNo(), accDetails);
				break;
			
			case 2: searchAccount(accList,scanner,"search");
				break;
			
			case 3: searchAccount(accList,scanner,"deposit");
				break;

			case 4:searchAccount(accList,scanner,"withdraw");
				break;
				
			case 5:searchAccount(accList,scanner,"delete");
			break;	

			case 0:	quit = true; System.out.println("Thank you.");
				break;

			default: System.out.println("Wrong choice.");
				break;

			}
			System.out.println();
		} while (!quit);

		System.out.println("Bye!");

	}

	public static void searchAccount(Map<Long, AccountDetails> accList, Scanner scanner,String param) 
	{
		System.out.println("Enter Account Number :");
		Long accNum = scanner.nextLong();
		
		for(Map.Entry<Long, AccountDetails> map1 : accList.entrySet())
		{
			if(map1.getKey().longValue() == accNum  )
			{
				if( param.equals("search") )
				{
					System.out.println("Balance : "+map1.getValue().getAccType() );
					System.out.println("Accunt No. : "+map1.getValue().getAccNo() );
					System.out.println("Name : "+map1.getValue().getName() );
					System.out.println("Balance : "+map1.getValue().getAmount() );
					System.out.println("Currency : "+map1.getValue().getCurrency() );
				}
				
				if( param.equals("deposit") )
				{
					BigDecimal amount = new BigDecimal("0");
					System.out.print("Amount to deposit: ");
					amount = scanner.nextBigDecimal();
	
					if ( amount.compareTo(new BigDecimal("0")) < 0 )
						System.out.println("Can't deposit nonpositive amount.");
					else 
					{
						BigDecimal existingAmt = map1.getValue().getAmount(); 
						map1.getValue().setAmount(existingAmt.add(amount) );
						System.out.println( amount + " has been deposited to Account number "+map1.getValue().getAccNo());
					}
				}
				
				if( param.equals("withdraw") )
				{
					BigDecimal amount = new BigDecimal("0");
					System.out.print("Amount to Withdraw: ");
					amount = scanner.nextBigDecimal();
					
					if ( amount.compareTo(new BigDecimal("0")) <= 0 )
						System.out.println("Can't withdraw nonpositive amount.");
					else 
					{
						BigDecimal existingAmt = map1.getValue().getAmount(); 
						map1.getValue().setAmount(existingAmt.subtract(amount) );
						System.out.println( amount + " has been withdrawn from Account number "+map1.getValue().getAccNo());
					}
				}
				
				if( param.equals("delete") )
				{                                           
					accList.remove(accNum);
				}
				
			}
			
			else {
				System.out.println("Please enter the correct account number");
				continue;
				
			}
		}
		
	}
	
	public static AccountDetails createAccount (Scanner scanner, AccountDetails accDetails  ) 
	{
		try 
		{
			System.out.println("Select Account Type");

			int ch;
			String accType = null;
			do {
				System.out.println("1 : Saving");
				System.out.println("2 : Current");
				ch = scanner.nextInt();

				switch(ch) {
				case 1: accType = "Savings";
				break;

				case 2: accType = "Current"; 
				break;

				default : System.out.println("Please make proper choice");	
				}
			} while(ch > 2 || ch < 1);	

			System.out.println("Enter Your Full Name");
			String name = scanner.next();
			while(!name.matches("[a-zA-Z ,]+")) {
				System.out.println("Please Enter valid Name");
				name = scanner.next();
			}

			Long accNum = null;
			boolean isNumber;

			do {
				System.out.println("Enter Account Number");
				if(scanner.hasNextLong()) {
					accNum = scanner.nextLong();
					isNumber = true;
				} else {
					System.out.println("I dont understand");
					isNumber = false;
					scanner.next();
				}
			}while(!(isNumber));

			BigDecimal amount = null;

			do {
				System.out.println("Amount");
				
				if(scanner.hasNextBigDecimal()) {
					amount = scanner.nextBigDecimal();
					isNumber = true;
				} else {
					System.out.println("I dont understand");
					isNumber = false;
					scanner.next();
				}
			}while(!(isNumber));

			System.out.println("Enter Currency Type");
			String curType = null;
			do {
				curType = scanner.next();
				if(curType.equalsIgnoreCase("USD") || curType.equalsIgnoreCase("INR") || curType.equalsIgnoreCase("AED") )
				{
					isNumber = true;
				} else {
					System.out.println("Please Enter Valid Currency Type");
					isNumber = false;
					//scanner.next();					
				}
			}while(!(isNumber));




			System.out.println("Your Account has been created");

			accDetails.setAccType(accType);
			accDetails.setName(name);
			accDetails.setAccNo(accNum);
			accDetails.setAmount(amount); 
			accDetails.setCurrency(curType);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return accDetails;

	}

}


class AccountDetails
{
	private String accType;
	private String name;
	private Long accNo;
	private BigDecimal amount;
	private String currency;
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAccNo() {
		return accNo;
	}
	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}