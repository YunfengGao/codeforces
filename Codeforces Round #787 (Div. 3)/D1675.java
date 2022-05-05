import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class D1675 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver();
        solver.solve(in, out);
        out.close();
    }

    static class Solver {
        private Map<Integer, List<Integer>> mp;
        private int[] degreeIn;
        private int[] vis;

        private void addEdge(int u, int v) {
            List<Integer> nexts = mp.getOrDefault(u, new ArrayList<>());
            nexts.add(v);
            mp.put(u, nexts);
            degreeIn[v]++;
        }

        void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                mp = new HashMap<>();
                degreeIn = new int[n + 1];
                vis = new int[n + 1];

                int root = 0;
                for (int i = 1; i <= n; i++) {
                    int parentNo = in.nextInt();
                    if (parentNo == i) {
                        root = i;
                    } else {
                        this.addEdge(i, parentNo);
                    }
                }

                int ans = 0;
                List<List<Integer>> details = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    if (degreeIn[i] == 0 && vis[i] == 0) {
                        List<Integer> detail = bfs(i, root);
                        if (ans == 0) {
                            detail.add(root);
                        }
                        details.add(detail);
                        ans++;
                    }
                }

                out.println(ans);
                for (List<Integer> detail : details) {
                    out.println(detail.size());
                    for (int i = detail.size() - 1; i >= 0; i--) {
                        out.print(detail.get(i) + " ");
                    }
                    out.println();
                }
                out.println();
            }
        }

        List<Integer> bfs(int x, int rootNo) {
            List<Integer> ans = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(x);
            vis[x] = 1;
            while (!queue.isEmpty()) {
                int now = queue.poll();
                if (now == rootNo) {
                    break;
                }
                ans.add(now);
                List<Integer> nexts = mp.getOrDefault(now, Collections.emptyList());
                for (Integer next : nexts) {
                    if (vis[next] == 0) {
                        vis[next] = 1;
                        queue.offer(next);
                    }
                }
            }
            return ans;
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
