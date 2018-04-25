/*
 *   Name:      Arellano, Josue
 *   File:      Main.java
 *   Project:   #1
 *   Due:       Feb 18, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package tree;

import java.util.Scanner;
import static tree.BinarySearchTreeMain.printError;
import static tree.BinarySearchTreeMain.printInorder;
import java.util.Random;

/**
 *
 * @author josue
 */
public class Main
{
    public static void main(String[] args)
    {
        RedBlackTree<Integer> x = new RedBlackTree<Integer>();
        x.add(2);
        x.add(1);
        x.add(4);
        x.add(5);
        x.add(9);
        x.add(3);
        x.add(6);
        x.add(7);
        x.inorderTraverse();
    }

    public static boolean contains(Integer[] arr, int index, Integer val)
    {
        for(int i = 0; i < index + 1; i++)
        {
            if(val == arr[i])
                return true;
        }
        return false;
    }
    
    public static void printHelpMenu()
    {
        System.out.println("  I  Insert a value\n"
                + "  D  Delete a value\n"
                + "  L  Number of leaves\n"
                + "  R  Range\n"
                + "  H  Height\n"
                + "  E  Exit the program\n"
                + "  M  Display this message");
    }
}
