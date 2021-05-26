import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A. Eshag Loves Big Arrays
 * 1≤n≤100
 * (1≤ai≤100)
 *
 * 一个包含n个数字的数列a，可以任意次做下面的操作：
 *  选出a中的几个数字，计算平均数AVG，然后把a中大于AVG的数都可以删除，然后继续选，继续删，
 *  问最多能删几个数字，比如：
 *  a=[1,1,1,2,2,3]
 *  选择a1,a5,a6 (a1+a5+a6)/3=6/3=2，这样a6就删除了，数列变成了：
 *  a=[1,1,1,2,2]
 *  选择这个数列，(a1+a2+a3+a4+a5)/5=7/5，这样a4,a5就删除了，数列变成了：
 *  a=[1,1,1] 没法继续删除了，所以答案是3
 *
 *  是个贪心：
 *  选整个数列最小的数字，大于这个数字的都能删，对于上面那个例子，就是统计有多少个数比1大...
 */
public class A1529 {

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
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = in.nextInt();
                }
                int min = Arrays.stream(a).min().getAsInt();
                int ans = (int) Arrays.stream(a).filter(s -> (s > min)).count();
                out.println(ans);
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
