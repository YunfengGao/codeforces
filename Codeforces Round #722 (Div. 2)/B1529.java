import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * B. Sifid and Strange Subsequences
 * http://codeforces.com/contest/1529/problem/B
 *
 * 如果一个序列B中任意两个数差的绝对值大等于序列中的最大值，则称这个序列为strange
 * 从序列B中挑一些元素组成strange，求最长的strange的长度是多少？
 *
 * n (1≤n≤105) — the length of the array a
 * a1,a2,…,an  (−10^9≤ai≤10^9)
 *
 *
 * 思路：
 * 两个数的差的绝对值就是一维数轴上两点的距离，先想到的是排序
 * 任意两点间距离要大等于最大值，贪心的想法是让最大值尽可能小，来放更多的点上去，代价想不清楚，比较复杂
 * 模拟呢？排好序后从左到右一次放，同时维护一个最小距离，如果这个距离不小于新值，就把新值加入进来（正确性不好证明啊，）
 * 而且这个蓄力中最多只有1个正数，两个正数不满足性质，比如：|2 - 1| < 2
 * 说到这里，是不是选所有负数和0，再随便选一个正数就行了...
 *
 * -3 0 2 0 这个用例答案是3，|0 - 0| < 2了，这里 -3 0 0 或者 -3 2 0 收益是一样的，所以如果有相同的非正数，就不能再加正数了
 *
 * 23:20 WA2
 * 4
 * 9494 -6977 -9248 2372
 * 正数也不能随便选，有可能两个非正数间的距离，小于这个正数
 * 还是得全记录下来排序，维护最小距离，有小于等于这个最小距离得正数，再+1
 *
 * 117512099	May/27/2021 23:29UTC+8	gyf1995	B - Sifid and Strange Subsequences	Java 8	Accepted	124 ms	0 KB
 */
public class B1529 {

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
                int ans = 0;
                for (int i = 0; i < n; i++) {
                    a[i] = in.nextInt();
                    if (a[i] <= 0) {
                        ans++;
                    }
                }
                Arrays.sort(a);
                int minLength = Integer.MAX_VALUE;
                for (int i = 1; i < n; i++) {
                    minLength = Math.min(minLength, a[i] - a[i - 1]);
                    if (a[i] > 0) {
                        if (a[i] <= minLength) {
                            ans++;
                        }
                        break;
                    }
                }
                if (ans == 0 && a[0] > 0) {
                    ans = 1;
                }
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
