/**
 * The Handle class is used to keep track of the offset of the song names and
 * artsit names in the byte arrays.
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class Handle implements Comparable<Handle>
{
    private int offset;


    public Handle(int byteOffset)
    {
        offset = byteOffset;
    }
    
    public int getOffset()
    {
        return offset;
    }


    @Override
    public int compareTo(Handle handle2)
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
