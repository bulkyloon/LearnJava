import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class LambdaDemo {
    // Attributes
    private ArrayList<Integer> arrayList;

    // Constructors
    LambdaDemo() {
        arrayList = new ArrayList<Integer>();
    }
    LambdaDemo(Integer[] array) {
        arrayList = new ArrayList<Integer>(Arrays.asList(array));
    }

    // Consumer is a functional interface
    private class InnerClass implements Consumer<Integer> {
        public void accept(Integer n) {
            System.out.print(n + "; ");
        }
    }

    private void start() {

        // Inner class example
        System.out.println("Inner class example:");
        arrayList.stream().forEach(new InnerClass());
        System.out.print("\n");

        // Anonymous inner class example
        System.out.println("Anonymous inner class example:");
        arrayList.stream().forEach(new Consumer<Integer>() {
            public void accept(Integer n) {
                System.out.print(n + "; ");
            }
        });
        System.out.print("\n");

        // Lambda expression example
        System.out.println("Lambda expression example:");
        arrayList.stream().forEach(n -> System.out.print(n + "; "));
        System.out.print("\n");
    }

    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo(new Integer[]{6, 7, 8, 9, 10});
        demo.start();
    }
}
