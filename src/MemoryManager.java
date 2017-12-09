import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * This class contains byte arrays as well as management tools for the artists
 * and songs data.
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 */
public class MemoryManager
{
    private byte[]           dataItems;
    private int              capacity;
    private int              size;
    private static final int OFFSET_LENGTH_ONE   = 1;
    private static final int OFFSET_LENGTH_TWO   = 2;
    private static final int OFFSET_STRING_BEGIN = 3;


    /**
     * This constructor sets the initial size of the byte arrays.
     * 
     * @param size
     *            Initial Size of the byte arrays.
     */
    public MemoryManager(int cap)
    {
        // songCapacity = capacity;
        // artistCapacity = capacity;
        capacity = cap;
        // songNames = new byte[capacity];
        // artistNames = new byte[capacity];
        dataItems = new byte[capacity];
        // nameSize = 0;
        // artistSize = 0;
        size = 0;
    }


    /**
     * Inserts a new item into the byte array that holds the data.
     * 
     * @param name
     *            is the name of the song/artist to insert
     */  
    public void insertItem(String name)
    {
        if (size + name.getBytes().length + 3 > capacity)
        {
            this.expandDataArray();
            capacity = dataItems.length;
        }

        dataItems[size] = 1; // Set the name to be active
        size++;

        for (int i = 2; i < 4; i++)
        {
            dataItems[size] = ByteBuffer.allocate(4).putInt(name.length())
                .array()[i];
            size++;
        }

        for (byte b : name.getBytes())
        {
            dataItems[size] = b;
            size++;
        }
    }

    /**
     * Deletes a song name from the songNames byte array by setting the first
     * byte to 0, thus indicating that it is inactive
     * 
     * @param offset
     *            is the position in the byte array to set to 0
     */
    public void deleteItem(int offset)
    {
        dataItems[offset] = 0;
    }

    /**
     * Exapnds the byte array that holds the names of the artists if the array
     * is too small to hold the next entry. It expands the array by doubling the
     * size.
     */
    public void expandDataArray()
    {
        byte[] newArr = new byte[dataItems.length * 2];
        System.arraycopy(dataItems, 0, newArr, 0, dataItems.length);
        dataItems = newArr;
    }

    // -----------------------------------------------------------------------

    /**
     * This uses an offset to find a String representation of the song.
     * 
     * @param offset
     *            Place in array for song.
     * @return String Songs name.
     */
    public String getItemString(int offset)
    {
        if (offset < 0)
        {
            return "";
        }
        
        byte[] dataByteLength = new byte[4];
        dataByteLength[0] = 0;
        dataByteLength[1] = 0;
        dataByteLength[2] = dataItems[offset + OFFSET_LENGTH_ONE];
        dataByteLength[3] = dataItems[offset + OFFSET_LENGTH_TWO];
        int length = bytesToInt(dataByteLength);

        byte[] itemStringBytes = new byte[length];
        System.arraycopy(dataItems, offset + OFFSET_STRING_BEGIN,
            itemStringBytes, 0, length);
        String itemName = "";
        try
        {
            itemName = new String(itemStringBytes, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return itemName;
    }

    /**
     * This method converts bytes to an integer value.
     * 
     * @param bytes
     *            Byte array to turn into int.
     * @return int Representation of the byte array.
     */
    private int bytesToInt(byte[] bytes)
    {
        int asInt = ByteBuffer.wrap(bytes).getInt();
        return asInt;
    }

    /**
     * Getter method for the byte array that holds the song names
     * 
     * @return the songNames field, which is a byte array
     */
    public byte[] getDataItems()
    {
        return dataItems;
    }

    /**
     * Getter method for the current size of the songNames array. In this
     * context, size is the number of elements in the array
     * 
     * @return the nameSize field, which is an int
     */
    public int getDatabaseSize()
    {
        return size;
    }
}