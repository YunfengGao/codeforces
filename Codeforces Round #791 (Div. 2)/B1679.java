import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class B1679 {
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
            int n = in.nextInt();
            int q = in.nextInt();
            long[] a = new long[n + 1];
            int[] time = new int[n + 1];
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextLong();
                sum += a[i];
            }
            int allTime = 0;
            long allTimeValue = 0;

            for (int i = 1; i <= q; i++) {
                int type = in.nextInt();
                if (type == 1) {
                    int x = in.nextInt();
                    long y = in.nextLong();

                    long valueX = a[x];
                    // 位置x已经被全局修改过了，取全局值
                    if (allTime > time[x]) {
                        valueX = allTimeValue;
                    }
                    long diff = y - valueX;
                    sum += diff;
                    a[x] = y;
                    time[x] = i;
                    out.println(sum);
                } else {
                    long y = in.nextLong();
                    sum = y * n;
                    out.println(sum);
                    allTime = i;
                    allTimeValue = y;
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

        Long nextLong() {
            return Long.valueOf(next());
        }
    }
}
