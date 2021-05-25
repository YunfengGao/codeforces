import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 序列的权重定义为：在序列a中，a[i]=a[j]且i<j的无序(i,j)对数，比如
 * a=[1,1,2,2,1]的权重为4，其中值相同的无序对为 (1,2),(1,5),(2,5),(3,4)
 *
 * 现在给一个长度为n(1 <= n <= 10^5)的序列a，求a的所有子序列的权重的和
 *
 *
 * 权重最大的场景是a序列中所有元素都是相同的，此时答案量级别为
 * A(10^5, 2) + A(10^5, 3) + A(10^5, 4) + ... + A(10^5, 10^5)
 * = 10^5 * (10^5-1) / 2 + 10^5 * (10^5-1) * (10^5-2) / (2 * 3) + ... + 1
 * = 超过int范围(todo：算下这里是多少)
 *
 * dp[k] 表示以位置k结尾的序列权重和，则本题答案为dp[1] + dp[2] + ... + dp[n]
 * 考虑dp[k] 和 dp[k-1]的关系：dp[k] = dp[k-1] + 位置为k的元素带来的贡献Wk
 * 在k之前，序列中有值与a[k]相等，k才能带来贡献。
 *  6 6 0 7 3 5 [6]
 *  1 2 3 4 5 6  7
 *  新增加的6，带来了多少贡献？
 *
 *
 */
public class C {

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
        void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                int[] a = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    a[i] = in.nextInt();
                }
                long[] dp = new long[n + 1];
                dp[0] = 0;
                Map<Integer, Long> mp = new HashMap<>();
                for (int i = 1; i <= n; i++) {
                    long weight = mp.getOrDefault(a[i], 0L);
                    dp[i] = dp[i - 1] + weight;
                    mp.put(a[i], weight + i);
                }
                long ans = Arrays.stream(dp).sum();
                out.println(ans);
            }
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

    }
}
