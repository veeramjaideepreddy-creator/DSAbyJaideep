class Solution {
    public int minimumPushes(String word) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) cnt[c - 'a']++;
        Arrays.sort(cnt);
        int total = 0;
        for (int i = 0; i < 26; i++) {
            int freq = cnt[25 - i];
            total += freq * (i / 8 + 1);
        }
        return total;
    }
}