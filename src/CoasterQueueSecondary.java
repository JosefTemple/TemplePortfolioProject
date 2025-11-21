import components.standard.StandardSecondary;

/**
 * Layered implementation of secondary methods for {@code CoasterQueue}.
 *
 * @param <T>
 *            type of elements in queue
 */
public abstract class CoasterQueueSecondary<T>
        extends StandardSecondary<CoasterQueue<T>> implements CoasterQueue<T> {

    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T frontPerson = this.getOnRide();

        this.getInLine(frontPerson);

        for (int i = 0; i < this.length() - 1; i++) {
            T temp = this.getOnRide();
            this.getInLine(temp);
        }

        return frontPerson;
    }

    @Override
    public void switchRides(CoasterQueue<T> q) {
        assert q != null : "Violation of: q is not null";
        assert q != this : "Violation of: q is not this";

        while (q.length() > 0) {
            T x = q.getOnRide();
            this.getInLine(x);
        }
    }

}