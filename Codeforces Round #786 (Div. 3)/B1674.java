import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class B1674 {
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
            Map<String, Integer> ans = new HashMap<>();
            int cnt = 1;
            for (char i = 'a'; i <= 'z'; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (i == j) {
                        continue;
                    }
                    String s = String.valueOf(i)+ j;
                    ans.put(s, cnt);
                    cnt++;
                }
            }
            int t = in.nextInt();
            while (t-- > 0) {
                String str = in.next();
                out.println(ans.get(str));
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

        Long nextLong() {
            return Long.valueOf(next());
        }
    }
}
