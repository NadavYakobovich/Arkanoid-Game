/**
 * id - 207984956.
 * name nadav yakobovich.
 * A Counter Class.
 */
public class Counter {
    private int count;

    /**
     * constructor - set the counter to 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     *
     * @param number that need to add to counter
     */
    void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number that need to decrease
     */
    void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get current count.
     *
     * @return the counter value
     */
    int getValue() {
        return this.count;
    }
}
