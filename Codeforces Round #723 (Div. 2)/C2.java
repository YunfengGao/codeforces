import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * C2. Potions (Hard Version)
 * https://codeforces.com/contest/1526/problem/C1
 *
 * 从左到由有n(1≤n≤200 000)个点，每个点有一个价值a[i](−10^9≤ai≤10^9)，每次可以选择获取a[i]或者忽略a[i]，问在初始值为0，累计值始终不为负数的情况下，最多可以取到多少个点？
 * input
 * 6
 * 4 -4 1 -3 1 -3
 * output
 * 5
 * 在样例中可以取1,3,4,5,6共5个点
 *
 * n这个范围，n^2的dp是用不了了
 * 贪心，一边向右走，一边取数，如果累计值为负数了，就把最小的值拿出去
 */
public class C2 {

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
            int n = in.nextInt();
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(Long::longValue));
            long sum = 0;
            for (int i = 1; i <= n; i++) {
                long temp = in.nextLong();
                sum += temp;
                priorityQueue.add(temp);

                while (sum < 0) {
                    sum -= priorityQueue.poll();
                }
            }
            out.println(priorityQueue.size());
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
