package RailwayTicket;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class main {
    static Scanner sc=new Scanner (System.in);
	
	public static void main(String[] args) {
		Station.station.add(new Station("Coimbatore"));
		Station.station.add(new Station("Tirupur"));
		Station.station.add(new Station("Erode"));
		Station.station.add(new Station("Salem"));
		Station.station.add(new Station("MGR Chennai Central"));
		Station.station.add(new Station("Bangalore"));
		Station.station.add(new Station("Mangalore"));
		Station.station.add(new Station("Ooty"));
		Station.station.add(new Station("karur"));
		Station.station.add(new Station("Tirchy"));
		Station.station.add(new Station("Palakkad"));
		Station.station.add(new Station("Pondichery"));
		Station.station.add(new Station("Mysore"));
		Admin.admin.put("harish@gmail.com", new Admin("harish@gmail.com","Hari","hari123"));
		User.user.put("hari", new User("hari","Harish","123",new ArrayList<Integer>(),new ArrayList<Integer>()));
		Train.train.put(1857, new Train(1857,"jan shatabdi",Station.station.get(0),Station.station.get(5),new ArrayList<Station>(Arrays.asList(Station.station.get(0),Station.station.get(1),Station.station.get(2),Station.station.get(3),Station.station.get(4),Station.station.get(5))),5,new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0))));
		Train.train.put(1224, new Train(1224,"Cheran Express",Station.station.get(6),Station.station.get(9),new ArrayList<Station>(Arrays.asList(Station.station.get(6),Station.station.get(7),Station.station.get(8),Station.station.get(9))),10,new ArrayList<Integer>(Arrays.asList(0,0,0,0))));
		Train.train.put(1244, new Train(1244,"Mangalore Express",Station.station.get(4),Station.station.get(8),new ArrayList<Station>(Arrays.asList(Station.station.get(4),Station.station.get(5),Station.station.get(6),Station.station.get(7),Station.station.get(8))),5,new ArrayList<Integer>(Arrays.asList(0,0,0,0,0))));
		Train.train.put(4522, new Train(4,"Bangalore Express",Station.station.get(4),Station.station.get(6),new ArrayList<Station>(Arrays.asList(Station.station.get(5),Station.station.get(7),Station.station.get(2),Station.station.get(1))),6,new ArrayList<Integer>(Arrays.asList(0,0,0,0))));
		Train.train.put(8922, new Train(5,"Palakkad Express",Station.station.get(10),Station.station.get(1),new ArrayList<Station>(Arrays.asList(Station.station.get(10),Station.station.get(11),Station.station.get(4),Station.station.get(7))),5,new ArrayList<Integer>(Arrays.asList(0,0,0,0))));
		Ticket.bookedtickets.put(1, new Ticket(1,1,"harish@gmail.com",Station.station.get(0),Station.station.get(1),"2/2/2003",1500,false));
		WaitingList.waitinglist.put(1, new WaitingList(1,1,"harish@gmail.com",20,Station.station.get(0),Station.station.get(1),"2/2/2003",2500,false));
		
		home();
	}
	public static void home() {
		while(true) {
			System.out.println("-------------------------- WELCOME TO IRCTC --------------------------------");
			System.out.print(" 1.SIGN UP\n 2.SIGN IN\n Enter your choice: ");
			try {
				int n=Integer.parseInt(sc.nextLine());
				if(n==1) {
					String email=signup("user");
					System.out.println("SIGNED UP SUCCESSFULLY");
					User.userhome(email);
				}
				else if(n==2)
				{
					signin();
				}
				else {
					System.out.println("Enter correct option");
					continue;
				}
			}
			catch(Exception e) {
				System.out.println("Enter correct option");
				continue;
			}
		}
	}
	
	public static String signup(String person) {
		System.out.print("Enter your EMAIL ID: ");
		String email=sc.nextLine();
		if(email.substring(email.length()-10, email.length()).equalsIgnoreCase("@gmail.com")) {
			email=email.toLowerCase();
			System.out.print("Enter your NAME: ");
			String name=sc.nextLine();
			System.out.print("Enter your PASSWORD: ");
			String password=sc.nextLine();
			if(person.equals("user")) {
				User.user.put(email, new User(email,name,password,new ArrayList<Integer>(),new ArrayList<Integer>()));
				return email;
			}
			else {
				Admin.admin.put(email, new Admin(email,name,password));
				return email;
			}
		}
		else {
			System.out.println("Enter your email ID with correct format ('@gmail.com')");
				signup(person);
		}
		return "";
	}
	public static void signin() {
		System.out.print("Enter '0' to EXIT\n Enter your EMAIL ID: ");
		String email=sc.nextLine();
		if(email.equals("0")) {
			home();
		}
		if(User.user.containsKey(email)) {
			System.out.print(" Enter your PASSWORD: ");
			String password=sc.nextLine();
			if(password.equals("0")) {
				home();
			}
			if(password.equals(User.user.get(email).password)) {
				User.userhome(email);
			}
			else {
				System.out.println("Enter correct  EMAIL ID or PASSWORD");
				signin();
			}
		}
		if(Admin.admin.containsKey(email)) {
			System.out.print("Enter your PASSWORD: ");
			String password=sc.nextLine();
			if(password.equals("0")) {
				home();
			}
			if(password.equals(Admin.admin.get(email).password)) {
				Admin.adminhome(email);
			}
			else {
				System.out.println("Enter correct  EMAIL ID or PASSWORD");
				signin();
			}
		}
		else {
			System.out.println("Enter correct EMAIL ID.");
			signin();
		}
	}
    
}
class Admin {
	String emailid;
	String adminname;
	String password;
	public Admin(String emailid,String adminname,String password) {
		this.emailid=emailid;
		this.adminname=adminname;
		this.password=password;
	}
	static HashMap<String,Admin> admin=new HashMap<>();
	public static void adminhome(String email) {
		System.out.println("---------------------WELCOME TO IRCTC "+Admin.admin.get(email).adminname+" -----------------------------");
		System.out.print("Enter '0' to EXIT\n 1.ADD USER \n 2.ADD ADMIN\n 3.ADD TRAINS \n 4.ADD JUNCTION\n 5.VIEW TRAINS \n 6.BOOKING MANAGEMENT \n 7.LOG OUT\n ENTER YOUR CHOICE: ");
		int n=0;
		while(true){
			try {
				n=Integer.parseInt(main.sc.nextLine());
			}
			catch(Exception e) {
				System.out.println("Enter correct option");
			}
	
			switch(n) {
			case 0:
				System.out.println("Are you sure you want to LOG OUT (yes/no): ");
				if(main.sc.nextLine().equals("yes")) {
					main.home();
				}
				else {
					Admin.adminhome(email);
				}
				break;
			case 1:
				String str=main.signup("user");
				System.out.println("NEW USER ADDED SUCCESSFULLY....");
				adminhome(email);
				break;
			case 2:
				String adm=main.signup("admin");
				System.out.println("NEW ADMIN ADDED SUCCESSFULLY....");
				adminhome(email);
				break;
			case 3:
				Train.addtrain(email);
				break;
			case 4:
				System.out.println("Enter a Junction name: ");
				boolean flag=Station.addstation(main.sc.nextLine());
				if(flag) {
					System.out.println("Junction added Successfully");
					adminhome(email);
				}
				else {
					System.out.println("Juction already Exists Please try again");
					adminhome(email);
				}
				break;
			case 5:
				boolean flag1=Train.viewtrains(email);
				if(flag1) {
					Admin.adminhome(email);
				}
				break;
			case 6:
				bookingmanagement();
				break;
			case 7:
				System.out.println("Are you sure you want to LOG OUT (yes/no): ");
				if(main.sc.nextLine().equals("yes")) {
					main.home();
				}
				else {
					Admin.adminhome(email);
				}
				break;
			
			}
		}
		
	}
	public static void bookingmanagement() {
		ArrayList<Integer> booked=new ArrayList<>(Ticket.bookedtickets.keySet());
		ArrayList<Integer> waitbooked=new ArrayList<>(WaitingList.waitinglist.keySet());
		System.out.println();
		if(booked.size()>0) {
		System.out.println("------------------------------ BOOKED TICKETS -------------------------------------");
		for(int j=0;j<booked.size();j++) {
			int ticketid=booked.get(j);
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("TICKET ID:"+ticketid+"                                                  DATE:"+Ticket.bookedtickets.get(ticketid).strdate);
		System.out.println("TRAIN NO: "+Ticket.bookedtickets.get(ticketid).trainid);
		System.out.println("TRAIN NAME: "+Train.train.get(Ticket.bookedtickets.get(ticketid).trainid).trainname+"        "+Ticket.bookedtickets.get(ticketid).from.stationname+" - "+Ticket.bookedtickets.get(ticketid).to.stationname+"                      ");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		ArrayList<Passengers> list=Passengers.passenger.get(ticketid);
		for(int i=0;i<list.size();i++) {
			System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Total Amount:                                                          "+Ticket.bookedtickets.get(ticketid).totalamount+".00");
		System.out.println("------------------------------------------------------------------------------------");
		if(Ticket.bookedtickets.get(ticketid).cancallation)
			System.out.println("X X X X X X X X X X X X X X X X X X X X  TICKET CANCELLED X X X X X X X X X X X X X X X X X X X X");
		}
		}
		if(waitbooked.size()>0) {
			System.out.println("------------------------------ BOOKED TICKETS IN WAITING LIST-------------------------------------");
			for(int j=0;j<waitbooked.size();j++) {
				int id=waitbooked.get(j);
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("TICKET ID:"+id+"                                                  DATE:"+WaitingList.waitinglist.get(id).strdate);
			System.out.println("TRAIN NO: "+WaitingList.waitinglist.get(id).trainid);
			System.out.println("TRAIN NAME: "+Train.train.get(WaitingList.waitinglist.get(id).trainid).trainname+"        "+WaitingList.waitinglist.get(id).from.stationname+" - "+WaitingList.waitinglist.get(id).to.stationname+"                      ");
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
			
				ArrayList<Passengers> list=Passengers.waitpassenger.get(id);
				for(int i=0;i<list.size();i++) {
				System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
				System.out.println();
				}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("Total Amount:                                                          "+WaitingList.waitinglist.get(id).totalamount+".00");
			if(WaitingList.waitinglist.get(id).confirmation)
				System.out.println("**************************************** TICKET CONFIRMED *************************************************");
			System.out.println("------------------------------------------------------------------------------------");
			}
		}
	}
	
}
class Passengers {
	String name;
	int age;
	String gender;
	String berth;
	public Passengers(String name,int age,String gender,String berth) {
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.berth=berth;
	}
	static HashMap<Integer,ArrayList<Passengers>> passenger=new HashMap<>();
	static HashMap<Integer,ArrayList<Passengers>> waitpassenger=new HashMap<>();
	public static ArrayList<Passengers> addpassengers(int ticketid,int n, boolean b) {
		ArrayList<Passengers> list=new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			System.out.print(" Enter a passenger "+(i+1)+" name : ");
			String name=main.sc.nextLine();
			System.out.print("Enter a passenger "+(i+1)+" age: ");
			int age=Integer.parseInt(main.sc.nextLine());
			System.out.print("Enter a gender "+(i+1)+" (male/female): ");
			String gender=main.sc.nextLine();
			System.out.print("Enter a berth preferance(WS/M/A): ");
			String berth=main.sc.nextLine();
			list.add(new Passengers(name,age,gender,berth));
			System.out.println();
		}
		if(b)
			passenger.put(ticketid, list);
		else
			waitpassenger.put(ticketid, list);
		return list;
	}
}
class Station {
	String stationname;
	public Station(String stationname) {
		this.stationname=stationname;
	}
	static ArrayList<Station> station=new ArrayList<>();
	public static boolean addstation(String junction) {
		if(!station.contains(junction)) {
			station.add(new Station(junction));
			return true;
		}
		return false;
	}
	public static String viewstations(String email) {
		for(int i=0;i<station.size();i++)
		{
			System.out.println((i+1)+") "+station.get(i).stationname);
		}
		return "JUNCTIONS SHOWNED";
	}
	
}
class Ticket {
	int ticketid;
	int trainid;
	String userid;
	Station from;
	Station to;
	String strdate;
	int totalamount;
	boolean cancallation;
	public Ticket(int ticketid,int trainid,String userid,Station from,Station to,String strdate,int totalamount,boolean cancallation) {
		this.ticketid=ticketid;
		this.trainid=trainid;
		this.userid=userid;
		this.from=from;
		this.to=to;
		this.strdate=strdate;
		this.totalamount=totalamount;
		this.cancallation=cancallation;
	}
	static HashMap<Integer,Ticket> bookedtickets=new HashMap<>();
	static LocalDateTime date=LocalDateTime.now();
	static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("DD/MM/YYYY");
	static String strDate=date.format(formatter);
	
	public static void booktickets(String email) {
		System.out.println("------------------FROM JUNCTION---------------------");
		String s=Station.viewstations(email);
		System.out.println("----------------------------------------------------");
		int totalstops=0;
		
		System.out.print("Enter '0' to EXIT or ENTER A FROM JUNCTION : ");
		int from=Integer.parseInt(main.sc.nextLine());
		if(from==0)
		{
			User.userhome(email);
		}
		Station fromjunc=Station.station.get(from-1);
		System.out.print("ENTER A TO JUNCTION: ");
		int to=Integer.parseInt(main.sc.nextLine());
		if(to==0)
		{
			User.userhome(email);
		}
		Station tojunc=Station.station.get(to-1);
		if(fromjunc.equals(tojunc)) {
			System.out.println("your FROM and TO junction is same please choose correctly");
			booktickets(email);
		}
		ArrayList<Integer> train=new ArrayList<>();
		for(int tra:Train.train.keySet())
		{
				boolean F_sta=false;
				for(Station from_sta:Train.train.get(tra).stoppings) {
					if((fromjunc.stationname).equals(from_sta.stationname )) {
						F_sta=true;
						totalstops++;
					}
					if((tojunc.stationname).equals(from_sta.stationname) && F_sta) {
						train.add(tra);
						totalstops++;
						break;
					}
				}
		}
		while(true) {
		System.out.println("----------------------------------------------TRAINS------------------------------------------------");
		System.out.println("------------------------------ "+fromjunc.stationname+"-To-"+tojunc.stationname+"---------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.printf("| %5s %15s %15s %15s %15s    |", "S.NO","TRAIN NO.","TRAIN NAME","FROM","TO");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------");
		int i=1;
		for(int trains:train) {
			System.out.printf("| %5s %10s %21s %16s %15s  |", (i++),Train.train.get(trains).trainid,Train.train.get(trains).trainname,Train.train.get(trains).fromstation.stationname,Train.train.get(trains).tostation.stationname);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.print("Enter your option (S.NO) : ");
		int n=Integer.parseInt(main.sc.nextLine());
		int total_price=totalstops*50;
		if(n==0)
		{
			User.userhome(email);
		}
		if(n<=train.size())
		{
			System.out.print("Enter the no. of tickets: ");
			int tick=Integer.parseInt(main.sc.nextLine());
			int available=Train.availableseats(fromjunc.stationname, tojunc.stationname, train.get(n-1));
			if(tick<=available)
			{
				total_price=total_price*tick;
				System.out.print("Enter '1' to continue for ticket booking: ");
				if(main.sc.nextLine().equals("1")) {
					Ticket.bookedtickets.put(bookedtickets.size()+1, new Ticket(bookedtickets.size()+1,train.get(n-1),email,fromjunc,tojunc,strDate,total_price,false));
					Passengers.addpassengers(bookedtickets.size(),tick,true);
					ArrayList<Integer> seat=new ArrayList<>(Train.train.get(train.get(n-1)).seating);
					ArrayList<Station> dumtrain=new ArrayList<>(Train.train.get(train.get(n-1)).stoppings);
					boolean fromflag=false;
					boolean toflag=false;
					boolean came=false;
					for(int a=0;a<dumtrain.size();a++)
					{
						if(fromjunc.stationname.equals(dumtrain.get(a).stationname+"") && !toflag || (fromflag && !toflag)) {
							came=true;
							fromflag=true;
							if(tojunc.stationname.equals(dumtrain.get(a).stationname+"")){
								toflag=true;
							}
							if(!toflag) {
							seat.set(a,seat.get(a)+tick);
							}
							else {
								break;
							}
						}
					}
					Train.train.get(train.get(n-1)).seating=seat;
					//System.out.println(Train.train.get(train.get(n-1)).seating);
					User.user.get(email).mybooked.add(bookedtickets.size());
					System.out.println("*******************************************************************************************");
					System.out.println("**********************************TICKETS BOOKED SUCCESSFULLY******************************");
					System.out.println("*******************************************************************************************");
					Ticket.viewticket(email,bookedtickets.size());
					User.userhome(email);
				}
				else {
					User.userhome(email);
				}
			}
			else if(tick<=WaitingList.waitinglist.get(WaitingList.waitinglist.size()).waitseats) {
				total_price=total_price*tick;
				System.out.print("Enter '1' to continue for ticket booking in waiting list: ");
				if(main.sc.nextLine().equals("1")) {
					int balseats=0;
					boolean baltick=false;
					for(int wait_seats:WaitingList.waitinglist.keySet()) {
						if(WaitingList.waitinglist.get(wait_seats).trainid==train.get(n-1)) {
							balseats=WaitingList.waitinglist.get(wait_seats).waitseats-tick;
							baltick=true;
						}
					}
					if(!baltick) {
						balseats=20-tick;
					}
					WaitingList.waitinglist.put(WaitingList.waitinglist.size()+1, new WaitingList(WaitingList.waitinglist.size()+1,train.get(n-1),email,balseats,fromjunc,tojunc,strDate,total_price,false));
					Passengers.addpassengers(WaitingList.waitinglist.size(),tick,false);
					User.user.get(email).mywaitbooked.add(WaitingList.waitinglist.size());
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println(">>>>>>>>>>>>>>>>>TICKETS BOOKED SUCCESSFULLY IN WAITING LIST>>>>>>>>>>>>>>>");
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					Ticket.waitviewticket(email,WaitingList.waitinglist.size());
					User.userhome(email);
				}
				else {
					User.userhome(email);
				}
				
			}
			else {
				System.out.println("Tickets are not available please try in another train");
				booktickets(email);
			}
		}
		else {
			System.out.println("Enter correct option");
			continue;
		}
		}
	}

	public static boolean waitviewticket(String email, int id) {
		System.out.println("------------------------------ BOOKED TICKETS IN WAITING LIST-------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("TICKET ID:"+id+"                                                  DATE:"+WaitingList.waitinglist.get(id).strdate);
		System.out.println("TRAIN NO: "+WaitingList.waitinglist.get(id).trainid);
		System.out.println("TRAIN NAME: "+Train.train.get(WaitingList.waitinglist.get(id).trainid).trainname+"        "+WaitingList.waitinglist.get(id).from.stationname+" - "+WaitingList.waitinglist.get(id).to.stationname+"                      ");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		
			ArrayList<Passengers> list=Passengers.waitpassenger.get(id);
			for(int i=0;i<list.size();i++) {
			System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
			System.out.println();
			}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Total Amount:                                                          "+WaitingList.waitinglist.get(id).totalamount+".00");
		if(WaitingList.waitinglist.get(id).confirmation)
			System.out.println("**************************************** TICKET CONFIRMED *************************************************");
		System.out.println("------------------------------------------------------------------------------------");
		return true;
	}

	public static boolean viewticket(String email,int ticketid) {
		System.out.println(ticketid);
		System.out.println("------------------------------ BOOKED TICKETS -------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("TICKET ID:"+ticketid+"                                                  DATE:"+Ticket.bookedtickets.get(ticketid).strdate);
		System.out.println("TRAIN NO: "+Ticket.bookedtickets.get(ticketid).trainid);
		System.out.println("TRAIN NAME: "+Train.train.get(Ticket.bookedtickets.get(ticketid).trainid).trainname+"        "+Ticket.bookedtickets.get(ticketid).from.stationname+" - "+Ticket.bookedtickets.get(ticketid).to.stationname+"                      ");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		ArrayList<Passengers> list=Passengers.passenger.get(ticketid);
		for(int i=0;i<list.size();i++) {
			System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Total Amount:                                                          "+Ticket.bookedtickets.get(ticketid).totalamount+".00");
		System.out.println("------------------------------------------------------------------------------------");
		return true;
	}

	public static void canceltickets(String email) {
		System.out.println("YOUR BOOKED TICKET LIST");
		ArrayList<Integer> booked=new ArrayList<>(User.user.get(email).mybooked);
		int y=1;
		for(int i=0;i<booked.size();i++)
		{
			if(!Ticket.bookedtickets.get(booked.get(i)).cancallation)
			System.out.println((i+1)+". TICKET NO."+booked.get(i)+" ,"+Ticket.bookedtickets.get(booked.get(i)).from.stationname+" - "+Ticket.bookedtickets.get(booked.get(i)).to.stationname);
		}
		System.out.print("Enter '0' to EXIT\nEnter your choice to cancel the ticket:");
		int cancel=Integer.parseInt(main.sc.nextLine());
		if(cancel==0) {
			User.userhome(email);
		}
		System.out.println("ARE YOU SURE TO CANCEL THE TICKETS FROM-"+Ticket.bookedtickets.get(booked.get(cancel-1)).from.stationname+" TO-"+Ticket.bookedtickets.get(booked.get(cancel-1)).to.stationname+" (YES/NO): ");
		if(main.sc.nextLine().equalsIgnoreCase("yes")) {
			int ticketid=booked.get(cancel-1);
			int trainid=Ticket.bookedtickets.get(ticketid).trainid;
			Station from=Ticket.bookedtickets.get(ticketid).from;
			Station to=Ticket.bookedtickets.get(ticketid).to;
			int tick=Passengers.passenger.get(ticketid).size();
			ArrayList<Integer> seat=new ArrayList<>(Train.train.get(trainid).seating);
			ArrayList<Station> dumtrain=new ArrayList<>(Train.train.get(trainid).stoppings);
			boolean fromflag=false;
			boolean toflag=false;
			boolean came=false;
			for(int a=0;a<dumtrain.size();a++)
			{
				if(from.stationname.equals(dumtrain.get(a).stationname+"") && !toflag || (fromflag && !toflag)) {
					came=true;
					fromflag=true;
					if(to.stationname.equals(dumtrain.get(a).stationname+"")){
						toflag=true;
					}
					if(!toflag) {
					seat.set(a,seat.get(a)-tick);
					}
					else {
						break;
					}
				}
			}
			
			Train.train.get(trainid).seating=seat;
			Ticket.bookedtickets.get(ticketid).cancallation=true;
			System.out.println("*******************************************************************************************");
			System.out.println("**************************TICKETS CANCELLED SUCCESSFULLY************************************");
			System.out.println("*******************************************************************************************");
			for(int wait:WaitingList.waitinglist.keySet()) {
				if(WaitingList.waitinglist.get(wait).trainid==trainid && WaitingList.waitinglist.get(wait).confirmation==false) {
					int tick1=Passengers.waitpassenger.get(wait).size();
					Station waitfrom=WaitingList.waitinglist.get(wait).from;
					Station waitto=WaitingList.waitinglist.get(wait).to;
					int available=Train.availableseats(waitfrom.stationname, waitto.stationname, trainid);
					if(tick1<=available)
					{
						Ticket.bookedtickets.put(bookedtickets.size()+1, new Ticket(bookedtickets.size()+1,trainid,email,waitfrom,waitto,strDate,WaitingList.waitinglist.get(wait).totalamount,false));
//						User.user.get(email).mywaitbooked.remove(wait);
						System.out.println();
						Passengers.passenger.put(bookedtickets.size(),Passengers.waitpassenger.get(wait));
//						Passengers.waitpassenger.remove(wait);
						
						ArrayList<Integer> seat1=new ArrayList<>(Train.train.get(trainid).seating);
						ArrayList<Station> dumtrain1=new ArrayList<>(Train.train.get(trainid).stoppings);
						boolean fromflag1=false;
						boolean toflag1=false;
						boolean came1=false;
						for(int a=0;a<dumtrain1.size();a++)
						{
							if(waitfrom.stationname.equals(dumtrain1.get(a).stationname+"") && !toflag1 || (fromflag1 && !toflag1)) {
								came1=true;
								fromflag1=true;
								if(waitto.stationname.equals(dumtrain1.get(a).stationname+"")){
									toflag1=true;
								}
								if(!toflag1) {
								seat1.set(a,seat1.get(a)+tick1);
								}
								else {
									break;
								}
							}
						}
						Train.train.get(trainid).seating=seat1;
						//System.out.println(Train.train.get(trainid).seating);
						User.user.get(email).mybooked.add(bookedtickets.size());
						WaitingList.waitinglist.get(wait).confirmation=true;
						WaitingList.waitinglist.get(wait).waitseats=WaitingList.waitinglist.get(wait).waitseats+tick1;
						System.out.println("*******************************************************************************************");
						System.out.println("***********************WAITING LIST TICKETS CONFIRMED SUCCESSFULLY*************************");
						System.out.println("*******************************************************************************************");
						Ticket.viewticket(email,bookedtickets.size());
					}
				}
			}
			System.out.println(Train.train.get(trainid).seating);
			User.userhome(email);
		}
		else {
			User.userhome(email);
		}
		User.userhome(email);
	}
	
}
class Train {
	int trainid;
	String trainname;
	Station fromstation;
	Station tostation;
	int totalseats;
	ArrayList<Integer> seating=new ArrayList<>();
	ArrayList<Station> stoppings=new ArrayList<>();
	public Train(int trainid,String trainname,Station fromstation,Station tostation,ArrayList<Station> stoppings,int totalseats, ArrayList<Integer> seating) {
		this.trainid=trainid;
		this.trainname=trainname;
		this.fromstation=fromstation;
		this.tostation=tostation;
		this.stoppings=stoppings;
		this.totalseats=totalseats;
		this.seating=seating;
	}
	static HashMap<Integer,Train> train=new HashMap<>();
	public static void addtrain(String email) {
		ArrayList<Station> dupstation=new ArrayList<>(Station.station);
		ArrayList<Station> stoppings=new ArrayList<>();
		ArrayList<Integer> seatings=new ArrayList<>();
		System.out.print("Enter '0' to EXIT\nEnter a train id: ");
		int trainid=Integer.parseInt(main.sc.nextLine());
		if(trainid==0)
		{
			Admin.adminhome(email);
		}
		if(!train.containsKey(trainid)) {
			System.out.print("Enter a train name: ");
			String name=main.sc.nextLine();
			if(name.equals("0"))
			{
				Admin.adminhome(email);
			}
			for(int i=0;i<dupstation.size();i++)
			{
				System.out.println((i+1)+") "+dupstation.get(i).stationname);
			}
			System.out.print("choose a FROM STATION of train: ");
			int fromstation=Integer.parseInt(main.sc.nextLine());
			if(fromstation==0)
			{
				Admin.adminhome(email);
			}
			Station from=dupstation.get(fromstation-1);
			stoppings.add(from);
			seatings.add(0);
			dupstation.remove(fromstation-1);
			for(int i=0;i<dupstation.size();i++)
			{
				System.out.println((i+1)+") "+dupstation.get(i).stationname);
			}
			System.out.print("choose a TO STATION of train: ");
			int tostation=Integer.parseInt(main.sc.nextLine());
			if(tostation==0)
			{
				Admin.adminhome(email);
			}
			Station to=dupstation.get(tostation-1);
			dupstation.remove(tostation-1);
			System.out.println("ENTER THE STOPPINGS LISTED IN THE BELOW JUNCTIONS:");
			
			while(true) {
				for(int i=0;i<dupstation.size();i++)
				{
					System.out.println((i+1)+") "+dupstation.get(i).stationname);
				}
				System.out.print("Enter '0' to EXIT or Enter a stopping: ");
				int stop=Integer.parseInt(main.sc.nextLine());
				System.out.println("came");
				if(stop==0)
				{
//					flag=true;
					break;
				}
				stoppings.add(dupstation.get(stop-1));
				seatings.add(0);
				dupstation.remove(stop-1);
			}
			stoppings.add(to);
			seatings.add(0);
			train.put(trainid,new Train(trainid,name,from,to,stoppings,100,seatings));
			System.out.println("Train added with details successfully...........  ");
			Admin.adminhome(email);
		}
		else {
			System.out.println("Trian ID already exists please add a different ID.");
			addtrain(email);
		}
	}
	public static int availableseats(String from,String to,int trainid) {
		ArrayList<Integer> seat=new ArrayList<>(Train.train.get(trainid).seating);
		ArrayList<Station> train=new ArrayList<>(Train.train.get(trainid).stoppings);
		int maxseat=Train.train.get(trainid).totalseats;
		int totalseat=Train.train.get(trainid).totalseats;
		boolean fromflag=false;
		boolean toflag=false;
		boolean came=false;
		for(int i=0;i<train.size();i++)
		{
			if(from.equals(train.get(i).stationname+"") && !toflag || (fromflag && !toflag)) {
				came=true;
				fromflag=true;
				if(to.equals(train.get(i).stationname+"")){
					break;
				}
				else {
				maxseat=Math.min(maxseat, totalseat-seat.get(i));
				}
			}
		}
		if(came)
		return maxseat;
		else
			return 0;
	}
	
	public static boolean viewtrains(String email) {
		
		System.out.println("----------------------------------------------TRAINS------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.printf("| %5s %15s %15s %18s %17s    |", "S.NO","TRAIN NO.","TRAIN NAME","FROM","TO");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------");
		int i=1;
		for(int trains:Train.train.keySet()) {
			System.out.printf("| %5s %10s %21s %20s %15s  |", (i++),Train.train.get(trains).trainid,Train.train.get(trains).trainname,Train.train.get(trains).fromstation.stationname,Train.train.get(trains).tostation.stationname);
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
		return true;
	}
}
class User {
	String emailid;
	String username;
	String password;
	ArrayList<Integer> mybooked=new ArrayList<>();
	ArrayList<Integer> mywaitbooked=new ArrayList<>();
	public User(String emailid,String username,String password,ArrayList<Integer> mybooked,ArrayList<Integer> mywaitbooked) {
		this.emailid=emailid;
		this.username=username;
		this.password=password;
		this.mybooked=mybooked;
		this.mywaitbooked=mywaitbooked;
	}
	static HashMap<String,User> user=new HashMap<>();
	public static void userhome(String email) {
		System.out.println("-----------------------------WELCOME TO IRCTC "+User.user.get(email).username+" ----------------------------");
		System.out.print("Enter '0' to EXIT: \n 1.BOOK TICKETS\n 2.CANCEL TICKETS \n 3.VIEW TRAINS \n 4.MY BOOKINGS  \n 5.LOG OUT\n Enter your choice: ");
		int n=0;
		try {
		n=Integer.parseInt(main.sc.nextLine());
		}
		catch(Exception e) {
			System.out.println("Enter correct option");
			userhome(email);
		}
		switch(n){
			case 0:
				System.out.println("ARE YOU SURE YOU WANT TO LOG OUT (yes/no): ");
				if(main.sc.nextLine().equalsIgnoreCase("yes")) {
					main.home();
				}
				else {
					userhome(email);
				}
				break;
			case 1:
				Ticket.booktickets(email);
				break;
			case 2:
				Ticket.canceltickets(email);
				break;
			case 3:
				Train.viewtrains(email);
				User.userhome(email);
				break;
			case 4:
				mybookings(email);
				break;
			case 5:
				System.out.println("ARE YOU SURE YOU WANT TO LOG OUT (yes/no): ");
				if(main.sc.nextLine().equalsIgnoreCase("yes")) {
					main.home();
				}
				else {
					userhome(email);
				}
				break;
		}
	}
	public static void mybookings(String email) {
		ArrayList<Integer> booked=new ArrayList<>(User.user.get(email).mybooked);
		ArrayList<Integer> waitbooked=new ArrayList<>(User.user.get(email).mywaitbooked);
		System.out.println();
		boolean gone=false;
		if(booked.size()>0) {
			gone=true;
		System.out.println("------------------------------ BOOKED TICKETS -------------------------------------");
		for(int j=0;j<booked.size();j++) {
			int ticketid=booked.get(j);
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("TICKET ID:"+ticketid+"                                                  DATE:"+Ticket.bookedtickets.get(ticketid).strdate);
		System.out.println("TRAIN NO: "+Ticket.bookedtickets.get(ticketid).trainid);
		System.out.println("TRAIN NAME: "+Train.train.get(Ticket.bookedtickets.get(ticketid).trainid).trainname+"        "+Ticket.bookedtickets.get(ticketid).from.stationname+" - "+Ticket.bookedtickets.get(ticketid).to.stationname+"                      ");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		ArrayList<Passengers> list=Passengers.passenger.get(ticketid);
		for(int i=0;i<list.size();i++) {
			System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Total Amount:                                                          "+Ticket.bookedtickets.get(ticketid).totalamount+".00");
		System.out.println("------------------------------------------------------------------------------------");
		if(Ticket.bookedtickets.get(ticketid).cancallation)
			System.out.println("X X X X X X X X X X X X X X X X X X X X  TICKET CANCELLED X X X X X X X X X X X X X X X X X X X X");
		}
		}
		if(waitbooked.size()>0) {
			gone=true;
			System.out.println("------------------------------ BOOKED TICKETS IN WAITING LIST-------------------------------------");
			for(int j=0;j<waitbooked.size();j++) {
				int id=waitbooked.get(j);
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("TICKET ID:"+id+"                                                  DATE:"+WaitingList.waitinglist.get(id).strdate);
			System.out.println("TRAIN NO: "+WaitingList.waitinglist.get(id).trainid);
			System.out.println("TRAIN NAME: "+Train.train.get(WaitingList.waitinglist.get(id).trainid).trainname+"        "+WaitingList.waitinglist.get(id).from.stationname+" - "+WaitingList.waitinglist.get(id).to.stationname+"                      ");
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("|%5s %18s %20s %13s |","S.No","Passenger name","Passenger age","Gender");
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
			
				ArrayList<Passengers> list=Passengers.waitpassenger.get(id);
				for(int i=0;i<list.size();i++) {
				System.out.printf("|%5s %13s %20s %17s |",(i+1),list.get(i).name,list.get(i).age,list.get(i).gender);
				System.out.println();
				}
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("Total Amount:                                                          "+WaitingList.waitinglist.get(id).totalamount+".00");
			if(WaitingList.waitinglist.get(id).confirmation)
				System.out.println("**************************************** TICKET CONFIRMED *************************************************");
			System.out.println("------------------------------------------------------------------------------------");
			}
		}
		if(!gone) {
			System.out.println("No tickets booked.");
		}
		User.userhome(email);
	}
}
class WaitingList {
	int waitid;
	int trainid;
	String userid;
	int waitseats;
	Station from;
	Station to;
	String strdate;
	int totalamount;
	boolean confirmation;
	public WaitingList(int waitid,int trainid,String userid,int waitseats,Station from,Station to,String strdate,int totalamount,boolean confirmation) {
		this.waitid=waitid;
		this.trainid=trainid;
		this.userid=userid;
		this.waitseats=waitseats;
		this.from=from;
		this.to=to;
		this.strdate=strdate;
		this.totalamount=totalamount;
		this.confirmation=confirmation;
	}
	static HashMap<Integer,WaitingList> waitinglist=new HashMap<>();
	
}
