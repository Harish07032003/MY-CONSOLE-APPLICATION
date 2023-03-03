import java.util.*;
public class Railway{
	static Scanner sc=new Scanner(System.in);
	static HashMap<Integer,String> booked_tickets=new HashMap<>();
	static HashMap<Integer,String> waiting_list=new HashMap<>();
	static HashMap<String,String> passengers_details=new HashMap<>();
	static HashMap<Integer,String> confirmation=new HashMap<>();
	static int jnc[][]= {{0},{0,10},{0,10},{0,10},{0,10},{0,10}};
	static HashMap<String,ArrayList<Integer>> Passengers_allocated_seat=new HashMap<>();
//	static HashMap<String,ArrayList<Integer>> Passengers_confirmed_allocated_seat=new HashMap<>();
	static ArrayList<String> junction=new ArrayList<>(Arrays.asList("0","Coimbatore","Tirupur","Erode","Salem","Chennai"));
	public static void main(String[] args) {
		System.out.println();
		System.out.println();
		System.out.println("WELCOME TO IRCTC");
		System.out.println();
		System.out.println("1: BOOKING");
		System.out.println("2: CANCELATION");
		System.out.println("3: SIGN IN");
		int i=0;
		while(i==0) 
		{
			System.out.print("Enter no: ");
			String a=sc.nextLine();
			if(a.equals("1"))
			{
				booking();
				break;
			}
			else if(a.equals("2"))
			{
				cancel();
				break;
			}
			else if(a.equals("3"))
			{
				System.out.println();
				System.out.println("Enter EXIT to back");
				System.out.println("Enter your username: ");
				String name=sc.nextLine();
				if(name.equals("EXIT"))
				{
					main(null);
				}
				System.out.println("Enter your user ID: ");
				String password=sc.nextLine();
				if(password.equals("EXIT"))
				{
					main(null);
				}
				passengers_details.put(name, passengers_details.getOrDefault(name,password));
				System.out.println("Login successfully...");
				main(null);
			}
			else
			{
				System.out.println("Enter correct no: ");
				continue;
			}
		}
	}
	private static void booking() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("                                  BOOKING                                          ");
		ArrayList<String> pass_name=new ArrayList<>(passengers_details.keySet());
		ArrayList<String> pass_id=new ArrayList<>(passengers_details.values());
		System.out.println();
		for(int i=1;i<11;i++)
		{
			booked_tickets.put(i,booked_tickets.getOrDefault(i,null));
		}
		while(1!=0)
		{
		System.out.print("Enter username: ");
		String passenger_name=sc.nextLine();
		System.out.print("Enter password: ");
		String passenger_ID=sc.nextLine();
		if(pass_name.contains(passenger_name) && pass_id.contains(passenger_ID))
		{
		int g=0;
		while(g==0)
		{
		if(!passenger_name.equals(""))
		{
		System.out.println();
		System.out.println("Select a junction");
		System.out.println("1.Coimbatore");
		System.out.println("2.Tirupur");
		System.out.println("3.Erode");
		System.out.println("4.salem");
		System.out.println("5.Chennai");
		System.out.println("6.EXIT");
		System.out.println();
		String from_to=passenger_name+"-";
		int i=0;
		int jn1=0,jn2=0;
		while(i==0)
		{
			System.out.println("Enter a 'FROM' Junction no: ");
			try
			{
				int jn=sc.nextInt();
				if(jn==6)
				{
					main(null);
					break;
				}
				if(jn>0 && jn<6)
				{
					from_to+=junction.get(jn);
					
					jn1=jn;
					break;
				}
				else
				{
					System.out.println("Enter correct no: ");
					continue;
				}
			}
			catch(Exception e)
			{
				System.out.println("Enter numeric value: ");
				continue;
			}
		}
		while(i==0)
		{
			System.out.println("Enter a 'TO' Junction no: ");
			try
			{
				jn2=sc.nextInt();
				if(jn2>0 && jn2<6 && jn2>jn1)
				{
					from_to+="-"+junction.get(jn2);
					break;
				}
				else
				{
					System.out.println("Enter correct no: ");
					continue;
				}
			}
			catch(Exception e)
			{
				System.out.println("Enter numeric value: ");
			}
		}
		int totalseats_available=0;
		for(int k=jn1;k<=jn2;k++)
		{
			totalseats_available=Math.max(jnc[k][0], totalseats_available);
		}
		if(totalseats_available<=10)
		{
			System.out.println("From "+junction.get(jn1)+" To "+junction.get(jn2)+" available seats are "+Math.abs(totalseats_available-10));
		}
		else
		{
			System.out.println("seats not available if you continue it will be in waiting list...");
		}
		while(1!=0)
		{
			System.out.println("Enter the no.of seats: ");
			int seat=sc.nextInt();
			int total_seat=0;
//			if(seat>=totalseats_available)
//			{
//				System.out.println("Given seats are greater than availability if you continue give 'yes'");
//				while(1!=0)
//				{
//					System.out.println("Enter 'yes': ");
//					sc.nextLine();
//					if(sc.nextLine().equals("yes"))
//					{
//						break;
//					}
//					else
//					{
//						System.out.println("Enter correctly...");
//						continue;
//					}
//				}
//			}
			for(int j=1;j<booked_tickets.size()+1;j++)
			{
				if(booked_tickets.get(j)==null)
				{
					total_seat++;
				}
			}
			int final_seats=0;
			int bal=0;
			if(seat<=total_seat)
			{
				final_seats=seat;
				bal=0;
			}
			else
			{
				final_seats=total_seat;
				bal=Math.abs(seat-total_seat);
			}
				int no_of_seats=0;
					Passengers_allocated_seat.put(passengers_details.get(passenger_name),Passengers_allocated_seat.getOrDefault(passengers_details.get(passenger_name),new ArrayList<>()) );
					for(int j=1;j<booked_tickets.size()+1;j++)
					{
						boolean flag1=true;
						if(booked_tickets.get(j)==null)
						{
							for(int jn=jn1;jn<jn2;jn++)
							{
								if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0)
								{
									jnc[jn][0]++;
								}
								else
								{
									jnc[jn2][1]--;
									for(int jn_b=jn;jn_b>=jn1;jn_b--)
									{
										if(jnc[jn_b][0]-1>0)
										{
										jnc[jn_b][0]--;
										}
									}
									System.out.println(Arrays.deepToString(jnc));
									waitinglist(passenger_name,from_to,Math.abs((seat-no_of_seats)));
									flag1=false;
									break;
								}
							}
							System.out.println(Arrays.deepToString(jnc));
							booked_tickets.put(j, from_to);
							Passengers_allocated_seat.get(passengers_details.get(passenger_name)).add(j);
							if((jnc[jn2][1]-1)>0)
							{
								jnc[jn2][1]--;
							}
							else
							{
								flag1=false;
								no_of_seats++;
								System.out.println(Arrays.deepToString(jnc));
								waitinglist(passenger_name,from_to,Math.abs((seat-no_of_seats)));
								break;
							}
							no_of_seats++;
							if(no_of_seats==seat || flag1==false)
							{
								System.out.println(Arrays.deepToString(jnc));
								break;
							}
							if(flag1=false)
							{
								break;
							}
							
						}
					}
			if(bal>0)
			{
				Passengers_allocated_seat.put(passengers_details.get(passenger_name),Passengers_allocated_seat.getOrDefault(passengers_details.get(passenger_name),new ArrayList<>()) );
				no_of_seats=0;
				System.out.println("bal="+bal);
				for(int j=1;j<=confirmation.size()+bal;j++)
				{
					confirmation.put(j,confirmation.getOrDefault(j, null));
					boolean flag1=true;
					if(confirmation.get(j)==null)
					{
						for(int jn=jn1;jn<jn2;jn++)
						{
							if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0)
							{
								jnc[jn][0]++;
							}
							else
							{
								jnc[jn2][1]--;
								for(int jn_b=jn;jn_b>=jn1;jn_b--)
								{
									if(jnc[jn_b][0]-1>0)
									{
									jnc[jn_b][0]--;
									}
								}
								waitinglist(passenger_name,from_to,Math.abs(bal-no_of_seats));
								flag1=false;
								break;
							}
						}
						confirmation.put(j, from_to);
						Passengers_allocated_seat.get(passengers_details.get(passenger_name)).add(j);
						if((jnc[jn2][1]-1)>0)
						{
							jnc[jn2][1]--;
						}
						else
						{
							flag1=false;
							no_of_seats++;
							waitinglist(passenger_name,from_to,Math.abs(bal-no_of_seats));
							break;
						}
						no_of_seats++;
						if(no_of_seats==bal || flag1==false)
						{
							break;
						}
						if(flag1=false)
						{
							break;
						}
						
					}
				}
				break;
			}
			break;
		}
		System.out.println("BOOKED TICKETS.......");
		System.out.println("S.no               PASSENGER NAME          DESTINATION          ARRIVAL");
		System.out.println();
		int j=1;
		for(int a=1;a<=booked_tickets.size();a++)
		{
			if(booked_tickets.get(a)!=null)
			{
				String st[]=booked_tickets.get(a).split("-");
				System.out.println((j++)+".               "+st[0]+"         "+st[1]+"          "+st[2]);
			}
		}
		for(int a=1;a<=confirmation.size();a++)
		{
			if(confirmation.get(a)!=null)
			{
				String st[]=confirmation.get(a).split("-");
				System.out.println((j)+".               "+st[0]+"          "+st[1]+"          "+st[2]);
				j++;
			}
		}
		System.out.println();
		System.out.println("your seats are booked successfully....\n");
		System.out.println("Enter 3. to EXIT: ");
		sc.nextLine();
		while(1!=0)
		{
			String book=sc.nextLine();
			if(book.equals("3"))
			{
				main(null);
				break;
			}
			else
			{
				System.out.println("Enter correct no: ");
				continue;
			}
		}
		}
		else
		{
			System.out.println("Enter correct name: ");
			continue;
		}
		}
		
		break;
	}
		else
		{
			System.out.println("your passenger_name or passenger_ID is wrong \n    try again...");
			main(null);
		}
		}
	}
	private static void waitinglist(String passenger_name,String from_to,int seat) {
		System.out.println();
		int len=waiting_list.size();
		for(int i=1;i<=5;i++)
		{
			waiting_list.put(i,waiting_list.getOrDefault(i,null));
		}
		int no_of_seats=1;
		System.out.println("Total Waiting list seats for "+passenger_name+" is "+seat);
		for(int j=1;j<=5;j++)
		{
			if(waiting_list.get(j)==null && no_of_seats<=seat)
			{
				waiting_list.put(j, from_to);
				no_of_seats++;
			}
		}
		if(seat>5)
		{
			System.out.println((Math.abs(seat-5))+" seats has no tickets because only 5 seats are availability in waiting list");
		}
		System.out.println("BOOKED TICKETS.......");
		System.out.println("booked->"+booked_tickets+"\n confirmed->"+confirmation);
		System.out.println("S.no               PASSENGER NAME          DESTINATION          ARRIVAL");
		System.out.println();
		int j=1;
		for(int a=1;a<=booked_tickets.size();a++)
		{
			if(booked_tickets.get(a)!=null)
			{
				String st[]=booked_tickets.get(a).split("-");
				System.out.println((j++)+".               "+st[0]+"          "+st[1]+"          "+st[2]);
			}
		}
		for(int a=1;a<=confirmation.size();a++)
		{
			if(confirmation.get(a)!=null)
			{
				String st[]=confirmation.get(a).split("-");
				System.out.println((j)+".               "+st[0]+"          "+st[1]+"          "+st[2]);
				j++;
			}
		}
		System.out.println();
		System.out.println("Waiting->"+waiting_list);
		System.out.println("S.no                WAITING LIST PASSENGERS          DESTINATION          ARRIVAL");
		for(int a=1;a<=waiting_list.size();a++)
		{
			if(waiting_list.get(a)!=null)
			{
				String st[]=waiting_list.get(a).split("-");
				System.out.println(a+".               "+st[0]+"          "+st[1]+"          "+st[2]);
			}
		}
		System.out.println();
		System.out.println("Enter 3. to EXIT: ");
		int i=0;
		while(i==0)
		{
			if(sc.nextLine().equals("3"))
			{
				System.out.println("-----------------------------------------------------------------------------");
				main(null);
				break;
			}
			else
			{
				System.out.println("Enter correct no: ");
				continue;
			}
		}
	}
	private static void cancel() {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("                                CANCELATION                                       ");
		ArrayList<String> names=new ArrayList<>(passengers_details.keySet());
		ArrayList<String> IDs=new ArrayList<>(passengers_details.values());
		while(1!=0)
		{
			sc.nextLine();
			System.out.println("Enter EXIT to back");
		System.out.println("Enter your username: ");
		String name=sc.nextLine();
		if(name.equals("EXIT"))
		{
			main(null);
		}
		System.out.println("Enter your password: ");
		String ID=sc.nextLine();
		if(ID.equals("EXIT"))
		{
			main(null);
		}
		if(names.contains(name) && IDs.contains(ID))
		{
		System.out.println("1.cancel all seats"+"\n"+"2.cancel particular seats");
		System.out.println("Enter option 1 (or) 2 : ");
		String s=sc.nextLine();
		if(s.equals("1"))
		{
			boolean flag_one=false;
			int available_seats_booked=0;
			int available_seats_confirmed=0;
			String confirm_cancel="";
			for(int i=1;i<confirmation.size()+1;i++)
			{
				
				try {
				String chkcn[]= confirmation.get(i).split("-");
				if(chkcn[0].equals(name))
				{
					int jn1=junction.indexOf(chkcn[1]);
					int jn2=junction.indexOf(chkcn[2]);
						for(int jn_b=jn1;jn_b<jn2;jn_b++)
						{
							if(jnc[jn_b][0]-1>0)
							{
								jnc[jn_b][0]--;
							}
							else
							{
								break;
							}
						}
					confirm_cancel+="          "+chkcn[0]+"     "+chkcn[1]+"     "+chkcn[2]+"\n";
					confirmation.put(i,null);
					Passengers_allocated_seat.put(ID, null);
					jnc[jn2][1]++;
					available_seats_confirmed++;
					flag_one=true;
				}
				}
				catch(Exception e)
				{
					
					if(confirmation.get(i)==null)
					{
					available_seats_confirmed++;
					}
				}
			}
			System.out.println(confirm_cancel);
			confirm_cancel="";
			for(int i=0;i<booked_tickets.size()+1;i++)
			{
				try
				{
				String chkbk[]= booked_tickets.get(i).split("-");
				
				if(chkbk[0].equals(name))
				{
					int jn1=junction.indexOf(chkbk[1]);
					int jn2=junction.indexOf(chkbk[2]);
						for(int jn_b=jn1;jn_b<jn2;jn_b++)
						{
							if(jnc[jn_b][0]-1>0)
							{
								jnc[jn_b][0]--;
							}
							else
							{
								break;
							}
						}
						available_seats_booked++;
						confirm_cancel+="          "+chkbk[0]+"     "+chkbk[1]+"     "+chkbk[2]+"\n";
						Passengers_allocated_seat.put(ID,null);
						jnc[jn2][1]++;
						booked_tickets.put(i,null);
						flag_one=true;
				}
				}
				catch(Exception e)
				{
					if(booked_tickets.get(i)==null)
					{
						available_seats_booked++;
						System.out.println("available_seats_booked=>"+available_seats_booked);
					}
				}
			}
			System.out.println(confirm_cancel);
			for(int wait=1;wait<=5;wait++)
			{
				try
				{
				if(waiting_list.get(wait).contains(name))
				{
					waiting_list.put(wait, null);
				}
				}
				catch(Exception e)
				{
					waiting_list.put(wait, null);
				}
			}
			System.out.println();
			if(flag_one==true)
			{
				Passengers_allocated_seat.put(ID,new ArrayList<>());
				boolean flag=false;
				if(names.contains(name) && IDs.contains(ID))
				{
					System.out.println("Enter 'yes' to cancel all tickets");
					int k=0;
					while(k==0)
					{
						if(sc.nextLine().equals("yes"))
						{
							for(int w=1;w<=waiting_list.size();w++)
							{
								try
								{
								String split[]=waiting_list.get(w).split("-");
								int jn1=junction.indexOf(split[1]);
								int jn2=junction.indexOf(split[2]);
								boolean fg=true;
								for(int jn=jn1;jn<jn2;jn++)
								{
									if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0)
									{
										jnc[jn][0]++;
										fg=false;
									}
									else
									{
										if(fg==false)
										{
										jnc[jn2][1]--;
										for(int jn_b=jn;jn_b>=jn1;jn_b--)
										{
											if(jnc[jn_b][0]-1>0)
											{
											jnc[jn_b][0]--;
											}
										}
										}
										System.out.println();
										System.out.println("passenger details->"+passengers_details);
										System.out.println("booked->"+booked_tickets);
										System.out.println("Passengers_allocated_seat->"+Passengers_allocated_seat);
										System.out.println("junction->"+Arrays.deepToString(jnc));
										break;
									}
								}
								for(int bk=1;bk<booked_tickets.size();bk++)
								{
									if(booked_tickets.get(bk)==null)
									{
										booked_tickets.put(bk, waiting_list.get(w));
										waiting_list.put(w, null);
										Passengers_allocated_seat.get(passengers_details.get(split[0])).add(bk);
										break;
									}
								}
								if((jnc[jn2][1]-1)>0)
								{
									jnc[jn2][1]--;
									System.out.println("junction->"+Arrays.deepToString(jnc));
								}
								else
								{
									System.out.println();
									System.out.println("passenger details->"+passengers_details);
									System.out.println("booked->"+booked_tickets);
									System.out.println("Passengers_allocated_seat->"+Passengers_allocated_seat);
									System.out.println("junction->"+Arrays.deepToString(jnc));
								}
								}
								catch(Exception e)
								{
									break;
								}
							}
							
							for(int w=1;w<=waiting_list.size();w++)
							{
								try
								{
								String split[]=waiting_list.get(w).split("-");
								int jn1=junction.indexOf(split[1]);
								int jn2=junction.indexOf(split[2]);
								boolean fg=true;
								for(int jn=jn1;jn<jn2;jn++)
								{
									if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0 && (jnc[jn][1]-1)>0)
									{
										jnc[jn][0]++;
										fg=false;
									}
									else
									{
										if(fg==false)
										{
										jnc[jn2][1]--;
										for(int jn_b=jn;jn_b>=jn1;jn_b--)
										{
											if(jnc[jn_b][0]-1>0)
											{
											jnc[jn_b][0]--;
											}
										}
										}
										System.out.println();
										System.out.println("passenger details->"+passengers_details);
										System.out.println("confirm->"+confirmation);
										System.out.println("Passengers_confirmed_allocated_seat->"+Passengers_allocated_seat);
										System.out.println("junction->"+Arrays.deepToString(jnc));
										break;
									}
								}
								for(int bk=1;bk<confirmation.size();bk++)
								{
									if(confirmation.get(bk)==null)
									{
										confirmation.put(bk, waiting_list.get(w));
										waiting_list.put(w, null);
										Passengers_allocated_seat.get(passengers_details.get(split[0])).add(bk);
										break;
									}
								}
								if((jnc[jn2][1]-1)>0)
								{
									jnc[jn2][1]--;
									System.out.println("junction->"+Arrays.deepToString(jnc));
								}
								else
								{
									System.out.println();
									System.out.println("passenger details->"+passengers_details);
									System.out.println("confirm->"+confirmation);
									System.out.println("Passengers_confirmed_allocated_seat->"+Passengers_allocated_seat);
									System.out.println("junction->"+Arrays.deepToString(jnc));
								}
								}
								catch(Exception e)
								{
									break;
								}
							}
							System.out.println();
							System.out.println("booked tickets-> "+booked_tickets);
							System.out.println("confirmed-> "+confirmation);
							System.out.println("waiting list-> "+waiting_list);
							System.out.println("Passengers_confirmed_allocated_seat->"+Passengers_allocated_seat);
							System.out.println("Your seats are cancelled succesfully");
							System.out.println();
							main(null);
							break;
							}
							else if(flag==true)
							{
								break;
							}
							else
							{
								System.out.println("Enter correctly...");
								continue;
							}
						}
					System.out.println("WAITING LIST IS CONFIRMED.......");
					System.out.println("S.no               PASSENGER NAME          DESTINATION          ARRIVAL");
					System.out.println();
					for(int a=1;a<=booked_tickets.size();a++)
					{
						int j=0;
						if(booked_tickets.get(a)!=null)
						{
							String st[]=booked_tickets.get(a).split("-");
							System.out.println((j++)+".          "+st[0]+"     "+st[1]+"     "+st[2]);
						}
					}
					for(int a=1;a<=confirmation.size();a++)
					{
						int j=0;
						if(confirmation.get(a)!=null)
						{
							String st[]=confirmation.get(a).split("-");
							System.out.println((j)+".          "+st[0]+"     "+st[1]+"     "+st[2]);
							j++;
						}
					}
					System.out.println();
					System.out.println("Waiting list is filled in main list....");
					System.out.println();
					System.out.println("S.no                WAITING LIST PASSENGERS          DESTINATION          ARRIVAL");
					for(int a=1;a<=waiting_list.size();a++)
					{
						if(waiting_list.get(a)!=null)
						{
							String st[]=waiting_list.get(a).split("-");
							System.out.println(a+".               "+st[0]+"          "+st[1]+"          "+st[2]);
						}
					}
					}
					else
					{
						System.out.println("Enter correct 'name' (or) 'user ID'");
						cancel();
					}
			}
			available_seats_booked=0;
			}
		else if(s.equals("2"))
		{
			int total_seats=0;
			for(int i=1;i<=booked_tickets.size();i++)
			{
				try
				{
				if(booked_tickets.get(i).contains(name))
				{
					total_seats++;
				}
				}
				catch(Exception e)
				{
					i=i;
				}
			}
			for(int i=1;i<=confirmation.size();i++)
			{
				try
				{
				if(confirmation.get(i).contains(name))
				{
					total_seats++;
				}
				}
				catch(Exception e)
				{
					i=i;
				}
			}
			System.out.println("total_seats "+total_seats);
			System.out.println("\n Enter the number of seats to cancel: ");
			int urseat=sc.nextInt();
			System.out.println("1.coimbatore \n 2.Tirupur \n 3.Erode \n 4.salem \n 5.Chennai");
			System.out.println("Enter your 'From' city: ");
			int jn_1=sc.nextInt();
			System.out.println("Enter your 'To' city: ");
			int jn_2=sc.nextInt();
			Passengers_allocated_seat.get(ID).clear();
			ArrayList<Integer> allotseat=new ArrayList<>();
			int count=0;
			boolean forflag=false;
			int notcancel=0;
			for(int i=1;i<=booked_tickets.size();i++)
			{
				try
				{
				if(booked_tickets.get(i).contains(name) && booked_tickets.get(i).contains(junction.get(jn_1)) && booked_tickets.get(i).contains(junction.get(jn_2)))
				{
						try 
						{
							String chk_split[]=booked_tickets.get(i).split("-");
							if(name.equals(chk_split[0]))
							{
								int jn1=junction.indexOf(chk_split[1]);
								int jn2=junction.indexOf(chk_split[2]);
								for(int jn_b=jn1;jn_b<jn2;jn_b++)
								{
									if(jnc[jn_b][0]-1>0)
									{
										jnc[jn_b][0]--;
									}
									else
									{
										break;
									}
								}
								jnc[jn2][1]++;
								booked_tickets.put(i,null);
								notcancel++;
								count++;
								if(count==urseat)
								{
									forflag=true;
									break;
								}
							}
						}
						catch(Exception e)
						{
							i=i;
						}
				}
				else
				{
					allotseat.add(i);
				}
				}
				catch(Exception e)
				{
					i=i;
				}
			}
			if(forflag==false)
			{
			for(int i=1;i<=confirmation.size();i++)
			{
				try
				{
				if(confirmation.get(i).contains(name) && confirmation.get(i).contains(junction.get(jn_1)) && confirmation.get(i).contains(junction.get(jn_2)))
				{
					System.out.println("confirmed came");
						try 
						{
							String chk_split[]=confirmation.get(i).split("-");
							if(name.equals(chk_split[0]))
							{
								int jn1=junction.indexOf(chk_split[1]);
								int jn2=junction.indexOf(chk_split[2]);
								for(int jn_b=jn1;jn_b<jn2;jn_b++)
								{
									if(jnc[jn_b][0]-1>0)
									{
										jnc[jn_b][0]--;
									}
									else
									{
										break;
									}
								}
								jnc[jn2][1]++;
								confirmation.put(i,null);
								notcancel++;
								count++;
								if(count==urseat)
								{
									forflag=true;
									break;
								}
							}
						}
						catch(Exception e)
						{
							i=i;
						}
				}
				else
				{
					allotseat.add(i);
				}
				}
				catch(Exception e)
				{
					i=i;
				}
			}
			}
			if(forflag==false)
			{
			for(int i=1;i<=waiting_list.size();i++)
			{
				try
				{
				if(waiting_list.get(i).contains(name) && waiting_list.get(i).contains(junction.get(jn_1)) && waiting_list.get(i).contains(junction.get(jn_2)))
				{
						try 
						{
							String chk_split[]=waiting_list.get(i).split("-");
							if(name.equals(chk_split[0]))
							{
								int jn1=junction.indexOf(chk_split[1]);
								int jn2=junction.indexOf(chk_split[2]);
								for(int jn_b=jn1;jn_b<jn2;jn_b++)
								{
									if(jnc[jn_b][0]-1>0)
									{
										jnc[jn_b][0]--;
									}
									else
									{
										break;
									}
								}
								jnc[jn2][1]++;
								waiting_list.put(i,null);
								notcancel++;
								count++;
								if(count==urseat)
								{
									break;
								}
							}
						}
						catch(Exception e)
						{
							i=i;
						}
				}
				else
				{
					allotseat.add(i);
				}
				}
				catch(Exception e)
				{
					i=i;
				}
			}
			}
			if(notcancel!=0)
			{
			Passengers_allocated_seat.put(ID,allotseat);
			boolean flag=false;
			sc.nextLine();
			while(1==1)
			{
				System.out.println("Enter 'yes' to continue: ");
				if(sc.nextLine().equals("yes"))
				{
					for(int w=1;w<=waiting_list.size();w++)
					{
						try
						{
						String split[]=waiting_list.get(w).split("-");
						int jn1=junction.indexOf(split[1]);
						int jn2=junction.indexOf(split[2]);
						boolean fgcame=true;
						for(int jn=jn1;jn<jn2;jn++)
						{
							boolean fg=true;
							if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0)
							{
								jnc[jn][0]++;
								fg=false;
								System.out.println(Arrays.deepToString(jnc));
							}
							else
							{
								if(fg==false)
								{
								jnc[jn2][1]--;
								for(int jn_b=jn;jn_b>=jn1;jn_b--)
								{
									if(jnc[jn_b][0]-1>0)
									{
									jnc[jn_b][0]--;
									}
								}
								System.out.println(Arrays.deepToString(jnc));
								}
								break;
							}
						}
						for(int bk=1;bk<booked_tickets.size();bk++)
						{
							if(booked_tickets.get(bk)==null)
							{
								booked_tickets.put(bk, waiting_list.get(w));
								waiting_list.put(w, null);
								Passengers_allocated_seat.get(passengers_details.get(split[0])).add(bk);
								break;
							}
						}
						if((jnc[jn2][1]-1)>0)
						{
							jnc[jn2][1]--;
							System.out.println("junction->"+Arrays.deepToString(jnc));
						}
						else
						{
							System.out.println();
						}
						}
						catch(Exception e)
						{
							break;
						}
					}
					
					for(int w=1;w<=waiting_list.size();w++)
					{
						try
						{
						String split[]=waiting_list.get(w).split("-");
						int jn1=junction.indexOf(split[1]);
						int jn2=junction.indexOf(split[2]);
						boolean fgcame=true;
						for(int jn=jn1;jn<jn2;jn++)
						{
							boolean fg=true;
							if((jnc[jn][0]+1)<=10 && jnc[jn][0]+1>0)
							{
								jnc[jn][0]++;
								fg=true;
								System.out.println(Arrays.deepToString(jnc));
							}
							else
							{
								if(fg=true)
								{
								jnc[jn2][1]--;
								for(int jn_b=jn;jn_b>=jn1;jn_b--)
								{
									if(jnc[jn_b][0]-1>0)
									{
									jnc[jn_b][0]--;
									}
								}
								System.out.println(Arrays.deepToString(jnc));
								}
								break;
							}
						}
						for(int bk=1;bk<confirmation.size();bk++)
						{
							if(confirmation.get(bk)==null)
							{
								confirmation.put(bk, waiting_list.get(w));
								waiting_list.put(w, null);
								Passengers_allocated_seat.get(passengers_details.get(split[0])).add(bk);
								break;
							}
						}
						if((jnc[jn2][1]-1)>0)
						{
							jnc[jn2][1]--;
							System.out.println("junction->"+Arrays.deepToString(jnc));
						}
						else
						{
							System.out.println();
						}
						}
						catch(Exception e)
						{
							break;
						}
					}
					System.out.println("your tickets are cancelled....");
					System.out.println("booked->"+booked_tickets+"\n confirmed->"+confirmation);
					System.out.println("NOW WAITING LIST ARE ADDED TO BOOKED TICKETS....... \n");
					System.out.println("S.no               PASSENGER NAME          DESTINATION          ARRIVAL");
					System.out.println();
					int j=1;
					for(int a=1;a<=booked_tickets.size();a++)
					{
						if(booked_tickets.get(a)!=null)
						{
							String st[]=booked_tickets.get(a).split("-");
							System.out.println((j++)+".               "+st[0]+"          "+st[1]+"          "+st[2]);
						}
					}
					for(int a=1;a<=confirmation.size();a++)
					{
						if(confirmation.get(a)!=null)
						{
							String st[]=confirmation.get(a).split("-");
							System.out.println((j)+".               "+st[0]+"          "+st[1]+"          "+st[2]);
							j++;
						}
					}
					System.out.println();
					System.out.println("witing->"+waiting_list);
					System.out.println("S.no                WAITING LIST PASSENGERS          DESTINATION          ARRIVAL");
					for(int a=1;a<=waiting_list.size();a++)
					{
						if(waiting_list.get(a)!=null)
						{
							String st[]=waiting_list.get(a).split("-");
							System.out.println(a+".               "+st[0]+"          "+st[1]+"          "+st[2]);
						}
					}
					main(null);
					break;
					}
					else if(flag==true)
					{
						break;
					}
					else
					{
						System.out.println("Enter correctly...");
						continue;
					}
				}
			}
			else
			{
				System.out.println("no seats available...\n   try again");
			}
				}
			main(null);
		    break;
		}
		else
		{
			System.out.println("your username or password is wrong \n try again....");
			continue;
		}
		}
		
	}
}