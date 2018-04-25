/**
 *   Name:      Arellano, Josue
 *   File:      RedBlackTree.java
 *   Project:   #1
 *   Due:       Feb 17, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package tree;

public class RedBlackTree<T extends Comparable<? super T>>
        extends BinarySearchTree<T>
        implements RedBlackTreeInterface<T>
{
    final private boolean BLACK = true;
    final private boolean RED = false;
    public RedBlackTree()
    {
        setRootNode(null);
    }
    
    public RedBlackTree(T rootData)
    {
        this(rootData, null, null);
    }
    
    public RedBlackTree(T rootData, RedBlackTree<T> leftTree, 
            RedBlackTree<T> rightTree)
    {
        super();
        RedBlackNode<T> x = new RedBlackNode(rootData, BLACK);
        x.setBlack();
        setRootNode(x);
    }
    
    @Override
    public T add(T newEntry)
    {
        T result = null;
        if(isEmpty())
            setRootNode(new RedBlackNode<T>(newEntry, BLACK));
        else result = addEntry(getRootNode(),newEntry).getData();
        
        return result;
    }
    
    private BinaryNode<T> addEntry(BinaryNode<T> rootNode, T newEntry)
    {        
        if(rootNode == null)
            return new RedBlackNode<T>(newEntry, RED);
        
        int comparison = newEntry.compareTo(rootNode.getData());
        
        if(comparison < 0) 
            rootNode.setLeftChild(addEntry(rootNode.getLeftChild(), newEntry));
        else if(comparison > 0)
            rootNode.setRightChild(addEntry(rootNode.getRightChild(), newEntry));
        else
            rootNode.setData(newEntry);
        
        rootNode = fixRedBlack((RedBlackNode<T>)rootNode);
        
        return rootNode;
    }
    
    private BinaryNode<T> fixRedBlack(RedBlackNode<T> G)
    {
        BinaryNode<T> returnNode = G;
        
        if(G.hasLeftChild() && G.hasRightChild() && isBlack(G))
        {
            if(isBlack((RedBlackNode<T>) G.getLeftChild()) 
               && isRed((RedBlackNode<T>)G.getRightChild()))
                returnNode = rightColorInbalance(G);
            else if(isBlack((RedBlackNode<T>) G.getRightChild()) 
               && isRed((RedBlackNode<T>)G.getLeftChild()))
                returnNode = leftColorInbalance(G);
            else if(isRed((RedBlackNode<T>) G.getLeftChild()) 
               && isRed((RedBlackNode<T>)G.getRightChild()))
                colorFlip(G);
        }
        else if (G.hasLeftChild() && isBlack(G))
            returnNode = leftColorInbalance(G);
        else if(G.hasRightChild() && isBlack(G))
            returnNode = rightColorInbalance(G);
        if(G == getRootNode()) setRootNode(returnNode);
        return returnNode;
    }
    
    @Override
    public T remove(T entry)
    {
        ReturnObject oldEntry = new ReturnObject(null);
        removeEntry(getRootNode(), entry, oldEntry);
        return oldEntry.get();
    }
    
    private RedBlackNode<T> removeEntry(BinaryNode<T> rootNode, T entry,
                                      ReturnObject oldEntry)
    {
        if(rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if(comparison == 0)
            {
                oldEntry.set(rootData);
                rootNode = removeFromRoot2((RedBlackNode<T>)rootNode);
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
        return (RedBlackNode<T>)rootNode;
    }
    
    private void remove2(RedBlackNode<T> rootNode, T removeData, 
                        ReturnObject oldEntry)
    {
        if(rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = removeData.compareTo(rootData);
            if(comparison == 0)
            {
                if(rootNode.getRightChild() == null || rootNode.getLeftChild() == null)
                    deleteOneChild2(rootNode, oldEntry);
                else {
                    BinaryNode<T> pred = findLargest(rootNode.getLeftChild());
                    rootNode.setData(pred.getData());
                    remove2((RedBlackNode<T>) rootNode.getLeftChild(), pred.getData(), oldEntry);
                }
            }
            else if(comparison < 0)
                delete((RedBlackNode<T>)rootNode.getLeftChild(), removeData, oldEntry);
            else
                delete((RedBlackNode<T>)rootNode.getRightChild(), removeData, oldEntry);
        }
    }
    
    private void deleteOneChild2(RedBlackNode<T> nodeToDelete, ReturnObject oldEntry)
    {
        RedBlackNode<T> child = nodeToDelete.hasLeftChild() ? 
                                (RedBlackNode<T>)nodeToDelete.getLeftChild() : 
                                (RedBlackNode<T>)nodeToDelete.getRightChild();
        replaceNode(nodeToDelete, child, oldEntry);
        if(nodeToDelete.isBlack())
        {
            if(child.isRed())
                child.setBlack();
            else
                case1(child, oldEntry);
        }
    }
    
    private void case1(RedBlackNode<T> doubleBlackNode, ReturnObject oldEntry)
    {
        if(doubleBlackNode == getRootNode())
            setRootNode(doubleBlackNode);
        else
            case2(doubleBlackNode, oldEntry);
    }
    
    private RedBlackNode<T> case2(RedBlackNode<T> rootNode, ReturnObject oldEntry)
    {
        
    }
    
    private RedBlackNode<T> case3_1(RedBlackNode<T> rootNode)
    {
        if(rootNode == getRootNode())
            rootNode.setBlack();
        else
            rootNode = case3_2(rootNode);
        return rootNode;
    }
    
    private RedBlackNode<T> case3_2(RedBlackNode<T> rootNode)
    {
        
        return rootNode;
    }
    
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
    {
        if(rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            rootNode.setData(largestNode.getData());
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }
        else
        {
            caseHandler((RedBlackNode<T>) rootNode);
            BinaryNode<T> temp;
            if (rootNode != null)
            {
                //temp = findNode(getRootNode(), rootNode.getData());
                //rootNode = temp;
            }
            if (rootNode.hasRightChild())
                rootNode = rootNode.getRightChild();
            else
                rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }
    
    private BinaryNode<T> findNode(BinaryNode<T> rootNode, T val)
    {
        if(rootNode == null || rootNode.getData().equals(val))
            return rootNode;
        
        int comparison = val.compareTo(rootNode.getData());
        
        if(comparison < 0)
            return findNode(rootNode.getLeftChild(), val);
        
        return findNode(rootNode.getRightChild(), val);
    }
    
    private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    }
    
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
    {
        if(rootNode.hasRightChild())
        {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);
        }
        else
        {
            caseHandler((RedBlackNode<T>)rootNode);
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }
    
    private RedBlackNode<T> caseHandler(RedBlackNode<T> deletedNode)
    {
        RedBlackNode<T> returnNode = (RedBlackNode<T>)getRootNode();
        if(isBlack((RedBlackNode<T>)deletedNode) || !deletedNode.isLeaf())
        {
            if(hasOneRedChild(deletedNode))
            {
                RedBlackNode<T> redChild;
                if(deletedNode.hasLeftChild())
                    redChild = (RedBlackNode<T>) deletedNode.getLeftChild();
                else
                    redChild = (RedBlackNode<T>) deletedNode.getRightChild();
                redChild.setBlack();
                if(deletedNode == getRootNode())
                    setRootNode(redChild);
                else
                {
                    BinaryNode<T> parent = getParent(getRootNode(), deletedNode.getData());
                    if(parent.isLeftChild(deletedNode))
                        parent.setLeftChild(redChild);
                    else
                        parent.setRightChild(redChild);
                }
            }
            else
            {
                if(deletedNode != getRootNode())
                {
                    RedBlackNode<T> parent = 
                            (RedBlackNode<T>)getParent(getRootNode(), 
                                                       deletedNode.getData());
                    RedBlackNode<T> sibling;
             
                    if(parent.isLeftChild(deletedNode))
                        sibling = (RedBlackNode<T>) parent.getRightChild();
                    else
                        sibling = (RedBlackNode<T>) parent.getLeftChild();
                        
                    if(isRed(sibling))
                    {
                        if(parent.isLeftChild(deletedNode))
                        {
                            returnNode = rotateLeft(parent);
                            deletedNode = (RedBlackNode<T>)parent.getLeftChild();
                        }
                        else
                        {
                            returnNode = rotateRight(parent);
                            deletedNode = (RedBlackNode<T>)parent.getRightChild();
                        }
                        caseHandler(deletedNode);
                    }
                    else
                    {
                        if(hasRedChild(sibling))
                        {
                            if(parent == getRootNode())
                            {
                                RedBlackNode<T> temp;
                                if(parent.isLeftChild(deletedNode))
                                {
                                    if(sibling.hasRightChild() && 
                                       isRed((RedBlackNode<T>)sibling.getRightChild()))
                                    {
                                        temp = specialRotateLeft(parent);
                                        setRootNode(temp);
                                    }
                                    else
                                    {
                                        temp = rotateRightLeft(parent);
                                        setRootNode(temp);
                                    }
                                }
                                else
                                {
                                    if(sibling.hasLeftChild() &&
                                            isRed((RedBlackNode<T>)sibling.getLeftChild())) 
                                    {
                                        temp = specialRotateRight(parent);
                                        setRootNode(temp);
                                    }
                                    else
                                    {
                                        temp = rotateLeftRight(parent);
                                        setRootNode(temp);
                                    }
                                }
                            }
                            else
                            {
                                RedBlackNode<T> grandParent
                                        = (RedBlackNode<T>) getParent(getRootNode(),
                                                parent.getData());
                                RedBlackNode<T> temp;
                                if (parent.isLeftChild(deletedNode))
                                {
                                    if (sibling.hasRightChild() &&
                                            isRed((RedBlackNode<T>) sibling.getRightChild()))
                                    {
                                        temp = specialRotateLeft(parent);
                                        if (grandParent.isLeftChild(parent))
                                        {
                                            grandParent.setLeftChild(temp);
                                        } else
                                        {
                                            grandParent.setRightChild(temp);
                                        }
                                    } else
                                    {
                                        temp = rotateRightLeft(parent);
                                        if (grandParent.isLeftChild(parent))
                                        {
                                            grandParent.setLeftChild(temp);
                                        } else
                                        {
                                            grandParent.setRightChild(temp);
                                        }
                                    }
                                } else
                                {
                                    if (sibling.hasLeftChild()
                                            && isRed((RedBlackNode<T>) sibling.getLeftChild()))
                                    {
                                        temp = specialRotateRight(parent);
                                        if (grandParent.isLeftChild(parent))
                                        {
                                            grandParent.setLeftChild(temp);
                                        } else
                                        {
                                            grandParent.setRightChild(temp);
                                        }
                                    } else
                                    {
                                        temp = rotateLeftRight(parent);
                                        if (grandParent.isLeftChild(parent))
                                        {
                                            grandParent.setLeftChild(temp);
                                        } else
                                        {
                                            grandParent.setRightChild(temp);
                                        }
                                    }
                                }
                                
                                if (grandParent == getRootNode())
                                {
                                    setRootNode(grandParent); 
                                }
                                else
                                    returnNode = grandParent;
                            }
                        }
                        else
                        {
                            if(isBlack(parent))
                            {
                                sibling.setRed();
                                returnNode = caseHandler(parent);
                            }
                            else
                            {
                                sibling.setRed();
                                parent.setBlack();
                            }                            
                        }
                    }
                }
                else
                    setRootNode(null);
            }
        }
        return returnNode;
    }
    
    private void delete(RedBlackNode<T> rootNode, T entry, ReturnObject obj)
    {
        if(rootNode == null)
            return;
        int comparison = entry.compareTo(rootNode.getData());
        if(comparison == 0)
        {
            if(rootNode.getLeftChild() == null || rootNode.getRightChild() == null)
                deleteOneChild(rootNode, obj);
            else
            {
                BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
                BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
                rootNode.setData(largestNode.getData());
                delete((RedBlackNode<T>)rootNode.getLeftChild(), largestNode.getData(), obj);             
            }
        }
        
        else if(comparison < 0)
            delete((RedBlackNode<T>)rootNode.getRightChild(), entry, obj);
        else
            delete((RedBlackNode<T>)rootNode.getLeftChild(), entry, obj);
    }
    
    private void deleteOneChild(RedBlackNode<T> rootNode, ReturnObject obj)
    {
        BinaryNode<T> child = rootNode.hasLeftChild() ? rootNode.getLeftChild() : rootNode.getRightChild();
        //replaceNode(rootNode, child, obj);
        
    }
    
    private RedBlackNode<T> specialRotateLeftRight(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getLeftChild();
        nodeN.setLeftChild(rotateLeft(nodeC));
        return specialRotateRight(nodeN);
    }

    private RedBlackNode<T> specialRotateRightLeft(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getRightChild();
        nodeN.setRightChild(rotateRight(nodeC));
        RedBlackNode<T> returnNode = specialRotateLeft(nodeN);
        return returnNode;
    }
    private RedBlackNode<T> specialRotateLeft(RedBlackNode<T> rootNode)
    {
        boolean color = rootNode.isBlack();
        rootNode = rotateLeft(rootNode);
        ((RedBlackNode<T>)rootNode.getLeftChild()).setBlack();
        ((RedBlackNode<T>)rootNode.getRightChild()).setBlack();
        rootNode.setColor(color);
        return rootNode;
    }
    
    private RedBlackNode<T> specialRotateRight(RedBlackNode<T> rootNode)
    {
        boolean color = rootNode.isBlack();
        rootNode = rotateRight(rootNode);
        ((RedBlackNode<T>)rootNode.getLeftChild()).setBlack();
        ((RedBlackNode<T>)rootNode.getRightChild()).setBlack();
        rootNode.setColor(color);
        return rootNode;
    }
    
    private boolean hasOneRedChild(RedBlackNode<T> rootNode)
    {
        boolean oneRedChild = false;
        if(rootNode.hasLeftChild() && !rootNode.hasRightChild())
            if(hasRedChild(rootNode))
                oneRedChild = true;
        
        if(rootNode.hasRightChild() && !rootNode.hasLeftChild())
            if(hasRedChild(rootNode))
                oneRedChild = true;
        
        return oneRedChild;
    }
    
    private BinaryNode<T> getParent(BinaryNode<T> rootNode, T val)
    {
        if(rootNode == null || rootNode.isLeaf())
            return null;
        
        int comparison = val.compareTo(rootNode.getData());
        boolean leftCompare = false;
        if(rootNode.hasLeftChild())
            leftCompare = val.equals(rootNode.getLeftChild().getData());
        boolean rightCompare = false;
        if(rootNode.hasRightChild())
            rightCompare = val.equals(rootNode.getRightChild().getData());
        
        if(leftCompare || rightCompare)
            return rootNode;
        
        if(comparison < 0)
            return getParent(rootNode.getLeftChild(), val);
        else
            return getParent(rootNode.getRightChild(), val);
    }
    
    private void colorFlip(RedBlackNode<T> nodeG)
    {
        if(hasRedChild((RedBlackNode<T>) nodeG.getLeftChild())
           || hasRedChild((RedBlackNode<T>) nodeG.getRightChild()))
        {
            ((RedBlackNode<T>) nodeG.getLeftChild()).setBlack();
            ((RedBlackNode<T>) nodeG.getRightChild()).setBlack();      
            if(nodeG != getRootNode())
                nodeG.setRed();
        }           
    }
    
    private boolean hasRedChild(RedBlackNode<T> nodeG)
    {
        boolean redLeft = false;
        boolean redRight = false;
        if(nodeG.hasLeftChild())
            if(isRed((RedBlackNode<T>) nodeG.getLeftChild()))
                redLeft = true;
        
        if(nodeG.hasRightChild())
            if(isRed((RedBlackNode<T>) nodeG.getRightChild()))
                redRight = true;
        
        return redLeft || redRight;                
    }
    
    private RedBlackNode<T> rightColorInbalance(RedBlackNode<T> nodeG)
    {
        RedBlackNode<T> nodeP,nodeN;
        BinaryNode<T> returnNode = nodeG;
        nodeP = (RedBlackNode<T>) nodeG.getRightChild();
        if (isRed(nodeP))
        {
            if (nodeP.hasRightChild())
            {
                nodeN = (RedBlackNode<T>) nodeP.getRightChild();
                if (isRed(nodeN))
                    return rotateLeft(nodeG);
            }
            if (nodeP.hasLeftChild())   
            {
                nodeN = (RedBlackNode<T>) nodeP.getLeftChild();
                if (isRed(nodeN))
                    returnNode = rotateRightLeft(nodeG);
            }
        }
        return (RedBlackNode<T>) returnNode;
    }
    
    private RedBlackNode<T> leftColorInbalance(RedBlackNode<T> nodeG)
    {
        RedBlackNode<T> nodeP, nodeN;
        BinaryNode<T> returnNode = nodeG;
        nodeP = (RedBlackNode<T>) nodeG.getLeftChild();
        if (isRed(nodeP))
        {
            if (nodeP.hasLeftChild())
            {
                nodeN = (RedBlackNode<T>) nodeP.getLeftChild();
                if (isRed(nodeN))
                    return rotateRight(nodeG);
            }
            if (nodeP.hasRightChild())
            {
                nodeN = (RedBlackNode<T>) nodeP.getRightChild();
                if (isRed(nodeN))
                    return rotateLeftRight(nodeG);
            }
        }
        return (RedBlackNode<T>) returnNode;
    }
    
    private RedBlackNode<T> rotateLeftRight(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getLeftChild();
        nodeN.setLeftChild(rotateLeft(nodeC));
        return rotateRight(nodeN);
    }
    
    private RedBlackNode<T> rotateRightLeft(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getRightChild();
        nodeN.setRightChild(rotateRight(nodeC));
        RedBlackNode<T> returnNode = rotateLeft(nodeN);
        return returnNode;
    }
    
    private RedBlackNode<T> rotateRight(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getLeftChild();
        if(nodeN == getRootNode())
            setRootNode(nodeC);
        nodeN.setLeftChild(nodeC.getRightChild());
        nodeC.setRightChild(nodeN);
        nodeC.setBlack();
        nodeN.setRed();
        return nodeC;
    }
    
    private RedBlackNode<T> rotateLeft(RedBlackNode<T> nodeN)
    {
        RedBlackNode<T> nodeC = (RedBlackNode<T>) nodeN.getRightChild();
        if(getRootNode() == nodeN)
            setRootNode(nodeC);
        nodeN.setRightChild(nodeC.getLeftChild());
        nodeC.setLeftChild(nodeN);
        nodeC.setBlack();
        nodeN.setRed();
        return nodeC;
    }
    
    private boolean isBlack(RedBlackNode<T> rootNode)
    {
        return rootNode.isBlack();
    }
    
    private boolean isRed(RedBlackNode<T> rootNode)
    {
        return rootNode.isRed();
    }
    
    public void inorderTraverse()
    {
        inorderTraverse((RedBlackNode<T>) getRootNode());
    }
    
    private void inorderTraverse(RedBlackNode<T> node)
    {
        if(node != null)
        {
            inorderTraverse((RedBlackNode<T>) node.getLeftChild());
            System.out.print(node.getData() + " " 
                             + ((RedBlackNode<T>)node).getColor() + "\n");
            inorderTraverse((RedBlackNode<T>) node.getRightChild());
        }
    }
}
