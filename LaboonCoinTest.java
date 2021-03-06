//Brian Dang and Alex Coroi's LaboonCoinTest.java

import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Re-usable LaboonCoin reference.
    LaboonCoin _l;

    // Create a new LaboonCoin instance before each test.
    @Before
    public void setup() {
    _l = new LaboonCoin();
    }

    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
    assertNotNull(_l);
    }

    // Creating a block String with valid data should return
    // a valid block.  This includes printing data out
    // normally, the previous hash and current hash as a hex
    // representations of themself, and the nonce in hex
    // repretentation.  Values should be delimited by
    // pipes.
    @Test
    public void testCreateBlockValid() {
    int prevHash = 0x0;
    int nonce = 0x16f2;
    int hash = 0x545ac;
    String validBlock = _l.createBlock("kitten", prevHash, nonce, hash);
    assertEquals("kitten|00000000|000016f2|000545ac", validBlock);
    }

    // Trying to represent a blockchain which has no blocks
    // in it should just return an empty string.
    @Test
    public void testGetBlockChainNoElements() {
    // By default, _l.blockchain has no elements in it.
    // So we can just test it immediately.
    String blockChain = _l.getBlockChain();
    assertEquals("", blockChain);
    }


    // Viewing the blockchain as a full string which has valid
    // elements should include all of the elements.  Note that the
    // final element DOES have a trailing \n!
    @Test
    public void testGetBlockChainElements() {
    _l.blockchain.add("TESTBLOCK1|00000000|000010e9|000a3cd8");
    _l.blockchain.add("TESTBLOCK2|000a3cd8|00002ca8|0008ff30");
    _l.blockchain.add("TESTBLOCK3|0008ff30|00002171|0009f908");
    String blockChain = _l.getBlockChain();
    assertEquals("TESTBLOCK1|00000000|000010e9|000a3cd8\nTESTBLOCK2|000a3cd8|00002ca8|0008ff30\nTESTBLOCK3|0008ff30|00002171|0009f908\n", blockChain);
    }

    //----------------------------------------------------------------//
    //validHash() unit tests
    //----------------------------------------------------------------//

    // Ensure that validHash() returns true when the first argument
    // is the number of leading zeros for the second argument
    // tests a happy path
    @Test
    public void testValidHash4() {
        assertTrue(_l.validHash(4, 0x00001111));
    }

    // Ensure that validHash() returns false when the first argument
    // is NOT the number of leading zeros for the second argument
    // tests an edge case where it is off by one and the first argument = 1
    @Test
    public void testInvalidHash1(){
        assertFalse(_l.validHash(1, 0x10000000));
    }

    // Ensure that validHash() returns false when the first argument
    // is NOT the number of leading zeros for the second argument
    // tests another edge case where it is off by but the first argument != 1
    @Test
    public void testInvalidHash5(){
        assertFalse(_l.validHash(5, 0x00002fba));
    }

    //----------------------------------------------------------------//
    //hash() unit tests
    //----------------------------------------------------------------//

    //Ensure that hash() returns the correct hash for the given passed
    //in string. For example, when "bill" is passed in, the hash that is
    //returned is "0x53c4142c. This is testing a happy path.
    @Test
    public void testValidInput() {
        assertEquals(_l.hash("bill"), 0x53c4142c);
    }
	
    //Ensure that hash() returns 0x00989680 for a null or empty string.
    //This tests an edge case.
    @Test
    public void testNullInput() {
	assertEquals(_l.hash(""), 0x00989680);
    }
	
    //Ensure that hash() doesn't return 0x00989680 for a string that is
    //technically "empty" but actually has characters. This tests
    //an edge case.

    @Test
    public void testSpaceInput() {
	assertNotEquals(_l.hash("     "), 0x00989680);
    }

}
