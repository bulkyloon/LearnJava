import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class LambdaDemo {
    // Attributes
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
        int unassigned;
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
        /* arrayList.stream().forEach(n -> unassigned = 0); */  // Illegal: unassigned is not definitely assigned before lambda
        /* arrayList.stream().forEach(n -> numLocal = 0); */    // Illegal: numLocal is not effectively final because it occurs
                                                                //          as the left hand side in an assignment expression,
                                                                //          although its value actually never changes
        arrayList.stream().forEach(n -> n = numFinal);          // Legal: numFinal is effectively final
        arrayList.stream().forEach(n -> num = n);               // Legal: instance variables and static variables are not local variables
        /* for(int i=0; i<arrayList.size(); i++) arrayList.stream().forEach(n -> n = i); */     // Illegal: i is not effectively final
        for(Integer i : arrayList) arrayList.stream().forEach(n -> n = i);  // Legal: i is a new variable on each iteration,
                                                                            //        therefore it is effectively final
    }

    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo(new Integer[]{1, 2, 3, 4, 5});
        demo.start();
    }
}
