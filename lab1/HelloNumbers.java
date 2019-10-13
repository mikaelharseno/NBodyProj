public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int y = 1;
        while (y < 11) {
            System.out.print(x + " ");
            x = x + y;
            y = y + 1;
        }
    }
}
