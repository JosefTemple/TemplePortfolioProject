package components;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * {@code CoasterQueue} represented as a {@code java.util.List} with
 * probabilistic implementations.
 *
 * @param <T>
 *            type of elements in queue
 * @convention $this.rep is not null
 * @correspondence this = [value of $this.rep elements in sequence]
 */
public class CoasterQueue1L<T> extends CoasterQueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    private List<T> rep;

    private void createNewRep() {
        this.rep = new LinkedList<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    public CoasterQueue1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final CoasterQueue<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(CoasterQueue<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof CoasterQueue1L<?> : ""
                + "Violation of: source is of dynamic type CoasterQueue1L<?>";

        CoasterQueue1L<T> localSource = (CoasterQueue1L<T>) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void getInLine(T x) {
        assert x != null : "Violation of: x is not null";

        double chanceToEnter = (100.0 - this.rep.size()) / 100.0;

        if (Math.random() < chanceToEnter) {
            this.rep.add(x);
        }
    }

    @Override
    public final T getOnRide() {
        assert this.length() > 0 : "Violation of: this /= <>";

        if (Math.random() > 0.05) {
            return this.rep.remove(0);
        } else {
            return null;
        }
    }

    @Override
    public final int length() {
        return this.rep.size();
    }

    /*
     * Iterator ---------------------------------------------------------------
     */

    @Override
    public final Iterator<T> iterator() {
        return this.rep.iterator();
    }
}
