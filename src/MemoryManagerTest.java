/**
 * This test class tests the memory manager class. 
 * 
 * @author Alexander James Bochel
 * @version 12.3.2017
 *
 */
public class MemoryManagerTest {
    
    private MemoryManager memManager;
    
    /**
     * This method sets up the testig environment for the test class. 
     */
    public void setUp()
    {
        int initialSize = 100;
        memManager = new MemoryManager(initialSize);
    }
    
    
}
