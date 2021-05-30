package recursos;

import static java.lang.Thread.sleep;

/**
 *
 * @author kevin2
 */
public class HiloTemporizador extends Thread {

    private int seg;
    private int min;
    private int hor;
    private int milisegundos;

    public HiloTemporizador(int seg, int min, int hor, int mili) {
        this.seg = seg;
        this.min = min;
        this.hor = hor;
        this.milisegundos=mili;
    }

    public int getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(int milisegundos) {
        this.milisegundos = milisegundos;
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
                milisegundos++;
                if (milisegundos > 1000) {
                    milisegundos = 0;
                    seg++;
                }
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
                sleep(1);
            } catch (InterruptedException e) {
            }
        }

    }
}
