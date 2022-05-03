import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class F1674 {
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
            int n = in.nextInt();
            int m = in.nextInt();
            int q = in.nextInt();

            int[] col = new int[m];
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                String temp = in.next();
                map[i] = temp.toCharArray();
            }

            int countStar = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '*') {
                        countStar++;
                        col[j]++;
                    }
                }
            }

            // q: 2 * 1e5
            // m: 1e3
            // n: 1e3
            while (q-- > 0) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                if (map[x][y] == '*') {
                    map[x][y] = '.';
                    countStar--;
                    col[y]--;
                } else {
                    map[x][y] = '*';
                    countStar++;
                    col[y]++;
                }
                int needCol = countStar / n;
                int needRow = countStar % n;
                int temp = 0;
                for (int i = 0; i < needCol; i++) {
                    temp += col[i];
                }
                for (int i = 0; i < needRow; i++) {
                    temp += map[i][needCol] == '*' ? 1 : 0;
                }
                out.println(countStar - temp);
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
