import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * ————————————————————————————————————————————————WA1——————————————————————————————————————————————
 *  总结：周期算错了，应该多枚举几个看看规律
 *
 * 这个字符串是回文的，那么先手的人A，必然不可能执行操作2，只能执行操作1
 *     1.如果此时S中没有0，那么就是平局
 *     2.如果此时S有0，分两种情况：
 *         2.1.如果这个0在中心回文位，A一定花一个代价把中心的0变成1，此后每次B改变一个0，A就翻转一次，既：
 *         A变化(回文)，[B变化（非回文），A翻转（非回文），B变化（回文），A变化（非回文），B翻转（非回文），A变化（回文）]，[B变化，A翻转...]，最后结束于变化
 *         最后胜利取决于0的个数，如果有：
 *         1个0：B胜
 *         3个0：A胜
 *         5个0：B胜
 *         7个0：A胜
 *         9个0：B胜
 *         11个0：A胜
 *
 *          既：0的个数' % 4 = 1 B 胜
 *             0的个数' % 4 == 3 A 胜
 *
 *
 *
 *         2.2.如果这个0不在中心回文位，则有偶数个0，那么B可以翻转字符串，6个操作为一个周期，一个周期可以把4个0变成1，既：
 *         [A变化（非），B翻转（非），A变化（回），B变化（非），A翻转（非），B变化（回）] [A变化，B翻转...]，最后结束于变化
 *         最后的胜利取决于0的个数，如果 '0的个数' % 4 = 2，则B胜利，如果 '0的个数' % 4 = 0平局
 *         如果2个0，B胜
 *         如果4个0，平局
 *
 * ————————————————————————————————————————————————WA2——————————————————————————————————————————————
 * 0000 是BOB胜？题目读错了？
 * A:0001 +1
 * B:1001 +1
 * A:1101 +1
 * B:1011 +0
 * A:1111 +1
 * A Lose
 *
 * 可以这么操作... 难道补对手的对称位是最优的？B永远可以在只剩最后一个0的时候，做翻转，这样开局如果有偶数个0，A永远输
 * 000
 * A:001 + 1
 * B:101 + 1
 * A:111 + 1
 * A Lose
 *
 * 000
 * A:010 + 1
 * B:110 + 1
 * A:011 + 0
 * B:111 + 1
 * B Lose
 *
 * 结论：
 * 1个0，B 胜
 * 奇数个0，A 胜
 * 偶数个0，B 胜
 * 没有平局！！
 *
 * 问题出在策略不对
 *
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
                int zeroCnt = (int) Arrays.stream(s.split("")).filter("0"::equals).count();
                if (zeroCnt == 1) {
                    out.println("BOB");
                } else if ((zeroCnt & 1) == 1) {
                    out.println("ALICE");
                } else {
                    out.println("BOB");
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
