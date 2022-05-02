import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D1674 {

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
                boolean ans = true;
                int n = in.nextInt();
                if (n % 2 == 0) {
                    // 1 2 3 4
                    int[] a = new int[n];
                    for (int i = 0; i < n; i++) {
                        a[i] = in.nextInt();
                    }
                    for (int i = 0; i < n - 3; i += 2) {
                        int maxx = Math.max(a[i], a[i + 1]);
                        int minn = Math.min(a[i + 2], a[i + 3]);
                        if (maxx > minn) {
                            ans = false;
                            break;
                        }
                    }
                } else {
                    // 3 2 1
                    n++;
                    int[] a = new int[n];
                    for (int i = 1; i < n; i++) {
                        a[i] = in.nextInt();
                    }
                    a[0] = a[1];
                    for (int i = 0; i < n - 3; i += 2) {
                        int maxx = Math.max(a[i], a[i + 1]);
                        int minn = Math.min(a[i + 2], a[i + 3]);
                        if (maxx > minn) {
                            ans = false;
                            break;
                        }
                    }
                }
                out.println(ans ? "YES" : "NO");
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
