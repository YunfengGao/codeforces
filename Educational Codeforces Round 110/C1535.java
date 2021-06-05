import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * 我的想法是这样的：寻找一个最大的满足条件的区间，若这个区间长度为n，则这个区间内的答案为：(1 + n) * n / 2，然后查找下一个区间。
 * 针对带有???的区间，要减掉一个???的代价，因为带有???的区间会处理两次
 * 但是写起来很难处理，if...else条件太多了
 *
 * 比如：    01010101???0110
 * 可以分为：01010101???   ???01  10 这三个区间
 *
 *
 */
public class C1535 {

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
                String s = in.next();
                long ans = 0;

                s += '*';
                int lastPos = -1;
                long maxLength = 0;
                for (int i = 0; i < s.length() - 1; i++) {
                    char x = s.charAt(i);
                    char y = s.charAt(i + 1);
                    if (y == '*') {
                        maxLength++;
                        ans += (1 + maxLength) * maxLength / 2;
                        break;
                    }
                    if (x == '?') {
                        if (y == '?') {
                            // x = ? && y == ?
                            maxLength++;
                        } else {
                            // x = ? && y != ?
                            // 0??1
                            // p i
                            if (check(s, lastPos, i + 1)) {
                                maxLength++;
                            } else {
                                // x = ? && y != ?
                                // 0??0
                                // p i
                                maxLength++;
                                ans += (1 + maxLength) * maxLength / 2;
                                // 这个qLength用Integer会WA16，问题是连续的问号太多了，(1 + qLength) * qLength超过Integer最大值
                                long qLength = i - lastPos;
                                ans -= (1 + qLength) * qLength / 2;
                                maxLength = qLength;
                            }
                        }
                    } else {
                        // x != ? && y = ?
                        // 00000?
                        //     i
                        if (y == '?') {
                            lastPos = i;
                            maxLength++;
                        } else {
                            // x = 1 y = 1 || x = 0 y = 0
                            if (x == y) {
                                // 01111
                                //  i
                                maxLength++;
                                ans += (1 + maxLength) * maxLength / 2;
                                maxLength = 0;
                            } else {
                                // x = 0 y = 1 || x = 1 y = 0
                                // 01
                                // i
                                maxLength++;
                            }
                        }
                    }
                }
                out.println(ans);
            }
        }

        boolean check(String s, int i, int j) {
            if (i == -1) {
                //  ??????1
                // i      j
                return true;
            }
            if (s.charAt(i) == s.charAt(j)) {
                // 相同的数间只能有偶数个问号
                return (j - i) % 2 == 0;
            } else {
                return (j - i) % 2 == 1;
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
