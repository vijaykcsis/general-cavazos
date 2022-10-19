package com.cavazos;

import java.util.*; 
import org.json.simple.*;


public class CavazosExample {

  public static void main(String[] args) {
    String fileName =
      "/Users/redsm/Documents/GitHub/general-cavazos/src/cavazos/src/main/java/com/cavazos/commands.json";
      //"/Users/jerom/Documents/GitHub/class-java/general-cavazos/undoredo/src/main/java/com/cavazos/commands.json";

    // read coammands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);
    System.out.println(commandArray);

    Stack<Integer> stack;

    // print list of all commands
    System.out.println("----- List of all commands -----");
    print(commandArray);

    System.out.println(
      "----- Issuing 5 random commands from General Cavazos -----"
    );
    issueCommand(commandArray);
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
