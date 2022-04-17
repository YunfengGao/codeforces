import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class B1659 {
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
                int n = in.nextInt();
                int k = in.nextInt();
                String s = in.next();
                char[] chars = s.toCharArray();
                int[] ans = new int[chars.length];
                if (k % 2 == 0) {
                    for (int i = 0; i < n; i++) {
                        if (chars[i] == '0' && k > 0) {
                            k--;
                            ans[i]++;
                        }
                    }
                    ans[n - 1] += k;
                    for (int i = 0; i < n; i++) {
                        if (ans[i] % 2 != 0) {
                            chars[i] = (char)('0' + ('1' - chars[i]));
                        }
                    }
                } else {
                    for (int i = 0; i < n; i++) {
                        if (chars[i] == '1' && k > 0) {
                            k--;
                            ans[i]++;
                        }
                    }
                    ans[n - 1] += k;
                    for (int i = 0; i < n; i++) {
                        if (ans[i] % 2 == 0) {
                            chars[i] = (char)('0' + ('1' - chars[i]));
                        }
                    }
                }

                out.println(chars);
                for (int an : ans) {
                    out.print(an + " ");
                }
                out.println();
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

    }
}
