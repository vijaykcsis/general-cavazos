package com.cavazos;

import java.util.*; 
import org.json.simple.*;

// NOTE: Some of the menu functions in this program are 
// adapted from my ChavviCalc assignment code that I
// submitted earlier this semester

public class Cavazos {
  static Stack<Integer> undoStack = new Stack<Integer>();
  static Stack<Integer> redoStack = new Stack<Integer>();
  // In the stack we save the commands as integer 
  // indexes of the array rather than as strings.
  // (It is more efficient.)

  public static void main(String[] args) {
    String fileName =
      "/Users/redsm/Documents/GitHub/general-cavazos/src/cavazos/src/main/java/com/cavazos/commands.json";
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // This while loop runs until the user quits
    // This snippet of code is adapted from the
    // ChavviCalc assignment I submitted earlier  
    // in the semester.
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);
      executeCommand(scan, command, commandArray);
    }
    scan.close();
  }

  // get array of commands from the JSON file
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");

    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%02d\t%s\n", (i+1), commandArray[i]);
      // Note that the list of commands 
      // that is displayed to the user 
      // is NOT zero-indexed.
    }
  }

  // this function is used when printing out
  // the list of commands in the main menu
  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t%s\n", command, desc);
  }

  private static void printMenuLine() {
    System.out.println(
    "----------------------------------------------------------"
    );
  }

  // get the first character from the user input 
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';
    String rawInput = scan.nextLine();
    if (rawInput.length() > 0) {
        rawInput = rawInput.toLowerCase();
        command = rawInput.charAt(0);
    }
    return command;
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
  
  // issue the random commands
  public static void issueCommand(String[] commandArray) {
    Random rand = new Random();
    printMenuLine();
    int randIndex = rand.nextInt(commandArray.length);
    undoStack.push(randIndex);
    System.out.println("General Cavazos orders the troops to: " + commandArray[randIndex]);
  }

  private static Boolean executeCommand(Scanner scan, Character command, String[] commandArray) {
    Boolean success = true;
    switch (command) {
        case 'i':
          issueCommand(commandArray);
          break;
        case 'l':
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
              // Notice how we use the peek function 
              // to print out the most recently undid
              // command. This allows us to print 
              // out the undone command without having 
              // to save it as an extra variable.
          }
          break;
        case 'r':
            if (redoStack.empty()) {
              System.out.println("ERROR: There are no commands to redo.");
            }
            else { 
              undoStack.push(redoStack.pop()); 
              System.out.println("General Cavazos orders the troops to REDO: " + commandArray[undoStack.peek()]);   
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
}