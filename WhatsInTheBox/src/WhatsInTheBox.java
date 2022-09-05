import java.util.ArrayList;
import java.util.List;

public class WhatsInTheBox {

    int N, Q, L, R, A, B;
    List<Crate> cratesList;

    public WhatsInTheBox(String s) {
        String[] arr = s.split("\n");
        String[] init = arr[0].split(" ");
        this.N = Integer.parseInt(init[0]);
        this.Q = Integer.parseInt(init[1]);

        this.cratesList = new ArrayList<>();
        createEmptyCrates();

        for (int i = 1; i <= this.Q; i++) {
            init = arr[i].split(" ");
            String x = init[0];
            if (x.equals("1")) {
                this.L = Integer.parseInt(init[1]);
                this.R = Integer.parseInt(init[2]);
                this.A = Integer.parseInt(init[3]);
                this.B = Integer.parseInt(init[4]);

                for(int j = 1; j <= N; j++) {
                    cratesList.get(j-1).amountStones = ((j-L+1)*A)%B;
                }

            } else if (x.equals("2")) {
                int begin = Integer.parseInt(init[1]);
                int end = Integer.parseInt(init[2]);
                generateOutput(begin, end);
            } else {
                System.out.println("error");
            }
        }
    }

    public void createEmptyCrates() {
        for (int i = 1; i <= N; i++) {
            //int a = ((i-L+1)*A)%B;
            cratesList.add(new Crate(i));
        }
    }

    public void generateOutput(int begin, int end) {
        int res = 0;
        for (int i = begin; i <= end; i++) {
            res += cratesList.get(i-1).amountStones;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        String s = """
                4 4
                1 1 4 7 9
                2 1 4
                1 1 4 1 1
                2 1 4""";
        new WhatsInTheBox(s);
    }

}
