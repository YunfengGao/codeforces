import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B110 {

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
                int odd = 0;
                int even = 0;
                int[] odds = new int[n];
                for (int i = 0; i < n; i++) {
                    int x = in.nextInt();
                    if (x % 2 == 0) {
                        even++;
                    } else {
                        odds[odd] = x;
                        odd++;
                    }
                }
                int ans = 0;
                if (even > 0) {
                    ans += ((n - 1) + odd) * even / 2;
                }
                for (int i = 0; i < odd; i++) {
                    for (int j = i + 1; j < odd; j++) {
                        if (getGCD(odds[i], odds[j]) > 1) {
                            ans++;
                        }
                    }
                }
                out.println(ans);
            }
        }

        int getGCD(int m, int n) {
            if (n == 0) {
                return m;
            }
            return getGCD(n, m % n);
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
