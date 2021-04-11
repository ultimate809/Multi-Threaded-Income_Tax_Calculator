package interview;

public class Person_Details {
	protected int age;
	protected int year;
	protected String name;
	protected int income;
	protected int investment;
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}

	Person_Details()
	{
		name="master";
	}
	Person_Details(int age,int year,String name,int income,int investment)
	{
		this.age=age;
		this.year=year;
		this.name=name;
		this.income=income;
		this.investment=investment;
	}
	
}
