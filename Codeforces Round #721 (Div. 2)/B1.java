import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 给一个回文字符串S
 * A和B两个人每轮只能且必须对S做一个操作
 * 操作1：把S中的一个0变成1，花费一个成本
 * 操作2：如果S不是回文的，可以把S翻转，花费0个成本
 *
 * 当S中的每个字符都是1时，游戏结束，花费少的玩家获胜，问如果A和B都采用最优策略，谁获胜？如果平局，输出DRAW
 *
 * 解析：
 * 这个字符串是回文的，那么先手的人A，必然不可能执行操作2，只能执行操作1
 *     1.如果此时S中没有0，那么就是平局
 *     2.如果此时S有0，分两种情况：
 *         2.1.如果这个0在中心回文位，A一定花一个代价把中心的0变成1，此后每次B改变一个0，A就翻转一次，既：
 *         A变化，[B变化，A翻转，B变化，A变化，B翻转，A变化]，[B变化，A翻转...]，最后结束于变化
 *         最后胜利取决于0的个数，如果有：
 *         1个0：B胜
 *         3个0：A胜
 *         5个0：B胜
 *
 *         2.2.如果这个0不在中心回文位，那么B可以翻转字符串，6个操作为一个周期，既：
 *         [A变化，B翻转，A变化，B变化，A翻转，B变化] [A变化，B翻转...]，最后结束于变化
 *         最后的胜利取决于0的个数，如果 '0的个数' % 6 != 0，则B胜利，否则平局
 */
public class B1 {

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
                String s = in.next();
                int zeroCnt = 0;
                for (int i = 0; i < s.length(); i++) {
                    if ('0' == s.charAt(i)) {
                        zeroCnt++;
                    }
                }
                if (zeroCnt == 0) {
                    out.println("DRAW");
                    return;
                }
                if (n % 2 == 1 && s.charAt(n / 2) == '0') {
                    int k = zeroCnt % 6;
                    if (k == 1 || k == 5) {
                        out.println("BOB");
                    } else {
                        out.println("ALICE");
                    }
                } else {
                    if (zeroCnt % 6 != 0) {
                        out.println("BOB");
                    } else {
                        out.println("ALICE");
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

    }
}
