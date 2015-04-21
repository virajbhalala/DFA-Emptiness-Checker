import java.util.*;
public class p3_15s_vb97 {
	
	//5 items
	public static String Q="";
	//here alphabet=sigma notation
	public static String alphabet="";
	public static String transition="";
	//start state
	public static String Q_o="";
	//final state
	public static String F="";
	
	
	public static List<String> S = new ArrayList<String>();
	public static List<String> statesList =new ArrayList<String>();
	public static List<String> alphabetsList =new ArrayList<String>();
	public static String [] a=alphabet.split(",");
	public static void main(String[] args){
		Scanner s =new Scanner(System.in);
		//assumed user input DFA in the given format.
		String input = s.nextLine();
		System.out.println(input + "\n");
		
		//copies the input
		String temp =input;
		//{q0,q1,...,qn},{l1,l2,...,lr},{(q0,l1,qi0,1),(q0,l2,qi0,2),...,(qn,lr,qin,r)},q0,{qj1,qj2,...,qjs}
		
		//parsing begins here
		Q =temp.substring(1, temp.indexOf('}'));
		System.out.println("List of States in Q: "+Q +"\n");
		
		statesList= Arrays.asList(Q);
		System.out.println ("test: " + statesList);
		
		
		// added +2 so that it skips begining ",{"
		temp=temp.substring(temp.indexOf('}')+2);
		
		alphabet=temp.substring(1, temp.indexOf('}'));
		System.out.println("List of Symbols in Aplhabet: "+ alphabet + "\n");
		a=alphabet.split(",");
		alphabetsList= Arrays.asList(a);
		//System.out.println ("test: " + alphabetsList);
		
		temp=temp.substring(temp.indexOf('}')+2);
		
		//transition part starts here
		transition=temp.substring(1, temp.indexOf('}'));
		
		PrintTransition();
		
		//printing transition table
		//System.out.println("state \t" + alphabetsList.get(0) + "\t" + alphabetsList.get(1));
		
		
		
		
		//transition part ends here
		temp=temp.substring(temp.indexOf('}')+2);
		
		Q_o=temp.substring(0, 2);
		System.out.println("Starting state: "+ Q_o + "\n");
		
		temp=temp.substring(3);
		
		F=temp.substring(1, temp.indexOf('}'));
		System.out.println("List of accepting states: "+F + "\n");
		//parsing ends
		
		
	}

	
	public static void PrintTransition(//String transition,List<String> statesList, List<String> alphabetsList 
			){
		System.out.println(transition);
		System.out.println("state \t" + alphabetsList.get(0) + "\t" + alphabetsList.get(1));
		List<String> eachTransition = new ArrayList<String>();
		a=transition.split("[)],");
		for (int i=0; i<a.length; i++){
			if (i==a.length-1){
				a[i]=a[i].substring(1, a[i].length()-1);
			}
			else
				a[i]=a[i].substring(1);
			System.out.println(a[i]);
		}
		eachTransition = Arrays.asList(a);
		//System.out.println(eachTransition);
		  
		  
		  
		  
	}

}
