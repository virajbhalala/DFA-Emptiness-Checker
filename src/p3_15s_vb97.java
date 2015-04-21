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
	public static List<String> F = new ArrayList<String>();
	
	//made just to use later in algorithm's iterations
	public static List<String> S = new ArrayList<String>();
	//public static List<String> statesList =new ArrayList<String>();
	public static List<String> alphabetsList =new ArrayList<String>();
	public static String [] array1;
	public static String[][] array2;
	public static String[][] table;
	
	
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
		
		F=Arrays.asList(temp.substring(1, temp.indexOf('}')));
		System.out.println("List of accepting states: "+F + "\n");
		//parsing ends
		System.out.println(DFAchecker());
		
	}

	public static List<String> eachTransition;
	public static void PrintTransition(){
		
		/*
		 * breaking each state and letter and placing them in 2D array starts here
		 */
		//System.out.println(transitionString);
		eachTransition = new ArrayList<String>();
		array1=transitionString.split("[)],");
		List<String> TempList =new ArrayList<String>();
		array2= new String [array1.length][3];
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
				/*uncomment it if you want to print transition in table table style*/
				System.out.print(array2[i][j] + "\t");
			}
			/*uncomment it if you want to print transition in table table style*/
			System.out.println();
		}
		/*
		 * Parsing and separating states for transition ends here
		 */
		
		/*
		 * Constructing transition table starts here
		 */
		
		//did +1 so that first colum can be written as states and heading
		table= new String[Q.size()+1][alphabet.size()+1];
		//fills up the header
		table[0][0] = "states";
		for (int i=0; i<alphabet.size();i++){
			table[0][i+1]= alphabet.get(i);
		}
		System.out.println(table[0][0] +"\t"+table[0][1] +"\t"+table[0][2]);
		
		//fills up the left side (states)
		for (int i=0; i<Q.size();i++){
			table[i+1][0]= Q.get(i);
		}	
		for (int i=1; i<table.length;i++){
			
			for(int j=0; j<array2.length;j++){
				
				if(table[i][0].compareTo(array2[j][0])==0){

					//if so then check the if symbols are same
					for(int k =0; k<alphabet.size();k++){
						if(table[0][k+1].compareTo(array2[j][1])==0){
							//if so then copy the state (where it goes to after reading symbol) into the  table in specific column 
							table[i][k+1] =array2[j][2];	
						}
					}
				}
			}
			//prints each line
			for (int c=0; c<table[i].length;c++){
				System.out.print(table[i][c] +"\t");
			}
			//after printing go to the next line
			System.out.println();
		}
		
		/*
		 * Constructing and printing transition table ends here
		 */
		System.out.println();
		eachTransition = Arrays.asList(array1);
  
	}
	
	/*
	 * Most important stuffs happens after this line
	 * Below method use the algorithms from the slide 4-11 
	 */
	
	public static boolean DFAchecker(){
		//System.out.println(array2[4][0]);
		// Step 1 of the algorithms: check the start state
		if (Q_o.compareTo("q0")!=0){
			return false;
		}
		S.clear();
		S.add(Q_o);
		
		//itererate length of Q times (step 2 of the Algorithms)
		for (int i=0; i<Q.size();i++){
			for (int j=0; j<F.size();j++){
				if(S.contains(F.get(j))){
					return false;
				}
			}
			//add states to S that can be reached from current states by transition 
			String temp ="";
			for (String x:S){
				//System.out.println(x);
				for(int k =0; k<Q.size();k++){	
					if(x.compareTo(table[k+1][0])==0){
						for (int l=0; l<array1.length; l++){
							if(x.compareTo(array2[l][0])==0){
								for (int z=0; z<alphabet.size();z++){
									if(array2[l][1].compareTo(table[0][z+1])==0){
										temp= table[k+1][z+1];
										System.out.println("added "+table[k+1][z+1]);
									}
								}		
							}
						}
					}
				}
			}
			S.add(temp);
			
		}
		
		return false;
	}
	
}
