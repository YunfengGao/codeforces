import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class A1668 {
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
                int a = in.nextInt();
                int b = in.nextInt();
                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                // a 小 b 大
                if (a == 1) {
                    if (b == 1) {
                        out.print(0);
                    } else if (b == 2) {
                        out.print(1);
                    } else {
                        out.print(-1);
                    }
                    out.println();
                    continue;
                }
                int basic = (a - 1) * 2;
                int dis = (b - a);
                int longDis = dis / 2;
                int shortDis = dis - longDis;
                int ans = basic + longDis * 3 + shortDis;
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
