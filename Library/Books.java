
import java.util.Collections;

public class Books implements Comparable<Books> {
	String bookName;
	String authorName;
	int ISBNno;
	int totalquantity;
	int bookprice;
	int borrowcount=0;
	int availablequantity;
	String addername;
	public Books(String bookName,String authorName,int ISBNno,int totalquantity,int availablequantity,int bookprice,String addername) {
		this.bookName=bookName;
		this.authorName=authorName;
		this.ISBNno=ISBNno;
		this.totalquantity=totalquantity;
		this.availablequantity=availablequantity;
		this.bookprice=bookprice;
		this.addername=addername;
	}
	public static void addbook(int currentadmin) {
		System.out.println("Enter 0 to EXIT");
		System.out.print("Enter a Bookname: ");
		String book_name=Mainhome.sc.nextLine();
		if(book_name.equals("0"))
		{
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a author name: ");
		String author_name=Mainhome.sc.nextLine();
		if(author_name.equals("0"))
		{
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a ISBNno: ");
		int ISBN_no=Integer.parseInt(Mainhome.sc.nextLine());
		if(ISBN_no==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a quantity: ");
		int quantity=Integer.parseInt(Mainhome.sc.nextLine());
		if(quantity==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a Available quantity: ");
		int avail_quantity=Integer.parseInt(Mainhome.sc.nextLine());
		if(avail_quantity==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		System.out.print("Enter a book price: ");
		int book_price=Integer.parseInt(Mainhome.sc.nextLine());
		if(book_price==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		Mainhome.books.add(new Books(book_name,author_name,ISBN_no,quantity,avail_quantity,book_price,Mainhome.admin.get(currentadmin).name));
		if(quantity==1)
		{
			System.out.println("Book added successfully....");
			System.out.println();
		}
		else
		{
			System.out.println("Books added successfully....");
			System.out.println();
		}
		Admin.adminhomepage(currentadmin);
	}
	public static void modify(int currentadmin) {
		System.out.println("Enter 0 to EXIT");
		System.out.println(" 1.Modify the Quantity\n 2.Delete a book");
		System.out.print("Enter a choice: ");
		int n=Integer.parseInt(Mainhome.sc.nextLine());
		if(n==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		if(n==1) {
			Quantity(currentadmin);
		}
		else if(n==2) {
			Delete(currentadmin);
		}
	}
	public static void Delete(int currentadmin) {
		System.out.println("Enter 0 to EXIT");
		System.out.print("Enter a Book name: ");
		String book_name=Mainhome.sc.nextLine();
		if(book_name.equals("0")) {
			modify(currentadmin);
		}
		System.out.print("Enter a ISBN number name: ");
		int ISBN_no=Integer.parseInt(Mainhome.sc.nextLine());
		if(ISBN_no==0) {
			modify(currentadmin);
		}
		boolean f=false;
		for(int i=0;i<Mainhome.books.size();i++)
		{
			if(Mainhome.books.get(i).bookName.equals(book_name) && Mainhome.books.get(i).ISBNno==ISBN_no) {
				Mainhome.books.remove(i);
				System.out.println("Books deleted successfully...");
				System.out.println("available boooks list is : "+Mainhome.books.size());
				f=true;
				break;
			}
		}
		if(!f)
		{
			System.out.println("your given book name or ISBN number is wrong please enter correctly");
			Quantity(currentadmin);
		}
		modify(currentadmin);
	}
	public static void Quantity(int currentadmin) {
		System.out.println("Enter 0 to EXIT");
		System.out.print("Enter a Book name: ");
		String book_name=Mainhome.sc.nextLine();
		if(book_name.equals("0")) {
			modify(currentadmin);
		}
		System.out.print("Enter a ISBN number name: ");
		int ISBN_no=Integer.parseInt(Mainhome.sc.nextLine());
		if(ISBN_no==0) {
			modify(currentadmin);
		}
		boolean f=false;
		for(int i=0;i<Mainhome.books.size();i++)
		{
			if(Mainhome.books.get(i).bookName.equals(book_name) && Mainhome.books.get(i).ISBNno==ISBN_no) {
				System.out.println("Enter a quantity: ");
				int no_of_quantity=Integer.parseInt(Mainhome.sc.nextLine());
				Mainhome.books.get(i).totalquantity+=no_of_quantity;
				Mainhome.books.get(i).availablequantity+=no_of_quantity;
				System.out.println("Books quantity added succesfully....");
				System.out.println("available book quantity is : "+Mainhome.books.get(i).totalquantity);
				f=true;
				break;
			}
		}
		if(!f)
		{
			System.out.println("your given book name or ISBN number is wrong please enter correctly");
			Quantity(currentadmin);
		}
		modify(currentadmin);
	}
	public static void viewBooks(int currentadmin) {
		System.out.println("\n------VIEW BOOKs------");
		System.out.println("Enter 0 to EXIT:");
		System.out.println("1.View books in sorted by name. \n2.View books in sorted by available quantity\nEnter your choice: ");
		int n=Integer.parseInt(Mainhome.sc.nextLine());
		if(n==0)
		{
			Admin.adminhomepage(currentadmin);
		}
		else if(n==1)
		{
			System.out.println("\n------------SORTED BOOKS LIST---------");
			sortbyname(currentadmin);
		}
		else if(n==2)
		{
			System.out.println("\n------------BOOKS LIST---------");
			Collections.sort(Mainhome.books,(o1,o2)->{
				return o1.availablequantity - o2.availablequantity;
			});
		}
		display(currentadmin,"admin");
	}
	private static void sortbyname(int currentadmin) {
		Collections.sort(Mainhome.books);
		display(currentadmin,"admin");
	}
	public static void display(int currentperson,String person) {
		
		for(int i=0;i<Mainhome.books.size();i++)
		{
			System.out.println("Book Name: "+Mainhome.books.get(i).bookName);
			System.out.println("Author name: "+Mainhome.books.get(i).authorName);
			System.out.println("ISBN number of the book: "+Mainhome.books.get(i).ISBNno);
			System.out.println("Book added in library by: "+Mainhome.books.get(i).addername);
			System.out.println("Total Quantity of Books: "+Mainhome.books.get(i).totalquantity);
			System.out.println("Available Quantity of Books: "+Mainhome.books.get(i).availablequantity+"\n");
			System.out.println();
			
		}
		if(person.equals("admin")) {
		Admin.adminhomepage(currentperson);
		}
		else {
			User.userhomepage(currentperson);
		}
	}
	@Override
	public int compareTo(Books o) {
        if(this.bookName.compareTo(o.bookName)>0) {
        	return 1;
        }
        else if(this.bookName.compareTo(o.bookName)<0)
        {
        	return -1;
        }
		return 0;
    }
	public int compare(Books other) {
//      return this.bookName.compareTo(other.bookName);
		return 0;
  }
	public static void searchbooks(int currentadmin) {
		System.out.println("------SEARCH BOOKs------");
		System.out.println("Enter 0 to EXIT:");
		System.out.println("ENTER ISBM number or Book name for search: ");
		String str=Mainhome.sc.nextLine();
		if(str.equals("0"))
		{
			Admin.adminhomepage(currentadmin);
		}
		int disp=-1;
		try {
			int N=Integer.parseInt(str);
			for(int i=0;i<Mainhome.books.size();i++)
			{
				if(Mainhome.books.get(i).ISBNno==N)
				{
					disp=i;
					break;
				}
			}
		}
		catch(Exception e)
		{
			for(int i=0;i<Mainhome.books.size();i++)
			{
				if(Mainhome.books.get(i).bookName.equals(str))
				{
					disp=i;
					break;
				}
			}
		}
		if(disp>=0)
		{
			System.out.println("-----DETAILS OF THE BOOK-----");
			System.out.println("ISBN number of the book: "+Mainhome.books.get(disp).ISBNno);
			System.out.println("Book Name: "+Mainhome.books.get(disp).bookName);
			System.out.println("Author name: "+Mainhome.books.get(disp).authorName);
			System.out.println("Book added in libarary by: "+Mainhome.books.get(disp).addername);
			System.out.println("Total Quantity of Books: "+Mainhome.books.get(disp).totalquantity);
			System.out.println("Available Quantity of Books: "+Mainhome.books.get(disp).availablequantity+"\n");
			System.out.println();
			Admin.adminhomepage(currentadmin);
		}
		else
		{
			System.out.println("Your searched book not found Enter a correct number or book name\n");
			searchbooks(currentadmin);
		}
	}
 
	 
} Books {
    
}
