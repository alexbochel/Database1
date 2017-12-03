/**
 * This test class tests the BinarySearchTree class.
 * 
 * @author Alexander James Bochel
 * @version 9.21.2017
 * 
 */
public class BinarySearchTreeTest extends student.TestCase {

    private BinarySearchTree<String> tree;
    private BinarySearchTree<Integer> bigTree;
    private String x;
    private String y; 
    private String z; 

    /**
     * This sets up the environment for the test. 
     */
    public void setUp()
    { 
        x = "Item";
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
        assertTrue(bigTree.delete(5));
        assertTrue(bigTree.isEmpty());
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
     * This test checks what happens when deleting an element that is not
     * in the tree. 
     */
    public void testDeleteNull()
    {
        assertFalse(bigTree.delete(0));
    }
}
