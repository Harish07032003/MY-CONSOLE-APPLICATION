import java.util.*;
public class splitwise {
	static Scanner sc=new Scanner(System.in);
	static HashMap<String,String> user_details=new HashMap<>();
	static String place[]= {"0","Hotel","Taxi","Mall","Shopping","Cinema"};
	static HashMap<String,ArrayList<Integer>> split=new HashMap<>();
	public static void main(String[] args) {
		System.out.println("\n WELCOME TO SPLIT APP");
		System.out.println(" 1. SIGN IN \n 2. LOG IN");
		String n=sc.nextLine();
		if(n.equals("1"))
		{
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("                                       SIGN IN                                                ");
			while(1!=0)
			{
				
				System.out.print("\nEnter your name: ");
				String name=sc.nextLine();
				System.out.print("Enter your password: ");
				String password=sc.nextLine();
//				if(("ABCDEFGHIJKLMNOPQRSTUVWXYZbcdefghijklmnopqrstuvwxyz").contains(name) && ("ABCDEFGHIJKLMNOPQRSTUVWXYZbcdefghijklmnopqrstuvwxyz1234567890@#$%^&*").contains(password))
//				{
				if(!name.equals("")&&!password.equals(""))
				{
					if(!user_details.containsKey(name))
					{
						user_details.put(name, password);
						split.put(name,new ArrayList<>(Arrays.asList(0,0)));
						System.out.println("Sign in successfully.....");
						System.out.println("----------------------------------------------------------------------------------------------");
						main(null);
						break;
					}
					else
					{
						System.out.println("username already exists");
						continue;
					}
				}
				else
				{
					System.out.println("Invalid inputs");
					continue;
				}
//				}
//				else
//				{
//					System.out.println("Enter your password with \n1- Uppercase \n1-lowercase \n1-special charctern\n1-numeric digit");
//				}
			}
		}
		else if(n.equals("2"))
		{
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("                                          LOG IN                                             ");
			while(1!=0)
			{
				ArrayList<String> names=new ArrayList<>(user_details.keySet());
				ArrayList<String> passwords=new ArrayList<>(user_details.values());
				System.out.println("Press 3. to EXIT");
				System.out.print("\nEnter your name: ");
				String name=sc.nextLine();
				System.out.print("Enter your password: ");
				String password=sc.nextLine();
				if(names.contains(name) && passwords.contains(password))
				{
					System.out.println("Logged in successfully.....");	
					System.out.println("----------------------------------------------------------------------------------------------");
					adduser();
					break;
				}
				else if(names.equals("3") || password.equals("3"))
				{
					System.out.println("----------------------------------------------------------------------------------------------");
					main(null);
					break;
				}
				else
				{
					System.out.println("your username or password is wrong \ntryagain...");
					continue;
				}
			}
		}
	}
	private static void adduser() {
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("                                     ADD MY FRIENDS                                           ");
		while(1!=0)
		{
			System.out.print("\n Enter your friend  name: ");
			String name=sc.nextLine();
			System.out.print("set a password: ");
			String password=sc.nextLine();
//			if(("ABCDEFGHIJKLMNOPQRSTUVWXYZbcdefghijklmnopqrstuvwxyz").contains(name) && ("ABCDEFGHIJKLMNOPQRSTUVWXYZbcdefghijklmnopqrstuvwxyz1234567890@#$%^&*").contains(password))
//			{
			if(!name.equals("")&&!password.equals(""))
			{
				if(!user_details.containsKey(name))
				{
					user_details.put(name, password);
					split.put(name, new ArrayList<>(Arrays.asList(0,0)));
					ArrayList<String> names=new ArrayList<>(split.keySet());
					ArrayList<Integer> update=new ArrayList<>();
					for(int i=0;i<names.size();i++)
					{
						update=split.get(names.get(i));
						if(update.size()<split.size()+1)
						{
							for(int j=update.size();j<=split.size()+1;j++)
							{
								update.add(0);
							}
						}
						else
						{
							for(int j=update.size();j<=split.size()+1;j++)
							{
								update.add(0);
							}
						}
					}
					System.out.println(" Add my friends successfully.....");
					System.out.println("\n Add another friend Enter 'yes' to continue otherwise press 'Enter' button: ");
					if(sc.nextLine().equals("yes"))
					{
						continue;
					}
					else
					{
						System.out.println("----------------------------------------------------------------------------------------------");
						split();
						break;
					}
				}
				else
				{
					System.out.println("username already exists");
					continue;
				}
			}
			else
			{
				System.out.println("Invalid inputs");
				continue;
			}
//			}
		}
	}
	private static void split() {
		ArrayList<String> split_names=new ArrayList<>(split.keySet());
		ArrayList<Integer> split_the_amount=new ArrayList<>();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("                            SPLIT WITH YOUR FRIENDS                                           ");
		System.out.println("press 3. to go back otherwise----- press 'Enter' button to continue..");
		if(sc.nextLine().equals("3"))
		{
			main(null);
		}
//		System.out.println(split);
		System.out.println("\n choose your place: \n 1.Hotel\n 2.Taxi\n 3.Mall\n 4.shopping\n 5.cinema\n Enter your option: ");
		String puchase_place=sc.nextLine();
		System.out.print("Enter the Estimated amount for "+place[Integer.parseInt(puchase_place)]+"=");
		int estimated_amount=sc.nextInt();
		for(int i=0;i<split_names.size();i++)
		{
			System.out.println((i+1)+"."+split_names.get(i));
		}
		System.out.println("how many persons are there: \n Enter the number seperated by commas','....");
		sc.nextLine();
		String spl=sc.nextLine();
		String split_arr[]=spl.split(",");
		ArrayList<String> particular_persons=new ArrayList<>();
		for(int i=0;i<split_arr.length;i++)
		{
			particular_persons.add(split_names.get(Integer.parseInt(split_arr[i])-1));
			System.out.println((i+1)+". "+split_names.get(Integer.parseInt(split_arr[i])-1));
		}
		System.out.println(particular_persons);
		int person_no=0;
		while(1!=0)
		{
			System.out.println("\nEnter the person number who will pay? :");
			try
			{
				person_no=sc.nextInt();
				break;
			}
			catch(Exception e)
			{
				System.out.println("Enter your number correctly...");
			}
		}
		int splited_amt=(estimated_amount/particular_persons.size());
		split_the_amount=split.get(particular_persons.get(person_no-1));
		System.out.println(particular_persons.get(person_no-1)+","+split_the_amount);
		for(int i=0;i<split_the_amount.size();i++)
		{
			if(i==0)
			{
				System.out.println(split_the_amount.get(i)+","+estimated_amount+","+splited_amt+"="+((split_the_amount.get(i)-estimated_amount)+splited_amt));
				split_the_amount.set(i, ((split_the_amount.get(i)-estimated_amount)+splited_amt));
				System.out.println(split_the_amount);
			}
			else if(i==1)
			{
				split_the_amount.set(i, 1);
			}
			if(spl.contains((i+2)+"")&& !((i+2)+"").equals((person_no)+""))
			{
				split_the_amount.set(i, split_the_amount.get(i)+splited_amt);
			}
		}
		System.out.println(split_the_amount);
		split.put(particular_persons.get(person_no-1), split_the_amount);
		System.out.println(split);
		for(int j=0;j<particular_persons.size();j++)
		{
			if(!particular_persons.get(j).equals(particular_persons.get(person_no-1)))
			{
				split_the_amount=split.get(particular_persons.get(j));
				split_the_amount.set(0, (split_the_amount.get(0)+splited_amt));
				split_the_amount.set(1, 0);
			}
		}
		System.out.println(split);
		while(1==1)
		{
			System.out.println("continue your purchase Enter 'yes' otherwise skip");
			if(sc.nextLine().equals("yes"))
			{
				split();
				break;
			}
			else
			{
				split();
				break;
			}
		}
	}

}
