import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class C1679 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver();
        solver.solve(in, out);
        out.close();
    }

    static class BinaryIndexedTree {
        int size;
        int[] elementData;

        BinaryIndexedTree(int size) {
            this.size = size;
            elementData = new int[size + 1];
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        private void add(int pos, int val) {
            while (pos <= size) {
                elementData[pos] += val;
                pos += lowBit(pos);
            }
        }

        private int sum(int pos) {
            int ret = 0;
            while (pos > 0) {
                ret += elementData[pos];
                pos -= lowBit(pos);
            }
            return ret;
        }

        private int sum(int start, int end) {
            return sum(end) - sum(start - 1);
        }
    }

    private static class Solver {
        void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            BinaryIndexedTree r = new BinaryIndexedTree(n);
            BinaryIndexedTree c = new BinaryIndexedTree(n);
            Map<Integer, Integer> cntR = new HashMap<>();
            Map<Integer, Integer> cntC = new HashMap<>();
            while (q-- > 0) {
                int type = in.nextInt();
                if (type == 1) {
                    int x = in.nextInt();
                    int y = in.nextInt();
                    cntR.put(x, cntR.getOrDefault(x, 0) + 1);
                    cntC.put(y, cntC.getOrDefault(y, 0) + 1);
                    if (cntR.get(x) == 1) {
                        r.add(x, 1);
                    }
                    if (cntC.get(y) == 1) {
                        c.add(y, 1);
                    }
                } else if (type == 2) {
                    int x = in.nextInt();
                    int y = in.nextInt();
                    cntR.put(x, cntR.getOrDefault(x, 0) - 1);
                    cntC.put(y, cntC.getOrDefault(y, 0) - 1);
                    if (cntR.get(x) == 0) {
                        r.add(x, -1);
                    }
                    if (cntC.get(y) == 0) {
                        c.add(y, -1);
                    }
                } else {
                    int x1 = in.nextInt();
                    int y1 = in.nextInt();
                    int x2 = in.nextInt();
                    int y2 = in.nextInt();
                    int rr = r.sum(x1, x2);
                    int cc = c.sum(y1, y2);
                    if ((rr == x2 - x1 + 1) || (cc == y2 - y1 + 1)) {
                        out.println("Yes");
                    } else {
                        out.println("No");
                    }
                }
            }
        }
    }

    private static class InputReader {
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
