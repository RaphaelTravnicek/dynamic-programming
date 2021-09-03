import exceptions.InvalidArgumentException;

public class LongestCommonSubsequence {
    private final String string1;
    private final String string2;
    private final int[][] M;

    /**
     * Instantiate an instance of the Longest Common Subsequence (LCS) problem.
     * @param string1 the first string
     * @param string2 the second string
     * @throws InvalidArgumentException if string1 is null or string2 is null
     */
    public LongestCommonSubsequence(String string1, String string2) throws InvalidArgumentException {
        if (string1 == null || string2 == null) {
            throw new InvalidArgumentException("Exception: Given Strings must not be null.");
        }
        this.string1 = string1;
        this.string2 = string2;
        this.M = findLCS();
    }

    /**
     * Return a string of the longest common subsequence and its length.
     * @return the solution of the LCS problem
     */
    public String solve() {
        return "Solution: " + this.backtrackSolution() + " with length: " + this.M[this.string1.length()][this.string2.length()];
    }

    private int[][] findLCS() {
        int[][] M = new int[this.string1.length() + 1][this.string2.length() + 1];
        for (int i = 1; i <= this.string1.length(); i++) {
            for (int j = 1; j <= this.string2.length(); j++) {
                if (this.string1.charAt(i - 1) == this.string2.charAt(j - 1)) {
                    M[i][j] = M[i - 1][j - 1] + 1;
                } else {
                    M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
                }
            }
        }
        return M;
    }

    private String backtrackSolution() {
        StringBuilder s = new StringBuilder();
        int i = this.string1.length();
        int j = this.string2.length();
        while (i > 0 && j > 0) {
            if (this.M[i - 1][j] == this.M[i][j]) {
                i--;
            } else if (this.M[i][j - 1] == this.M[i][j]) {
                j--;
            } else {
                s.append(this.string1.charAt(i - 1));
                i--;
                j--;
            }
        }
        s.reverse();
        return s.toString();
    }

    @Override
    public String toString() {
        return "LCS Problem: String 1: " + this.string1 + ", String 2: " + this.string2;
    }
}
