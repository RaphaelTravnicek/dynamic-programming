import exceptions.InvalidArgumentException;

public class ConstrainedLCS {
    private final String string1;
    private final String string2;
    private final String pattern;
    private final int[][][] M;

    /**
     * Instantiate a new Constrained Common Longest Subsequence (CLCS) problem instance.
     * @param string1 the first string
     * @param string2 the second string
     * @param pattern the pattern string
     * @throws InvalidArgumentException if string1 or string2 or pattern is null
     */
    public ConstrainedLCS(String string1, String string2, String pattern) throws InvalidArgumentException {
        if (string1 == null || string2 == null || pattern == null) {
            throw new InvalidArgumentException("Exception: Given Strings must not be null.");
        }
        this.string1 = string1;
        this.string2 = string2;
        this.pattern = pattern;
        this.M = fillArray();
    }

    /**
     * Return a string of the constrained longest common subsequence and its length.
     * @return the solution of the CLCS problem
     */
    public String solve() {
        return "Solution: " + this.backtrackCLCS() + " with length: " + this.M[this.pattern.length()][this.string1.length()][this.string2.length()];
    }

    private int[][][] fillArray() {
        int[][][] M = new int[this.pattern.length() + 1][this.string1.length() + 1][this.string2.length() + 1];
        for (int i = 0; i <= this.pattern.length(); i++) {
            for (int j = 0; j <= this.string1.length(); j++) {
                for (int k = 0; k <= this.string2.length(); k++) {
                    if (j == 0 || k == 0) {
                        if (i != 0) {
                            M[i][j][k] = Integer.MIN_VALUE;
                        }
                    } else {
                        char s1 = this.string1.charAt(j - 1);
                        char s2 = this.string2.charAt(k - 1);
                        if (i > 0 && s1 == s2 && s2 == this.pattern.charAt(i - 1)) {
                            M[i][j][k] = 1 + M[i - 1][j - 1][k - 1];
                        } else if (s1 == s2 && (i == 0 || s1 != this.pattern.charAt(i -1))) {
                            M[i][j][k] = 1 + M[i][j - 1][k - 1];
                        } else {
                            M[i][j][k] = Math.max(M[i][j - 1][k], M[i][j][k - 1]);
                        }
                    }
                }
            }
        }
        return M;
    }

    private String backtrackCLCS() {
        StringBuilder s = new StringBuilder();
        int i = this.pattern.length();
        int j = this.string1.length();
        int k = this.string2.length();
        while (i >= 0 && j >= 1 && k >= 1) {
            if (this.M[i][j][k] == this.M[i][j - 1][k]) {
                j--;
            } else if (this.M[i][j][k] == this.M[i][j][k - 1]) {
                k--;
            } else if (this.M[i][j][k] == this.M[i][j - 1][k - 1]) {
                s.append(this.string1.charAt(j - 1));
                j--;
                k--;
            } else {
                s.append(this.string1.charAt(j - 1));
                i--;
                j--;
                k--;
            }
        }
        s.reverse();
        return s.toString();
    }

    @Override
    public String toString() {
        return "LCS Problem: String 1: " + this.string1 + ", String 2: " + this.string2 + ", Pattern: " + this.pattern;
    }
}
