import java.util.Iterator;
import java.util.Stack;

/**
 * This class implements the binary search tree data structure.
 * 
 * @author Alexander James Bochel
 * @version 9.24.2017
 * @param <T>
 *            The generic type.
 */
public class BinarySearchTree<T extends Comparable<? super T>>
    implements Iterable<T>
{

    private BinaryNode<T> root;


    /**
     * Constructor for the binary search tree.
     */
    public BinarySearchTree()
    {
        root = null;
    }


    /**
     * Empties the binary search tree.
     */
    public void makeEmpty()
    {
        root = null;
    }


    /**
     * Checks to see if the tree is empty.
     * 
     * @return Whether or not the tree is empty.
     */
    public boolean isEmpty()
    {
        return root == null;
    }


    /**
     * Searches for a specific item.
     * 
     * @param x
     *            The node being looked for.
     * @return A generic type that is found by the function.
     */
    public T find(T x)
    {
        return find(x, root);
    }


    /**
     * Helps find specific item using the element and the root
     * 
     * @param x
     *            the node being searched for
     * @return an element of a node
     */
    private T find(T x, BinaryNode<T> elemRoot)
    {
        if (elemRoot == null)
        {
            return null;
        }

        int compareResult = x.compareTo(elemRoot.element);

        if (compareResult < 0)
        {
            return find(x, elemRoot.left);
        }
        else if (compareResult > 0)
        {
            return find(x, elemRoot.right);
        }
        else
        {
            return elemRoot.element;
        }
    }


    /**
     * Function to insert an element into the BST
     * 
     * @param x
     *            item to be inserted into the tree.
     * @return If the insert was succesful.
     */
    public boolean insert(T x)
    {
        root = insert(x, root, 0);
        return (root == null);
    }


    /**
     * Helper function to help insert elements into the BST.
     * 
     * @param x
     *            Item inserted
     * @param elemRoot
     *            Starting point
     * @param currDepth
     *            Depth of the current node
     * @return The node at the insert point.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> elemRoot, int currDepth)
    {
        if (elemRoot == null)
        {
            return new BinaryNode<T>(x, null, null, currDepth);
        }

        int compareResult = x.compareTo(elemRoot.element);

        if (compareResult < 0)
        {
            elemRoot.left = insert(x, elemRoot.left, currDepth + 1);
        }
        else if (compareResult >= 0)
        {
            elemRoot.right = insert(x, elemRoot.right, currDepth + 1);
        }
        return elemRoot;
    }


    /**
     * Function to delete an element into the BST
     * 
     * @param x
     *            Item to be deleted.
     * @return If the item was deleted.
     * @throws Exception
     */
    public boolean delete(T x)
    {
        root = this.delete(x, root);
        return (root != null);
    }


    /**
     * This method helps delete the items from the BST.
     * 
     * @param x
     *            Item to be deleted
     * @param node
     *            Starting point
     * @return The node at the delete point.
     */
    private BinaryNode<T> delete(T x, BinaryNode<T> node)
    {
        BinaryNode<T> result = node;
        if (node == null)
        {
            return node;
        }
        if (x.compareTo(node.getElement()) < 0)
        {
            node.setLeft(delete(x, node.getLeft()));
        }
        else if (x.compareTo(node.getElement()) > 0)
        {
            node.setRight(delete(x, node.getRight()));
        }
        else
        {
            if (node.getLeft() != null && node.getRight() != null)
            {
                node.setElement(findMin(node.getRight()).getElement());
                node.setRight(delete(node.getElement(), node.getRight()));
            }
            else if (node.getLeft() != null)
            {
                result = node.getLeft();
            }
            else
            {
                result = node.getRight();
            }
        }
        return result;
    }


    /**
     * This method finds the minimum value from the starting point.
     * 
     * @param node
     *            The starting point for the search.
     * @return The node at the minimum point.
     */
    private BinaryNode<T> findMin(BinaryNode<T> node)
    {
        if (node.getLeft() == null)
        {
            return node;
        }
        else
        {
            return findMin(node.getLeft());
        }

    }


    /**
     * Function to find the minimum value
     * 
     * @param elemRoot
     *            Root destination.
     * @return Minimum object.
     */
    T minValue(BinaryNode<T> elemRoot)
    {
        T minv = elemRoot.element;
        while (elemRoot.left != null)
        {
            minv = elemRoot.left.element;
            elemRoot = elemRoot.left;
        }
        return minv;
    }


    /**
     * Private Binary Node class
     * 
     * @author Purnima Ghosh
     * @param <T>
     *            The generic type.
     */
    private static class BinaryNode<T>
    {
        private BinaryNode<T> right;
        private BinaryNode<T> left;
        private T             element;
        private int           depth;


        /**
         * Constructor.
         * 
         * @param elem
         *            Element.
         * @param rightChild
         *            Right node.
         * @param leftChild
         *            Left node.
         * @param depth
         *            depth of the tree.
         */
        public BinaryNode(
            T elem,
            BinaryNode<T> rightChild,
            BinaryNode<T> leftChild,
            int depth)
        {
            element = elem;
            right = rightChild;
            left = leftChild;
            depth = 0;
        }


        /**
         * This sets the element of the node.
         * 
         * @param element2
         */
        public void setElement(T element2)
        {
            element = element2;
        }


        /**
         * This gets the element of the node.
         * 
         * @return Element of the node.
         */
        public T getElement()
        {
            return this.element;
        }


        /**
         * Sets the left node.
         * 
         * @param leftNode
         *            Node to the left of the root.
         */
        @SuppressWarnings("unused")
        public void setLeft(BinaryNode<T> leftNode)
        {
            left = leftNode;
        }


        /**
         * Sets the right node.
         * 
         * @param rightNode
         *            Node to the right of the root.
         */
        @SuppressWarnings("unused")
        public void setRight(BinaryNode<T> rightNode)
        {
            right = rightNode;
        }


        /**
         * Gets the left node.
         * 
         * @return Left node.
         */
        public BinaryNode<T> getLeft()
        {
            return left;
        }


        /**
         * Gets the right node.
         * 
         * @return Right node.
         */
        public BinaryNode<T> getRight()
        {
            return right;
        }


        /**
         * Finds the depth of the tree.
         * 
         * @return The depth of the tree.
         */
        public int getDepth()
        {
            return depth;
        }
    }


    /**
     * Gets the height of a specific entree.
     * 
     * @param x
     *            The entry
     * @return The height
     */
    public int getHeight(T x)
    {
        return getNodeHeight(root, x, 0);
    }


    /**
     * This method helps find
     * 
     * @param rootLocal
     *            Root of the tree
     * @param x
     *            Entree for height
     * @param height
     *            Height of the entree
     * @return The height of the entry
     */
    private int getNodeHeight(BinaryNode<T> rootLocal, T x, int height)
    {
        if (rootLocal == null)
        {
            return 0;
        }
        if (rootLocal.element == x)
        {
            return height;
        }

        // check if the node is present in the left sub tree
        int level = getNodeHeight(rootLocal.left, x, height + 1);
        if (level != 0)
        {
            return level;
        }

        // check if the node is present in the right sub tree
        return getNodeHeight(rootLocal.right, x, height + 1);
    }


    /**
     * This method finds the size of the tree.
     * 
     * @return Size of the tree
     */
    public int size()
    {
        return size(root);
    }


    /**
     * This method helps find the size of the tree.
     * 
     * @param node
     *            The root
     * @return The size of the tree
     */
    private int size(BinaryNode<T> node)
    {
        if (node == null)
        {
            return 0;
        }
        else
        {
            return (size(node.left) + 1 + size(node.right));
        }
    }


    public void inOrderDump(BinaryNode<T> treeRoot)
    {
        if (treeRoot == null)
        {
            return;
        }

        // recursively traverse left tree:
        if (treeRoot.getLeft() != null)
        {
            inOrderDump(treeRoot.getLeft());
        }

        // TODO Indenting and Info for the current note
        for (int i = 0; i < treeRoot.getDepth() * 2; i++)
        {
            System.out.println(" ");
        }

        System.out.print(((KVPair)treeRoot.getElement()).getKey());
        System.out.print(" ");
        System.out.println(((KVPair)treeRoot.getElement()).getValue());

        // recursively traverse right tree:
        if (treeRoot.getRight() != null)
        {
            inOrderDump(treeRoot.getRight());
        }
    }


    @Override
    /**
     * Automatic iterator method.
     */
    public Iterator<T> iterator()
    {
        return new BSTIterator(this.root);
    }


    /**
     * Iterator class for traversing the binary search tree.
     * 
     * @author Purnima Ghosh
     * @version 9.21.2017
     */
    private class BSTIterator implements Iterator<T>
    {

        private Stack<BinaryNode<T>> stack;


        /**
         * The constructor for the Iterator.
         * 
         * @param root
         *            Root of the tree
         */
        public BSTIterator(BinaryNode<T> root)
        {
            stack = new Stack<BinaryNode<T>>();
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }
        }


        /**
         * Checks to see whether or not the iterator has a next object .
         */
        @Override
        public boolean hasNext()
        {
            return !stack.isEmpty();
        }


        /**
         * Moves the iterator to the next object. In-order: left, root, right
         */
        @Override
        public T next()
        {
            BinaryNode<T> node = stack.pop();
            T result = node.element;
            if (node.right != null)
            {
                node = node.right;
                while (node != null)
                {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }
}
