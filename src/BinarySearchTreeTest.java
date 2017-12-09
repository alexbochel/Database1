/**
 * This test class tests the BinarySearchTree class.
 * 
 * @author Alexander James Bochel
 * @version 9.21.2017
 */
public class BinarySearchTreeTest extends student.TestCase
{

    private BinarySearchTree<String>  tree;
    private BinarySearchTree<Integer> bigTree;
    private String                    y;
    private String                    z;


    /**
     * This sets up the environment for the test.
     */
    public void setUp()
    {
        String x = "Item";
        y = "AnItem";
        z = "ItemThree";

        tree = new BinarySearchTree<String>();
        tree.insert(x);
        tree.insert(y);
        tree.insert(z);

        bigTree = new BinarySearchTree<Integer>();
        bigTree.insert(5);
        bigTree.insert(4);
        bigTree.insert(4);
        bigTree.insert(4);
        bigTree.insert(2);
        bigTree.insert(1);
        bigTree.insert(3);
        bigTree.insert(20);
        bigTree.insert(7);
        bigTree.insert(8);
        bigTree.insert(25);
        bigTree.insert(22);
    }


    /**
     * This ensures that make empty completely removes objects from the tree.
     */
    public void testMakeEmpty()
    {
        assertFalse(tree.isEmpty());
        tree.makeEmpty();
        assertTrue(tree.isEmpty());
    }


    /**
     * This method ensures the find method works when the root is null.
     */
    public void testFindNull()
    {
        tree.makeEmpty();
        assertNull(tree.find("Item"));
    }


    /**
     * This test ensures that the find method of BinarySearchTree works
     * correctly.
     */
    public void testFind()
    {
        assertEquals("AnItem", tree.find(y));
        assertEquals("ItemThree", tree.find(z));
    }


    /**
     * This test ensures that the delete function is working correctly.
     */
    public void testDelete()
    {
        assertTrue(bigTree.delete(1));
        assertTrue(bigTree.delete(3));
        assertTrue(bigTree.delete(2));
        assertTrue(bigTree.delete(4));
        assertTrue(bigTree.delete(4));
        assertTrue(bigTree.delete(4));
        assertTrue(bigTree.delete(20));
        assertTrue(bigTree.delete(7));
        assertTrue(bigTree.delete(8));
        assertTrue(bigTree.delete(22));
        assertTrue(bigTree.delete(25));
        assertFalse(bigTree.isEmpty());
    }


    /**
     * This test ensures that the delete function is working correctly.
     */
    public void testDelete2()
    {
        assertTrue(bigTree.delete(20));
        assertTrue(bigTree.delete(7));
        assertTrue(bigTree.delete(8));
        assertTrue(bigTree.delete(22));
        assertTrue(bigTree.delete(25));
        assertTrue(bigTree.delete(4));
        assertTrue(bigTree.delete(4));
        assertTrue(bigTree.delete(1));
        assertTrue(bigTree.delete(3));
        assertTrue(bigTree.delete(2));
        assertTrue(bigTree.delete(4));
    }


    /**
     * This test checks what happens when deleting an element that is not in the
     * tree.
     */
    public void testDeleteNull()
    {
        assertFalse(bigTree.delete(0));
    }


    /**
     * Tests the inOrderDump method so we can check if it is being printed to
     * the console correctly
     */
    public void testInOrderDump()
    {
        BinarySearchTree<KVPair> pairTree = new BinarySearchTree<KVPair>();

        Handle key1 = new Handle(50);
        Handle value1 = new Handle(500);
        KVPair pair1 = new KVPair(key1, value1);

        Handle key2 = new Handle(40);
        Handle value2 = new Handle(400);
        KVPair pair2 = new KVPair(key2, value2);

        Handle key3 = new Handle(30);
        Handle value3 = new Handle(300);
        KVPair pair3 = new KVPair(key3, value3);

        Handle key4 = new Handle(60);
        Handle value4 = new Handle(600);
        KVPair pair4 = new KVPair(key4, value4);

        Handle key5 = new Handle(75);
        Handle value5 = new Handle(750);
        KVPair pair5 = new KVPair(key5, value5);

        pairTree.insert(pair1);
        pairTree.insert(pair2);
        pairTree.insert(pair3);
        pairTree.insert(pair4);
        pairTree.insert(pair5);

        pairTree.dump();

        assertFalse(pairTree.isEmpty());
    }
}
