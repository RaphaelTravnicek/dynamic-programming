package helperclasses;

import exceptions.InvalidArgumentException;

public class Item {
    private final String name;
    private final int size;
    private final int value;

    /**
     * Instantiate an Item object representing an item for the Knapsack problem.
     * @param name the name of the item
     * @param size the size of the item
     * @param value the value of the item
     * @throws InvalidArgumentException if name is null, or size is negative, or value is negative
     */
    public Item(String name, int size, int value) throws InvalidArgumentException {
        if (name == null) {
            throw new InvalidArgumentException("Exception: Given name must not be null.");
        }
        if (size < 0) {
            throw new InvalidArgumentException("Exception: Given size must be non-negative.");
        }
        if (value < 0) {
            throw new InvalidArgumentException("Exception: Given value must be non-negative.");
        }
        this.name = name;
        this.size = size;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item " + this.name + ": (Size: " + this.size + ", Value: " + this.value + ")";
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }
}
