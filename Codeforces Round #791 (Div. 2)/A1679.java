import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class A1679 {
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
            // a * 4 + b * 6 == n
            // min (a + b)
            // max (a + b)
            while (t-- > 0) {
                long n = in.nextLong();
                if (n < 4 || n % 2 == 1) {
                    out.println("-1");
                    continue;
                }
                long min = 0;
                long w4 = 0;
                while (w4 * 4 <= n) {
                    long w = n - w4 * 4;
                    if (w % 6 == 0) {
                        min = w4 + w / 6;
                        break;
                    }
                    w4++;
                }

                long max = 0;
                long w6 = 0;
                while (w6 * 6 <= n) {
                    long w = n - w6 * 6;
                    if (w % 4 == 0) {
                        max = w6 + w / 4;
                        break;
                    }
                    w6++;
                }

                out.println(min + " " + max);
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
