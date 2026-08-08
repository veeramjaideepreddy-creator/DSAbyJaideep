class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] suffixMatch = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) j--;
            suffixMatch[i] = m - 1 - j;
        }

        int[] result = new int[m];
        int i = 0, jx = 0;
        boolean usedChange = false;
        while (i < n && jx < m) {
            if (word1.charAt(i) == word2.charAt(jx)) {
                result[jx++] = i++;
            } else if (!usedChange && suffixMatch[i + 1] >= m - jx - 1) {
                result[jx++] = i++;
                usedChange = true;
            } else {
                i++;
            }
        }

        return jx == m ? result : new int[0];
    }
}