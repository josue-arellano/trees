/*************************************************************** 
*   file: SearchTreeInterface.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This source file is the interface for the Binary Search Tree class.
* 
****************************************************************/ 
package tree;

public interface SearchTreeInterface<T extends Comparable<? super T>>
        extends BinaryTreeInterface<T>
{
    public boolean contains(T entry);
    public T getEntry(T entry);
    public T add(T newEntry);
    public T remove(T entry);
    public T getPredecessor(T entry);
}
