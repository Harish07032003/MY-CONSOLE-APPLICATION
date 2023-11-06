package Library;


import java.util.*;
public class Mainhome {
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Admin> admin=new ArrayList<>();
	static ArrayList<User> user=new ArrayList<>();
	static ArrayList<Books> books=new ArrayList<>();
	public static void main(String[] args) {
		admin.add(new Admin("harish","hari","hari123"));
		user.add(new User("Harish","hari@gmail.com","hari123",1500));
		user.add(new User("Jones","jones@gmail.com","jones123",1500));
		user.add(new User("Vishnu","vishnu@gmail.com","vishnu123",1500));
		books.add(new Books("Preacher","Author c",3,55,55,160,"harish"));
		books.add(new Books("Batman:The Dark Knight","Author d",4,60,60,140,"harish"));
		books.add(new Books("Kingdom come","Author a",1,50,50,150,"harish"));
		books.add(new Books("The sandman","Author e",5,70,70,250,"harish"));
		books.add(new Books("X-men","Author b",2,120,120,170,"harish"));
		
		home();
	}

	public static void home() {
		System.out.println();
		System.out.println("-----------WELCOME TO OUR LIBRARY-------------");
		System.out.println("Enter 1 for ADMIN Login");
		System.out.println("Enter 2 for USER Login");
		System.out.println("Enter any other number for EXIT");
		System.out.print("Enter your choice: ");
		int n=Integer.parseInt(sc.nextLine());
		if(n==1)
		{
			Admin.login();
		}
		else if(n==2)
		{
			User.login();
		}
		else
		{
			System.exit(0);
		}
	}

}
