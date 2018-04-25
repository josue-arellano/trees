/*************************************************************** 
*   file: BinaryTree.java 
*   author: J. Arellano 
*   class: CS 241 – Data Structures and Algorithms II
* 
*   assignment: program 1 
*   date last modified: 1/20/2018 
* 
*   purpose: This source file contains the definitions to the methods
*            needed to create a binary tree. This class allows to create
*            a binary tree that can be traversed in order, postorder and 
*            pre-order. 
* 
****************************************************************/ 
package tree;

public class BinaryTree<T>
        implements BinaryTreeInterface<T>
{
    private BinaryNode<T> root;
    
    public BinaryTree()
    {
        root = null;
    }
    
    public BinaryTree(T data)
    {
    root = new BinaryNode<>(data);
    }
    
    public BinaryTree(T data, BinaryTree<T> leftTree,
                              BinaryTree<T> rightTree)
    {
        privateSetTree(data, leftTree, rightTree);
    }
    
    //method:  setTree
    //purpose: This method creates a new BinaryBinary node to the private root.
    public void setTree(T data)
    {
        root = new BinaryNode<T>(data);
    }
    
    //method:  setTree
    //purpose: This method utilizes the privateSetTree() method to fill the
    //         root with a data and insert a left child and a right child.
    public void setTree(T data, BinaryTreeInterface<T> leftTree,
                                BinaryTreeInterface<T> rightTree)
    {
        privateSetTree(data, (BinaryTree<T>)leftTree, 
                             (BinaryTree<T>)rightTree);
    }
    
    //method:  privateSetTree
    //purpose: This method fills the root of the Binary Tree with a new 
    //         BinaryNode with data. It also will fill the left child and
    //         right child of the root with binary trees but it first checks
    //         the inputs are not empty so it will not fill the tree with 
    //         nothing, then it makes sure that the two inputs are not the same 
    //         binary node. Finally, it clears the inputs.
    private void privateSetTree(T data, BinaryTree<T> leftTree,
                                        BinaryTree<T> rightTree)
    {
        root = new BinaryNode<T>(data);
        
        if((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
        if((rightTree != null) && !rightTree.isEmpty())
        {
            if(rightTree == leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        }
        
        if((leftTree != null) && (leftTree != this))
            leftTree.clear();
        if((rightTree != null) && rightTree != this)
            rightTree.clear();
    }
    
    //method:  getRootData
    //purpose: This method returns the data of the root node.
    public T getRootData()
    {
        T rootData = null;
        if(root != null)
            rootData = root.getData();
        return rootData;
    }   
    
    //method:  isEmpty
    //purpose: This method checks to see that the binary tree is not empty.
    //         It returns either true or false.
    public boolean isEmpty()
    {
        return root == null;
    }
    
    //method:  clear
    //purpose: This method sets the root to null to make the binary tree emtpy.
    public void clear()
    {
        root = null;
    }
    
    //method:  setRootData
    //purpose: This method sets the root's data to the input value.
    protected void setRootData(T data)
    {
        root.setData(data);
    }
    
    //method:  setRootNode
    //purpose: This method fills the root with a given BinaryNode.
    protected void setRootNode(BinaryNode<T> rootNode)
    {
        root = rootNode;
    }
    
    //method:  getRootNode
    //purpose: This method returns the root node.
    protected BinaryNode<T> getRootNode()
    {
        return root;
    }
    
    //method:  getHeight
    //purpose: This method utilizes the getHeight() method of a the BinaryNode
    //         class to get height of the tree.
    public int getHeight()
    {
        return root.getHeight();
    }
    
    public int getNumberOfLeaves()
    {
        return root.getNumberOfLeaves();
    }
    
    //method:  getNumberOfNodes()
    //purpose: This method utilizes the getNumberOfNodes() method of the 
    //         BinaryNode class to get the number of nodes in the binary tree.
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    }
    
    //method:  inorderTraverse
    //purpose: This method utilizes the overloading method that takes an input
    //         and inputs the root of the node.
    public void inorderTraverse()
    {
        inorderTraverse(root);
    }
    
    //method:  inorderTraverse
    //purpose: This method traverses the input node in order. The method first
    //         checks to make sure that the input node is not null. Then it will
    //         recursively inorder traverse in the nodes left child. It will 
    //         then output the value in the node. Finally, it will recursively
    //         inorder traverse the nodes right child.
    private void inorderTraverse(BinaryNode<T> node)
    {
        if(node != null)
        {
            inorderTraverse(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inorderTraverse(node.getRightChild());
        }
    }
    
    //method:  postorderTraverse
    //purpose: This method utilizes the overloading method that takes an input
    //         and inputs the root of the node.
    public void postorderTravers()
    {
        postorderTraverse(root);
    }
    
    //method:  postorderTraverse
    //purpose: This method traverses the input in post order. The method first
    //         checks to make sure that the input node is not null. Then it will
    //         recursively post order traverse the nodes left child. It will 
    //         then recursively post order traverse the nodes right child. 
    //         Finally it will output the value in the node.
    private void postorderTraverse(BinaryNode<T> node)
    {
        if(node != null)
        {
            postorderTraverse(node.getLeftChild());
            postorderTraverse(node.getRightChild());
            System.out.print(node.getData() + " ");
        }
    }
    
    //method:  preorderTraverse
    //purpose: This method utilizes the overloading method that takes an input
    //         and inputs the root of the node.
    public void preorderTraverse()
    {
        preorderTraverse(root);
    }
    
    //method:  preorderTraverse
    //purpose: This method traverses the input in pre-order. The method first
    //         checks to make sure that the input node is not null. Then it will
    //         output the value in the node. Next, it will recursively pre order
    //         traverse the node's left child. Finally, it will recursively 
    //         pre-order traverse the node's right child.
    private void preorderTraverse(BinaryNode<T> node)
    {
        if(node != null)
        {
            System.out.print(node.getData() + " ");
            preorderTraverse(node.getLeftChild());
            preorderTraverse(node.getRightChild());
        }
    }
}
