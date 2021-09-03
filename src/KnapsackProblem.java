import exceptions.InvalidArgumentException;
import helperclasses.*;

public class KnapsackProblem {
    private final Item[] items;
    private final int capacity;
    private int[][] M;

    /**
     * Instantiate a new instance of the Knapsack problem.
     * @param items a list of items with value and size
     * @param capacity the volume of the knapsack
     * @throws InvalidArgumentException if one of the given items is null
     */
    public KnapsackProblem(Item[] items, int capacity) throws InvalidArgumentException {
        if (isValidInput(items)) {
            this.items = items;
        } else {
            throw new InvalidArgumentException("Exception: Given items must not be null.");
        }
        this.capacity = capacity;
        this.M = this.fillArray();
    }

    /**
     * Solve the optimal solution for the knapsack problem for a given knapsack capacity.
     * @param capacity the capacity
     * @return a String representing the optimal solution and the corresponding value
     */
    public String solve(int capacity) throws InvalidArgumentException {
        if (capacity > this.capacity) {
            throw new InvalidArgumentException("Exception: Given capacity must not be greater than knapsack capacity.");
        }
        int i = this.items.length;
        int k = capacity;
        int value = 0;
        StringBuilder s = new StringBuilder("Solution: ");
        while (i > 0 && k > 0) {
            if (this.M[i][k] != M[i - 1][k]) {
                s.append(this.items[i - 1]).append(", ");
                k = k - this.items[i - 1].getSize();
                value += this.items[i - 1].getValue();
            }
            i--;
        }
        s.append("with total value: ").append(value).append(".");
        return s.toString();
    }

    private int[][] fillArray() {
        int[][] M = new int[this.items.length + 1][this.capacity + 1];
        for (int i = 1; i <= this.items.length; i++) {
            for (int j = 0; j <= this.capacity; j++) {
                if (this.items[i - 1].getSize() > j) {
                    M[i][j] = M[i - 1][j];
                } else {
                    M[i][j] = Math.max(M[i - 1][j], this.items[i - 1].getValue() + M[i - 1][j - this.items[i - 1].getSize()]);
                }
            }
        }
        return M;
    }

    private boolean isValidInput(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Knapsack Problem with capacity").append(" ").append(this.capacity).append("\n").append("Items: \n");
        for (int i = 0; i < this.items.length; i++) {
            s.append(this.items[i]);
            if (i != this.items.length - 1) {
                s.append(",\n");
            }
        }
        return s.toString();
    }
}
