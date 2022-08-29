/**
 * id - 207984956.
 * name nadav yakobovich.
 * A HitNotifier class.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener that need to be add to the
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener that need to be remove to the
     */
    void removeHitListener(HitListener hl);
}