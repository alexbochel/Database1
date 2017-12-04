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
    private byte[]           songNames;
    private byte[]           artistNames;
    private int              songCapacity;
    private int              artistCapacity;
    private int              nameSize;
    private int              artistSize;
    private static final int OFFSET_LENGTH_ONE   = 1;
    private static final int OFFSET_LENGTH_TWO   = 2;
    private static final int OFFSET_STRING_BEGIN = 3;


    /**
     * This constructor sets the initial size of the byte arrays.
     * 
     * @param size
     *            Initial Size of the byte arrays.
     */
    public MemoryManager(int capacity)
    {
        songCapacity = capacity;
        artistCapacity = capacity;
        songNames = new byte[capacity];
        artistNames = new byte[capacity];
        nameSize = 0;
        artistSize = 0;
    }


    /**
     * Inserts a new song name into the byte array that holds the song names
     * 
     * @param name
     *            is the title of the song to insert
     */
    public void insertName(String name)
    {
        if (nameSize + name.getBytes().length > songCapacity)
        {
            this.expandNameArray();
            songCapacity = songNames.length;
        }

        songNames[nameSize] = 1; // Set the name to be active
        nameSize++;

        for (byte b : ByteBuffer.allocate(2).putInt(name.length()).array())
        {
            songNames[nameSize] = b;
            nameSize++;
        }

        for (byte b : name.getBytes())
        {
            songNames[nameSize] = b;
            nameSize++;
        }
    }


    /**
     * Inserts a new artist into the byte array that holds the names of the
     * artists
     * 
     * @param artist
     *            is the name of the artist to insert
     */
    public void insertArtist(String artist)
    {
        if (artistSize + artist.getBytes().length > artistCapacity)
        {
            this.expandArtistArray();
            artistCapacity = artistNames.length;
        }

        artistNames[artistSize] = 1; // Set the name to be active
        artistSize++;

        for (byte b : ByteBuffer.allocate(2).putInt(artist.length()).array())
        {
            artistNames[nameSize] = b;
            artistSize++;
        }

        for (byte b : artist.getBytes())
        {
            artistNames[artistSize] = b;
            artistSize++;
        }
    }


    /**
     * Deletes a song name from the songNames byte array by setting the first
     * byte to 0, thus indicating that it is inactive
     * 
     * @param offset
     *            is the position in the byte array to set to 0
     */
    public void deleteName(int offset)
    {
        songNames[offset] = 0;
    }


    /**
     * Deletes an artist from the artistNames byte array by setting the first
     * byte to 0, thus indicating that it is inactive
     * 
     * @param offset
     *            is the position in the byte array to set to 0
     */
    public void deleteArtist(int offset)
    {
        artistNames[offset] = 0;
    }


    /**
     * Exapnds the byte array that holds the names of the artists if the array
     * is too small to hold the next entry. It expands the array by doubling the
     * size.
     */
    public void expandArtistArray()
    {
        byte[] newArr = new byte[artistNames.length * 2];
        System.arraycopy(artistNames, 0, newArr, 0, artistNames.length);
        artistNames = newArr;
    }


    /**
     * Exapnds the byte array that holds the names of the songs if the array is
     * too small to hold the next entry. It expands the array by doubling the
     * size.
     */
    public void expandNameArray()
    {
        byte[] newArr = new byte[songNames.length * 2];
        System.arraycopy(songNames, 0, newArr, 0, songNames.length);
        songNames = newArr;
    }


    // -----------------------------------------------------------------------

    /**
     * This uses an offset to find a String representation of the song.
     * 
     * @param offset
     *            Place in array for song.
     * @return String Songs name.
     */
    public String getSongString(int offset)
    {
        byte[] songByteLength = new byte[2];
        songByteLength[0] = songNames[offset + OFFSET_LENGTH_ONE];
        songByteLength[1] = songNames[offset + OFFSET_LENGTH_TWO];
        int length = bytesToInt(songByteLength);

        byte[] songStringBytes = new byte[length];
        System.arraycopy(songNames, offset + OFFSET_STRING_BEGIN,
            songStringBytes, 0, length);
        String songTitle = "";
        try
        {
            songTitle = new String(songStringBytes, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return songTitle;
    }


    /**
     * This uses an offset to find a String representation of the artist.
     * 
     * @param offset
     *            Place in array for artist.
     * @return String Artists name.
     */
    public String getArtistString(int offset)
    {
        byte[] artistByteLength = new byte[2];
        artistByteLength[0] = artistNames[offset + OFFSET_LENGTH_ONE];
        artistByteLength[1] = artistNames[offset + OFFSET_LENGTH_TWO];
        int length = bytesToInt(artistByteLength);

        byte[] artistStringBytes = new byte[length];
        System.arraycopy(artistNames, offset + OFFSET_STRING_BEGIN,
            artistStringBytes, 0, length);
        String artistName = "";

        try
        {
            artistName = new String(artistStringBytes, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return artistName;
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
        int asInt = bytes[0] | bytes[1] << 8;
        return asInt;
    }


    /**
     * Getter method for the byte array that holds the song names
     * 
     * @return the songNames field, which is a byte array
     */
    public byte[] getSongNames()
    {
        return songNames;
    }


    /**
     * Getter method for the byte array that holds the artist names
     * 
     * @return the artistNames field, which is a byte array
     */
    public byte[] getArtistNames()
    {
        return artistNames;
    }


    /**
     * Getter method for the current size of the songNames array. In this
     * context, size is the number of elements in the array
     * 
     * @return the nameSize field, which is an int
     */
    public int getNameSize()
    {
        return nameSize;
    }


    /**
     * Getter method for the current size of the artistNames array. In this
     * context, size is the number of elements in the array
     * 
     * @return the artistSize field, which is an int
     */
    public int getArtistSize()
    {
        return artistSize;
    }
}
