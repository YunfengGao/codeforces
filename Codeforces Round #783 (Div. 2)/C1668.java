import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class C1668 {
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
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }
            long[] b = new long[n];
            // 找一位不变
            long ans = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                long currentAns = 0;
                b[i] = 0;
                for (int j = i - 1; j >= 0; j--) {
                    long times = b[j + 1] / a[j] + 1;
                    b[j] = a[j] * times;
                    currentAns += times;
                }
                for (int j = i + 1; j < n; j++) {
                    long times = b[j - 1] / a[j] + 1;
                    b[j] = a[j] * times;
                    currentAns += times;
                }
                if (currentAns < ans) {
                    ans = currentAns;
//                    for (int j = 0; j < n; j++) {
//                        out.print(b[j] + " ");
//                    }
//                    out.println("-----------------");
                }
            }
            out.println(ans);
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
