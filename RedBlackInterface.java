/**
 *   Name:      Arellano, Josue
 *   File:      RedBlackInterface.java
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
public interface RedBlackInterface<T>
        extends BinaryNodeInterface<T>
{
    public String getColor();
    public void setBlack();
    public void setRed();
}
