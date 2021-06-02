import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * B. Lord of the Values
 * http://codeforces.com/contest/1523/problem/B
 *
 * 给一个偶数长度的数组，a1,a2,…,an，在5000次操作内把它转换为−a1,−a2,…,−an，输出操作步骤
 * 允许的操作有两种：对于任意的 i, j 且 i < j
 * 1.ai=ai+aj
 * 2.aj=aj−ai
 *
 * 举例：
 * 1 1 1 1
 *
 * "2 1 2". Values of variables after performing the operation: [1, 0, 1, 1]
 * "2 1 2". Values of variables after performing the operation: [1, -1, 1, 1]
 * "2 1 3". Values of variables after performing the operation: [1, -1, 0, 1]
 * "2 1 3". Values of variables after performing the operation: [1, -1, -1, 1]
 * "2 1 4". Values of variables after performing the operation: [1, -1, -1, 0]
 * "2 1 4". Values of variables after performing the operation: [1, -1, -1, -1]
 * "1 1 2". Values of variables after performing the operation: [0, -1, -1, -1]
 * "1 1 2". Values of variables after performing the operation: [-1, -1, -1, -1]
 *
 * 想一种固定的执行顺序（策略），重复执行，把元素挨个变成负的就行了。这个策略好难想啊...
 *
 * 对于任意的i,j，可以在6步把(ai, aj)变成(-ai, -aj)
 * 先执行1 ai=ai+aj
 * 再执行2 aj=aj-(ai+aj)=-ai
 * 再执行1 ai=ai+aj+(-ai)=aj
 * 再执行2 aj=-ai-(aj)=-ai-aj
 * 再执行1 ai=aj+(-ai-aj)=-ai
 * 再执行2 aj=-ai-aj-(-ai)=-aj
 *
 * 之后就是随便找两个没处理过的元素，按这6步处理就行了，比如：1,2 一组；3,4一组；
 * 或者1，3一组；2，4一组
 *
 * 要执行多少次：每执行3次可以把一个ai变成-ai，最多需要3000次操作即可
 *
 */
public class B1523 {

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
                for (int i = 0; i < n; i++) {
                    in.next();
                }
                out.println(3 * n);
                for (int i = 1; i <= n; i += 2) {
                    for (int j = 0; j < 3; j++) {
                        out.println(1 + " " + i + " " + (i + 1));
                        out.println(2 + " " + i + " " + (i + 1));
                    }
                }
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
