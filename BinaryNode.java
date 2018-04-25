/*************************************************************** 
*   file: BinaryNode.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This program defines the methods in the BinaryNode interface.
*            This class allows a user to create a binary node which contains
*            a data and a link to a left and right child. This class also has
*            methods that will allow the user to get the height of the tree
*            and the number of nodes in the tree. Copying trees is also a given
*            function.
* 
****************************************************************/ 
package tree;

public class BinaryNode<T>
        implements BinaryNodeInterface<T>
{
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    
    public BinaryNode()
    {
        this(null);
    }
    
    public BinaryNode(T data)
    {
        this(data, null, null);
    }
    
    public BinaryNode(T data, BinaryNode<T> leftChild,
                                     BinaryNode<T> rightChild)
    {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    //method:  getData
    //purpose: This method returns the data in the Binary Node
    public T getData()
    {
        return data;
    }
    
    //method:  setData
    //purpose: This method sets the data field of the Binary Node to the input
    //         value.
    public void setData(T data)
    {
        this.data = data;
    }
    
    //method:  getLeftChild
    //purpose: This method returns the leftChild of the Binary Node.
    public BinaryNode<T> getLeftChild()
    {
        return leftChild;
    }
    
    //method:  setLeftChild
    //purpose: This method takes a Binary Node as an input and sets it to the 
    //         left child of the node.
    public void setLeftChild(BinaryNode<T> leftChild)
    {
        this.leftChild = leftChild;
    }
    
    //method:  hasLeftChild
    //purpose: This method verifies that the left child of the node is not null.
    //         It then returns true or false.
    public boolean hasLeftChild()
    {
        return leftChild != null;
    }
    
    //method:  getRightChild
    //purpose: This method returns the right child of the node.
    public BinaryNode<T> getRightChild()
    {
        return rightChild;
    }
    
    //method:  setRightChild
    //purpose: This method sets the right child field with the input Binary
    //         node.
    public void setRightChild(BinaryNode<T> rightChild)
    {
        this.rightChild = rightChild;
    }
    
    //method:  hasRightChild
    //purpose: This method verifies that the right child of the node is not 
    //         null. I then returns true or false.
    public boolean hasRightChild()
    {
        return rightChild != null;
    }
    
    //method:  isLeaf
    //purpose: This method checks to see if the node is a binary tree leaf. It
    //         checks by verifying that the right and left child of the node
    //         are null. It then returns true or false.
    public boolean isLeaf()
    {
        return !this.hasLeftChild() && !this.hasRightChild();
    }
    
    //method:  getNumberOfNodes
    //purpose: This method returns the number nodes in the binary tree. It does
    //         this by checking the children of the node. The method first 
    //         checks that the left node is not empty, then it recursively 
    //         gets the number of nodes on the left child. This number is stored
    //         in a variable leftNumber. It then checks the right child and 
    //         also gets the number of nodes recursively on the right child.
    //         Finally it returns the sum of 1, the leftNumber and the 
    //         rightNumber.
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;
        
        if(leftChild != null)
            leftNumber = leftChild.getNumberOfNodes();
        if(rightChild != null)
            rightNumber = rightChild.getNumberOfNodes();
        
        return 1 + leftNumber + rightNumber;
    }
    
    //method:  getHeight
    //purpose: This method utilizes the overloading method with this to return
    //         the height of the tree.
    public int getHeight()
    {
        return getHeight(this);
    }
    
    //method:  getHeight
    //purpose: This method takes a binary node as an input and adds 1 to the
    //         either the height of its left child or its right child. It does
    //         this through recursively getting the height of its children.
    public int getHeight(BinaryNode<T> node)
    {
        int height = 0;
        if(node != null)
            height = 1 + Math.max(getHeight(node.leftChild), 
                                  getHeight(node.rightChild));
        return height;
    }
    
    public int getNumberOfLeaves()
    {
        return getNumberOfLeaves(this);
    }
    
    public int getNumberOfLeaves(BinaryNode<T> node)
    {
        if(node == null)
            return 0;
        if(node.leftChild == null && node.rightChild == null)
            return 1;
        else
            return getNumberOfLeaves(node.leftChild) 
                    + getNumberOfLeaves(node.rightChild);
    }
    
    //method:  copy
    //purpose: This method returns itself in a new BinaryNode. It does this by
    //         creating a new Binary Node and if the roots left and right child
    //         are not empty, it will give the new node these left and right 
    //         children. It then returns the new Binary Node.
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if(leftChild != null)
            newRoot.setLeftChild(leftChild.copy());
        if(rightChild != null)
            newRoot.setRightChild(rightChild.copy());
        return newRoot;
    }
    
    protected boolean isLeftChild(BinaryNode<T> child)
    {
        return leftChild == child;
    }
}
