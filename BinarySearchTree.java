/*************************************************************** 
*   file: BinarySearchTree.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This source file contains the definitions to the methods
*            needed to create a binary search tree. This class extends 
*            Comparable so that we could compare the different types. It also
*            extends the BinaryTree class and implements the 
*            SearchTreeInterface. This class will allow the user to create
*            a binary search tree that places inputs in order. This class
*            allows the user to delete, add and access predecessors and 
*            successors to any of the nodes in the search tree.
* 
****************************************************************/ 
package tree;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>>
        extends BinaryTree<T>
        implements SearchTreeInterface<T>
{    
    public BinarySearchTree()
    {
        super();
    }
    
    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    }
    
    //method:  setTree
    //purpose: This method overloads the BinaryTree method so that the user
    //         is unable to use this method.
    public void setTree(T rootData)
    {
        throw new UnsupportedOperationException();
    }
    
    //method:  setTree
    //purpose: This method overloads the BinaryTree method so that the user
    //         is unable to use this method.
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                               BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    }
    
    //method:  getEntry
    //purpose: This method allows the user to find an input in the binary search
    //         tree. This method utilizes the findEntry method.
    public T getEntry(T entry)
    {
        return findEntry(getRootNode(), entry);
    }
    
    //method:  findEntry
    //purpose: This method finds an input in the given Binary Node. First the
    //         method sets a variable result to null then it checks if the 
    //         input node is null. Then the node data is checked. if the data is
    //         equal to the input value, then the value is returned. If the
    //         node data is greater than the input value then it recursively
    //         finds the entry in the left child of the node.
    private T findEntry( BinaryNode<T> rootNode, T entry)
    {
        T result = null;
        if(rootNode != null)
        {
            T rootEntry = rootNode.getData();
            if(entry.equals(rootEntry))
                result = rootEntry;
            else if(entry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(), entry);
            else result = findEntry(rootNode.getRightChild(), entry);
        }
        return result;
    }
    
    //method:  contains
    //purpose: This method checks to see if the tree has a value. It does this
    //         by inputing the value into the getEntry method.
    public boolean contains(T entry)
    {
        return getEntry(entry) != null;
    }
    
    //method:  add
    //purpose: This method adds a new value to the Binary Search Tree. It does 
    //         this by checking the root. If the root is empty it will create
    //         a BinaryNode and set it to the root. If it is not empty it will
    //         utilize the addEntry method to insert the value. It will return
    //         null if the value is a new input. If not, it will return the
    //         found value which should be the same as the input.
    public T add(T newEntry)
    {
        T result = null;
        if(isEmpty())
            setRootNode(new BinaryNode<T>(newEntry));
        else result = addEntry(getRootNode(), newEntry);
        
        return result;
    }
    
    //method:  addEntry
    //purpose: This method inputs an entry into the Binary Search Tree. First,
    //         the method asserts that input node is not null. It then compares
    //         entry with the root data. If the entry is found, then it will 
    //         set the root to the entry and then return the root data. If the
    //         entry is less than the root, it will check if the root has a 
    //         left child then recursively add the entry to the left child. If 
    //         the node does not have a left child it will create the left child
    //         and input the entry. A similar process is done on the right 
    //         child if the entry is greater than the node value.
    private T addEntry(BinaryNode<T> rootNode, T newEntry)
    {
        assert rootNode != null;
        T result = null;
        
        int comparison = newEntry.compareTo(rootNode.getData());
        
        if(comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(newEntry);
        }
        else if(comparison < 0)
        {
            if(rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), newEntry);
            else 
                rootNode.setLeftChild(new BinaryNode<> (newEntry));
        }
        else 
        {
            assert comparison < 0;
            if(rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), newEntry);
            else 
                rootNode.setRightChild(new BinaryNode<>(newEntry));
        }
        return result;
    }
    
    //method:  getPredecessor
    //purpose: This method utilizes the overloading method to find the 
    //         predecessor of the entry.
    public T getPredecessor(T entry)
    {
        ReturnObject pre = new ReturnObject(null);
        getPredecessor(getRootNode(), entry, pre);
        return pre.get();
    }

    //method:  getPredecessor
    //purpose: This method returns the predecessor of the entry. It first checks
    //         that the input node is not empty and that the value is actually
    //         in the tree. It compares the entry with the root data. If they
    //         match then predecessor is found in the left childs furthest right
    //         node. If the value is less than the node data, then it 
    //         recursively checks for the predecessor in the left child. If it
    //         is greater than the root data, then the root data is set as the 
    //         predecessor and it recursively looks for the predecessor in the
    //         right child.
    private BinaryNode<T> getPredecessor(BinaryNode<T> rootNode, 
                                         T entry, ReturnObject pre)
    {
        if(rootNode != null && contains(entry))
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if(comparison == 0)
            {
                if(rootNode.hasLeftChild())
                {
                    BinaryNode<T> temp = rootNode.getLeftChild();
                    pre.set(findLargest(temp).getData());
                }
            }
            else if(comparison < 0)
            {
                rootNode = getPredecessor(rootNode.getLeftChild(), entry, pre);
            }
            else
            {
                pre.set(rootNode.getData());
                rootNode = getPredecessor(rootNode.getRightChild(), entry, pre);
            }
        }
        return rootNode;
    }
    
    //method:  getSuccessor
    //purpose: This method utilizes the overloading method to find the 
    //         successor of the entry.
    public T getSuccessor(T entry)
    {
        ReturnObject suc = new ReturnObject(null);
        getSuccessor(getRootNode(), entry, suc);
        return suc.get();
    }

    //method:  getSuccessor
    //purpose: This method first checks if the given binary node is not null
    //         and that the tree actually contains the entry. Then it will
    //         compare the entry to the root data. If the entry is found, then
    //         it will replace the root data with the entry and return the 
    //         root data. If the entry is greater than the entry then it will
    //         recursively find the successor in the right childs leftmost 
    //         child. If it is less than the root node then it will set the 
    //         successor as the root data. Then it will recursively find the 
    //         successor in the left sub tree.
    private BinaryNode<T> getSuccessor(BinaryNode<T> rootNode, 
                                         T entry, ReturnObject suc)
    {
        if(rootNode != null && contains(entry))
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if(comparison == 0)
            {
                if(rootNode.hasRightChild())
                {
                    BinaryNode<T> temp = rootNode.getRightChild();
                    suc.set(findSmallest(temp).getData());
                }
            }
            else if(comparison > 0)
            {
                rootNode = getSuccessor(rootNode.getRightChild(), entry, suc);
            }
            else
            {
                suc.set(rootNode.getData());
                rootNode = getSuccessor(rootNode.getLeftChild(), entry, suc);
            }
        }
        return rootNode;
    }
    
    //method:  findSmallest
    //purpose: This method finds the leftmost child of the input value. It does
    //         this by recursively going to the left child until it finds a null
    //         node. It then returns the node.
    private BinaryNode<T> findSmallest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasLeftChild())
            rootNode = findLargest(rootNode.getLeftChild());
        return rootNode;
    }
    
    //method:  remove
    //purpose: This method utilizes the removeEntry method to remove an entry.
    //         It then returns the removed entry.
    public T remove(T entry)
    {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);
        return oldEntry.get();
    }
    
    //method:  removeEntry
    //purpose: This method first checks that the input node is not null. Then
    //         it compares the input to the root data. If they are equal, then
    //         it will set return the oldEntry and use the removeFromRoot method
    //         to delete the node. If the entry is less than the root, then it
    //         will set the left child to the removed node from the left child.
    //         It will do the same to the right child if the entry is greater
    //         than the root data.
    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry,
                                      ReturnObject oldEntry)
    {
        if(rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if(comparison == 0)
            {
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            }
            else if(comparison < 0)
            {
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                rootNode.setLeftChild(removeEntry(leftChild, 
                                                  entry, oldEntry));
            }
            else
            {
                BinaryNode<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, 
                                                   entry, oldEntry));
            }
        }
        return rootNode;
    }
    
    //method:  removeFromRoot
    //purpose: This method removes the root of a given node. If the tree has
    //         two children, then it will find the largest node of the left
    //         child and set it as the root. Then it will remove the largest 
    //         value of the left child. If it only has a right child, then the
    //         root is set with the right child.
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
    {
        if(rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            rootNode.setData(largestNode.getData());
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }
        else if(rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }
    
    //method:  findLargest
    //purpose: This method finds the rightmost child of the input value. It does
    //         this by recursively going to the right child until it finds a 
    //         null node. It then returns the node.
    private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    }
    
    //method:  removeLargest
    //purpose: This method removes the largest of the nodes. If the node has a 
    //         right child then it will recursively remove the right child. If 
    //         it does not have a right child it will set the root with the
    //         left child.
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasRightChild())
        {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);
        }
        else rootNode = rootNode.getLeftChild();
        return rootNode;
    }
    
    public void between(T small, T large)
    {
        this.between(getRootNode(), small, large);
    }
    
    private void between(BinaryNode<T> rootNode, T small, T large) 
    {         
        if (rootNode == null)
            return;
 
        if (small.compareTo(rootNode.getData()) < 0) 
            between(rootNode.getLeftChild(), small, large);
 
        if (small.compareTo(rootNode.getData()) <= 0
            && large.compareTo(rootNode.getData()) >= 0) {
            System.out.print(rootNode.getData() + " ");
        }
 
        if (large.compareTo(rootNode.getData()) > 0) {
            between(rootNode.getRightChild(), small, large);
        }
    }
    
    //class:   ReturnObject
    //purpose: This class is used in the BinarySearchTree class to store values
    //         in recursive methods. It only has a single data field that can be 
    //         accessed and changed through methods.
    class ReturnObject
    {
        T data;
        ReturnObject(T data)
        {
            this.data = data;
        }
        
        //method:  get
        //purpose: This method returns the data field.
        public T get()
        {
            return data;
        }
        
        //method:  set
        //purpsoe: This method sets the data with the input value.
        public void set(T data)
        {
            this.data = data;
        }
    }
}
