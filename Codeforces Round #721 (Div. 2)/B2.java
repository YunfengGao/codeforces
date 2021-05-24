import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 和B1的不同点在于，初始的字符串S不一定是回文的
 *
 * 给一个字符串S
 * A和B两个人每轮只能且必须对S做一个操作
 * 操作1：把S中的一个0变成1，花费一个成本
 * 操作2：如果S不是回文的，可以把S翻转，花费0个成本
 *
 * 当S中的每个字符都是1时，游戏结束，花费少的玩家获胜，问如果A和B都采用最优策略，谁获胜？如果平局，输出DRAW
 *
 * S不是回文的，则A先手，可以翻转，这样B只能把一个0变成1，A再翻转，B再花费一个代价，直到字符串变成回文的，这个过程中，B损失了K个代价
 * 11000001
 * 假设这是B把一个0变成1之后的状态，这时候A有两种选择：
 * 选择1.继续翻转，变成 10000011，下轮B把0变成1，即11000011，这时到了B1的场景，在这个回文串的翻转过程中，B能比A少2个代价。这种场景不确定A，B谁会获胜
 * 选择2.主动花费1个代价，把S变成11000011，这种选择A至少比B少花费1个代价
 * 所以得到结论：如果有两个以上0的非回文字符串，A必胜
 *
 * 考虑只有1个0的场景，这时A翻转就行了，A也必胜
 * 考虑有2个0的场景，先翻转，让B先把0变成1，并花费1个代价，这时候有两种情况：
 *  B改变之后S不是回文的：
 *      这时候A继续翻转，等B继续把0变成1，A必胜
 *  B改变之后S是回文的：
 *      这时候A只能花费一个代价把0变成1，平局
 *      在什么时候，有两个0，改变其中一个会变成回文的，即变成只有一个0的回文串？答：S长度为奇数，其中一个0在中间位置
 *
 * 总结：S是非回文串，如果长度为奇数，有两个0，且其中一个0在中间位置，那么平局，否则A必胜
 *      S是回文串，结论和B1一致
 *
 *
 */
public class B2 {

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
                boolean isM = true;
                int zeroCnt = (int) Arrays.stream(s.split("")).filter("0"::equals).count();
                for (int i = 0; i < s.length(); i++) {
                    char l = s.charAt(i);
                    char r = s.charAt(s.length() - i - 1);
                    if (i >= s.length() - i - 1) {
                        break;
                    }
                    if (l != r) {
                        isM = false;
                        break;
                    }
                }
                if (isM) {
                    if (zeroCnt == 1) {
                        out.println("BOB");
                    } else if ((zeroCnt & 1) == 1) {
                        out.println("ALICE");
                    } else {
                        out.println("BOB");
                    }
                } else {
                    if ((s.length() & 1) == 1 && zeroCnt == 2 && s.charAt(s.length() / 2) == '0') {
                        out.println("DRAW");
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
