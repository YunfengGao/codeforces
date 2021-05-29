import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A. Mean Inequality
 *
 * 给一个序列a，包含2*n个元素，对这个序列重新排序，使For every i from 1 to 2n, bi≠(b[i−1]+b[i+1])/2, where b0=b2n and b2n+1=b1
 * 题目数据保证有解
 *
 *  构造题，先对序列排序，从中间平分成两等份。奇数位放小的，偶数位放大的。（反过来也行），
 *  比如：1 5 2 3 4 6
 *  排序：1 2 3 4 5 6
 *  奇小偶大：1 4 2 5 3 6
 */
public class A1526 {

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
                int[] a = new int[n * 2];
                for (int i = 0; i < n * 2; i++) {
                    a[i] = in.nextInt();
                }
                Arrays.sort(a);
                for (int i = 0; i < n; i++) {
                    out.print(a[i] + " ");
                    out.print(a[i + n] + " ");
                }
                out.println();
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
