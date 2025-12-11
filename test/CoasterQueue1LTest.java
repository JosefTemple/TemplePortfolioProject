
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.CoasterQueue;
import components.CoasterQueue1L;

/**
 * JUnit test fixture for CoasterQueue1L's kernel methods.
 */
public class CoasterQueue1LTest {

    /**
     * Helper method to create a new generic queue for testing. We use String
     * for the generic type T.
     * 
     * @return a new CoasterQueue1L<String> instance
     */
    private CoasterQueue<String> createFromArgs(String... args) {
        CoasterQueue<String> q = new CoasterQueue1L<>();
        for (String arg : args) {
            q.getInLine(arg);
        }
        return q;
    }

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor() {
        CoasterQueue<String> q = new CoasterQueue1L<>();
        assertEquals(0, q.length());
        assertEquals("[]", q.toString());
    }

    /**
     * Test newInstance.
     */
    @Test
    public void testNewInstance() {
        CoasterQueue<String> q1 = this.createFromArgs("A");
        CoasterQueue<String> q2 = q1.newInstance();

        assertEquals(0, q2.length());
        assertEquals(q1.getClass(), q2.getClass());
    }

    /**
     * Test clear.
     */
    @Test
    public void testClear() {
        CoasterQueue<String> q = this.createFromArgs("A", "B");
        q.clear();
        assertEquals(0, q.length());
        assertEquals("[]", q.toString());
    }

    /**
     * Test transferFrom.
     */
    @Test
    public void testTransferFrom() {
        CoasterQueue<String> q1 = this.createFromArgs("A", "B");
        CoasterQueue<String> q2 = this.createFromArgs();

        q2.transferFrom(q1);

        assertEquals(0, q1.length());
        assertEquals(2, q2.length());
        assertEquals("<A,B>", q2.toString()); // Assuming standard toString format
    }

    /**
     * Test getInLine on an empty queue. Probability of entry is (100-0)/100 =
     * 100%. This should always succeed.
     */
    @Test
    public void testGetInLine_Empty() {
        CoasterQueue<String> q = this.createFromArgs();
        q.getInLine("A");

        assertEquals(1, q.length());
        assertEquals("<A>", q.toString());
    }

    /**
     * Test getInLine on a non-empty queue. We accept that it *might* fail, but
     * we check state consistency.
     */
    @Test
    public void testGetInLine_NonEmpty() {
        CoasterQueue<String> q = this.createFromArgs("A");
        int initialLen = q.length();

        q.getInLine("B");

        // Either it added (len + 1) or it didn't (len same)
        assertTrue(q.length() == initialLen || q.length() == initialLen + 1);

        if (q.length() == initialLen + 1) {
            // If added, verify order
            assertEquals("<A,B>", q.toString());
        }
    }

    /**
     * Test getOnRide. Logic: Either returns T and removes it, or returns null
     * and keeps it.
     */
    @Test
    public void testGetOnRide() {
        CoasterQueue<String> q = this.createFromArgs("A", "B");
        int initialLen = q.length();

        String result = q.getOnRide();

        if (result != null) {
            // Success case: A was removed
            assertEquals("A", result);
            assertEquals(initialLen - 1, q.length());
            assertEquals("<B>", q.toString());
        } else {
            // Failure case: Queue must remain unchanged
            assertEquals(initialLen, q.length());
            assertEquals("<A,B>", q.toString());
        }
    }

    /**
     * Test length.
     */
    @Test
    public void testLength() {
        CoasterQueue<String> q = this.createFromArgs("A", "B", "C");
        // Note: createFromArgs might fail to add B or C due to probability,
        // so we can't hardcode expected length as 3. We rely on q.toString() consistency.

        // Verify length matches the string representation count
        String rep = q.toString();
        int expectedLen = 0;
        if (!rep.equals("<>")) {
            // Simple comma counting for test verification
            expectedLen = rep.length() - rep.replace(",", "").length() + 1;
        }

        assertEquals(expectedLen, q.length());
    }
}