import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 给一个n*m的格子堆，其中W代表白色，R代表红色，.代表黑色，任务是把.染成W或者R，使任意的同色格子边不相邻，如果能成功输出涂色后的格子堆 t 组用例(1≤t≤100), n (1≤n≤50) and m (1≤m≤50)
 *
 * 样例： 3 4 6 .R.... ...... ...... .W.... 4 4 .R.W .... .... .... 5 1 R W R W R
 *
 * 结果： YES WRWRWR RWRWRW WRWRWR RWRWRW NO YES R W R W R
 *
 *
 * 暴力：这个矩阵就两种情况，要不使RWRWRW...，要不就是WRWRWR...
 */
public class A1534 {

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
                int m = in.nextInt();
                char[][] s = new char[n][m];
                for (int i = 0; i < n; i++) {
                    s[i] = in.next().toCharArray();
                }
                if (test(s, 'W', 'R')) {
                    print(s, 'W', 'R', out);
                } else if (test(s, 'R', 'W')) {
                    print(s, 'R', 'W', out);
                } else {
                    out.println("NO");
                }
            }
        }

        boolean test(char[][] s, char odd, char even) {
            int n = s.length;
            int m = s[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((i + j) % 2 == 0 && s[i][j] == odd) {
                        return false;
                    } else if (((i + j) % 2 == 1 && s[i][j] == even)) {
                        return false;
                    }
                }
            }
            return true;
        }

        void print(char[][] s, char odd, char even, PrintWriter out) {
            out.println("YES");
            int n = s.length;
            int m = s[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((i + j) % 2 == 0) {
                        s[i][j] = even;
                    } else {
                        s[i][j] = odd;
                    }
                }
                out.println(s[i]);
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
