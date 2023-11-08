package supermarket;

import java.util.*;
import java.util.Map.Entry;
public class main {
	static Scanner sc=new Scanner(System.in);
	static HashMap<String,Admin> admin=new HashMap<>();
	static HashMap<String,Customers> customers=new HashMap<>();
	static HashMap<Integer,Products> products=new HashMap<>();
	static HashMap<Integer,Cart> cart1=new HashMap<>();
	public static void main(String[] args) {
		admin.put("harish@gmail.com",new Admin("Harish","harish@gmail.com","hari123"));
		customers.put("hari@gmail.com",new Customers("Harish","hari@gmail.com","hari123",1000,0));
		customers.put("jones@gmail.com",new Customers("Jones","jones@gmail.com","jones123",1000,0));
		customers.put("vishnu@gmail.com",new Customers("Vishnu","vishnu@gmail.com","vishnu123",1000,0));
		products.put(1,new Products(1,"Sugar",40,10,"Harish"));
		products.put(2,new Products(2,"Rice",50,10,"Harish"));
		products.put(3,new Products(3,"Atta",35,10,"Harish"));
		products.put(4,new Products(4,"Oil",100,20,"Harish"));
		products.put(5,new Products(5,"salt",25,20,"Harish"));
		cart1.put(1, new Cart(1,"hari@gmail.com",new ArrayList(Arrays.asList("1-Oil-8-200")),"07/11/2023"));
		home();
	}
	public static void home() {
		System.out.print("\n-------------------WELCOME TO SUPER MARKET-------------------\n1.Sign up\n2.Log in\nEnter your choice:");
		try {
			int n=Integer.parseInt(sc.nextLine());
			if(n==1)
			{
				signup();
			}
			else if(n==2)
			{
				login();
			}
		}
		catch(Exception e) {
			System.out.println("Enter option correctly");
			home();
		}
	}
	public static void login() {
		System.out.print("\n--------------------LOG IN--------------------\n Enter '0' to EXIT:\n Enter your Email ID: ");
		String email=sc.nextLine();
		if(admin.containsKey(email)) {
			System.out.print(" Enter your password: ");
			String password=sc.nextLine();
			if((admin.get(email).password).equals(password))
			{
				Admin.adminhome(email);
			}
			else if(password.equals("0"))
			{
				home();
			}
			else
			{
				System.out.println("Your username or password is wrong, please enter correctly");
				login();
			}
			
		}
		else if(customers.containsKey(email)) {
			System.out.print(" Enter your password: ");
			String password=sc.nextLine();
			if(customers.get(email).password.equals(password))
			{
				Customers.customerhome(email);
			}
			else if(password.equals("0"))
			{
				home();
			}
			else
			{
				System.out.println("Your username or password is wrong, please enter correctly");
				login();
			}
		}
		else if(email.equals("0"))
		{
			home();
		}
		else
		{
			System.out.println("please enter correct username or password");
			login();
		}
	}
	private static void signup() {
		System.out.print("Enter '0' to EXIT:\n Enter your Email ID: ");
		String email=sc.nextLine();
		
		if(email.substring(email.length()-10,email.length()).equals("@gmail.com")){
			if(!customers.containsKey(email))
			{
			System.out.println(" Enter your name: ");
			String name=sc.nextLine();
			System.out.print(" Enter your password: ");
			String password=sc.nextLine();
			customers.put(email,new Customers(name,email,password,1000,0));
			System.out.println("Sign up successfully......");
			Customers.customerhome(email);
			}
			else
			{
				System.out.println(" Email already exists...");
				signup();
			}
		}
		else
		{
			System.out.println("please enter the email id in correct format \"@gmail.com\"");
			signup();
		}
	}

}

class Admin {

	String adminname;
	String email;
	String password;
	public Admin(String adminname,String email,String password) {
		this.adminname=adminname;
		this.email=email;
		this.password=password;
	}
	public static void adminhome(String email) {
		System.out.println("\n--------------------WELCOME TO SUPER MARKET "+main.admin.get(email).adminname+"--------------------\n Enter '0' to EXIT\n 1.Add an item\n 2.Modify an item \n 3.Add admins \n 4.Add customers \n 5.View list of all produucts\n 6.search a product\n 7.purchased products list\n 8.Increase the credit item\n Enter your choice: ");
		try {
			int n=Integer.parseInt(main.sc.nextLine());
			switch(n) {
			case 0:
				main.home();
				break;
			case 1:
				Products.additems(email);
				break;
			case 2:
				Products.modifyitems(email);
				break;
			case 3:
				addadmin(email);
				break;
			case 4:
				addcustomer(email);
				break;
			case 5:
				Products.productlist(email);
				break;
			case 6:
				Products.search(email);
				break;
			case 7:
				Cart.purchasedproducts(email);
				break;
			case 8:
				Customers.Increasecredit();
				break;
				
			}
		}
		catch(Exception e) {
			System.out.println("Enter correct option");
			adminhome(email);
		}
	}
	
	public static void addadmin(String email2) {
		System.out.println("Enter '0' to EXIT: \n1.Enter an Email ID: ");
		String email=main.sc.nextLine();
		if(email.contentEquals("0"))
		{
			adminhome(email2);
		}
		if(email.substring(email.length()-10,email.length()).equals("@gmail.com")){
			if(!main.admin.containsKey(email))
			{
			System.out.print(" Enter your name: ");
			String name=main.sc.nextLine();
			System.out.print(" Enter your password: ");
			String password=main.sc.nextLine();
			main.admin.put(email,new Admin(name,email,password));
			System.out.println("Admin added successfully......");
			adminhome(email2);
			}
			else
			{
				System.out.println(" Email already exists...");
				addadmin(email2);
			}
		}
		else
		{
			System.out.println("please enter the email id in correct format \"@gmail.com\"");
			addadmin(email2);
		}
		
	}
	public static void addcustomer(String email2) {
		System.out.print("\n--------------------ADD CUSTOMER--------------------\n Enter '0' to EXIT:\n Enter your Email ID: ");
		String email=main.sc.nextLine();
		
		if(email.substring(email.length()-10,email.length()).equals("@gmail.com")){
			if(!main.customers.containsKey(email))
			{
			System.out.println(" Enter your name: ");
			String name=main.sc.nextLine();
			System.out.print(" Enter your password: ");
			String password=main.sc.nextLine();
			main.customers.put(email,new Customers(name,email,password,1000,0));
			System.out.println("Customer added successfully......");
			adminhome(email2);
			}
			else
			{
				System.out.println(" Email already exists...");
				addcustomer(email2);
			}
		}
		else
		{
			System.out.println("please enter the email id in correct format \"@gmail.com\"");
			addcustomer(email2);
		}
	}
}
class Customers {
	String name;
	String password;
	String email;
	int credit;
	int loyaltyscore;
	public Customers(String name,String email,String password,int credit,int loyaltyscore) {
		this.name=name;
		this.email=email;
		this.password=password;
		this.credit=credit;
		this.loyaltyscore=loyaltyscore;
	}
	static ArrayList<String> cart=new ArrayList<>(); 
	static ArrayList<Integer> rep_pro=new ArrayList<>();
	static int totalproductprice=0;
	public static void customerhome(String email) {
		System.out.print("\n----------------------------WELCOME TO SUPER MARKET "+main.customers.get(email).name+" --------------------------------\n------------------------------PURCHASE YOUR PRODUCT----------------------------\n Enter '0' to EXIT: \n 1.VIEW PRODUCT LIST\n 2.ADD MORE ITEMS\n 3.DELETE THE PRODUCT\n 4.VIEW KART\n 5.ADD MONEY TO THE ACCOUNT\n 6.PURCHASE HISTORY\n Enter your choice: ");
		int n=Integer.parseInt(main.sc.nextLine());
		switch(n) {
		case 0:
			main.home();
			break;
		case 1:
			selectproduct(email);
			break;
		
		case 2:
			selectproduct(email);
			break;
		case 3:
			removefromcart(email);
			break;
		case 4:
			viewcart(email);
			break;
		case 5:
			debitpayment(email);
			break;
		case 6:
			Cart.purchasehistory(email);
			break;
		}
		
	}
	public static void viewcart(String email2) {
		System.out.println("---------------------------------------KART LIST---------------------------------------");
		System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		int j=1;
			for(String i:cart) {
				String str[]=i.split("-");
				System.out.printf("| %4s %12s %17s %18s |",(j++),str[1],str[2],str[3]);
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------------------------------");
			customerhome(email2);
	}
	public static void debitpayment(String email2) {
		System.out.println("Enter an amount: ");
		int n=Integer.parseInt(main.sc.nextLine());
		main.customers.get(email2).credit+=n;
		System.out.println("Amount debitted in your account succesfully.....");
		customerhome(email2);
	}
	public static void selectproduct(String email) {
		
		while(true) {
			System.out.println();
			int j=1;
			System.out.println("---------------------------------------PRODUCT LIST---------------------------------------");
			System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
		for(int i:main.products.keySet()) {
			System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------");
		System.out.print("Enter '0' to Exit or Select the product to add in kart: ");
		int n=Integer.parseInt(main.sc.nextLine());
		
		
			if(n==0)
			{
				customerhome(email);
			}
			else if(!rep_pro.contains(n))
			{
				ArrayList<Integer> id=new ArrayList<>(main.products.keySet());
				int pro_id=id.get(n-1);
				String addcart="";
				System.out.print("Enter a quantity : ");
				int quantity=Integer.parseInt(main.sc.nextLine());
				if(quantity<=main.products.get(pro_id).quantity) {
					cart.add(pro_id+"-"+main.products.get(pro_id).name+"-"+quantity+"-"+main.products.get(pro_id).price);
//					System.out.println(cart);
					System.out.print(" Your product added to kart...\n continue to add product Enter '1' to continue:");
					if(main.sc.nextLine().equals("1")) {
						rep_pro.add(pro_id);
						totalproductprice+=main.products.get(pro_id).price*quantity;
						System.out.println("now purchased products added in kart successfully....");
						continue;
					}
					else
					{
						System.out.print("Are you sure to stop to add in kart...If sure please Enter a 'yes': ");
						if(main.sc.nextLine().equals("yes")) {
							break;
						}
						else {
							rep_pro.add(pro_id);
							totalproductprice+=main.products.get(pro_id).price*quantity;
							System.out.println("Continue to add in kart");
							System.out.println("now purchased products added in kart successfully....");
							continue;
						}
					}
				}
				else
				{
					System.out.println("There is no much amount of quantity in this product.....");
				}
				
			}
			else if(rep_pro.contains(n))
			{
				System.out.println("Product already added in cart you can change the quantity");
				System.out.print("Are you change the quantity give '1' to continue: ");
				String one=main.sc.nextLine();
				if(one.equals("1")) {
					int repeated=rep_pro.get(rep_pro.indexOf(n));
					System.out.println(repeated);
					System.out.println("Enter a quantity to change: ");
					int quantity=Integer.parseInt(main.sc.nextLine());
					if(quantity<=main.products.get(repeated).quantity) {
						String str[]=cart.get(rep_pro.indexOf(n)).split("-");
						totalproductprice-=main.products.get(repeated).price*Integer.parseInt(str[2]);
						cart.set(rep_pro.indexOf(n), str[0]+"-"+str[1]+"-"+quantity+"-"+str[3]);
//						System.out.println(cart);
						totalproductprice+=main.products.get(repeated).price*quantity;
						System.out.println("Your product quantity changed...");
						selectproduct(email);
				}
					else
					{
						System.out.println("There is no much amount of quantity.....");
					}
			}
				continue;
			}
			else
			{
				purchasemodify(email);
			}
			
		}
		purchasemodify(email);
	}
	public static void purchasemodify(String email) {
		System.out.println("\n 1. Add more items into kart\n 2.Remove the product from kart \n 3.Proceed to payment\n Enter your choice: ");
		int n1=Integer.parseInt(main.sc.nextLine());
		switch(n1) {
		case 1:
			selectproduct(email);
			break;
		case 2:
			removefromcart(email);
			break;
		case 3:
			payment(email);
			break;
		}	
		}
	public static void payment(String email) {
		System.out.println("Enter '1' to proceed the payment: ");
		if(main.sc.nextLine().equals("1")) {
			System.out.println("Enter a today Date with format(DD/MM/YYYY): ");
			String date=main.sc.nextLine();
			for(int i=0;i<cart.size();i++)
			{
				String str[]=cart.get(i).split("-");
				ArrayList<String> list=new ArrayList<>();
				list.add(str[0]);
				list.add(str[1]);
				list.add(str[2]);
				list.add(str[3]);
				if(main.customers.get(email).credit>(Integer.parseInt(str[3])*Integer.parseInt(str[2]))) {
					main.customers.get(email).credit=main.customers.get(email).credit-(Integer.parseInt(str[3])*Integer.parseInt(str[2]));
					main.products.get(Integer.parseInt(str[0])).quantity=main.products.get(Integer.parseInt(str[0])).quantity-Integer.parseInt(str[2]);
				}
				else
				{
					System.out.println("You should not have sufficient amount..");
					break;
				}
			}
			ArrayList<String> dup=new ArrayList<>(cart);
			main.cart1.put(main.cart1.size()+1,new Cart(main.cart1.size()+1,email,dup,date));
			System.out.println("Products purchased successfully...");
			if(totalproductprice>=5000)
			{
				main.customers.get(email).credit+=100;
			}
			int loy_score=totalproductprice%100;
			main.customers.get(email).loyaltyscore+=loy_score;
			cart.clear();
			rep_pro.clear();
			totalproductprice=0;
			customerhome(email);
		}
		else
		{
			purchasemodify(email);
		}
	}
	public static void removefromcart(String email) {
		System.out.println("---------List of selected Items in kart----------------");
		
		System.out.println("---------------------------------------KART LIST---------------------------------------");
		System.out.printf("| |%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
		System.out.println("---------------------------------------------------------------------------------------");
		int j=1;
			for(String i:cart) {
				String str[]=i.split("-");
				System.out.printf("| %4s %12s %17s %18s |",(j++),str[1],str[2],str[3]);
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Select your choice to remove the product from kart or '0' to EXIT: ");
		int rem=Integer.parseInt(main.sc.nextLine()); 
		if(rem==0)
		{
			purchasemodify(email);
		}
		if(rem-1<=cart.size())
		{
			String str[]=cart.get(rem-1).split("-");
			cart.remove(cart.get(rem-1));
			totalproductprice-=main.products.get(Integer.parseInt(str[0])).price*Integer.parseInt(str[2]);
			System.out.println("Product removed successfully");
			System.out.println("Continue to delete item in kart Enter 'yes' :");
			if(main.sc.nextLine().equals("yes"))
			{
				removefromcart(email);
			}
			else
			{
				purchasemodify(email);
			}
		}
		else
		{
			System.out.println("Enter correct option");
			removefromcart(email);
		}
	}
	public static void Increasecredit() {
		
	}
}

 class Products {
	int id;
	String name;
	int price;
	int quantity;
	String addedby;
	public Products(int id,String name, int price,int quantity,String addedby) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
		this.addedby=addedby;
	}
	public static void additems(String email) {
		
		System.out.print(" \nEnter '0' to EXIT: \n Enter the product name: ");
		String name=main.sc.nextLine();
		if(name.contentEquals("0"))
		{
			Admin.adminhome(email);
		}
		System.out.print(" Enter the product price: ");
		int price=Integer.parseInt(main.sc.nextLine());
		if(price==0)
		{
			Admin.adminhome(email);
		}
		System.out.print(" Enter the product quantity: ");
		int quantity=Integer.parseInt(main.sc.nextLine());
		if(quantity==0)
		{
			Admin.adminhome(email);
		}
		main.products.put(main.products.size()+1,new Products(main.products.size()+1,name,price,quantity,main.admin.get(email).adminname) );
		System.out.println("Product added successfully...");
		Admin.adminhome(email);
	}
	public static void modifyitems(String email) {
		System.out.println("\n Enter '0' to EXIT: \n 1.Item details and quantity\n 2.Delete an item\n Enter your choice: ");
		int n=Integer.parseInt(main.sc.nextLine());
		switch(n)
		{
		case 1:
			Itemdetails(email);
			break;
		case 2:
			deleteitem(email);
			break;
		}
		
	}

	private static void Itemdetails(String email) {
		System.out.println("\n--------------------ITEMS LISTS--------------------\n");
		List<Map.Entry<Integer, Products>> productList = new ArrayList<>(main.products.entrySet());

        Collections.sort(productList, new Comparator<Map.Entry<Integer, Products>>() {
            @Override
            public int compare(Map.Entry<Integer, Products> o1, Map.Entry<Integer, Products> o2) {
                return Integer.compare(o1.getValue().id, o2.getValue().id);
            }
        });
        ArrayList<Integer> list=new ArrayList<>(main.products.keySet());
        System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
		System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
		System.out.println("---------------------------------------------------------------------------------------");
		int j=1;
	for(int i:main.products.keySet()) {
		System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
		System.out.println();
	}
	System.out.println("---------------------------------------------------------------------------------------");
        String p_name="";
        int p_price=0;
        int p_quantity=0;
	      System.out.print(" Enter '0' to EXIT: \n Select your choice to edit: ");
	      int n=Integer.parseInt(main.sc.nextLine());
	      System.out.print(" \nEnter '0' to EXIT: \n The product name is : "+main.products.get(n).name+"\n change name to give '1' else give any number to continue: ");
	      if(main.sc.nextLine().equals("1")) {
	    	  System.out.println("Enter a product name: ");
	    	   p_name=main.sc.nextLine();
	      }
	      System.out.print(" \nEnter '0' to EXIT: \n The product price is : "+main.products.get(n).price+"\n change name to give '1' else give any number to continue: ");
	      if(main.sc.nextLine().equals("1")) {
	    	  System.out.println("Enter a product price: ");
	    	  p_price=Integer.parseInt(main.sc.nextLine());
	      }
	      System.out.print(" \nEnter '0' to EXIT: \n The product quantity is : "+main.products.get(n).quantity+"\n change name to give '1' else give any number to continue: ");
	      if(main.sc.nextLine().equals("1")) {
	    	  System.out.println("Enter a product quantity: ");
	    	  p_quantity=Integer.parseInt(main.sc.nextLine());
	      }
			main.products.put(n,new Products(n,p_name,p_price,p_quantity,main.admin.get(email).adminname) );
			System.out.println("product edited successfully...");
			modifyitems(email);
	}
	public static void deleteitem(String email) {
		System.out.println("\n--------------------ITEMS LISTS--------------------\n");
		List<Map.Entry<Integer, Products>> productList = new ArrayList<>(main.products.entrySet());

        Collections.sort(productList, new Comparator<Map.Entry<Integer, Products>>() {
            @Override
            public int compare(Map.Entry<Integer, Products> o1, Map.Entry<Integer, Products> o2) {
                return Integer.compare(o1.getValue().id, o2.getValue().id);
            }
        });
        ArrayList<Integer> list=new ArrayList<>(main.products.keySet());
        System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
		System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
		System.out.println("---------------------------------------------------------------------------------------");
		int j=1;
	for(int i:main.products.keySet()) {
		System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
		System.out.println();
	}
	System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\n Enter '0' to EXIT: \n Select a choice to delete an item: ");
        int n=Integer.parseInt(main.sc.nextLine());
        if(n==0)
        {
        	modifyitems(email);
        }
        else
        {
        	main.products.remove(n);
        	System.out.println("Product removed successfully...");
        }
        modifyitems(email);
	}
	public static void productlist(String email) {
		System.out.println("\n-------------------ITEMS LIST--------------------\nEnter '0' to EXIT: \n 1.sorted by name \n 2.sorted by price\n Enter your choice: ");
		int n=Integer.parseInt(main.sc.nextLine());
		switch(n) {
		case 0:
			Admin.adminhome(email);
		case 1:
			sorteditem(email);
			break;
		case 2:
			sortedprice(email);
			break;
			
		}
	}
	public static void sortedprice(String email) {
		System.out.println("\n--------------------ITEMS LISTS--------------------\n");
		List<Map.Entry<Integer, Products>> productList = new ArrayList<>(main.products.entrySet());

        Collections.sort(productList, new Comparator<Map.Entry<Integer, Products>>() {
            @Override
            public int compare(Map.Entry<Integer, Products> o1, Map.Entry<Integer, Products> o2) {
                return Integer.compare(o1.getValue().price, o2.getValue().price);
            }
        });

        System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
		System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
		System.out.println("---------------------------------------------------------------------------------------");
		int j=1;
	for(int i:main.products.keySet()) {
		System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
		System.out.println();
	}
	System.out.println("---------------------------------------------------------------------------------------");
        if(main.admin.containsKey(email))
        {
        	productlist(email);
        }
	}
	public static void sorteditem(String email) {
		System.out.println("\n--------------------ITEMS LISTS--------------------\n");
		 List<Map.Entry<Integer, Products>> productList = new ArrayList<>(main.products.entrySet());

	        Collections.sort(productList, new Comparator<Map.Entry<Integer, Products>>() {
	            @Override
	            public int compare(Map.Entry<Integer, Products> o1, Map.Entry<Integer, Products> o2) {
	                return o1.getValue().name.compareTo(o2.getValue().name);
	            }
	        });
	        System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
			System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
			System.out.println("---------------------------------------------------------------------------------------");
			int j=1;
		for(int i:main.products.keySet()) {
			System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------------");
	        productlist(email);
	}
	public static void search(String email) {
		System.out.println("\n------------------SEARCH A PRODUCT-----------------");
		
		System.out.println("Enter a product name: ");
		String searchName=main.sc.nextLine();
		 ArrayList<Integer> list=new ArrayList<>(main.products.keySet());
		 boolean f=true;
	        for (int i: list) {
	        	if(main.products.get(i).name.equals(searchName))
	        	{
	        		 System.out.println("---------------------------------------SEARCHED PRODUCT---------------------------------------");
	        			System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","PRICE");
	        			System.out.println("---------------------------------------------------------------------------------------");
	        			int j=1;
	        			System.out.printf("| %4s %12s %17s %18s |",(j++),main.products.get(i).name,main.products.get(i).quantity,main.products.get(i).price);
	        		System.out.println("---------------------------------------------------------------------------------------");
	            f=false;
	        	}
	        }
	        if(f) {
	        	System.out.println("Your searched name doesn't exists");
	        	search(email);
	        }
		Admin.adminhome(email);	
	}
}
class Cart {
	String customerid;
	int cartid;
	ArrayList<String> cart_items=new ArrayList<>();
	String purchased_date;
	public Cart(int cartid,String customerid,ArrayList<String> cart_items,String purchased_date) {
		this.customerid=customerid;
		this.cartid=cartid;
		this.cart_items=cart_items;
		this.purchased_date=purchased_date;
	}
	
	public static void purchasehistory(String email) {
		System.out.println("------------------------------ "+main.customers.get(email).name+" PURCHASE HISTORY ------------------------------");
		for(int a:main.cart1.keySet()) {
			if(main.cart1.get(a).customerid.equals(email)) {
				System.out.println("CART ID: "+a+"                                                                    Date: "+main.cart1.get(a).purchased_date);
				 System.out.println("CUSTOMER NAME: "+main.customers.get(email).name);
				System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
					System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","AMOUNT");
					System.out.println();
					System.out.println("---------------------------------------------------------------------------------------");
				
				//System.out.println((j++)+". Bill no: "+a+" - Customer name: "+main.customers.get(email).name+" - Date: "+main.cart.get(a).purchased_date);
				int tot_amt=0;
				int j=1;
				for(int i=0;i<main.cart1.get(a).cart_items.size();i++)
				{
					String str[]=main.cart1.get(a).cart_items.get(i).split("-");
					tot_amt+=Integer.parseInt(str[3]);
					System.out.printf("| %4s %12s %17s %18s |",(j++),str[1],str[2],str[3]+".00");
					System.out.println();
				}
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("Total amount:                                     "+tot_amt+".00");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

			}
		}
		Customers.customerhome(email);
	}
	public static void purchasedproducts(String email) {
		System.out.println("---------------------------ALL PURCHASED PRODUCTS HISTORY DETALS----------------------------------");
		
		
		for(int a:main.cart1.keySet()) {
			System.out.println("CART ID: "+a+"                                                                    Date: "+main.cart1.get(a).purchased_date);
			 System.out.println("CUSTOMER NAME: "+main.customers.get(main.cart1.get(a).customerid).name);
			System.out.println("---------------------------------------PRODUCT LIST------------------------------------");
				System.out.printf("|%7s %15s %15s %15s |","S.NO","PRODUCT NAME","QUANTITY","AMOUNT");
				System.out.println();
				System.out.println("---------------------------------------------------------------------------------------");
			
			//System.out.println((j++)+". Bill no: "+a+" - Customer name: "+main.customers.get(email).name+" - Date: "+main.cart.get(a).purchased_date);
			int tot_amt=0;
			int j=1;
			for(int i=0;i<main.cart1.get(a).cart_items.size();i++)
			{
				String str[]=main.cart1.get(a).cart_items.get(i).split("-");
				tot_amt+=Integer.parseInt(str[3]);
				System.out.printf("| %4s %12s %17s %18s |",(j++),str[1],str[2],str[3]+".00");
				System.out.println();
			}
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("Total amount:                                     "+tot_amt+".00");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		}
		Admin.adminhome(email);
	}
}
