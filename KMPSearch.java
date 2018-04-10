import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class KMPSearch {

  public static int[][] dfa;       // the KMP automoton

  public static boolean contains(String txt, String pat) {
    //If pattern is found in string, return true
    if(searchFirst(txt, pat) != -1)
      return true;

    else
    return false;
  }

  /*
   * This method creates a dfa which is then used to process the pattern string.
   * Standard radix of 256 
   */
  public static void createDFA(String pat){

        int R = 256;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m]; 
        dfa[pat.charAt(0)][0] = 1; 
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases. 
            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
            x = dfa[pat.charAt(j)][x];     // Update restart state. 
        } 
  }

  /*
   * The method returns the index of the first ocurrence of a pattern pat in String txt.
   * It should return -1 if the pat is not present
   */
  public static int searchFirst(String txt, String pat) {
    //First create dfa
    if(txt.length() == 0 || pat.length() == 0)
    	return -1;

    createDFA(pat);

    // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return -1;                    // not found
  }

  /*
   * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
   */
  public static int searchAll(String txt, String pat) {
  	if(txt.length() == 0 || pat.length() == 0)
    	return 0;

    //First create dfa
    createDFA(pat);
    String tmp = txt;
    int count = 0;
    int result;
    boolean carryOn = true;

    //Recursively search for string, slicing 
    while(carryOn){
    	result = searchFirst(tmp, pat);

    	//If pattern found in text continue
    	if(result != -1){
    		count++;
    		//**
    		tmp = tmp.substring(result+pat.length());
    	}
    	else
    		carryOn = false;
    }

    if(count!=0)
    	return count;

    else 
    	return count;
  }

  /*
   *  This method is an example of handling a .json file
   */
  public static void searchBuses() throws IOException {

    String vehiclePat = "VehicleNo";
    String vehicleNum1 = "16555";
    String location = "HAMPTON PARK";
    String vehicleNum2 = "9043409";
    String jsonFile = new String(Files.readAllBytes(Paths.get("./txt_files/BUSES_SERVICE_0.json")));
    int result;

    System.out.println("\nGetting total number of vehicles...");
    System.out.println(searchAll(jsonFile, vehiclePat));

    System.out.println("\nChecking if VehicleID " + vehicleNum1 + " is present...");
    System.out.println(contains(jsonFile, vehicleNum1));

    System.out.println("\nLocating first record about location '" + location + "'...");
    result = searchFirst(jsonFile, location);

    if(result != -1)
      System.out.println("Found at index " + result);

    else
      System.out.println("Location not found");
  
    System.out.println("\nChecking if VehicleID " + vehicleNum2 + " is present...");
    System.out.println(contains(jsonFile, vehicleNum2) + "\n");

  }

  public static void searchXinY(String pat, String txt) {

    System.out.println("\nPattern entered = " + pat);
    System.out.println("Text entered = " + txt);

    System.out.println("\nChecking if text '" + txt + "' contains the pattern '" + pat + "'...\n");
    boolean result = contains(txt, pat);

    if(result){
      System.out.println("Pattern found!\n");
      System.out.println("First index pattern occurs at is at index " + searchFirst(txt, pat));
      System.out.println("Total number of occurances is " + searchAll(txt, pat));
    }

    else
      System.out.println("Pattern not found!\n\n");
  }

  public static void readInput(){

    StdOut.println("\nEnter the pattern you would like to search for:");
    String needle = StdIn.readString();
    StdOut.println("\nEnter the string you would like to search for this pattern in:");
    String haystack = StdIn.readString();

    searchXinY(needle, haystack);
  }

  public static void main(String[] args) throws IOException {

    String input = args[0];

    switch(input){
      case "-r": 
        String pattern = args[1];
        String text = args[2];
        searchXinY(pattern, text);
        break;
      case "-j":
        searchBuses();
        break;
      case "-s":
        readInput();
        break;
      default:
        System.out.println("Incorrect input...");
        break;
    }

  }
}