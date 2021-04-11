package interview;

import java.util.ArrayList;

public class Income_Tax_Calculator extends Person_Details implements Runnable {
	
	static ArrayList <Income_Tax_Calculator> l;
	
	static{
	l=new ArrayList<Income_Tax_Calculator>();
	}
	public Thread t=null;
	
	Income_Tax_Calculator()
	{
		super();
		t = new Thread(this);
	}
	Income_Tax_Calculator(int age,int year,String name,int income,int investment)
	{
		super(age,year,name,income,investment);
		t = new Thread(this);
	}
	private int calculate_tax_by_percent(int taxable_income,int tax_percentage)
	{
		int tax_amount=0;
		
		tax_amount=(taxable_income * tax_percentage)/100;
		
		return tax_amount;
	}
	public int calculate_tax()
	{
		int taxable_income;
		int tax;
		
		taxable_income = this.income-this.investment;
		//System.out.println("Name : " + this.getName() + "Taxable Income : " + taxable_income);
		
		int tax_percentage= Year_Percentage.get_tax_percentage(this.year , taxable_income);
		//System.out.println("Name : " + this.getName() + "Tax Percentage : " + tax_percentage);
				
		tax = calculate_tax_by_percent(taxable_income,tax_percentage);
		//System.out.println("Name : " + this.getName() + "Tax by Percentage : " + tax);
		
		tax= Year_Percentage.calculate_cess_tax(this.year,tax);
		
		return tax;
	}
	public void run()
	{
		try {
			if(this.getName()=="master")
			{
				Income_Tax_Calculator.execute();
			}
			else
			{
				System.out.println("Name : " + this.getName() + "\tTax :" + this.calculate_tax());
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in "+ this.getName());
			System.out.println(e.toString());
		}
	}
	public static void execute()
	{
		try 
		{	
			Thread.sleep(80);
			Income_Tax_Calculator obj=null;
			
			synchronized (l)
			{
				if(l.size()>0)
					obj=l.get(0);
				else
					obj=null;
			}
			
			while(obj!=null)
			{
				obj.t.start();
				synchronized (l)
				{
					l.remove(obj);
				}
				
				Thread.sleep(50);
				
				synchronized (l)
				{
					if(l.size()>0)
						obj=l.get(0);
					else
						obj=null;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void generate_people()
	{
		try {
			for (char c='a' ;c < 'e' ; c+=1)
			{
				Income_Tax_Calculator obj=new Income_Tax_Calculator(25,2018,"Rana"+c,825000 + (int)c * 10000,75000);
				Income_Tax_Calculator obj2 = new Income_Tax_Calculator(25,2018,"Shaurya"+c,1825000 + (int)c * 20000,275000);
				synchronized(l)
				{
					l.add(obj);
					l.add(obj2);
				}
				System.out.println("Generated people " + ((int)c -96));
				Thread.sleep(100);
			}
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
		}
			
	}
public static void main(String[] args) {
		
		System.out.println("********** INCOME TAX CALCULATOR ********** ");
		
		Income_Tax_Calculator master=new Income_Tax_Calculator();  // Master Thread for all calculation threads
	    master.t.start();
	    
	    Income_Tax_Calculator.generate_people();	// Main will execute the user inputs
	}
}
