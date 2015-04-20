import java.util.*;
public class p3_15s_vb97 {
	
	//5 items
	public static String Q="";
	//here alphabet=sigma notation
	public static String alphabet="";
	//here transition =delta notation
	public static String transition="";
	//start state
	public static String Q_o="";
	//final state
	public static String F="";
	
	
	public static LinkedList S = new LinkedList(); 
	
	public static void main(String[] args){
		Scanner s =new Scanner(System.in);
		//assumed user input DFA in the given format.
		String input = s.nextLine();
		System.out.println(input + "\n");
		
		//copies the input
		String temp =input;
		//{q0,q1,...,qn},{l1,l2,...,lr},{(q0,l1,qi0,1),(q0,l2,qi0,2),...,(qn,lr,qin,r)},q0,{qj1,qj2,...,qjs}
		
		
		//parsing begins here
		Q=temp.substring(1, temp.indexOf('}'));
		System.out.println("List of States in Q: "+Q +"\n");
		
		// added +2 so that it skips begining ",{"
		temp=temp.substring(temp.indexOf('}')+2);
		
		alphabet=temp.substring(1, temp.indexOf('}'));
		System.out.println("List of Symbols in Aplhabet: "+ alphabet + "\n");
		
		temp=temp.substring(temp.indexOf('}')+2);
		
		transition=temp.substring(1, temp.indexOf('}'));
		System.out.println(transition);
		
		temp=temp.substring(temp.indexOf('}')+2);
		
		Q_o=temp.substring(0, 2);
		System.out.println("Starting state: "+Q_o + "\n");
		
		temp=temp.substring(3);
		
		F=temp.substring(1, temp.indexOf('}'));
		System.out.println("List of accepting states: "+F + "\n");
		//parsing ends
	}


}
