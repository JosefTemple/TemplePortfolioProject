package components;

/**
 * Enhanced interface for a roller coaster queue.
 *
 * @param <T>
 *            type of elements in queue (e.g., Rider)
 */
public interface CoasterQueue<T> extends CoasterQueueKernel<T> {

    /**
     * Reports the person at the front of the line without removing them.
     *
     * @return the person at the front
     * @aliases reference returned by front
     * @pre |this| > 0
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Appends all entries from {@code q} to the end of {@code this}. * @param q
     * the queue to append
     *
     * @updates this
     * @clears q
     * @ensures this = #this * #q
     */
    void switchRides(CoasterQueue<T> q);

}
