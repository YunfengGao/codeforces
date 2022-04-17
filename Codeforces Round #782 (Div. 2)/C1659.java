import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class C1659 {
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
        void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                long[] x = new long[n];
                for (int i = 0; i < n; i++) {
                    x[i] = in.nextLong();
                }

                // A 移动首都
                // B 攻占城市
                // 当前首都的绝对位置
                long current = 0;
                // 已攻占城市坐标
                int vis = -1;
                long ans = 0;
                for (int i = 0; i < n; i++) {
                    // 直接攻占的代价
                    long directCost = (x[i] - current) * b;
                    // 先移动再攻占的代价
                    long sumCost = Long.MAX_VALUE;
                    long sumCost2 = Long.MAX_VALUE;
                    if (vis != -1) {
                        // 移动的代价
                        long costA = (x[vis] - current) * a;
                        // 移动为未来节省的代价
                        long saveB = (x[vis] - current) * (n - 1 - i) * b;
                        // 攻占i的代价
                        long costB = (x[i] - x[vis]) * b;
                        sumCost = costA + costB;
                        sumCost2 = sumCost - saveB;
                    }
                    if (sumCost2 <= directCost) {
                        current = x[vis];
                        ans += sumCost;
                    } else {
                        ans += directCost;
                    }
                    vis = i;
                }
                out.println(ans);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
