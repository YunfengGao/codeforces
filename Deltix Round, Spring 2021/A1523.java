import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A. Game of Life
 * http://codeforces.com/contest/1523/problem/A
 *
 * 给一个由01构成的长度为n的字符串，如果0的左右有且只有1个1，那么下一回合这个0会变成1，问m回合后，这个字符串会变成什么样子？
 *  (2≤n≤10^3,1≤m≤10^9)
 *  It is guaranteed that the sum of n over all test cases does not exceed 10^4
 *
 * 11 3
 * 01000000001  — initial state
 * 11100000011  — first iteration of evolution
 * 11110000111  — second iteration of evolution
 * 11111001111  — third iteration of evolution
 *
 * 10 2
 * 0110100101  — initial state
 * 1110111101  — first iteration of evolution
 * 1110111101  — second iteration of evolution
 *
 * 思路：不要被m的范围吓到，这个串最多也就10^3长，所以最多也就只有10^3回合，然后保留基于上一个回合的状态，推算下一个回合状态，暴力N^2的复杂度
 *
 */
public class A1523 {

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
                String s = in.next();
                m = Math.min(n, m);
                for (int j = 0; j < m; j++) {
                    StringBuilder nextS = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        if (s.charAt(i) == '1') {
                            nextS.append('1');
                            continue;
                        }
                        int cnt = 0;
                        if (i - 1 >= 0 && s.charAt(i - 1) == '1') {
                            cnt++;
                        }
                        if (i + 1 < s.length() && s.charAt(i + 1) == '1') {
                            cnt++;
                        }
                        if (cnt == 1) {
                            nextS.append('1');
                        } else {
                            nextS.append('0');
                        }
                    }
                    s = nextS.toString();
                }
                out.println(s);
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
