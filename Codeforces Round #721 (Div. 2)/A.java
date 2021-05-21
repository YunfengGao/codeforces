import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * n & (n−1) & (n−2) & (n−3) & ... (k) = 0
 * 找到最大的k使等式成立
 *
 * 直接暴力也挺快的 —— 超时了
 * --------------------------------------------
 * 再想想：
 * 如果一个值要变成0，那个这个值的各个位都要变成0
 * 相对其他位，最高位一定是最后变成0的
 * 而且最高位变成0后，其他位都至少已经变成0一次了
 * 所以计算什么时候最高位变成0就行了
 *
 * 如果一个数有n位，第n位变成0是在首位为0，剩余n-1位为1
 * 既：pow(2, n-1) - 1
 *
 * 1001
 * 1000
 * 0111   7
 * 0110
 * 0101
 * 0100
 * 0011   3
 * 0010
 * 0001
 *
 *
 */
public class A {

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
                long ans = in.nextLong();
                String binary = Long.toBinaryString(ans);
                out.println((1 << (binary.length() - 1)) - 1);
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
