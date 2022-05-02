import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class C1674 {
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
                String a = in.next();
                String b = in.next();
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < b.length(); i++) {
                    char c = b.charAt(i);
                    set.add(c);
                }

                long ans;
                if (set.size() == 1) {
                    if (set.iterator().next() == 'a') {
                        // 只有1个a
                        ans = b.length() == 1 ? 1 : -1;
                    } else {
                        // 只有一个其他 b
                        ans = (1L << a.length());
                    }
                } else {
                    if (set.contains('a')) {
                        // 有a 也有其他字母：abvd
                        ans = -1;
                    } else {
                        // 没有a 有其他字母：bcd
                        ans = (1L << a.length());
                    }
                }
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
