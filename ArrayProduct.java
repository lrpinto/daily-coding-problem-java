import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * [Hard]
 * 
 * Given an array of integers, return a new array such that each element at index i of the new array is 
 * the product of all the numbers in the original array except the one at i.
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. 
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 * 
 * Follow-up: what if you can't use division?
 * 
 * Problem Source: https://www.dailycodingproblem.com/
 * 
 * Solution by Luisa R. Pinto
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
    public long[] productsExceptI_WithDivision() {
        if (this.arr == null) return null;
        long[] productsExceptI = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            productsExceptI[i] = divideProductByI(i);
        }
        return productsExceptI;
    }

    /**
     * This method is Time = O(NxN) as it performs N x N iterations, where N is the size of the array
     * @return the product of all elements in the array
     */
    public long[] productsExceptI_WithoutDivision() {
        if (this.arr == null) return null;
        long[] productsExceptI = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            long productForI = 1;
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    productForI *= arr[j];
                }
            }
            productsExceptI[i] = productForI;
        }
        return productsExceptI;
    }

    public static void main(String[] args) {

        // Option 1: With division  This is a O(N + N) solution, so it is a linear solution
        int[] arr = {1, 2, 3, 4, 5};
        long[] expected = {120, 60, 40, 30, 24};
        ArrayProduct arrayProduct = new ArrayProduct(arr); // O(N)
        long[] actual = arrayProduct.productsExceptI_WithDivision(); // O(N)
        boolean result = Objects.deepEquals(expected, actual);
        System.out.println(result ? "PASS" : "FAIL");

        int[] arr1 = {3, 2, 1};
        long[] expected1 = {2, 3, 6};
        ArrayProduct arrayProduct1 = new ArrayProduct(arr1); // O(N)
        long[] actual1 = arrayProduct1.productsExceptI_WithDivision(); // O(N)
        boolean result1 = Objects.deepEquals(expected1, actual1);
        System.out.println(result1 ? "PASS" : "FAIL");



        // Option 2: Without division  This is a O(N x N) solution, so it is a quadratic solution
        int[] arr3 = {1, 2, 3, 4, 5};
        long[] expected3 = {120, 60, 40, 30, 24};
        ArrayProduct arrayProduct3 = new ArrayProduct(arr3); // O(N)
        long[] actual3 = arrayProduct3.productsExceptI_WithoutDivision(); // O(N)
        boolean result3 = Objects.deepEquals(expected3, actual3);
        System.out.println(result3 ? "PASS" : "FAIL");

        int[] arr4 = {3, 2, 1};
        long[] expected4 = {2, 3, 6};
        ArrayProduct arrayProduct4 = new ArrayProduct(arr4); // O(N)
        long[] actual4 = arrayProduct4.productsExceptI_WithoutDivision(); // O(N)
        boolean result4 = Objects.deepEquals(expected4, actual4);
        System.out.println(result4 ? "PASS" : "FAIL");

        // Option 3: Is there a solution without division better than O(N^2)? 

    }
    
}
