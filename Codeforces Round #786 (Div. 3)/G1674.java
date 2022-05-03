import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class G1674 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver();
        solver.solve(in, out);
        out.close();
    }

    private static class Solver {
        private Map<Integer, List<Integer>> mp;
        private int[] degreeIn;
        private int[] degreeOut;
        private int[] dp;

        private void addEdge(int u, int v) {
            List<Integer> nexts = mp.getOrDefault(u, new ArrayList<>());
            nexts.add(v);
            mp.put(u, nexts);
            this.degreeIn[v]++;
            this.degreeOut[u]++;
        }

        private void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            // 已i为头的最长链的长度
            dp = new int[n + 1];
            degreeIn = new int[n + 1];
            degreeOut = new int[n + 1];
            mp = new HashMap<>();

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                this.addEdge(u, v);
            }

            for (int i = 1; i <= n; i++) {
                this.dfs(i);
            }

            Arrays.stream(dp).max().ifPresent(out::println);
        }

        private void dfs(int father) {
            if (dp[father] > 0) {
                return;
            }
            dp[father] = 1;
            // 出度为0的时候，没有下一个点可以走；出度为1的时候，要删一条出边，也没下一个点可以走
            if (degreeOut[father] < 2) {
                return;
            }
            List<Integer> sons = mp.getOrDefault(father, Collections.emptyList());
            for (int son : sons) {
                dfs(son);
                if (degreeIn[son] > 1) {
                    dp[father] = Math.max(dp[father], dp[son] + 1);
                }
            }
        }
    }

    private static class InputReader {
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
    }
}
