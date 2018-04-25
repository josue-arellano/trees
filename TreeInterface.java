/*************************************************************** 
*   file: TreeInterface.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This program is the interface for the Tree class. 
*            I extend this interface for use in the Binary
*            Tree class.
* 
****************************************************************/ 
package tree;

public interface TreeInterface<T>
{
    public T getRootData();
    public int getHeight();
    public int getNumberOfNodes();
    public boolean isEmpty();
    public void clear();    
}
