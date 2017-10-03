import java.util.Vector;

public class MyTank extends Tank{
    Vector<Bullet> bs = new Vector<Bullet>();
    Bullet b = null;
    public MyTank(int x, int y){
        super(x, y);
    }

    public void shoot(){
        switch (this.direct){
            case 0:
                b = new Bullet(x, y-30, 0);
                bs.add(b);
                break;
            case 1:
                b = new Bullet(x+30, y, 1);
                bs.add(b);
                break;
            case 2:
                b = new Bullet(x,y+30,2);
                bs.add(b);
                break;
            case 3:
                b = new Bullet(x-30, y, 3);
                bs.add(b);
                break;
        }
        Thread t = new Thread(b);
        t.start();
    }
    public void movingUp(){
        y-= speed + 5;
    }
    public void movingRight(){
        x+= speed + 5;
    }
    public void movingDown(){
        y+= speed + 5;
    }
    public void movingLeft(){
        x-= speed + 5;
    }
}

