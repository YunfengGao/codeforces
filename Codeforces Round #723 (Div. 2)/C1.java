import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * C1. Potions (Easy Version)
 * https://codeforces.com/contest/1526/problem/C1
 *
 * 从左到由有n(1≤n≤2000)个点，每个点有一个价值a[i](−10^9≤ai≤10^9)，每次可以选择获取a[i]或者忽略a[i]，问在初始值为0，累计值始终不为负数的情况下，最多可以取到多少个点？
 * input
 * 6
 * 4 -4 1 -3 1 -3
 * output
 * 5
 * 在样例中可以取1,3,4,5,6共5个点
 *
 * dp[i][j]表示当前处理第i个点，取到的总点数为j时，的总价值
 * 那么答案为 dp[n][j]里面价值大于0的，最大的j
 *
 * 状态转移方程：
 * for j : 1 -> i
 * 若dp[i-1][j-1] + a[j] >= 0 则：
 * dp [i][j] =
 *      max(
 *          dp[i-1][j-1] + a[i] 表示满足>=0条件，取第i个点,此时的总点数为j，总价值为dp[i-1][j-1] + a[i]
 *          dp[i-1][j] 表示不取第i个点，已经拿了j个点时的总价值
 *      )
 * 否则
 * dp [i][j] =  dp[i-1][j] 表示不取第i个点，已经拿了j个点时的总价值
 *
 */
public class C1 {

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
            int n = in.nextInt();
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextLong();
            }

            long[][] dp = new long[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Integer.MIN_VALUE;
                }
            }

            for (int i = 1; i <= n; i++) {
                dp[i][0] = 0;
                for (int j = 1; j <= i; j++) {
                    if (dp[i - 1][j - 1] + a[i] < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + a[i], dp[i - 1][j]);
                    }
                }
            }

            int ans = n;
            while (dp[n][ans] < 0) {
                ans--;
            }
            out.println(ans);
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
