import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [Easy]
 * 
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * Bonus: Can you do this in one pass?
 * 
 * Problem Source: https://www.dailycodingproblem.com/
 * 
 * Solution by Luisa R. Pinto
 */
public class KComplement {

    private int k;

    public KComplement(int k) {
        this.k = k;
    }

    public int getKComplement(int n) {
        return this.k - n;
    }

    public boolean hasKComplements(int[] arr) {
        Set<Integer> kComplements = new HashSet<>();
        for (int i = 0; i < arr.length ; i++) {
            int kComplement = this.getKComplement(arr[i]);
            if (kComplements.contains(kComplement)) {
                return true;
            } else {
                kComplements.add(arr[i]);
            }
        }
        return false;
    }

    public static void main (String[] args) throws Exception {
        // ------------ Expect a pass as the list has complements of 17 = 10 + 7

        int[] arr = {10, 15, 3, 7};
        int k = 17;

        KComplement kComplement = new KComplement(k);

        String errorMessage = String.format("Expected %s to have complements of %d", Arrays.toString(arr), k);
        String successMessage = String.format("%s has complements of %d", Arrays.toString(arr), k);

        assertTrue(errorMessage, successMessage, kComplement.hasKComplements(arr));


        // ------------ Expect a fail as the list no longer has complements of 17 (removed 7)

        int[] arr1 = {10, 15, 3};
        k = 17;

        errorMessage = String.format("Expected %s to NOT have complements of %d", Arrays.toString(arr1), k);
        successMessage = String.format("%s does not have complements of %d", Arrays.toString(arr1), k);

        assertFalse(errorMessage, successMessage, kComplement.hasKComplements(arr1));

        // ------------ Expect fail as a negative of complement shoudl not count as complement

        int[] arr2 = {10, 15, 3, -7};
        k = 17;
        
        errorMessage = String.format("Expected %s to NOT have complements of %d", Arrays.toString(arr2), k);
        successMessage = String.format("%s does not have complements of %d", Arrays.toString(arr2), k);
        
        assertFalse(errorMessage, successMessage, kComplement.hasKComplements(arr2));

        // ------------ Expect fail as no two numbers add to -3
        int[] arr3 = {10, 15, 3, -7};
        k = -3;

        kComplement = new KComplement(k);

        errorMessage = String.format("Expected %s to NOT have complements of %d", Arrays.toString(arr3), k);
        successMessage = String.format("%s does not have complements of %d", Arrays.toString(arr3), k);

        assertFalse(errorMessage, successMessage, kComplement.hasKComplements(arr3));

        // ------------ Expect pass as 10 + (-7) = 3
        int[] arr4 = {10, 15, 3, -7};
        k = 3;
        
        kComplement = new KComplement(k);
        
        errorMessage = String.format("Expected %s to have complements of %d", Arrays.toString(arr4), k);
        successMessage = String.format("%s has complements of %d", Arrays.toString(arr4), k);

        assertTrue(errorMessage, successMessage, kComplement.hasKComplements(arr4));

    }

    private static void assertTrue(String errorMessage, String successMessage, boolean value) throws Exception {
        if (!value) {
            throw new Exception(errorMessage);
        } else {
            System.out.println(successMessage);
        }
    }

    private static void assertFalse(String errorMessage, String successMessage, boolean value) throws Exception {
        if (value) {
            throw new Exception(errorMessage);
        } else {
            System.out.println(successMessage);
        }
    }
}
