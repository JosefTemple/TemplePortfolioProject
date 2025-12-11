package components;

import java.util.Iterator;

public abstract class CoasterQueueSecondary<T> implements CoasterQueue<T> {

    /*
     * Secondary Methods
     */
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

    /*
     * Object Methods (Manually added because we removed StandardSecondary)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append(">");
        return sb.toString();
    }

    // Basic equality check (Pointer equality only for now to save time)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return false;
    }
}