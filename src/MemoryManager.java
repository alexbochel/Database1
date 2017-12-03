import java.io.UnsupportedEncodingException;

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
            this.expandArray(songNames);
            songCapacity = songNames.length;
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
            this.expandArray(artistNames);
            artistCapacity = artistNames.length;
        }

        for (byte b : artist.getBytes())
        {
            artistNames[artistSize] = b;
            artistSize++;
        }
    }


    /**
     * Deletes a song from the songNames byte array by setting the first byte to
     * 0, thus indicating that it is inactive
     * 
     * @param offset
     *            is the position in the byte array to set to 0
     */
    public void deleteSong(int offset)
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
     * Expands the byte array arr if it is too small to hold the next entry. It
     * doubles the current capacity of the array.
     * 
     * @param arr
     *            is the byte array to make bigger
     */
    public void expandArray(byte[] arr)
    {
        byte[] newArr = new byte[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
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
        int asInt = java.nio.ByteBuffer.wrap(bytes).getInt();
        return asInt;
    }
}
