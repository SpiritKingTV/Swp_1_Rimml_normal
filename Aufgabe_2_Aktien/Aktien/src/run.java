import java.util.TimerTask;

public class run extends TimerTask {
    @Override
    public void run() {
       readAktien.launch();
        System.out.println("HELLO");
    }
}
