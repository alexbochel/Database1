/**
 * This class will test the methods and functions contained in the KVPairTree
 * class
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/3/2017
 */
public class KVPairTreeTest extends student.TestCase
{
    MemoryManager manager;
    KVPairTree tree;
    
    /**
     * Sets up the testing environment
     */
    public void setUp()
    {
        manager = new MemoryManager(100);
        tree = new KVPairTree(manager, true);
    }
    
    public void testInsert()
    {
        
    }
}
