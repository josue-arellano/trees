/**
 *   Name:      Arellano, Josue
 *   File:      RedBlackNode.java
 *   Project:   #1
 *   Due:       Feb 17, 2018
 *   Course:    cs14103-w18
 *
 *   Description:
 *              This
 */
package tree;

/**
 *
 * @author josue
 */
public class RedBlackNode<T> extends BinaryNode<T>
        implements RedBlackInterface<T>
{
    private boolean black;
    final private boolean BLACK = true;
    final private boolean RED = false;
    private boolean doubleBlack;
    
    public RedBlackNode()
    {
        this(null);
    }
    
    public RedBlackNode(T rootData)
    {
        this(rootData, null, null);
    }
    
    public RedBlackNode(T rootData, boolean black)
    {
        this(rootData, null, null, black);
    }
    
    public RedBlackNode(T rootData, RedBlackNode<T> left, RedBlackNode<T> right)
    {
        this(rootData, left, right, false);
    }
    
    public RedBlackNode(T rootData, RedBlackNode<T> left, RedBlackNode<T> right,
                        boolean black)
    {
        super(rootData, left, right);
        this.black = black;
        doubleBlack = false;
    }
    
    public void setDoubleBlack()
    {
        doubleBlack = true;
    }
    
    public boolean isDoubleBlack()
    {
        return doubleBlack;
    }
    
    public boolean isBlack()
    {
        return black;
    }
    
    public boolean isRed()
    {
        return !black;
    }
    
    public void setColor(boolean color)
    {
        black = color;
    }
    
    public String getColor()
    {
        String color;
        if(isBlack())
            color = "black";
        else
            color = "red";
        return color;
    }
    
    public void setBlack()
    {
        black = BLACK;
    }
    
    public void setRed()
    {
        black = RED;
    }
}
