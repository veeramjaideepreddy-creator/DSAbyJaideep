class Solution {
    static class Node {
        char lc, rc;
        int pre, suf, best, len;

        Node(char c) {
            lc = rc = c;
            pre = suf = best = len = 1;
        }

        Node() {}
    }

    char[] s;
    Node[] tree;
    int n;

    public int[] longestRepeating(String S, String queryCharacters, int[] queryIndices) {
        s = S.toCharArray();
        n = s.length;

        int q = queryIndices.length;
        int[] ans = new int[q];

        // Safety for empty string
        if (n == 0) {
            return ans;
        }

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        for (int i = 0; i < q; i++) {
            int pos = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, pos, ch);
            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(s[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private void update(int idx, int l, int r, int pos, char ch) {
        if (l == r) {
            tree[idx] = new Node(ch);
            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, ch);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, ch);
        }

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node merge(Node L, Node R) {
        Node node = new Node();

        node.len = L.len + R.len;
        node.lc = L.lc;
        node.rc = R.rc;

        // Prefix
        node.pre = L.pre;
        if (L.pre == L.len && L.lc == R.lc) {
            node.pre = L.len + R.pre;
        }

        // Suffix
        node.suf = R.suf;
        if (R.suf == R.len && L.rc == R.rc) {
            node.suf = R.len + L.suf;
        }

        // Best
        node.best = Math.max(L.best, R.best);

        // Run crossing the boundary
        if (L.rc == R.lc) {
            node.best = Math.max(node.best, L.suf + R.pre);
        }

        return node;
    }
}
