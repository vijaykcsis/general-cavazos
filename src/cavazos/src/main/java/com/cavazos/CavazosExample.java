package com.cavazos;

import java.util.*; 
import org.json.simple.*;


public class CavazosExample {

  public static void main(String[] args) {
    String fileName =
      "/Users/redsm/Documents/GitHub/general-cavazos/src/cavazos/src/main/java/com/cavazos/commands.json";

    // read commands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);
    //System.out.println(commandArray);

    Stack<Integer> stack;

    // print list of all commands
    //System.out.println("----- List of all commands -----");
    //print(commandArray);

    //issueCommand(commandArray);
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // While loop until user quits
    while (command != 'q') {
        printMenu();
        System.out.print("Enter a command: ");
        command = menuGetCommand(scan);
        executeCommand(scan, command);
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

  private static Boolean executeCommand(Scanner scan, Character command) {
    Boolean success = true;
    switch (command) {
        case 'i':
            break;
        case 'l':
            break;
        case 'u':
            break;
        case 'r':
            break;
        case 'q':
            System.out.println("Thank you for using Chavvi Calc");
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
  public static void issueCommand(String[] commandArray) {
    int numCommand = 1;
    Random rand = new Random();
    System.out.printf("-----------------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("\t%s\n", commandArray[randIndex]);
    }
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("-----------------------\n");
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
