import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E1674 {
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
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int ans = Integer.MAX_VALUE;
            // 敲两个相邻的
            for (int i = 0; i < n - 1; i++) {
                ans = Math.min(ans, this.attackBeside(a[i], a[i + 1]));
            }
            // 靠溅射伤害
            for (int i = 1; i < n - 1; i++) {
                ans = Math.min(ans, this.attackTwoSide(a[i - 1], a[i + 1]));
            }
            // 单独敲两块不相邻的
            Arrays.sort(a);
            ans = Math.min(ans, upper(a[0], 2) + upper(a[1], 2));

            out.println(ans);
        }

        private int attackBeside(int a, int b) {
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a * 2 < b) {
                return upper(b, 2);
            } else {
                return upper(a + b, 3);
            }
            // 先把高生命压低
//            int large = (b - a) / 2;
//            b -= large * 2;
            // 不能减少到负数
//            a -= large;
            // 如果小墙减成0，大墙就不能减3了，要减2
//            return upper(a + b, 3) + large;
        }

        private int attackTwoSide(int a, int b) {
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            return a + upper(b - a, 2);
        }

        private int upper(int a, int b) {
            if (a % b == 0) {
                return a / b;
            }
            return a / b + 1;
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
