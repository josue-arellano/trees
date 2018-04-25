/*************************************************************** 
*   file: Main.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/21/2018 
* 
*   purpose: This source file tests the BinarySearchTree class. It creates a 
*            user interface that will allow user inputs.
* 
****************************************************************/ 
package tree;

import java.util.Scanner;
import java.util.Random;

public class BinarySearchTreeMain
{
    public static void remain(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        String input = "";
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        Scanner inputScan = new Scanner(input);
        do
        {
            System.out.println("Please enter the initial sequence of values: ");
            input = kb.nextLine();
            inputScan = new Scanner(input);
        } while(input.length() == 0 || !inputScan.hasNextInt());
        Scanner set = new Scanner(input);
        tree = new BinarySearchTree<Integer>(set.nextInt());
        while(set.hasNextInt())
        {
            Integer x = set.nextInt();
            tree.add(x);
        }
        System.out.print("Preorder: ");
        tree.preorderTraverse();
        System.out.print("\nInorder: ");
        tree.inorderTraverse();
        System.out.print("\nPostorder: ");
        tree.postorderTravers();
        System.out.println();
        //BinaryNode<Integer> v = tree.getPredecessor(4);
        //System.out.println(v.getData());
        boolean cont = true;
        while(cont)
        {
            input = "";
            char ans = 'H';
            while(input.length() == 0)
            {
                System.out.print("Command? ");
                input = kb.nextLine();
            }
            ans = input.charAt(0);
            ans = Character.toUpperCase(ans);
            Scanner cmnd = new Scanner(input);
            cmnd.next();
            Integer num = null;
            if(cmnd.hasNextInt())
                num = cmnd.nextInt();
            switch(ans)
            {
                case 'H':
                    printHelpMenu();
                    break;
                case 'I':
                    if(num != null)
                    {
                        Integer added = tree.add(num);
                        if (added != null)
                            System.out.println(added + " already exists, ignore.");
                        else
                            printInorder(tree);
                    }
                    else
                        printError(ans);
                    break;
                case 'D':
                    if(num != null)
                    {
                        Integer removed = tree.remove(num);
                        if (removed == null)
                            System.out.println(num + " doesn't exist!");
                        else
                            printInorder(tree);
                    }
                    else
                        printError(ans);
                    break;
                case 'P':
                    if(num != null)
                    {
                        Integer pre = tree.getPredecessor(num);
                        if(pre != null)
                            System.out.println(pre);        
                        else
                            System.out.println("There is no predecessor or "
                                                + "number is not in the tree!");                       
                    }
                    else
                        printError(ans);
                    break;
                case 'S':
                    if(num != null)
                    {
                        Integer succ = tree.getSuccessor(num);
                        if(succ != null)
                            System.out.println(succ);        
                        else
                            System.out.println("There is no successor or the "
                                                + "number is not in the tree!");
                    }
                    else
                        printError(ans);
                    break;
                case 'E':
                    cont = false;
                    break;
                default:
                    System.out.println("Wrong input! Try one of these: ");
                    printHelpMenu();
                    break;
            }
        }
        System.out.println("Thank you for using my program!");
    }
    
    //method:  printInorder
    //purpose: This method prints the inorder traversal of an input 
    //         BinarySearchTree.
    public static void printInorder(BinarySearchTree<Integer> tree)
    {
        System.out.print("Inorder: ");
        tree.inorderTraverse();
        System.out.println();        
    }
    
    //method:  printHelpMenu
    //purpose: This method prints the inputs that the user can use.
    public static void printHelpMenu()
    {
        System.out.println("  I  Insert a value\n"
                + "  D  Delete a value\n"
                + "  P  Find predecessor\n"
                + "  S  Find successor\n"
                + "  E  Exit the program\n"
                + "  H  Display this message");
    }
    
    //method:  printError
    //purpose: This method prints an error when the user inputs an incorrect
    //         input.
    public static void printError(char x)
    {
        Random rand = new Random();
        int y = rand.nextInt(20);
        System.out.println("Error! An integers is required for this input."
                           + " ex: " + x + " " + y);
    }
}
