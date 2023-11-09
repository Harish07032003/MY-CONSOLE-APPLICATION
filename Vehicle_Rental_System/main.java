package Vehicle_Rental_System;

import java.util.*;
public class main {
	static Scanner sc=new Scanner (System.in);
	static HashMap<String,Borrower> borrower=new HashMap<>();
	static HashMap<String,Admin> admin=new HashMap<>();
	static HashMap<String,Vehicle> vehicle=new HashMap<>();
	static HashMap<Integer,Rental> rental=new HashMap<>();
	static ArrayList<Borrower> borrowervehicles=new ArrayList<>();
	public static void main(String[] args) {
		borrower.put("hari@gmail.com", new Borrower("hari@gmail.com","Harish","123",30000));
		borrower.put("jones@gmail.com", new Borrower("jones@gmail.com","Jones","jones123",30000));
		borrower.put("vishnu@gmail.com", new Borrower("vishnu@gmail.com","Vishnu","vishnu123",30000));
		vehicle.put("TN39 CR101", new Vehicle("car","TN39 CR101","Maruthi Suzuki",10,8000,true));
		vehicle.put("TN39 DS102", new Vehicle("bike","TN39 DS102","Honda DIO",15,2000,true));
		vehicle.put("TN42 YF101", new Vehicle("bike","TN42 YF101","Yamaha FZ",4,3000,false));
		admin.put("harish@gmail.com", new Admin("harish@gmail.com","Harish","hari123"));
		
		//rental.put(1, new Rental(1,"Harish",new ArrayList<String>(Arrays.asList("TN39 CR101")),"12/3/2023",false,""));
		home();
	}
	public static void home() {
		System.out.print("-----------------VEHICLE RENTAL SYSTEM----------------------\n 1.SIGN UP\n 2.SIGN IN\n Enter your choice: ");
		try {
			while(true) {
			int n=Integer.parseInt(sc.nextLine());
			if(n==1) {
				signup();
			}
			else if(n==2) {
				signin();
			}
			else {
				System.out.println("Enter correct option");
				home();
			}
			}
			
		}catch(Exception e) {
			System.out.println("Enter correct option");
			home();
		}
		
	}
	public static void signin() {
		System.out.println("--------------SIGN IN------------\n Enter '0' to EXIT: ");
		System.out.print("Enter your email ID: ");
		String email=sc.nextLine();
		if(email.equals("0")) {
			home();
		}
		if(borrower.containsKey(email)) {
			System.out.print("Enter your password: ");
			String password=sc.nextLine();
			if(password.equals("0")) {
				home();
			}
			if(borrower.get(email).password.equals(password)) {
				System.out.println("Sign in SUCCESSFULLY");
				Borrower.borrowerhome(email);
			}
			else {
				System.out.println("your password is wrong please try again..");
				signin();
			}
		}
		if(admin.containsKey(email)) {
			System.out.print("Enter your password: ");
			String password=sc.nextLine();
			if(password.equals("0")) {
				home();
			}
			if(admin.get(email).password.equals(password)) {
				System.out.println("Sign in SUCCESSFULLY");
				Admin.adminhome(email);
			}
			else {
				System.out.println("your password is wrong please try again..");
				signin();
			}
		}
		else
		{
			System.out.println("Your email id is not signed up please sign up and try");
		}
	}
	public static void signup() {
		System.out.println("--------------SIGN UP------------\n Enter '0' to EXIT: ");
		System.out.print("Enter your email ID: ");
		String email=sc.nextLine();
		if(email.equals("0")) {
			home();
		}
		if(email.substring(email.length()-10,email.length()).equals("@gmail.com")) {
			System.out.print("Enter your name: ");
			String name=sc.nextLine();
			if(name.equals("0")) {
				home();
			}
			System.out.print("Enter your password: ");
			String password=sc.nextLine();
			if(password.equals("0")) {
				home();
			}
			borrower.put(email, new Borrower(email,name,password,30000));
			System.out.println("Sign up SUCCESSFULLY");
			Borrower.borrowerhome(email);
		}
		else
		{
			System.out.println("Enter a email in correct format '@gmail.com'");
			signup();
		}
	}

}
class Borrower {
	String emailid;
	String borrowername;
	String password;
	int depositamount;
	String rentbuydate;
	ArrayList<String> rentedvehicles=new ArrayList<>();
	int rentalid;
	public Borrower(String emailid,String borrowername,String password,int depositamount){
		this.emailid=emailid;
		this.borrowername=borrowername;
		this.password=password;
		this.depositamount=depositamount;
	}
	public Borrower(int rentalid,ArrayList<String> rentedvehicles,String rentbuydate)
	{
		this.rentalid=rentalid;
		this.rentedvehicles=rentedvehicles;
		this.rentbuydate=rentbuydate;
	}
	static ArrayList<String> checkoutcart=new ArrayList<>();
	public static void borrowerhome(String email) {
		System.out.println("---------------------WELCOME TO VEHICLE RENTAL SYSTEM :"+main.borrower.get(email).borrowername+" --------------------\n Enter '0' to EXIT \n 1. VIEW OF ALL VEHICLES\n 2.ADD A VEHICLE\n 3.REMOVE A VEHICLE \n 4.RETURN THE VEHICLE \n 5.DEPOSIT AMOUNT \n 6.MAKE PAYMENT \n 7.SIGN OUT");
		System.out.println("Enter '0' to EXIT or Enter your choice: ");
		int n=Integer.parseInt(main.sc.nextLine());
		switch(n) {
		case 0:
			System.out.println("sure you want to SIGN OUT? (yes/no): ");
			if((main.sc.nextLine()).equalsIgnoreCase("yes")) {
				main.home();
			}
			break;
		case 1:
			selectvehicle(email,Vehicle.viewvehicle(email));
			break;
		case 2:
			selectvehicle(email,Vehicle.viewvehicle(email));
			break;
		case 3:
			removevehicle(email);
			break;
		case 4:
			returnvehicle(email);
		case 5:
			System.out.println("Your Deposit balance is: "+main.borrower.get(email).depositamount);
			borrowerhome(email);
			break;
		case 6:
			payment(email);
			break;
		case 7:
			System.out.println("sure you want to SIGN OUT? (yes/no): ");
			if((main.sc.nextLine()).equalsIgnoreCase("yes")) {
				main.home();
			}
			break;
		}
		
	}
	public static void payment(String email) {
        if(checkoutcart.size()>0){
			System.out.println("Enter '0 to EXIT or Enter today date: ");
			String date=main.sc.nextLine();
			if(date.equals("0")) {
				borrowerhome(email);
			}
			ArrayList<String> dup=new ArrayList<>(checkoutcart);
			main.rental.put(main.rental.size()+1,new Rental(main.rental.size()+1,email,dup,date,false,""));
			main.borrowervehicles.add(new Borrower(main.rental.size()+1,dup,date));
			checkoutcart.clear();
			System.out.println("Vehicles Rented successfully...");
        }
        else{
            System.out.println("No vehicles added in cart please select and make payment");

        }
			borrowerhome(email);
		
	}
	public static void returnvehicle(String email) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+main.borrower.get(email).borrowername+" RENTED VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		ArrayList<String> bor_rent_vehicles=new ArrayList<>(main.borrowervehicles.get(0).rentedvehicles);
		System.out.println("Rental date: "+main.borrowervehicles.get(main.borrowervehicles.size()-1).rentbuydate);
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		int j=1;
		for(String st: bor_rent_vehicles) {
			System.out.printf("|%3s %20s %25s %13s %20s %25s |",(j++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter today's date: ");
		String returndate=main.sc.nextLine();
		for(int i=0; i<bor_rent_vehicles.size();i++) {
		int n=i+1;
		if(n==0) {
			borrowerhome(email);
		}
		else {
			
			if(returndate.equals(main.borrowervehicles.get(main.borrowervehicles.size()-1).rentbuydate)) {
				main.borrower.get(email).depositamount+=main.vehicle.get(bor_rent_vehicles.get(n-1)).rental_price;
				main.rental.get(main.borrowervehicles.size()).returnstatus=true;
				main.rental.get(main.borrowervehicles.size()).returndate=returndate;
				System.out.println("Enter the km of "+main.vehicle.get(bor_rent_vehicles.get(i)).vehiclename+": ");
				int km=Integer.parseInt(main.sc.nextLine());
				if(km>500)
				{
					int tot_rent=(main.vehicle.get(bor_rent_vehicles.get(i)).rental_price/100)*15;
					main.borrower.get(email).depositamount-=tot_rent;
					System.out.println("vehicle km beats the limit of 500km that has 15% reduced in your deposit amount...");
					
					if(main.vehicle.get(bor_rent_vehicles.get(i)).type.equals("car") && km>3000) {
						main.vehicle.get(bor_rent_vehicles.get(i)).servicestatus=false;
					}
					else if(main.vehicle.get(bor_rent_vehicles.get(i)).type.equals("bike") && km>1500){
						main.vehicle.get(bor_rent_vehicles.get(i)).servicestatus=false;
					}
				}
				else {
					System.out.println("Vehicle returned successfully...");
				}
			}
			else {
				System.out.println("Your date was extended that fine amount is debited in your account balance...");
				int fineamt=main.borrower.get(email).depositamount-main.vehicle.get(bor_rent_vehicles.get(n-1)).rental_price;
				main.rental.get(main.borrowervehicles.get(main.borrowervehicles.size()-1).rentalid).returnstatus=true;
				main.rental.get(main.borrowervehicles.get(main.borrowervehicles.size()-1).rentalid).returndate=returndate;
				borrowerhome(email);
			}
			
		}
		}
		System.out.println("vehicle returned successfully");
		borrowerhome(email);
	}
	public static void removevehicle(String email) {
		System.out.println();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CART VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		int i=1;
		for(String st: checkoutcart) {
			System.out.printf("|%3s %20s %25s %13s %20s %25s |",(i++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter '0' to EXIT or Enter an vehicle list S.NO :");
		int n=Integer.parseInt(main.sc.nextLine());
		if(n==0) {
			borrowerhome(email);
		}
		else {
			main.borrower.get(email).depositamount+=main.vehicle.get(checkoutcart.get(n-1)).rental_price;
			checkoutcart.remove(n-1);
			main.vehicle.get(checkoutcart.get(n-1)).available++;
			System.out.println("vehicle removed successfully");
			borrowerhome(email);
		}
	}
	public static void selectvehicle(String email, ArrayList<String> arrayList) {
		System.out.println("select vehicle S.NO :  ");
		int n1=Integer.parseInt(main.sc.nextLine());
		if(n1==0)
		{
			borrowerhome(email);
		}
		String vehicleid=arrayList.get(n1-1);
		System.out.println("Are you select a vehicle to add on cart? (yes/no): ");
		String choice=main.sc.nextLine();
		if(choice.equalsIgnoreCase("yes")) {
			if(checkoutcart.size()==0) {
			if(main.borrower.get(email).depositamount>=main.vehicle.get(vehicleid).rental_price) {
				checkoutcart.add(vehicleid);
				main.borrower.get(email).depositamount-=main.vehicle.get(vehicleid).rental_price;
				main.vehicle.get(vehicleid).available--;
				System.out.println("vehicle added to the cart successfully...continue to add vehicle?(yes/no): ");
				if(main.sc.nextLine().equalsIgnoreCase("yes")) {
					selectvehicle(email,arrayList);
				}
				else {
					borrowerhome(email);
				}
			}
			else {
				borrowerhome(email);
			}
			}
			else if(!main.vehicle.get(checkoutcart.get(0)).type.equals(main.vehicle.get(vehicleid).type)){
				if(main.borrower.get(email).depositamount>=main.vehicle.get(vehicleid).rental_price) {
					checkoutcart.add(vehicleid);
					main.borrower.get(email).depositamount-=main.vehicle.get(vehicleid).rental_price;
					System.out.println("vehicle added to the cart successfully...You added car and bike both in your cart continue to rent?(yes/no): ");
					if(main.sc.nextLine().equalsIgnoreCase("yes")) {
						System.out.println("Enter today date: ");
						String date=main.sc.nextLine();
						ArrayList<String> dup=new ArrayList<>(checkoutcart);
						main.rental.put(main.rental.size()+1,new Rental(main.rental.size()+1,email,dup,date,false,""));
						
						main.borrowervehicles.add(new Borrower(main.rental.size()+1,dup,date));
						checkoutcart.clear();
						System.out.println("Vehicles Rented successfully...");
						borrowerhome(email);
					}
					else {
						borrowerhome(email);
					}
				}
				else {
					borrowerhome(email);
				}
			}
			else {
				System.out.println("please select the different vehicle type");
				borrowerhome(email);
			}
		}
		else {
			borrowerhome(email);
		}
		
	}
	public static void borrdepositamount(String email) {
		System.out.print("Enter '0' to EXIT\n Enter the borrower emailid:");
		String emailid=main.sc.nextLine();
		if(main.borrower.containsKey(emailid)) {
		System.out.print(" Enter the deposit amount: ");
		int amt=Integer.parseInt(main.sc.nextLine());
		main.borrower.get(emailid).depositamount=main.borrower.get(emailid).depositamount+amt;
		}
		else {
			System.out.println("This borrower id not found please enter correctly....");
			Admin.adminhome(email);
		}
	}
}
class Admin {
	String emailid;
	String adminname;
	String password;
	public Admin(String emailid,String adminname,String password){
		this.emailid=emailid;
		this.adminname=adminname;
		this.password=password;
	}
	public static void adminhome(String email) {
		System.out.println("\n---------------------WELCOME TO VEHICLE RENTAL SYSTEM :"+main.admin.get(email).adminname+" --------------------");
		System.out.println("Enter '0' to EXIT: \n 1.ADD A VEHICLE\n 2.MODIFY THE VEHICLE\n 3.VIEW LIST OF VEHICLES\n 4.SEARCH A VEHICLE \n 5.SECURITY DEPOSIT AMOUNT FOR BORROWER \n 6.UNSERVICED VEHICLES \n 7.RENTAL DETAILS \n 8.SIGN OUT \n ENTER YOUR CHOICE: ");
		int n=Integer.parseInt(main.sc.nextLine());
		switch(n) {
		case 0:
			System.out.print("sure you want to SIGN OUT? (yes/no): ");
			if((main.sc.nextLine()).equalsIgnoreCase("yes")) {
				main.home();
			}
			break;
		case 1:
			addvehicle(email);
			break;
		case 2:
			Vehicle.modifyvehicle(email);
			break;
		case 3:
			Vehicle.viewvehicle(email);
			adminhome(email);
			break;
		case 4:
			System.out.print("Enter the vehicle NAME or vehicle number:");
			String name=main.sc.nextLine();
			boolean f=Vehicle.searchvehicle(name);
			if(!f) {
				System.out.println("your searched vehicle not found plase try correctly");
				adminhome(email);
			}
			else {
				adminhome(email);
			}
			break;
		case 5:
			Borrower.borrdepositamount(email);
			break;
		case 6:
			Vehicle.unserviced(email);
			break;
		case 7:
			Rental.rentaldetails(email);
			break;
		case 8:
			System.out.println("sure you want to SIGN OUT? (yes/no): ");
			if((main.sc.nextLine()).equalsIgnoreCase("yes")) {
				main.home();
			}
			break;
		}
		
		
	}
	public static void addvehicle(String email) {
		System.out.print(" Enter '0' to EXIT\n Enter a Type (car/bike): ");
		String type=main.sc.nextLine();
		if(type.equals("0")) {
			adminhome(email);
		}
		System.out.print(" Enter a vehicle No: ");
		String numberplate=main.sc.nextLine();
		if(numberplate.equals("0")) {
			adminhome(email);
		}
		System.out.print(" Enter a vehicle name: ");
		String vehiclename=main.sc.nextLine();
		if(vehiclename.equals("0")) {
			adminhome(email);
		}
		System.out.print(" Enter a availability: ");
		int available=Integer.parseInt(main.sc.nextLine());
		if(available==0) {
			adminhome(email);
		}
		System.out.print(" Enter a vehicle rent price: ");
		int rentprice=Integer.parseInt(main.sc.nextLine());
		if(rentprice==0) {
			adminhome(email);
		}
		main.vehicle.put(numberplate, new Vehicle(type,numberplate,vehiclename,available,rentprice,true));
		System.out.println("new vehicle added successfully");
		adminhome(email);
	}
	public static void addadmin(String email2) {
		System.out.println("-------------- ADMIN SIGN UP------------\n Enter '0' to EXIT: ");
		System.out.print("Enter your email ID: ");
		String email=main.sc.nextLine();
		if(email.equals("0")) {
			adminhome(email2);
		}
		if(email.substring(email.length()-11,email.length()).equals("@gmail.com")) {
			System.out.print("Enter your name: ");
			String name=main.sc.nextLine();
			if(name.equals("0")) {
				adminhome(email2);
			}
			System.out.print("Enter your password: ");
			String password=main.sc.nextLine();
			if(password.equals("0")) {
				adminhome(email2);
			}
			main.admin.put(email, new Admin(email,name,password));
			System.out.println("Sign up SUCCESSFULLY");
			Admin.adminhome(email2);
		}
	}
}
 class Vehicle {
	String type;
	String vehiclenum;
	String vehiclename;
	int available;
	int rental_price;
	boolean servicestatus;
	public Vehicle(String type,String vehiclenum,String vehiclename, int available,int rental_price,boolean servicestatus) {
		this.type=type;
		this.vehiclenum=vehiclenum;
		this.vehiclename=vehiclename;
		this.available=available;
		this.rental_price=rental_price;
		this.servicestatus=servicestatus;
	}
	public static void modifyvehicle(String email) {
		System.out.println("ENTER '0' to EXIT: \n CHOOSE THE VEHICLE TO MODIFY: ");
		viewvehicle(email);
		System.out.println("choose the vehicle list S.NO : ");
		int n=Integer.parseInt(main.sc.nextLine());
		if(n==0) {
			Admin.adminhome(email);
		}
		ArrayList<String> veh=new ArrayList<>(main.vehicle.keySet());
		boolean f1=searchvehicle(veh.get(n-1));
		System.out.print("Enter '0' to EXIT: \n 1.EDIT VEHICLE No. \n 2.EDIT QUANTITY\n 3.EDIT PRICE\n Enter your choice: ");
		int n1=Integer.parseInt(main.sc.nextLine());
		switch(n1) {
		case 0:
			Admin.adminhome(email);
			break;
		case 1:
			System.out.println("Enter a vehicle number:");
			String st=main.sc.nextLine();
			if(st.equals("0")) {
				Admin.adminhome(email);
			}
			main.vehicle.get(veh.get(n-1)).vehiclenum=st;
			System.out.println("VEHICLE NUMBER changed successfully");
			modifyvehicle(email);
			break;
		case 2:
			System.out.println("Enter a QUANTITY:");
			st=main.sc.nextLine();
			if(st.equals("0")) {
				Admin.adminhome(email);
			}
			main.vehicle.get(veh.get(n-1)).available=Integer.parseInt(st);
			System.out.println("QUANTITY changed successfully");
			modifyvehicle(email);
			break;
		case 3:
			System.out.println("Enter a PRICE amount:");
			st=main.sc.nextLine();
			if(st.equals("0")) {
				Admin.adminhome(email);
			}
			main.vehicle.get(veh.get(n-1)).rental_price=Integer.parseInt(st);
			System.out.println("PRICE AMOUNT changed successfully");
			modifyvehicle(email);
			break;
		}
	}
	public static void unserviced(String email) {
		ArrayList<String> dupli=new ArrayList<>();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>UNSERVICED  VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		int i=1;
		for(String st: main.vehicle.keySet()) {
			if(main.vehicle.get(st).servicestatus==false) {
				dupli.add(st);
			System.out.printf("|%3s %20s %25s %13s %20s %25s |",(i++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
			System.out.println();
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter '0' to EXIT or Enter the vehicle S.NO: ");
		int n=Integer.parseInt(main.sc.nextLine());
		if(n==0) {
			Admin.adminhome(email);
		}
		System.out.println("Now serviced or Not?()yes/no");
		if(main.sc.nextLine().equalsIgnoreCase("yes")){
		main.vehicle.get(dupli.get(n-1)).servicestatus=true;
		unserviced(email);
		}
		else {
			unserviced(email);
		}
	}
	public static ArrayList<String> viewvehicle(String email) {
		ArrayList<String> id=new ArrayList<>();
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	System.out.println("-------------------------------------------------------------------------------------------------------------------------");
	System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
	System.out.println();
	System.out.println("-------------------------------------------------------------------------------------------------------------------------");
	int i=1;
	for(String st: main.vehicle.keySet()) {
		if(main.vehicle.get(st).servicestatus==true) {
			id.add(st);
		System.out.printf("|%3s %20s %25s %13s %20s %25s |",(i++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
		System.out.println();
		}
	}
	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	return id;
	}
	public static boolean searchvehicle(String name) {
		if(main.vehicle.containsKey(name)) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		int i=1;
		String st=name;
			System.out.printf("|%3s %20s %25s %13s %20s %25s |",(i++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
			System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		return true;
		}
		return false;
	}
}
class Rental {
	int rentalid;
	String Borrowername;
	ArrayList<String> rentedvehicle=new ArrayList<>();
	String renteddate;
	boolean returnstatus;
	String returndate;
	public Rental(int rentalid,String Borrowername,ArrayList<String> rentedvehicle,String renteddate,boolean returnstatus,String returndate) {
		this.rentalid=rentalid;
		this.Borrowername=Borrowername;
		this.rentedvehicle=rentedvehicle;
		this.renteddate=renteddate;
		this.returndate=returndate;
		this.returnstatus=returnstatus;
	}
	public static void rentaldetails(String email) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>RENTAL VEHICLE LIST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		for(int ren:main.rental.keySet()) {
			System.out.println("RENTAL ID: "+ren+"                                                                                  RENTAL DATE: "+main.rental.get(ren).renteddate);
			System.out.println("Borrower name: "+main.rental.get(ren).Borrowername+"                                                        RETURN DATE:"+main.rental.get(ren).returndate);
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%5s %20s %21s %15s %28s %20s   |","S.NO","VEHICLE NO:","VEHICLE NAME","TYPE","AVAILABLE QUANTITY","RENTAL PRICE");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------------------------------------");
			int i=1;
			ArrayList<String> vehicles=new ArrayList<>(main.rental.get(ren).rentedvehicle);
			for(String st: vehicles) {
				if(main.vehicle.get(st).servicestatus==true) {
					System.out.printf("|%3s %20s %25s %13s %20s %25s |",(i++),main.vehicle.get(st).vehiclenum,main.vehicle.get(st).vehiclename,main.vehicle.get(st).type,main.vehicle.get(st).available,main.vehicle.get(st).rental_price+".00");
					System.out.println();
				}
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");	
			if(main.rental.get(ren).returnstatus==true)
				System.out.println("vehicle service status: NOT NEED");
			else
				System.out.println("vehicle service status: NEED");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");	
		}
		Admin.adminhome(email);
	}
}

