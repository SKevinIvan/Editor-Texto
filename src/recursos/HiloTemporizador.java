package recursos;

import static java.lang.Thread.sleep;
import javax.swing.JProgressBar;

/**
 *
 * @author kevin2
 */
public class HiloTemporizador extends Thread {

    public int seg;
    public int min;
    public int hor;

    public HiloTemporizador(int seg, int min, int hor) {
        this.seg = seg;
        this.min = min;
        this.hor = hor;
    }

    public int getSeg() {
        return seg;
    }

    public void setSeg(int seg) {
        this.seg = seg;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHor() {
        return hor;
    }

    public void setHor(int hor) {
        this.hor = hor;
    }

    /**
     *
     */
    @Override
    public void run() {
        for (;;) {
            try {
                seg++;
                if (seg > 59) {
                    seg = 0;
                    min++;
                }
                if (min > 59) {
                    min = 0;
                    seg = 0;
                    hor++;
                }
                if (hor > 59) {
                    seg = 0;
                    min = 0;
                    hor = 0;
                }
                sleep(1000);
            } catch (Exception e) {
            }
        }

    }
}
