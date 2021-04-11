package interview;

public class Year_Percentage {
	
	static int get_tax_percentage(int year ,int taxable_income)
	{
		int percent=0;
		
		if(year==2018)
		{
			if(taxable_income < 100000) 
				percent=0;
			else if(taxable_income<500000)
				percent=10;
			else if(taxable_income<1000000)
				percent=20;
			else if(taxable_income>100000)
				percent=30;
		}
		else if(year==2019)
		{
			if(taxable_income < 100000) 
				percent=0;
			else if(taxable_income<600000)
				percent=10;
			else if(taxable_income<120000)
				percent=20;
			else if(taxable_income>120000)
				percent=30;
		}
		
		return percent;
		
	}
	static int calculate_cess_tax(int year,int initial_tax)
	{
		int cess_percent=0;
		if(year==2018 && initial_tax > 300000)
		{
			cess_percent=1;
		}
		else if(year==2019 && initial_tax > 300000)
		{
			cess_percent=2;
		}
		
		int tax = (initial_tax*(100+cess_percent))/100;
		return  tax;
	}
}
