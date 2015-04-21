import java.util.*;
public class p3_15s_vb97 {
	
	//5 items
	//public static String Q="";
	public static List<String> Q = new ArrayList<String>();
	//here alphabet=sigma notation
	public static List<String> alphabet = new ArrayList<String>();
	public static String transitionString="";
	//start state
	public static String Q_o="";
	//final state
	public static String F="";
	
	
	public static List<String> S = new ArrayList<String>();
	//public static List<String> statesList =new ArrayList<String>();
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
		Q =Arrays.asList(temp.substring(1, temp.indexOf('}')).split(","));
		System.out.println("List of States in Q: "+Q +"\n");
				
		
		// added +2 so that it skips begining ",{"
		temp=temp.substring(temp.indexOf('}')+2);
		
		alphabet=Arrays.asList(temp.substring(1, temp.indexOf('}')).split(","));
		System.out.println("List of Symbols in Language: "+ alphabet + "\n");
		//array1=alphabet.split(",");
		//alphabetsList= Arrays.asList(array1);
		//System.out.println ("test: " + alphabetsList);
		
		temp=temp.substring(temp.indexOf('}')+2);

		transitionString=temp.substring(1, temp.indexOf('}'));
		
		PrintTransition();
	
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
		
		/*
		 * breaking each state and letter and placing them in 2D array starts here
		 */
		//System.out.println(transitionString);
		//System.out.println("state \t" + alphabet.get(0) + "\t" + alphabet.get(1));
		List<String> eachTransition = new ArrayList<String>();
		array1=transitionString.split("[)],");
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
			//System.out.println();
		}
		/*
		 * Ends here
		 */
		
		/*
		 * Making transition table starts here
		 */
		
		//did +1 so that first colum can be written as states and heading
		String[][] table = new String[Q.size()+1][alphabet.size()+1];
		//fills up the header
		table[0][0] = "states";
		for (int i=0; i<alphabet.size();i++){
			table[0][i+1]= alphabet.get(i);
		}
		System.out.println(table[0][0] +"\t"+table[0][1] +"\t"+table[0][2]);
		
		//fills up the lsft side
		for (int i=0; i<Q.size();i++){
			table[i+1][0]= Q.get(i);
		}
		
		
		eachTransition = Arrays.asList(array1);
		 
		  
		  
		  
	}

}
