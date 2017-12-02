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


    /**
     * Constructor for the Handle class, initializes the offset variable
     * 
     * @param byteOffset
     *            is the location of the name/artist in the byte array
     */
    public Handle(int byteOffset)
    {
        offset = byteOffset;
    }


    /**
     * returns the offset field, which is the location of the name/artist in the
     * byte array
     * 
     * @return the offset field as an int
     */
    public int getOffset()
    {
        return offset;
    }


    /**
     * Compares the offset in this Handle to the offset in another Handle
     */
    public int compareTo(Handle handle2)
    {
        return (this.offset - handle2.getOffset());
    }

}
