import java.util.*;
public class atm {
	static Scanner sc=new Scanner(System.in);
	static String Adminpass="cdc123";
	public static int Total_balance=0;
	static HashMap<Integer,Integer> map=new HashMap<>();
	static HashMap<String,String> users=new HashMap<>();
	static HashMap<String,String> mini_statement=new HashMap<>();
	static ArrayList<String> arr=new ArrayList<>();
	static ArrayList<String> pass=new ArrayList<>();
	static HashMap<Integer,Integer> usermap=new HashMap<>();
	public static void main(String[] args) {
		System.out.println();
			System.out.println("WELCOME TO MY ATM");
			System.out.println();
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("3.Add User");
			System.out.print("Enter no: ");
			int i=0;
			while(i==0)
			{
			int l=sc.nextInt();
			if(l==1)
			{
				Admin();
			}
			else if(l==2)
			{
				user();
			}
			else if(l==3)
			{
				sc.nextLine();
				add_user();
			}
			else
			{
				System.out.println("Enter correct no: ");
			}
			}
	}
	private static void add_user() {
		System.out.println();
		System.out.print("Enter username: ");
		String username=sc.nextLine();
		System.out.print("Enter password: ");
		String password=sc.nextLine();
		if(!users.containsKey(username))
		{
			users.put(username,password);
			mini_statement.put(username, "");
			user_update();
		}
		else
		{
			System.out.println("username already exists");
		}
		int i=0;
		while(i==0)
		{
			System.out.println("Add another user Enter 'yes': ");
			System.out.println("         (or)");
			System.out.println("Press 3.EXIT:");
			String st=sc.nextLine();
			if(st.equals("yes"))
			{
				add_user();
				break;
			}
			else if(st.equals("3"))
			{
				main(null);
				break;
			}
			else
			{
				System.out.println("Enter correctly....");
			}
		}
	}
	private static void user_update() {
		arr=new ArrayList<>(users.keySet());
		pass=new ArrayList<>(users.values());
	}
	private static void user() {
		System.out.println();
		for(int i=0;i<arr.size();i++)
		{
			System.out.println("User "+(i+1)+"="+arr.get(i));
		}
		System.out.println("Press 4.EXIT");
		System.out.print("Enter the user no: ");
		int a=sc.nextInt();
		if(a==4)
		{
			main(null);
		}
		String userID="";
		String userpass="";
		sc.nextLine();
		int i=0;
		while(i==0)
		{
				System.out.println("press 3.EXIT");
				System.out.print("Enter user ID: ");
				userID=sc.nextLine();
				if(userID.equals("3"))
				{
					user();
					break;
				}
				System.out.print("Enter user password: ");
				userpass=sc.nextLine();
				if(userpass.equals("3"))
				{
					user();
					break;
				}
				try {
				if((userID.equals(arr.get(a-1)) && userpass.equals(pass.get(a-1))&& a==1)||(userID.equals(arr.get(a-1)) && userpass.equals(pass.get(a-1))&& a==2)||(userID.equals(arr.get(a-1)) && userpass.equals(pass.get(a-1))&& a==3))
				{
					homepage(a);
					break;
				}
				else
				{
					System.out.println("Your userID or Password is wrong");
					continue;
				}
				}
				catch (Exception e)
				{
					System.out.println("Enter correct password or username");
					continue;
				}
		}
	}
	private static void homepage(int a) {
		System.out.println();
		System.out.println();
		System.out.println("Welcome to MY ATM "+arr.get(a-1));
		System.out.println();
		System.out.println("Press 1.Cash Withdrawal");
		System.out.println("Press 2.Cash Deposit");
		System.out.println("Press 3.Balance");
		System.out.println("Press 4.Mini statement");
		System.out.println("Press 5.Money transfer");
		System.out.println("Press 6.pin change");
		System.out.println("Press 7:EXIT");
		System.out.print("Enter a no: ");
		int j=0;
		while(j==0)
		{
			int k=sc.nextInt();
			if(k==7)
			{
				user();
				break;
			}
			if(k==1)
			{
				withdrawal(a);
				break;
			}
			else if(k==2)
			{
				deposit(a);
				homepage(a);
				break;
			}
			else if(k==3)
			{
				balance(a);
				break;
			}
			else if(k==4)
			{
				mini_statement(a);
				break;
			}
			else if(k==5)
			{
				money_transfer(a);
				break;
			}
			else if(k==6)
			{
				pinchange(a);
				break;
			}
			else
			{
				System.out.println("Enter correct no. ");
				continue;
			}
		}
	}
	private static void pinchange(int a) {
		sc.nextLine();
		System.out.println("Enter your new pin: ");
		String password=sc.nextLine();
		users.put(arr.get(a-1),password);
		System.out.println("Pin changed successfully....");
		user_update();
		System.out.println("Press 3.EXIT");
		int i=0;
		while(i==0)
		{
			if(sc.nextInt()==3)
			{
				homepage(a);
				break;
			}
		}
	}
	private static void money_transfer(int a) {
		System.out.println();
		usermap.put(a,usermap.getOrDefault(a,5000));
		System.out.println("Press 3.EXIT: ");
		System.out.print("Enter Username: ");
		sc.nextLine();
		int i=0;
		while(i==0)
		{
		String user_n=sc.nextLine();
		if(user_n.equals("3"))
		{
			homepage(a);
			break;
		}
		if(arr.contains(user_n))
		{
			if(user_n.equals(arr.get(a-1)))
			{
				System.out.println("username equals to your ID");
				continue;
			}
			int amount=0;
			while(i==0)
			{
				System.out.println("Enter the transfer amount: ");
				amount=sc.nextInt();
				usermap.put(arr.indexOf(user_n)+1,usermap.getOrDefault(arr.indexOf(user_n)+1,5000));
				if(amount<=usermap.get(a))
				{
				usermap.put(arr.indexOf(user_n)+1,usermap.get(arr.indexOf(user_n)+1)+amount);
				usermap.put(a, usermap.get(a)-amount);
				String user1="you transfer the amount Rs "+amount+" to "+arr.get(arr.indexOf(user_n))+" now your available balance is "+usermap.get(a);
				String user2="you recieved the amount Rs "+amount+" from "+arr.get(a-1)+" now your available balance is "+usermap.get(arr.indexOf(user_n)+1);
				mini_statement.put(arr.get(a-1), mini_statement.get(arr.get(a-1))+"\n"+user1);
				mini_statement.put(arr.get(arr.indexOf(user_n)), mini_statement.get(arr.get(arr.indexOf(user_n)))+"\n"+user2);
					System.out.println("Amount transfered succesfully");
					homepage(a);
					break;
				}
				else
				{
					System.out.println("your account has declined not sufficient amount in your account");
				}
			}
		}
		else
		{
			System.out.println();
			System.out.println("Username does not EXists");
			System.out.print("Re-Enter the username: ");
		}
		}
	}
	private static void mini_statement(int a) {
		System.out.println();
		System.out.println(arr.get(a-1)+" mini Statement for Last 5 transactions");
		System.out.println();
		String mini=mini_statement.get(arr.get(a-1));
		int last=mini.length();
		int count=0;
		String mini_state="";
		for(int i=mini.length()-1;i>=0;i--)
		{
			if((mini.charAt(i)+"").equals("\n"))
			{
				count++;
				mini_state=mini.substring(i,last)+"\n"+mini_state;
				last=i;
			}
			if(count==5)
			{
				break;
			}
		}
		System.out.println(mini_state);
		homepage(a);
	}
	private static void balance(int a) {
		System.out.println();
		usermap.put(a,usermap.getOrDefault(a,5000));
		System.out.println("your balance is: Rs "+usermap.get(a));
		System.out.println("Press 3.EXIT");
		int i=0;
		while(i==0)
		{
			if(sc.nextInt()==3)
			{
				homepage(a);
				break;
			}
		}
		
	}
	private static void deposit(int a) {
		System.out.println();
		map.put(2000,map.getOrDefault(2000,0));
		map.put(500,map.getOrDefault(500,0));
		map.put(200,map.getOrDefault(200,0));
		map.put(100,map.getOrDefault(100,0));
		System.out.println("Enter a amount for deposit: ");
		System.out.println("Press 3.EXIT");
		int dep=sc.nextInt();
		if(dep==3)
		{
			homepage(a);
		}
		int j=0;
		if(dep<=50000)
		{
			while(j==0)
			{
				int check=0;
				if(dep%100==0 && dep>0)
				{
					System.out.print("no.of 2000= ");
					int addamt1=sc.nextInt();
					check+=addamt1*2000;
					System.out.print("no.of 500= ");
					int addamt2=sc.nextInt();
					check+=addamt2*500;
					System.out.print("no.of 200= ");
					int addamt3=sc.nextInt();
					check+= addamt3*200;
					System.out.print("no.of 100= ");
					int addamt4=sc.nextInt();
					check+=addamt4*100;
					System.out.println(map);
					if(addamt1+map.get(2000)<=10 && addamt2+map.get(500)<=20 && addamt3+map.get(200)<=50 && addamt4+map.get(100)<=100)
					{
						if(check==dep)
						{
						map.put(2000,addamt1+map.get(2000));
						map.put(500,addamt2+map.get(500));
						map.put(200,addamt3+map.get(200));
						map.put(100,addamt4+map.get(100));
						ATM_total_amount(map);
						usermap.put(a,usermap.getOrDefault(a,5000));
						usermap.put(a,usermap.get(a)+dep);
						String mini="you deposited amount Rs"+dep+" and your available balance is Rs "+usermap.get(a);
						mini_statement.put(arr.get(a-1), mini_statement.get(arr.get(a-1))+"\n"+mini);
						System.out.println("Amount added successfully in your account");
						break;
						}
						else
						{
							System.out.println("your total Amount should not equals to your given rupees");
							System.out.println();
							continue;
						}
					}
					else
					{
						System.out.println("your amount exceeds limit");
						deposit(a);
						System.out.println();
						break;
					}
				}
				else {
					System.out.println("Amount doesn't multipes of 100 x....Enter correctly");
					deposit(a);
					System.out.println();
					break;
				}
			}
		}
		System.out.println("Press 3.EXIT");
		int k=0;
		while(k==0)
		{
			if(sc.nextInt()==3)
			{
				homepage(a);
				break;
			}
		}
		
	}
	private static void withdrawal(int a) {
		System.out.println();
		usermap.put(a,usermap.getOrDefault(a,5000));
		int i=0;
		while(i==0)
		{
			System.out.println("Press 3.EXIT");
			System.out.print("Enter a withdrawal amount:");
			int amt=sc.nextInt();
			if(amt==3)
			{
				homepage(a);
			}
			if(amt%100==0)
			{
				if(amt>usermap.get(a))
				{
					System.out.println("Your entered amount greater than in your account");
					withdrawal(a);
				}
			if(amt<=usermap.get(a))
			{
				if(amt<=Total_balance)
				{
					userwithdrwal_inATM(amt,a);
				}
				else
				{
					System.out.println("MY ATM has not sufficient amount please enter less than "+Total_balance);
					System.out.println("Press 3.EXIT");
					if(sc.nextLine().equals("3"))
					{
						homepage(a);
					}
					else
					{
						continue;
					}
				}
			}
			else
			{
				System.out.println("insufficient denomination");
				System.out.println();
				if(sc.nextLine().equals("3"))
				{
					user();
				}
				else
				{
					continue;
				}
			}
			}
			else
			{
				System.out.println("Enter the amount multiples of 100 x (or) greater than Rs 100");
				System.out.println();
				continue;
			}
		}
	}
	private static void userwithdrwal_inATM(int amt,int a) {
		int chk=0;
		System.out.println();
		usermap.put(a,usermap.getOrDefault(a,5000));
		if(amt>Total_balance)
		{
			withdrawal(a);
		}
		else
		{
		while(amt!=chk)
		{
				if(map.get(2000)!=0 && chk+2000<=amt)
				{
					chk+=2000;
					map.put(2000,map.get(2000)-1);
					System.out.println("2000->"+map);
				}
				else if(map.get(500)!=0 && chk+500<=amt)
				{
					chk+=500;
					map.put(500,map.get(500)-1);
					System.out.println("500->"+map);
				}
				else if(map.get(200)!=0 && chk+200<=amt)
				{
					chk+=200;
					map.put(200,map.get(200)-1);
					System.out.println("200->"+map);
				}
				else if(map.get(100)!=0 && chk+100<=amt)
				{
					chk+=100;
					map.put(100,map.get(100)-1);
					System.out.println("100->"+map);
				}
				else
				{
					System.out.println(map);
					System.out.println("your cash amount doesn't exceeded");
					withdrawal(a);
				}
		}
		}
		if(amt==chk)
		{
			System.out.println("your amount successfully withdrawal");
			usermap.put(a,usermap.get(a)-amt);
			String withdraw="your withdrawal amount Rs "+amt+" and your available balance is Rs "+usermap.get(a);
			mini_statement.put(arr.get(a-1), mini_statement.get(arr.get(a-1))+"\n"+withdraw);
		}
		else
		{
			System.out.println("your cash amount doesn't exceeded");
		}
		int i=0;
		System.out.println("Press 3.EXIT");
		
		while(i==0)
		{
		int k=sc.nextInt();
		if(k==3)
		{
			homepage(a);
			break;
		}
		else
		{
			System.out.println("Enter correct no: ");
			continue;
		}
		}
		
	}
	private static void Admin() {
		System.out.println();
		sc.nextLine();
		int i=0;
		while(i==0)
		{
			System.out.print("Enter your password: ");
			if(sc.nextLine().equals(Adminpass))
			{
				mainadmin();
				break;
			}
			else
			{
				System.out.println("Invalid password");
				System.out.println("Try again");
			}
		}
	}

	private static void mainadmin() {
		System.out.println();
		System.out.println("1.Load Amount");
		System.out.println("2.ATM balance");
		System.out.println("3.Exit");
		System.out.println("Enter no: ");
		int l=sc.nextInt();
		if(l==1)
		{
			load();
		}
		if(l==2)
		{
			ATM_total_amount(map);
			System.out.println("balance: "+Total_balance);
			System.out.println("press 3:EXIT");
			int i=0;
			while(i==0)
			{
				if(sc.nextInt()==3)
				{
					mainadmin();
					break;
				}
				else
				{
					System.out.println("Enter correct no: ");
				}
			}
		}
		if(l==3)
		{
			main(null);
		}
	}

	private static void load() {
		System.out.println();
		map.put(2000,map.getOrDefault(2000,0));
		map.put(500,map.getOrDefault(500,0));
		map.put(200,map.getOrDefault(200,0));
		map.put(100,map.getOrDefault(100,0));
		ArrayList<Integer> value=new ArrayList<>(map.values());
		ArrayList<Integer> keys=new ArrayList<>(map.keySet());
		int j=0;
		while(j==0)
		{
			System.out.print("Enter your total amount: ");
			int totamt=sc.nextInt();
			int check=0;
			if(totamt%100==0 && totamt>0)
			{
				System.out.print("no.of 2000= ");
				int addamt1=sc.nextInt();
				check+=addamt1*2000;
				System.out.print("no.of 500= ");
				int addamt2=sc.nextInt();
				check+=addamt2*500;
				System.out.print("no.of 200= ");
				int addamt3=sc.nextInt();
				check+= addamt3*200;
				System.out.print("no.of 100= ");
				int addamt4=sc.nextInt();
				check+=addamt4*100;
				if(addamt1+map.get(2000)<=10 && addamt2+map.get(500)<=20 && addamt3+map.get(200)<=50 && addamt4+map.get(100)<=100)
				{
					if(check==totamt)
					{
					map.put(2000,addamt1+map.get(2000));
					map.put(500,addamt2+map.get(500));
					map.put(200,addamt3+map.get(200));
					map.put(100,addamt4+map.get(100));
					ATM_total_amount(map);
					System.out.println(map);
					System.out.println("Amount added successfully");
					break;
					}
					else
					{
						System.out.println("your total Amount should not equals to your given rupees");
						System.out.print("Press 3.EXIT: ");
						while(j==0)
						{
							if(sc.nextInt()==3)
							{
								load();
								break;
							}
							else
							{
								System.out.print("Enter correct no: ");
								continue;
							}
						}
					}
				}
				else
				{
					System.out.println(map);
					System.out.println("your amount exceeds limit");
					System.out.println();
					continue;
				}
			}
			else {
				System.out.println("Amount doesn't multipes of 100 x....Enter correctly");
				System.out.println();
				continue;
			}
		}
		System.out.print("press 3. EXIT: ");
		while(j==0)
		{
		int a=sc.nextInt();
		if(a==3)
		{
			mainadmin();
			break;
		}
		else {
			System.out.print("Enter correct no:");
		}
		}
	}

	private static void ATM_total_amount(HashMap newmap) {
		System.out.println();
		HashMap<Integer,Integer> map=new HashMap<>(newmap);
		ArrayList<Integer> keys=new ArrayList<>(map.keySet());
		int bal=0;
		for(int i=0;i<keys.size();i++)
		{
			bal+=keys.get(i)*map.get(keys.get(i));
		}
		Total_balance=bal;
	}
}