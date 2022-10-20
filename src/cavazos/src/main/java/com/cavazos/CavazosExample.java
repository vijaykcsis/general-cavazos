package com.cavazos;

import java.util.*; 
import org.json.simple.*;

// Note: Some of the menu functions in this program are 
// adapted from my ChavviCalc assignment that I submitted
// earlier this semester
public class CavazosExample {

  // The below variables are defined statically
  // so that other methods can use them

  public static void main(String[] args) {
    //System.out.println(commandArray);
    // print list of all commands
    //System.out.println("----- List of all commands -----");
    //print(commandArray);

    String fileName =
      "/Users/redsm/Documents/GitHub/general-cavazos/src/cavazos/src/main/java/com/cavazos/commands.json";
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    //issueCommand(commandArray);
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    Stack<Integer> undoStack = new Stack<Integer>();
    Stack<Integer> redoStack = new Stack<Integer>();

    // In the stack we save the commands as integer 
    // indexes rather than strings.
    // (It is more efficient.)

    // This while loop runs until the user quits
    // This snippet of code is adapted from the
    // ChavviCalc assignment I submitted earlier  
    // in the semester.

    while (command != 'q') {
        printMenu();
        System.out.print("Enter a command: ");
        command = menuGetCommand(scan);
        executeCommand(scan, command, commandArray, undoStack, redoStack);
    }

    scan.close();
  }

    // This function is used when printing out the list of commands in the main menu
    private static void printMenuCommand(Character command, String desc) {
        System.out.printf("%s\t%s\n", command, desc);
    }

    public static void printMenu() {
      printMenuLine();
      System.out.println("General Cavazos Commander App");
      printMenuLine();
      printMenuCommand('i', "Issue a command");
      printMenuCommand('l', "List all of the commands");
      printMenuCommand('u', "Undo the last command that was issued");
      printMenuCommand('r', "Redo the last command that was issued");
      printMenuCommand('q', "Quit");
      printMenuLine();
  }

  private static void printMenuLine() {
    System.out.println(
    "----------------------------------------------------------"
    );
  }

  private static Boolean executeCommand(Scanner scan, Character command, String[] commandArray,
  Stack<Integer> undoStack, Stack<Integer> redoStack) {
    Boolean success = true;
    switch (command) {
        case 'i':
          issueCommand(commandArray, undoStack);
          break;
        case 'l':
          System.out.println("command = " + command.toString());
          print(commandArray);
          break;
        case 'u':
          printMenuLine();
          if (undoStack.empty()) {
            System.out.println("ERROR: There are no commands to undo.");
          }
          else {
            redoStack.push(undoStack.pop()); 
            System.out.println("General Cavazos orders the troops to UNDO: " + commandArray[redoStack.peek()]);  
          }
          break;
        case 'r':
            if (redoStack.empty()) {
              System.out.println("ERROR: There are no commands to redo.");
            }

          break;
        case 'q':
          System.out.println("Thank you for using the General Cavazos Commander App.");
          break;
        default:
          System.out.println("ERROR: Unknown commmand");
          success = false;
    }
    return success;
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';
    String rawInput = scan.nextLine();
    if (rawInput.length() > 0) {
        rawInput = rawInput.toLowerCase();
        command = rawInput.charAt(0);
    }
    return command;
  }

  // randomly issue commands from General Cavazos
  public static void issueCommand(String[] commandArray, Stack<Integer> undoStack) {
    Random rand = new Random();
    printMenuLine();
    int randIndex = rand.nextInt(commandArray.length);
    undoStack.push(randIndex);
    System.out.println("General Cavazos orders the troops to: " + commandArray[randIndex] + ", index:" + Integer.toString(randIndex));
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");

    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // get array of commands
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }
}
