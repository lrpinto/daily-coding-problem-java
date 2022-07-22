import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * Given an array of integers, return a new array such that each element at index i of the new array is 
 * the product of all the numbers in the original array except the one at i.
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. 
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 * 
 * Follow-up: what if you can't use division?
 */
public class ArrayProduct {

    private int[] arr;
    private long product;

    /**
     * This is a O(N) constructor as it makes a call to calculateProduct() in order to initialise the product of all elements in the array
     * @param arr an array of integers
     */
    public ArrayProduct(int[] arr) {
        this.arr = arr;
        this.product = calculateProduct();
    }

    /**
     * This method is Time = O(N) as it performs N iterations, where N is the size of the array
     * @return the product of all elements in the array
     */
    public long calculateProduct() {
        this.product = 1;
        if (this.arr != null) {
            for (int i = 0; i < arr.length; i++) {
                this.product *= arr[i];
            }
        }
        return this.product;
    }

    /**
     * This method is O(1)
     * @param i the index of the element to exclude from the product
     * @return the product of the all elements in the array except element i
     */
    public long divideProductByI(int i) {
        if (arr == null || i < 0 || i >= arr.length) {
            throw new InvalidParameterException("i must be in range of arr length");
        }
        return this.product / arr[i]; // Assumed these divisions will never lead to decimals 
    }

    /**
     * This method is Time = O(N) as it performs N iterations, where N is the size of the array
     * @return the product of all elements in the array
     */
    public long[] productsExceptI() {
        if (this.arr == null) return null;
        long[] productsExceptI = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            productsExceptI[i] = divideProductByI(i);
        }
        return productsExceptI;
    }

    public static void main(String[] args) {

        // Option 1: With division  This is a O(N + N) solution, so it is a linear solution
        int[] arr = {1, 2, 3, 4, 5};
        long[] expected = {120, 60, 40, 30, 24};
        ArrayProduct arrayProduct = new ArrayProduct(arr); // O(N)
        long[] actual = arrayProduct.productsExceptI(); // O(N)
        boolean result = Objects.deepEquals(expected, actual);
        System.out.println(result ? "PASS" : "FAIL");

    }
    
}
