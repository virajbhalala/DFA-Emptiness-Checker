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
	public static String [] array1;
	public static String[][] array2 = new String [100][100];
	
	
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
		array1=alphabet.split(",");
		alphabetsList= Arrays.asList(array1);
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
		array1=transition.split("[)],");
		List<String> TempList =new ArrayList<String>();
		Arrays.fill(array2[0], null);		
		for (int i=0; i<array1.length; i++){
			//System.out.println(array1.length);
			if (i==array1.length-1){
				array1[i]=array1[i].substring(1, array1[i].length()-1);
				TempList=Arrays.asList(array1[i].split(","));
				
			}
			else{
				array1[i]=array1[i].substring(1);
				TempList=Arrays.asList(array1[i].split(","));				
			}

			for (int j =0; j<TempList.size(); j++){
				
				array2[i][j] = TempList.get(j);
				//System.out.print(array2[i][j] + "\t");
			}
			System.out.println();
		}
		
		
		eachTransition = Arrays.asList(array1);
		  
		  
		  
		  
	}

}
