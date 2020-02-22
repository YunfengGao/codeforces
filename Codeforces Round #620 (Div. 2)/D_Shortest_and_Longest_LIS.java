import java.io.*;
import java.util.StringTokenizer;

public class D_Shortest_and_Longest_LIS {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(in, out);
        out.close();
    }

    static class TaskD {
        void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                String s = in.next();
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = n - i;
                }
                for (int i = 0; i < s.length(); i++) {
                    int l = i;
                    while (i < s.length() && s.charAt(i) == '<') {
                        i++;
                    }
                    reverse(a, l, i);
                }
                for (int i = 0; i < n; i++) {
                    out.print(a[i] + " ");
                }
                out.println();
                for (int i = 0; i < n; i++) {
                    a[i] = i + 1;
                }
                for (int i = 0; i < s.length(); i++) {
                    int l = i;
                    while (i < s.length() && s.charAt(i) == '>') {
                        i++;
                    }
                    reverse(a, l, i);
                }
                for (int i = 0; i < n; i++) {
                    out.print(a[i] + " ");
                }
                out.println();
            }
        }

        void reverse(int[] a, int l, int r) {
            while (l < r) {
                int tep = a[l];
                a[l] = a[r];
                a[r] = tep;
                l++;
                r--;
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
