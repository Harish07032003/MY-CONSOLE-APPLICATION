package Library;

import java.util.*;
public class User {
	String username;
	String email;
	String password;
	int userBal=1500;
	
	public User(String username,String email,String password,int userBal) {
		this.username=username;
		this.email=email;
		this.password=password;
		this.userBal=userBal;
	}
	static HashMap<Integer,ArrayList<String>> borrowedbooks=new HashMap<>();
	public static void login() {
		System.out.println("\n\n-----------WELCOME TO OUR LIBRARY-------------\nEnter \"0\" to exit");
		int currentuser=-1;
		System.out.print("Enter your Email-Id: ");
		String Email_id=Mainhome.sc.nextLine();
		if(Email_id.equals("0"))
		{
			Mainhome.home();
		}
		else
		{
			System.out.print("Enter your password: ");
			String pass_word=Mainhome.sc.nextLine();
			for(int i=0;i<Mainhome.user.size();i++)
			{
				if(Mainhome.user.get(i).email.equals(Email_id) && Mainhome.user.get(i).password.equals(pass_word)) {
					currentuser=i;
					break;
				}
			}
			if(currentuser<0)
			{
				System.out.println("Enter correct EMAIL ID or PASSWORD");
				login();
			}
			else {
				userhomepage(currentuser);
			}
		}
		
	}
    
	public static void userhomepage(int currentuser) {
		System.out.println("\n\n-----------WELCOME TO OUR LIBRARY "+Mainhome.user.get(currentuser).username+"-----------\nEnter \"0\" to exit");
		System.out.println(" 1.view a list of all Books\n 2.Select the Book\n 3.Return book");
		System.out.print("Enter your choice: ");
		int n=Integer.parseInt(Mainhome.sc.nextLine());
		if(n==0)
		{
			Mainhome.home();
		}
		else if(n==1)
		{
			Books.display(currentuser,"user");
		}
		else if(n==2) {
			User.selectforborrow(currentuser);
		}
		else if(n==3)
		{
			User.returnbook(currentuser);
		}
				
	}

	

	public static void selectforborrow(int currentuser) {
		System.out.println("----------SELECT THE BOOK----------\n Enter 0 to EXIT: ");
		ArrayList<Integer> cart=new ArrayList<>();
		int totalbookbal=0;
		int loop=1;
		int n1=0;
		while(true) {
		for(int i=0;i<Mainhome.books.size();i++)
		{
			System.out.println((i+1)+". "+Mainhome.books.get(i).bookName);
		}
		System.out.println("choose your Book no or press 'Enter' to continue: ");
		int n=0;
		try {
	    n=Integer.parseInt(Mainhome.sc.nextLine());
		}
		catch(Exception e)
		{
			System.out.println("Enter 1 to continue: ");
			n1=Integer.parseInt(Mainhome.sc.nextLine());
			break;
		}
	    if(n<=0) {
	    	break;
	    }
	    if(cart.size()==3)
	    {
	    	System.out.println("sorry you have already taken 3 books\n Enter '1' to continue or '0' to EXIT ");
	    	n1=Integer.parseInt(Mainhome.sc.nextLine());
    	    if(n1==1)
    	    {
    	    	break;
    	    }
    	    else
    	    {
    	    	userhomepage(currentuser);
    	    }
	    	break;
	    }
	    boolean copied=false;
	    if(borrowedbooks.containsKey(n-1))
	    {
	    	ArrayList<String> dupcheck=new ArrayList();
	    	dupcheck=borrowedbooks.get(n-1);
	    	for(int i=0;i<dupcheck.size();i++)
	    	{
	    		String st[]=dupcheck.get(i).split("-");
	    		if(st[0].equals(Mainhome.user.get(currentuser).username)) {
	    			System.out.println("You already taken this book copy please select another book\n");
	    			copied=true;
	    		}
	    	}
	    }
	    if(!cart.contains(n-1) && !copied)
	    {
	    	if(Mainhome.user.get(currentuser).userBal>500 && (totalbookbal+Mainhome.books.get(n-1).bookprice)<=Mainhome.user.get(currentuser).userBal ){
	    	totalbookbal+=Mainhome.books.get(n-1).bookprice;
	    	cart.add(n-1);
	    	}
	    	else
	    	{
	    		System.out.println("you are not eligible to borrow the book, your amount was insufficient give continue to added the selected books in cart \n\nEnter '1' to continue or '0' to Exit \n");
	    	    n1=Integer.parseInt(Mainhome.sc.nextLine());
	    	    if(n1==1)
	    	    {
	    	    	break;
	    	    }
	    	    else
	    	    {
	    	    	userhomepage(currentuser);
	    	    }
	    	}
	    }
	    else
	    {
	    	System.out.println("Please select a different book, you can take one book once");
	    }
		}
	    while(true) {
    	    if(n1==1)
    	    {
    	    	System.out.println("Enter '0' to EXIT:\n 1.Remove the book from cart\n 2.continue");
    	    	int n2=Integer.parseInt(Mainhome.sc.nextLine());
    	    	if(n2==0)
    	    	{
    	    		break;
    	    	}
    	    	else if(n2==1)
    	    	{
    	    		for(int i=0;i<cart.size();i++)
    	    		{
    	    			System.out.println((i+1)+". "+Mainhome.books.get(i).bookName);
    	    		}
    	    		System.out.println("Enter '0' to EXIT\n Enter your choice: ");
    	    		int n3=Integer.parseInt(Mainhome.sc.nextLine());
    	    		if(n3==0)
    	    		{
    	    			continue;
    	    		}
    	    		else {
    	    			cart.remove(cart.get(n3-1));
    	    			System.out.println(cart);
    	    		}
    	    	}
    	    	else if(n2==2)
    	    	{
    	    		String date="";
	    			while(true)
	    			{
	    			System.out.println("\nEnter a date with format(DD/MM/YYYY): ");
	    			date=Mainhome.sc.nextLine();
	    			try {
	    			String s[]=date.split("/");
	    			if(Integer.parseInt(s[0])<=31 && Integer.parseInt(s[1])<=12 && Integer.parseInt(s[2])<=2023 ) {
	    				break;
	    			}
	    			else
	    			{
	    				System.out.println("Enter correct Date");
	    				continue;
	    			}
	    			}
	    			catch(Exception e)
	    			{
	    				System.out.println("Enter correct format");
	    				continue;
	    			}
	    			}
    	    		for(int i=0;i<cart.size();i++)
    	    		{
    	    			ArrayList<String> names=new ArrayList<>();
    	    			
    	    				Mainhome.user.get(currentuser).userBal=Mainhome.user.get(currentuser).userBal-Mainhome.books.get(i).bookprice;
//    	    				for(int j=0;j<Mainhome.user.size();j++)
//    	    				{
//    	    					System.out.println((j+1)+". username= "+Mainhome.user.get(j).username+", userbalance= "+Mainhome.user.get(j).userBal);
//    	    				}
    	    				Mainhome.books.get(cart.get(i)).availablequantity--;
//    	    				for(int j=0;j<Mainhome.books.size();j++)
//    	    				{
//    	    					System.out.println((j+1)+". ISBN no: "+Mainhome.books.get(j).ISBNno+", Book name: "+Mainhome.books.get(j).bookName+", availablequantity= "+Mainhome.books.get(j).availablequantity);
//    	    				}
    	    				if(!borrowedbooks.containsKey(cart.get(i)))
    	    				{
    	    					names.add((Mainhome.user.get(currentuser).username+"-"+date));
    	    				}
    	    				else
    	    				{
    	    					names=borrowedbooks.get(cart.get(i));
    	    					names.add((Mainhome.user.get(currentuser).username+"-"+date));
    	    				}
    	    				borrowedbooks.put(cart.get(i),names);
    	    				System.out.println(borrowedbooks.get(cart.get(i)));
    	    				
    	    				System.out.println("books borrowed successfully...");
    	    		}
    	    		cart.clear();
//    	    		System.out.println(cart);
//    	    		for(int i:borrowedbooks.keySet())
//    	    		{
//    	    			System.out.println(borrowedbooks.get(i));
//    	    		}
    	    		break;
    	    	}
    	    }
    	    else
    	    {
    	    	userhomepage(currentuser);
    	    }
    	    }
	    
	    userhomepage(currentuser);
		
	}
	public static void returnbook(int currentuser) {
		System.out.println("Enter a book ISBN number: ");
		int n=Integer.parseInt(Mainhome.sc.nextLine());
		boolean f=false;
		String startdate="";
		String returndate="";
		if(borrowedbooks.containsKey(n-1)) {
			ArrayList<String> dupcheck=new ArrayList();
	    	dupcheck=borrowedbooks.get(n-1);
	    	for(int i=0;i<dupcheck.size();i++)
	    	{
	    		String st[]=dupcheck.get(i).split("-");
	    		if(st[0].equals(Mainhome.user.get(currentuser).username)) {
	    			startdate=st[1];
	    			f=true;
	    		}
	    	}
		}
		if(f) {
		
		while(true)
		{
		System.out.println("\nEnter a return date with format(DD/MM/YYYY): ");
		returndate=Mainhome.sc.nextLine();
		try {
		String s[]=returndate.split("/");
		if(Integer.parseInt(s[0])<=31 && Integer.parseInt(s[1])<=12 && Integer.parseInt(s[2])<=2023 ) {
			break;
		}
		else
		{
			System.out.println("Enter correct Date");
			continue;
		}
		}
		catch(Exception e)
		{
			System.out.println("Enter correct format");
			continue;
		}
		}
		}
		else {
			System.out.println("Enter a correct ISBN number...");
			returnbook(currentuser);
		}
		
		Admin.finesetting(currentuser,startdate,returndate,n);
	}
	public static void adduser(int currentadmin) {
		System.out.println();
		System.out.println("Enter 0 to EXIT:");
		System.out.print("Enter a user name: ");
		String name=Mainhome.sc.nextLine();
		if(name.equals("0")) {
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a user email: ");
		String email=Mainhome.sc.nextLine();
		if(email.equals("0")) {
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a user password: ");
		String password=Mainhome.sc.nextLine();
		if(password.equals("0")) {
			Admin.adminhomepage(currentadmin);
		}
		Mainhome.user.add(new User(name,email,password,1500));
		System.out.println("user added successfully...");
		Admin.adminhomepage(currentadmin);
	}

	public static void viewusers(int currentadmin) {
		System.out.println("\n-----------------Libarary Users List--------------------");
		for(int i=0;i<Mainhome.user.size();i++)
		{
			System.out.println((i+1)+". "+Mainhome.user.get(i).username);
		}
		Admin.adminhomepage(currentadmin);
	}
}
