import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * B. I Hate 1111
 * http://codeforces.com/contest/1526/problem/B
 *
 * 给一个数X (1≤x≤10^9) ，问X是否可以被任意个 11,111,1111组成？
 *
 * 比如：33=11+11+11
 *       144=111+11+11+11
 *
 *  因为1111 = 11 * 100 + 11，11111 = 111 * 100 + 11，即任何一个大于111的1111...11111都是可以用111和11组成的，
 *  所以这个题目简化为：给一个数X，这个数是否可以用任意个11和111组成？
 *
 *  X = A * 11 + B * 111
 *  X = A * 11 + B * 110 + B
 *  X = A * 11 + 10B * 11 + B
 *  X = (A + 10B) * 11 + B
 *
 *  这里要求 A + 10B > 0才可以，设 A + 10B = Y
 *  B = X - X / 11 * 11
 *  Y = (X - B) / 11
 *  如果 Y >= 10B,就是有可能的
 *
 *  比如说：X = 11111
 *         B = X - X / 11 * 11 = 1
 *         Y = (X - B) / 11 = 1010
 *         Y >= 10B
 *
 *  比如说：X = 1110
 *         B = X - X / 11 * 11 = 10
 *         Y = (X - B) / 11 = 100
 *         Y == 10B
 *
 *  比如说：X = 521
 *         B = X - X / 11 * 11 = 4
 *         Y = (X - B) / 11 = 47
 *         Y > 10B
 */
public class B1526 {

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
                int x = in.nextInt();
                int b = x - (x / 11 * 11);
                int y = (x - b) / 11;
                out.println(y >= 10 * b ? "YES" : "NO");
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
