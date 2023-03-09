import java.util.*;
public class bookmyshow {
	static Scanner sc=new Scanner(System.in);
	static ArrayList<String> Total_movies_list=new ArrayList<>(Arrays.asList("0","Vaathi","Bakasuran","Dada","Bagheera","Ariyavan","Kaduva","Ant-Man and the Wasp:QUANTUMANIA"));
	static String total_theatre_list[]= {"0","Sri Sakthi Cinemas Multiplex-8","Sakthi Cinemas-3","Sivan Cinemas-5","Usha Cinemas-4","Diamond Cinemas A/C 4K Dolby-2","Varanashi Multiplex-3","Revathi Theatre A/C 2K DOLBY-1","Srinivasa Cinemas(SAP) 4K Dolby-2","Shanthi cinemas-1",};
	static ArrayList<String> movies_list=new ArrayList<>();
	static ArrayList<String> theatre_list=new ArrayList<>();
	static HashMap<String,String> user_details=new HashMap<>();
	static HashMap<String,Integer> booked_details=new HashMap<>();
	static ArrayList<String> showtime=new ArrayList<>(Arrays.asList("10.30 AM","2.00 PM","6.00 PM","9.30 PM"));
	static HashMap<String,String[][]> movies_Screen_sepearation=new HashMap<>();
	static HashMap<String,int[][]> movies_seat_allocation=new HashMap<>();
	public static void main(String[] args) {
		System.out.println("\n ^^^^^^^^^^^^^^^^^^^^^^^^^^ SIGN UP / LOG IN  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ \n                           ******************                                     \n 1.SIGN IN \n 2.LOG IN\n 3.ADMIN");
		while(1!=0){
			int i=0;
			System.out.println(" Enter no: ");
			try
			{
			i=sc.nextInt();
			if(i==1)
			{
				ArrayList<String> passwords=new ArrayList<>(user_details.values());
				sc.nextLine();
				System.out.print(" Enter your name: ");
				String name=sc.nextLine();
				System.out.print(" Enter your password: ");
				String password=sc.nextLine();
				if(!user_details.containsKey(name) && !passwords.contains(password))
				{
					if(!name.equals(password))
					{
						user_details.put(name,password);
						System.out.println(" sign up Succesfully");
						System.out.println(" ____________________________________________________________________________________________________");
						main(null);
						break;
					}
					else
					{
						System.out.println("your name and password is same please change it..");
						main(null);
						break;
					}
				}
				else
				{
					System.out.println("user already exits");
					continue;
				}
			}
			else if(i==2)
			{
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("                                       LOG IN                                                ");
				System.out.println("                                      ********                                                ");
				while(1==1)
				{
					System.out.println("Go BACK to press 'b'");
					ArrayList<String> passwords=new ArrayList<>(user_details.values());
					sc.nextLine();
					System.out.print("Enter your name: ");
					String name=sc.nextLine();
					System.out.print("Enter your password: ");
					String password=sc.nextLine();
					if(user_details.containsKey(name) && passwords.contains(password))
					{
						user_details.put(name,password);
						homepage(name);
						break;
					}
					else if(name.equals("b") || password.equals("b"))
					{
						main(null);
					}
					else
					{
						System.out.println("your name/password is wrong");
						continue;
					}
				}
			}
			else if(i==3)
			{
				System.out.println("-----------------------------------------------------------------------------------------------------");
				while(1==1)
				{
					sc.nextLine();
					System.out.println("GO BACK to press 'Enter' button... \n Enter Admin password: ");
					String password=sc.nextLine();
					if(password.equals("bookmyshow321"))
					{
						admin();
						break;
					}
					if(password.equals(""))
					{
						main(null);
						break;
					}
					else
					{
						System.out.println(("your password is wrong try again...."));
						continue;
					}
				}
				
			}
			else
			{
				System.out.println("Enter number correctly...");
				sc.nextLine();
				continue;
			}
			break;
			}
			catch(Exception e)
			{
				System.out.println("Enter number in digit...");
				
			}
			sc.nextLine();
		}
		
	}
	private static void homepage(String name) {
		System.out.println("________________________________________*************************_____________________________________________");
		System.out.println("--------------------------------------...WELCOME TO BOOK MY SHOW...-------------------------------------------");
		System.out.println("________________________________________*************************_____________________________________________");
		while(1!=0)
		{
			try
			{
				System.out.println("\n MOVIES LIST");
				String customer=name+"-";
				boolean flag=false;
				for(int i=0;i<movies_list.size();i++)
				{
					System.out.println((i+1)+"."+movies_list.get(i));
				}
				System.out.print("\n Enter your favourite movie no. : ");
				int movie=sc.nextInt()-1;
				if(movie>=0 && movie<=movies_list.size())
				{
					customer+=movies_list.get(movie)+"-";
					System.out.println("***************************************"+movies_list.get(movie)+" BOOKING****************************************");
					System.out.println("                                       ^^^^^^^^^^^^^^^^^^^^^^^                                         ");
					while(1!=0)
					{
						System.out.println("\n 1.BOOK TICKETS\n 2.CANCEL\n 3.EXIT");
						try
						{
							System.out.print("\n Enter your choice no. :");
							int book_movie=sc.nextInt();
							if(book_movie==1)
							{
								System.out.println("***************************************"+movies_list.get(movie)+" BOOKING****************************************");
								System.out.println("                                       ^^^^^^^^^^^^^^^^^^^^^^^                                         \n");
								System.out.println("Select your favourite theatre\n");
								ArrayList<String> current_theatres_list=new ArrayList<>();
								ArrayList<String> current_theatres=new ArrayList<>(movies_Screen_sepearation.keySet());
								ArrayList<String> temp_booked=new ArrayList<>();
								int book_sno=1;
								int k1=1;
								try
								{
								for(int k=0;k<current_theatres.size();k++)
								{
								for(int i=0;i<movies_Screen_sepearation.get(current_theatres.get(k)).length;i++)
								{
									for(int j=0;j<movies_Screen_sepearation.get(current_theatres.get(k))[0].length;j++)
									{
										if(movies_Screen_sepearation.get(current_theatres.get(k))[i][j]!=null)
										{
										if(movies_Screen_sepearation.get(current_theatres.get(k))[i][j].equals(movies_list.get(movie)) && !current_theatres_list.contains(current_theatres.get(k)))
										{
											System.out.println((k1++)+". "+current_theatres.get(k));
											current_theatres_list.add(current_theatres.get(k));
										}
										}
									}
								}
								}
								}
								catch(Exception e)
								{
//									System.out.println("no pb");
								}
								if(current_theatres_list.size()==0)
								{
									System.out.println("no theatres found");
									homepage(name);
									break;
								}
//								for(int k=1;k<=current_theatres_list.size();k++)
//								{
//									System.out.println(k+". "+current_theatres_list.get(k-1));
//								}
								while(1==1)
								{
								System.out.println("\nEnter your favourite theatre no.\n");
								int book_theatre=sc.nextInt()-1;
								
								if(book_theatre>=0 && book_theatre<current_theatres_list.size())
								{
								try
								{
									System.out.println("Choose your convenient show time: \n");
									for(int i=0;i<movies_Screen_sepearation.get(current_theatres_list.get(book_theatre)).length;i++)
									{
										for(int j=0;j<movies_Screen_sepearation.get(current_theatres_list.get(book_theatre))[0].length;j++)
										{
											if(movies_Screen_sepearation.get(current_theatres.get(book_theatre))[i][j]!=null)
											{
											if(movies_Screen_sepearation.get(current_theatres_list.get(book_theatre))[i][j].equals(movies_list.get(movie)))
											{
												System.out.println((book_sno++)+". "+current_theatres_list.get(book_theatre)+"-"+showtime.get(j));
												temp_booked.add(current_theatres_list.get(book_theatre)+"-"+i+"-"+j);
											}
											}
										}
									}
								}
								catch(Exception e)
								{
//									System.out.println("no prblm");
								}
								
								System.out.println("\n Enter your show time");
								System.out.println("press '"+(book_sno++)+".' to  EXIT");
								while(1==1)
								{
									try
									{
										sc.nextLine();
										int show_time=sc.nextInt();
										if(show_time==book_sno)
										{
											homepage(name);
											break;
										}
										else if(show_time>=0 && show_time<=temp_booked.size())
										{
											customer+=temp_booked.get(show_time-1);
											String show_split[]=temp_booked.get(show_time-1).split("-");
											System.out.println("Available seats for movie "+movies_list.get(movie)+" in "+show_split[0]+" is "+movies_seat_allocation.get(show_split[0])[Integer.parseInt(show_split[1])][Integer.parseInt(show_split[2])]);
											System.out.println("how many seats you want to book: ");
											int ticket=sc.nextInt();
											int total_tickets=movies_seat_allocation.get(show_split[0])[Integer.parseInt(show_split[1])][Integer.parseInt(show_split[2])];
											if(total_tickets==0)
											{
												System.out.println("Tickets sold out...");
												homepage(name);
												break;
											}
											if(ticket>=0 && ticket<=total_tickets)
											{
												movies_seat_allocation.get(show_split[0])[Integer.parseInt(show_split[1])][Integer.parseInt(show_split[2])]=Math.abs(total_tickets-ticket);
//												customer+=ticket+"";
												if(booked_details.containsKey(customer))
												{
													String split[]=customer.split("-");
													customer=split[0]+"-"+split[1]+"-"+split[2]+"-"+split[3]+"-"+split[4];
													booked_details.put(customer, Math.abs(booked_details.get(customer)+ticket));
												}
												else
												{
													booked_details.put(customer, ticket);
												}
												System.out.println(booked_details);
												System.out.println("your seats are booked succesfully....");
												String split[]=customer.split("-");
												System.out.println(split[0]+" - "+split[1]+" - theatre ("+split[2]+")- Screen ("+(Integer.parseInt(split[3])+1)+") - show time ("+showtime.get(Integer.parseInt(split[4]))+")- total booked seats - "+booked_details.get(customer));
												homepage(name);
												break;
											}
										}
									}
									catch(Exception e)
									{
										System.out.println("Enter number correctly");
									}
								}
								break;
								}
								else
								{
									System.out.println("Enter correct number...");
								}
								}
							}
							else if(book_movie==2)
							{
								System.out.println("***************************************"+movies_list.get(movie)+" CANCEL****************************************");
								System.out.println("                                       ^^^^^^^^^^^^^^^^^^^^^^^                                         \n");
								System.out.println("Select your booked theatre\n");
								ArrayList<String> select_booked=new ArrayList<>(booked_details.keySet());
								ArrayList<String> cancel_select=new ArrayList<>();
								System.out.println(" your total booked seats for cancel");
								int can=1;
								for(int i=0;i<booked_details.size();i++)
								{
									if(select_booked.get(i).contains(name))
									{
										String split[]=select_booked.get(i).split("-");
										System.out.println((can++)+". "+split[0]+" - "+split[1]+" - theatre ("+split[2]+")- Screen ("+(Integer.parseInt(split[3])+1)+") - show time ("+showtime.get(Integer.parseInt(split[4]))+")- total booked seats - "+booked_details.get(select_booked.get(i)));
										cancel_select.add(select_booked.get(i));
									}
								}
								System.out.println("Enter your cancel number: ");
								int cancel_movie=sc.nextInt();
								String split[]=cancel_select.get(cancel_movie-1).split("-");
								System.out.println("total number of seats you booked : "+booked_details.get(cancel_select.get(cancel_movie-1)));
								while(1==1)
								{
								System.out.println("Enter number of tickets to cancel: ");
								int cancel_1=sc.nextInt();
								System.out.println("totalseats=>"+booked_details.get(cancel_select.get(cancel_movie-1)));
								if(cancel_1>0 && cancel_1<=booked_details.get(cancel_select.get(cancel_movie-1)))
								{
								int total_tickets=movies_seat_allocation.get(split[2])[Integer.parseInt(split[3])][Integer.parseInt(split[4])];
								movies_seat_allocation.get(split[2])[Integer.parseInt(split[3])][Integer.parseInt(split[4])]=Math.abs(total_tickets+cancel_1);
								booked_details.put(cancel_select.get(cancel_movie-1), booked_details.get(cancel_select.get(cancel_movie-1))-cancel_1);
								System.out.println("Tickets cancelled successfully");
								System.out.println(booked_details);
								homepage(name);
								break;
								}
								else
								{
									System.out.println("Enter correct number");
								}
								}
							}
							else if(book_movie==3)
							{
								break;
							}
						}
						catch(Exception e)
						{
							System.out.println("Enter number correctly...");
							continue;
						}
						break;
					}
					
				}
			}
			catch(Exception e)
			{
				System.out.println("Enter number correctly...");
				sc.nextLine();
			}
			break;
		}
		
	}
	private static void admin(){
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                                     ADMIN page                                              ");
		System.out.println("                                    ************                                             ");
		while(1!=0)
		{
			System.out.println("\n ADD CHANGES \n\n 1.Theatres \n 2.movies \n 3.change show time and film in theatre");
			System.out.println("\n GO BACK to press 'Enter' button");
				System.out.print(" Enter no:");
				String i=sc.nextLine();
				if(i.equals("1"))
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("                                     THEATRES                                              ");
					System.out.println("                                    ************                                             ");
					
					while(1!=0)
					{
							System.out.println("\n 1.Add Theatre\n 2.remove Theatre\n\n GO BACK to press 'Enter' button");
							System.out.println("Enter no:");
							String j=sc.nextLine();
							if(j.equals("1"))
							{
								System.out.println("----------------------------------------------------------------------------------------------");
								System.out.println("                                     ADD THEATRE                                              ");
								while(1!=0)
								{
									System.out.println("THEATRE LIST\n");
									for(int T=1;T<total_theatre_list.length;T++)
									{
										System.out.println(T+"."+total_theatre_list[T]);
									}
									try
									{
										System.out.println("\n\n Press '0' and Enter to GO BACK: ");
										System.out.print("Enter Theatre no: ");
										int theatre=sc.nextInt();
										if(0==theatre)
										{
											break;
										}
										else if(theatre>0 && theatre<=total_theatre_list.length)
										{
											String screensplit[]=total_theatre_list[theatre].split("-");
											if(!theatre_list.contains(screensplit[0]) )
											{
											theatre_list.add(screensplit[0]);
												String scrnput[][]=new String[Integer.parseInt(screensplit[1])][showtime.size()];
												movies_Screen_sepearation.put(screensplit[0], scrnput );
												movies_seat_allocation.put(screensplit[0], null);
												System.out.println("\n screens added successfully...\n\n continue to press 'Enter' button\n GO BACK to type 'back' and Enter ");
												
												if(sc.nextLine().equals("back"))
												{
													break;
												}
												else
												{
													sc.nextLine();
													continue;
												}
											}
											else
											{
												System.out.println("\n -------theatre already exists-------\n try-again.....");
											}
										}
										else
										{
											System.out.println("Enter number correctly....\n");
											continue;
										}
									}
									catch(Exception e)
									{
										System.out.println("Enter number correctly....\n");
									}
									sc.nextLine();
								}
							}
							else if(j.equals("2"))
							{
								System.out.println("----------------------------------------------------------------------------------------------");
								System.out.println("                                    REMOVE THEATRE                                            ");
								System.out.println("CURRENT THEATRE LIST\n");
								while(1!=0)
								{
									for(int T=1;T<=theatre_list.size();T++)
									{
										System.out.println(T+"."+theatre_list.get(T-1));
									}
									try
									{
										System.out.println("Press '0' and Enter to GO BACK: ");
										System.out.println("Choose the theatre no. to remove:");
										int rem=sc.nextInt();
										String rem_theatre=theatre_list.get(rem-1);
										if(rem==0)
										{
											break;
										}
										if(rem>0 && rem<theatre_list.size()) 
										{
											movies_Screen_sepearation.remove(rem_theatre);
											movies_seat_allocation.remove(rem_theatre);
											theatre_list.remove(rem-1);
											System.out.println(rem_theatre+" removed successfully");
											System.out.println(movies_Screen_sepearation);
											System.out.println("Continue to remove enter 'yes' else press 'Enter' button ");
											if(sc.nextLine().equals("yes"))
											{
												continue;
											}
											else
											{
												break;
											}
										}
										else
										{
											System.out.println("Enter number correctly...\n");
											continue;
										}
									}
									catch(Exception e)
									{
										System.out.println("Enter number correctly in digit...\n");
									}
									sc.nextLine();
								}
							}
							else if(j.equals(""))
							{
								admin();
								break;
							}
							else
							{
								System.out.println("Enter number correctly....\n");
								continue;
							}
					}
				}
				else if(i.equals("2"))
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("                                     MOVIES                                              ");
					System.out.println("                                    ************                                             ");
					
					System.out.println("\n 1.Add movie\n 2.remove movie");
					while(1!=0)
					{
						try
						{
							System.out.println("\n\n Press '0' and Enter to GO BACK: ");
							System.out.println("Enter no:");
							int j=sc.nextInt();
							if(j==0)
							{
								break;
							}
							else if(j==1)
							{
								System.out.println("----------------------------------------------------------------------------------------------");
								System.out.println("                                    ADD MOVIES                                            ");
								System.out.println("TOTAL CURRENT MOVIES LIST");
							    for(int c=1;c<Total_movies_list.size();c++)
							    {
							    	System.out.println(c+"."+Total_movies_list.get(c));
							    }
							    while(1!=0)
								{
									try
									{
										System.out.println("Press '0' and Enter to GO BACK: ");
										System.out.println("Enter movie no: ");
										int movie=sc.nextInt();
										if(0==movie)
										{
											break;
										}
										else if(movie>0 && movie<=Total_movies_list.size())
										{
											if(!movies_list.contains(Total_movies_list.get(movie))){
											movies_list.add(Total_movies_list.get(movie));
											}
											else
											{
												System.out.println("movie already exists...");
											}
										}
										else
										{
											System.out.println("Enter number correctly....\n");
											continue;
										}
									}
									catch(Exception e)
									{
										System.out.println("Enter number correctly....\n");
									}
									sc.nextLine();
								}
							    System.out.println(movies_list);
							}
							else if(j==2)
							{
								System.out.println("----------------------------------------------------------------------------------------------");
								System.out.println("                                    REMOVE MOVIES                                            ");
								System.out.println("CURRENT MOVIES LIST");
							    for(int c=1;c<=movies_list.size();c++)
							    {
							    	System.out.println(c+"."+movies_list.get(c-1));
							    }
							    while(1!=0)
								{
									try
									{
										System.out.println("Press '0' and Enter to GO BACK: ");
										System.out.println("Enter movie no: ");
										int movie=sc.nextInt();
										if(0==movie)
										{
											break;
										}
										else if(movie>0 && movie<=Total_movies_list.size())
										{
											movies_list.remove(Total_movies_list.get(movie));
										}
										else
										{
											System.out.println("Enter number correctly....\n");
											continue;
										}
									}
									catch(Exception e)
									{
										System.out.println("Enter number correctly....\n");
									}
									sc.nextLine();
								}
							    System.out.println(movies_list);
							}
							else
							{
								System.out.println("Enter number correctly....\n");
								continue;
							}
						}
						catch(Exception e)
						{
							System.out.println("Enter number in Digits....\n");
						}
						sc.nextLine();
					}
				}
				else if(i.equals("3"))
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					System.out.println("                     SHOW TIME AND FILM CHANGES IN THEATRE                                   ");
					System.out.println("                     *************************************                                   ");
					System.out.println("CHOOSE THEATRE TO CHANGE/ADD THE SHOW TIME AND FILM\n");
					ArrayList<String> theatres=new ArrayList<>(movies_Screen_sepearation.keySet());
					boolean flg=false;
					while(1!=0)
					{
						try
						{
							for(int T=1;T<=theatre_list.size();T++)
							{
								System.out.println(T+"."+theatre_list.get(T-1));
							}
							System.out.println("\n Press '"+(theatre_list.size()+1)+"' and Enter to GO BACK----else press 'Enter' button: ");
							System.out.println("Enter no: ");
							int T_list=sc.nextInt();
							if(T_list==(theatre_list.size()+1))
							{
								break;
							}
							String showfilm[][]=new String[movies_Screen_sepearation.get(theatre_list.get(T_list-1)).length][showtime.size()];
							int showbooking[][]=new int[movies_Screen_sepearation.get(theatre_list.get(T_list-1)).length][showtime.size()];
//							String showfilm[][]=movies_Screen_sepearation.get(theatre_list.get(T_list-1));
//							movies_Screen_sepearation.put(theatre_list.get(Integer.parseInt(screensplit[0])), scrnput );
//							movies_seat_allocation.put(theatre_list.get(Integer.parseInt(screensplit[0])), scrnput);
							System.out.println("select a showtime and movie\n");
							for(int scr=0;scr<movies_Screen_sepearation.get(theatre_list.get(T_list-1)).length;scr++)
							{
								boolean flag=true;
							for(int time=0;time<showtime.size();time++)
							{
								System.out.println("MOVIES LIST");
							    for(int c=1;c<=movies_list.size();c++)
							    {
							    	System.out.println(c+". "+movies_list.get(c-1));
							    }
							    System.out.println("Enter '-1' to go back");
							    System.out.println("\n "+(time+1)+". "+showtime.get(time)+" =");
							    if(showfilm[scr][time]==null)
							    {
							    int select=sc.nextInt();
							    if(select>0 && select<=movies_list.size() && showfilm[scr][time]==null)
							    {
							    showfilm[scr][time]=movies_list.get(select-1);
							    showbooking[scr][time]=10;
							    }
							    else if(select==-1)
							    {
							    	flag=false;
							    	break;
							    }
							    else 
							    {
							    	System.out.println("Select correct movie no. :");
							    }
							    }
							    else
							    {
							    	System.out.println("movies already selected you want to change Enter 'yes' to continue...");
							    	if(sc.nextLine().equals("yes"))
							    	{
							    		int select=sc.nextInt();
									    if(select>0 && select<=movies_list.size() && showfilm[scr][time]==null)
									    {
									    showfilm[scr][time]=movies_list.get(select-1);
									    showbooking[scr][time]=10;
									    }
									    else if(select==-1)
									    {
									    	flag=false;
									    	break;
									    }
									    else 
									    {
									    	System.out.println("Select correct movie no. :");
									    }
							    	}
							    }
							}
							if(flag==false)
							{
								break;
							}
							}
							movies_Screen_sepearation.put(theatre_list.get(T_list-1), showfilm);
							movies_seat_allocation.put(theatre_list.get(T_list-1), showbooking);
//							for(int th=0;th<theatres.size();th++)
//							{
//								System.out.println("movies_Screen_sepearation->"+Arrays.deepToString(movies_Screen_sepearation.get(theatres.get(th))));
//								System.out.println("movies_seat_allocation->"+Arrays.deepToString(movies_seat_allocation.get(theatres.get(th))));
//							}
							System.out.println("\n Press '0' and Enter to GO BACK----else press 'Enter' button: ");
							System.out.println("Enter no: ");
							if(sc.nextLine().equals("0"))
							{
								flg=true;
								break;
							}
						}
						catch(Exception e)
						{
							System.out.println("Enter number correctly in digit...\n");
						}
						if(flg==true)
						{
							break;
						}
					}
//					for(String scr:movies_Screen_sepearation.keySet())
//					{
//						System.out.println(scr+"->"+Arrays.deepToString(movies_Screen_sepearation.get(scr)));
//					}
				}
				else if(i.equals(""))
				{
					main(null);
					break;
				}
			sc.nextLine();
		}
	}

}
