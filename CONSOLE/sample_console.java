import java.util.*;
public class sample_console 
{

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> comprep=new ArrayList<>();
		ArrayList<ArrayList<String>> list=new ArrayList<>();
		int i=0;
		String str="";
			while(i==0)
			{
				String st=sc.nextLine();
				String subadd="";
				String subset="";
				String connect="";
				String route="";
				try {
					route=st.substring(0,10);
					if(route.equals("INFO_ROUTE"))
					{
						System.out.println(list);
						String ar[]=st.split(" ");
						String first=ar[1];
						String second=ar[2];
						if(!comprep.contains(first) || !comprep.contains(second))
						{
							System.out.println("Error:Route not found");
							continue;
						}
						if(comprep.contains(second) && comprep.contains(first) && !first.contains("R") && !second.contains("R"))
						{
						int j=comprep.indexOf(first);
						res+=first+"->";
						String ans=route(first,second,list,j,comprep);
						System.out.println(ans);
						continue;
						}
						else
						{
							System.out.println("Error: Route cannot be calculated with a repeater");
							continue;
						}
					}
					
				}
				catch(Exception e)
				{
					i=1;
				}
				if(i==0)
				{
				try
				{
				connect=st.substring(0,7);
				}
				catch(Exception e)
				{
					i=0;
				}
				}
				if(st.length()>=15 && i==0)
				{
					subadd=st.substring(0,14);
					if(st.length()>=20)
						subset=st.substring(0,19);
					connect=st.substring(0,7);
					if(subadd.equals("ADD COMPUTER A"))
					{
						String com=st.substring(13,st.length());
						if(!comprep.contains(com))
						{
							comprep.add(com);
							list.add(new ArrayList());
							System.out.println("Successfully added "+com);
						}
						else
						{
							System.out.println("Error:That name already exits");
						}
					}
					else if(subadd.equals("ADD REPEATER R"))
					{
						String rep=st.substring(13,st.length());
						if(!comprep.contains(rep))
						{
							comprep.add(rep);
							list.add(new ArrayList());
							System.out.println("Successfully added "+rep);
						}
						else
						{
							System.out.println("Error:That name already exits");
						}
					}
					else if(subset.equals("SET_DEVICE_STRENGTH"))
					{
						String nam=st.substring(23,st.length());
						try {
							int a=Integer.parseInt(nam);
							System.out.println("Succesfully defined strength "+a);
						}
						catch(Exception e)
						{
							System.out.println("Error:Invalid strength");
						}
					}
					else if(connect.equals("connect"))
					{
						String ar[]=st.split(" ");
					}
					else
					{
						System.out.println("Error:Invalid command syntax");
					}
				}
				else if(connect.equals("connect") && i==0)
				{
					Collections.sort(comprep);
					String ar[]=st.split(" ");
					String first=ar[1];
					String second=ar[2];
					if(comprep.contains(first) && comprep.contains(second))
					{
						int j=comprep.indexOf(first);
						if(!list.get(j).contains(second))
						{
							list.get(j).add(second);
							System.out.println("Successfully connected");
						}
					}
					else
					{
						System.out.println("Error:node not found");
					}
				}
				else if(i==0)
				{
					System.out.println("Error:Invalid command syntax");
				}
			}
		}
	static String res="";
	private static String route(String first, String second,ArrayList<ArrayList<String>> arr,int j,ArrayList<String> comp){
		if(arr.get(j).contains(second))
		{
			String result="";
			result=res+second;
			res="";
			return result;
		}
			for(int i=0;i<arr.get(j).size();i++)
			{
				res+=arr.get(j).get(i)+"->";
				System.out.println(res);
				route(arr.get(j).get(i),second,arr,comp.indexOf(arr.get(j).get(i)),comp);
			}
			if(res.substring(res.length()-2,res.length()).equals("->"))
			{
				return res+second;
			}
			
		return "Route not found";
	}

}
