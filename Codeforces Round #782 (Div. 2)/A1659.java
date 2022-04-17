import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class A1659 {
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
            int k = in.nextInt();
            while (k-- > 0) {
                int n, r, b;
                n = in.nextInt();
                r = in.nextInt();
                b = in.nextInt();
                int x = r / (b + 1);
                int ext = r - x * (b + 1);
                for (int i = 1; i <= r; i++) {
                    out.print('R');
                    if (i % x == 0 && ext > 0) {
                        out.print('R');
                        ext--;
                        r--;
                    }
                    if (i % x == 0 && b > 0) {
                        out.print('B');
                        b--;
                    }
                }
                while (b > 0) {
                    out.print('B');
                    b--;
                }
                out.println();
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
