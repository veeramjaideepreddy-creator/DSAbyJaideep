class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] calls = new List[n];
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) { calls[i] = new ArrayList<>(); adj[i] = new ArrayList<>(); }
        for (int[] e : invocations) {
            int a = e[0], b = e[1];
            calls[a].add(b);
            adj[a].add(b);
            adj[b].add(a);
        }

        boolean[] suspicious = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        suspicious[k] = true;
        stack.push(k);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : calls[u]) {
                if (!suspicious[v]) { suspicious[v] = true; stack.push(v); }
            }
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) { visited[i] = true; stack.push(i); }
        }
        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    suspicious[v] = false;
                    stack.push(v);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!suspicious[i]) ans.add(i);
        return ans;
    }
}