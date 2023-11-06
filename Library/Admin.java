package Library;
import java.util.*;
public class Admin {
	String name;
	String email;
	String password;
	public Admin(String name,String email,String password) {
		this.name=name;
		this.email=email;
		this.password=password;
	}
	public static void login() {
		System.out.println("\n\n");
		System.out.println("-----------WELCOME TO OUR LIBRARY-------------\nEnter \"0\" to exit");
		int currentadmin=-1;
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
			for(int i=0;i<Mainhome.admin.size();i++)
			{
				if(Mainhome.admin.get(i).email.equals(Email_id) && Mainhome.admin.get(i).password.equals(pass_word)) {
					currentadmin=i;
					break;
				}
			}
			if(currentadmin<0)
			{
				System.out.println("Enter correct EMAIL ID or PASSWORD");
				login();
			}
			else {
				adminhomepage(currentadmin);
			}
		}
		
	}
	public static void adminhomepage(int currentadmin) {
		System.out.println("\n\n");
		System.out.println("-----------WELCOME TO OUR LIBRARY "+Mainhome.admin.get(currentadmin).name+"-----------\nEnter \"0\" to exit");
		
		System.out.print(" 1.Add a book\n 2.Modift a book\n 3.Add admins\n 4.Add users\n 5.View list of all books\n 6.View all the users \n 7.Search a book\n 8.fine management\n Enter your choice: ");
		int n=Integer.parseInt(Mainhome.sc.nextLine());
		if(n==0)
		{
			Mainhome.home();
		}
		switch(n) {
		case 1:
			Books.addbook(currentadmin);
			break;
		case 2:
			Books.modify(currentadmin);
			break;
		case 3:
			addAdmin(currentadmin);
			break;
		case 4:
			User.adduser(currentadmin);
			break;
		case 5:
			Books.viewBooks(currentadmin);
			break;
		case 6:
			User.viewusers(currentadmin);
			break;
		case 7:
			Books.searchbooks(currentadmin);
			break;
		case 8:
			finemanage(currentadmin);
	}
  }
	private static void finemanage(int currentadmin) {
		
	}
	public static void finesetting(int currentperson,String startdate,String returndate,int bookno) {
		String start[]=startdate.split("/");
		String ret[]=returndate.split("/");
		
		int day=Math.abs(Integer.parseInt(ret[0])-Integer.parseInt(start[0]));
		int finedays=31-day;
		if(finedays>=15)
		{
			int totalfine=Math.abs(finedays-15);
			Mainhome.user.get(currentperson).userBal-=(totalfine*2);
			for(int j=0;j<Mainhome.user.size();j++)
			{
				System.out.println((j+1)+". username= "+Mainhome.user.get(j).username+", userbalance= "+Mainhome.user.get(j).userBal);
			}
			for(int i=0;i<Mainhome.books.size();i++)
			{
				if(Mainhome.books.get(i).ISBNno==bookno)
				{
					Mainhome.books.get(i).availablequantity++;
					for(int j=0;j<Mainhome.books.size();j++)
    				{
    					System.out.println((j+1)+". ISBN no: "+Mainhome.books.get(j).ISBNno+", Book name: "+Mainhome.books.get(j).bookName+", availablequantity= "+Mainhome.books.get(j).availablequantity);
    				}
					System.out.println("Book returned successfully.....");
					User.userhomepage(currentperson);
				}
			}
		}
	}
	public static void addAdmin(int currentadmin) {
		System.out.println();
		System.out.println("Enter 0 to EXIT:");
		System.out.print("Enter a Admin name: ");
		String name=Mainhome.sc.nextLine();
		if(name.equals("0")) {
			adminhomepage(currentadmin);
		}
		System.out.print("Enter a Admin email: ");
		String email=Mainhome.sc.nextLine();
		if(email.equals("0")) {
			adminhomepage(currentadmin);
		}
		System.out.print("Enter a Admin password: ");
		String password=Mainhome.sc.nextLine();
		if(password.equals("0")) {
			adminhomepage(currentadmin);
		}
		Mainhome.admin.add(new Admin(name,email,password));
		System.out.println("Admin added successfully...");
		adminhomepage(currentadmin);
	}
}
