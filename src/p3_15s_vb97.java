/*Authon: Viraj Bhalala */


import java.util.*;
public class p3_15s_vb97 {
	
	//5 items
	//public static String Q="";
	public static ArrayList<String> Q = new ArrayList<String>();
	//here alphabet=sigma notation
	public static ArrayList<String> alphabet = new ArrayList<String>();
	public static String transitionString="";
	//start state
	public static String Q_o="";
	//final state
	public static ArrayList<String> F = new ArrayList<String>();
	
	//made just to use later in algorithm's iterations
	public static ArrayList<String> S = new ArrayList<String>();
	public static ArrayList<String> alphabetsList =new ArrayList<String>();
	public static String [] array1;
	public static String[][] array2;
	public static String[][] table;
	
	public static void main(String[] args){
		Scanner s =new Scanner(System.in);
		//assumed user input DFA in the given format.
		System.out.println("Do you want to enter an input? Please enter \"y\" or \"yes\"");
		String inputYN = s.nextLine();
		System.out.println("You entered: "+ inputYN);
		if (inputYN.compareTo("y")==0 || inputYN.compareTo("yes")==0){
			System.out.println("Please input DFA");
			String input = s.nextLine();
			System.out.println("You entered: "+ input + "\n");
			
			//copies the input
			String temp =input;
			
			//parsing begins here
			String [] temp1=temp.substring(1, temp.indexOf('}')).split(",");
			for (int i=0;i<temp1.length;i++){ Q.add(temp1[i]);}
			//Q =Arrays.asList(temp.substring(1, temp.indexOf('}')).split(","));
			System.out.println("List of States in Q: "+Q +"\n");
					
			// added +2 so that it skips begining ",{"
			temp=temp.substring(temp.indexOf('}')+2);
			
			String [] temp2=temp.substring(1, temp.indexOf('}')).split(",");
			for (int i=0;i<temp2.length;i++){ alphabet.add(temp2[i]);}
			//alphabet=Arrays.asList(temp.substring(1, temp.indexOf('}')).split(","));
			System.out.println("List of Symbols in Language: "+ alphabet + "\n");
			
			temp=temp.substring(temp.indexOf('}')+2);

			transitionString=temp.substring(1, temp.indexOf('}'));
			//prints the table
			PrintTransition();
		
			temp=temp.substring(temp.indexOf('}')+2);
			Q_o=temp.substring(0, 2);
			System.out.println("Starting state: "+ Q_o + "\n");
			
			temp=temp.substring(3);
			String [] temp3=temp.substring(1, temp.indexOf('}')).split(",");
			for (int i=0;i<temp3.length;i++){F.add(temp3[i]);}
			
			System.out.println("List of accepting states: "+F + "\n");
			//parsing ends
			S.add(Q_o);
			
			
			if (DFAchecker()==true){
				System.out.println("ACCEPTED"+"\n" +"\n");
			}
			else{
				System.out.println("REJECTED" +"\n"+"\n");
			}
			main(args);
		}
		//when input is something other then y or yes
		else
			System.out.println("terminating");
	}

	public static List<String> eachTransition;
	public static void PrintTransition(){
		
		/* breaking each state and letter and placing them in 2D array starts here*/
		eachTransition = new ArrayList<String>();
		array1=transitionString.split("[)],");
		List<String> TempList =new ArrayList<String>();
		array2= new String [array1.length][3];
		Arrays.fill(array2[0], null);		
		for (int i=0; i<array1.length; i++){
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
				//System.out.print(array2[i][j] + "\t");
			}
			/*uncomment it if you want to print transition in table table style*/
			//System.out.println();
		}
		
		System.out.println();
		/*Parsing and separating states for transition ends here*/
		/*Constructing transition table starts here*/
		
		//added +1 so that first column can be written as states and heading
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
			//after printing each line go to the next line
			System.out.println();
		}
		
		/*Constructing and printing transition table ends here */
		System.out.println();
		eachTransition = Arrays.asList(array1);
	}
	
	/*
	 * Most important stuffs happens after this line
	 * Below method use the algorithms from the slide 4-11 
	 */
	public static List<String> tempList =new ArrayList<String>();
	
	public static boolean DFAchecker(){
		// Step 1 of the algorithms: check the start state
		if (!S.contains("q0")){
			clear();
			return false;
		}

		System.out.println("[S] initially: "+S);
		//itererate length of Q times (step 2 of the Algorithms)
		for (int i=0; i<Q.size();i++){
			//check if anything from F is in S (step 2 A for the Algorithms)
			for (int j=0; j<F.size();j++){
				if(S.contains(F.get(j))){
					//clear everything before returning
					clear();
					return false;
				}
			}
			
			//add states to S that can be reached from current states by transition 					
			//compare the state that is just added with the other state in first column in the transition table
			//and the states that can be reached using transition table
			
			//for each S	
			for(int p=0;p<S.size();p++)	{
				for(int k =0; k<Q.size();k++){
					//compare it with first column in transition table
					if (table[k+1][0].compareTo(S.get(p)) ==0){
						//add that row
						for (int c=0; c<alphabet.size();c++){
							if(!S.contains(table[k+1][c+1])){
								//tempList will hold that states temporarily
								tempList.add(table[k+1][c+1]);			
							}
						}
					}
				}
			}
			for(String l: tempList){
				if (!S.contains(l))
				S.add(l);
			}
			//after each iteration print the list
			System.out.println(S);
			//clear the tempList to use it again another time
			tempList.clear();
		}
		
		// last step of the algorithm to check if F and S have anything in common. If they have then reject
		for (int j=0; j<F.size();j++){
			if(S.contains(F.get(j))){
				clear();
				return false;
			}
		}
		clear();
		return true;
	}
	
	//used as clearing list after finishing each input and output
	public static void clear(){
		Q.clear();
		alphabet.clear();
		transitionString="";
		Q_o="";
		F.clear();
		S.clear();
		alphabetsList.clear();
		Arrays.fill(array2, null);
		Arrays.fill(array1, null);
		Arrays.fill(table, null);
	}
}
