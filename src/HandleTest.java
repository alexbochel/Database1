public class HandleTest extends student.TestCase
{

    Handle handle;
    
    public void setUp()
    {
        handle = new Handle(5);
    }

    public void test()
    {
        assertEquals(5, handle.getOffset());
    }

}
