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
     * @return the product of all elements in the array except element i
     */
    public long[] productsExceptI_WithoutDivision_ONN() {
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

    /**
     * This method is Time = O(N+N) as it performs N + N iterations, where N is the size of the array, so it is a linear solution
     * @return the product of all elements in the array except element i
     */
    public long[] productsExceptI_WithoutDivision_O2N() {
        if (this.arr == null) return null;
        long[] productsExceptI = new long[arr.length];

        // First pass (left to right): calculate products of a before a[i]
        productsExceptI[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            productsExceptI[i] = productsExceptI[i-1] * this.arr[i-1];
        }

        // Second pass: (right to left): multiply the already set product of a before a[i] by products of a after a[i] 
        long productAfter = 1;
        for (int j = (productsExceptI.length - 2); j >= 0; j--) {
            productAfter *= this.arr[j+1];
            productsExceptI[j] *= productAfter;
        }

        return productsExceptI;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        long[] expected = {120, 60, 40, 30, 24};
        ArrayProduct arrayProduct = new ArrayProduct(arr); // O(N)

        int[] arr1 = {3, 2, 1};
        long[] expected1 = {2, 3, 6};
        ArrayProduct arrayProduct1 = new ArrayProduct(arr1); // O(N)


        // Option 1: With division  This is a O(N + N) solution, so it is a linear solution
        long[] actual = arrayProduct.productsExceptI_WithDivision(); // O(N)
        boolean result = Objects.deepEquals(expected, actual);
        System.out.println(result ? "PASS" : "FAIL");

        long[] actual1 = arrayProduct1.productsExceptI_WithDivision(); // O(N)
        boolean result1 = Objects.deepEquals(expected1, actual1);
        System.out.println(result1 ? "PASS" : "FAIL");


        // Option 2: Without division  This is a O(N x N) solution, so it is a quadratic solution
        long[] actual3 = arrayProduct.productsExceptI_WithoutDivision_ONN(); // O(N^2)
        boolean result3 = Objects.deepEquals(expected, actual3);
        System.out.println(result3 ? "PASS" : "FAIL");

        long[] actual4 = arrayProduct1.productsExceptI_WithoutDivision_ONN(); // O(N^2)
        boolean result4 = Objects.deepEquals(expected1, actual4);
        System.out.println(result4 ? "PASS" : "FAIL");

        // Option 3: Is there a solution without division better than O(N^2)? 
        long[] actual5 = arrayProduct.productsExceptI_WithoutDivision_O2N(); // O(2N)
        boolean result5 = Objects.deepEquals(expected, actual5);
        System.out.println(result5 ? "PASS" : "FAIL");

        long[] actual6 = arrayProduct1.productsExceptI_WithoutDivision_O2N(); // O(2N)
        boolean result6 = Objects.deepEquals(expected1, actual6);
        System.out.println(result6 ? "PASS" : "FAIL");

    }
    
}
