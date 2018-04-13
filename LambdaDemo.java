import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class LambdaDemo {
    // Attribute
    private int num;
    private ArrayList<Integer> arrayList;

    // Constructors
    LambdaDemo() {
        num = 0;
        arrayList = new ArrayList<Integer>();
    }
    LambdaDemo(Integer[] array) {
        num = 0;
        arrayList = new ArrayList<Integer>(Arrays.asList(array));
    }

    // Consumer is a functional interface
    private class Action implements Consumer<Integer> {
        public void accept(Integer n) {
            System.out.print(n + "; ");
        }
    }

    private void start() {
        // Variables
        int numLocal = 0;
        int numFinal = 0;

        // Inner classes are useful for defining handler classes
        System.out.println("Inner class example:");
        arrayList.stream().forEach(new Action());
        System.out.print("\n");

        // Anonymous inner class combines defining and creating an inner class into one step
        System.out.println("Anonymous inner class example:");
        arrayList.stream().forEach(new Consumer<Integer>() {
            public void accept(Integer n) {
                System.out.print(n + "; ");
            }
        });
        System.out.print("\n");

        // Lambda expression can be viewed as an anonymous class with a concise syntax
        System.out.println("Lambda expression example:");
        arrayList.stream().forEach(n -> System.out.print(n + "; "));
        System.out.print("\n");

        // Test lambda expression
        // 01. Local variables used but not declared in a lambda expression must be effectively final and definitely assigned.
        /* arrayList.stream().forEach(n -> numLocal = n); */    // Illegal: numLocal is not effectively final
        arrayList.stream().forEach(n -> n = numFinal);          // Legal: numFinal is effectively final
        arrayList.stream().forEach(n -> num = n);               // Legal: instance variables and static variables are not local variables
    }

    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo(new Integer[]{6, 7, 8, 9, 10});
        demo.start();
    }
}
