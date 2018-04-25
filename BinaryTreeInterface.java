/*************************************************************** 
*   file: BinaryTreeInterface.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This program is the interface for the Binary Tree class. 
*            This extends the Tree Interface.
* 
****************************************************************/ 
package tree;

public interface BinaryTreeInterface<T> extends TreeInterface<T>
{
    public void setTree(T data);
    public void setTree(T data, BinaryTreeInterface<T> leftChild,
                                BinaryTreeInterface<T> rightChild);
}
