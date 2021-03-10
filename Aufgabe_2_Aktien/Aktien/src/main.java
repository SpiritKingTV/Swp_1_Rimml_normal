import java.util.Timer;
import java.util.TimerTask;

public class main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new run();
        timer.schedule(task,5000,10000);


    }
}
