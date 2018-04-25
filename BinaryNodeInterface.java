/*************************************************************** 
*   file: BinaryNodeInterface.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This program is the interface to the Binary Node class. 
* 
****************************************************************/ 
package tree;

public interface BinaryNodeInterface<T>
{
    public T getData();
    public void setData(T data);
    public BinaryNode<T> getLeftChild();
    public void setLeftChild(BinaryNode<T> leftChild);
    public boolean hasLeftChild();
    public BinaryNode<T> getRightChild();
    public void setRightChild(BinaryNode<T> rightChild);
    public boolean hasRightChild();
    public boolean isLeaf();
    public int getNumberOfNodes();
    public int getHeight();
    public int getHeight(BinaryNode<T> node);
    public BinaryNode<T> copy();
}
