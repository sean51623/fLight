import java.util.*;

public class TestTimer1 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new DateTask1(), 1000, 3000);
        System.out.println("Current time: " + new Date());

        try {
            Thread.sleep(20000);
        }
        catch(InterruptedException e) {
        }

        timer.cancel();
    }
}