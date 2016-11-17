import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
  public static void main(String[] argv) throws Exception {
    int minimum = 0;
    int maximum = 100;
    JProgressBar progress = new JProgressBar(minimum, maximum);

    progress.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        JProgressBar comp = (JProgressBar) evt.getSource();
        int value = comp.getValue();
        int min = comp.getMinimum();
        int max = comp.getMaximum();
      }
    });
  }
}