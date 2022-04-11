import java.util.*;
import java.io.*;

public class Main extends LazyBinarySearchTree {

    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");

        Scanner sc = new Scanner(file);
        LazyBinarySearchTree root = new LazyBinarySearchTree();
        FileWriter output = new FileWriter("output.txt");
        while (sc.hasNextLine()){
            String thisLine = sc.nextLine();
            String[] commands = thisLine.split(":", 2);
            commands[0] = commands[0].toLowerCase(Locale.ROOT);
            //Have different switch commands to write the result onto the "output.txt" file.
            switch(commands[0]) {
                case "insert":
                    if (commands.length == 1) {
                        output.write("Error in Line: Insert");
                        output.write(System.lineSeparator());
                        break;
                    }
                    try {
                        int key = Integer.parseInt(commands[1]);
                        if (root.insert(key))
                            output.write("True");
                        else
                            output.write("False");
                    } catch (IllegalArgumentException e) {
                        output.write("Error in insert: IllegalArgumentException raised");
                    }
                    output.write(System.lineSeparator());
                    break;


                case "delete":
                    if (commands.length == 1) {
                        System.out.println("Error in Line: Delete");
                        output.write(System.lineSeparator());
                        break;
                    }
                    try {
                        int key = Integer.parseInt(commands[1]);
                        if (root.delete(key))
                            output.write("True");
                        else
                            output.write("False");
                        } catch (IllegalArgumentException e) {
                            output.write("Error in Delete: IllegalArgumentException raised");
                        }
                    output.write(System.lineSeparator());
                    break;

                case "contains":
                    if (commands.length == 1) {
                        System.out.println("Error in Line: Contains");
                        output.write(System.lineSeparator());
                        break;
                    }
                    try {
                        int key = Integer.parseInt(commands[1]);
                        if (root.contains(key))
                            output.write("True");
                        else
                            output.write("False");
                        } catch (IllegalArgumentException e) {
                            output.write("Error in Contains: IllegalArgumentException raised");
                        }
                    output.write(System.lineSeparator());
                    break;

                case "findmin":
                    output.write(Integer.toString(root.findMin()));
                    output.write(System.lineSeparator());
                    break;

                case "findmax":
                    output.write(Integer.toString(root.findMax()));
                    output.write(System.lineSeparator());
                    break;

                case "height":
                    output.write(Integer.toString(root.height()));
                    output.write(System.lineSeparator());
                    break;

                case "size":
                    output.write(Integer.toString(root.size()));
                    output.write(System.lineSeparator());
                    break;

                case "printtree":
                    output.write(root.toString());
                    output.write(System.lineSeparator());
                    break;


                default:output.write("Error in Line: " + thisLine);
            }
        }
        sc.close();
        output.close();
    }
}



