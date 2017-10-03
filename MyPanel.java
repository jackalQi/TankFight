import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements ActionListener, Runnable, KeyListener {
    MyTank mt = null;
    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    Vector<Bomb> bombs = new Vector<Bomb>();

    int enSize = 6;
    public MyPanel(){
        mt = new MyTank(400,600);
        for(int i = 0; i < enSize; i++){
            EnemyTank et = new EnemyTank((i + 1) * 100,25);
            et.setColor(1);
            et.setDirect(2);
            ets.add(et);
            et.setEnemyTank(ets);
            Thread t1 = new Thread(et);
            t1.start();
            Bullet b = new Bullet(et.x, et.y, et.direct);
            et.bs.add(b);
            Thread t2 = new Thread(b);
            t2.start();
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0, 0, 800, 600);
        if (mt.isLive){
            this.drawTank(mt.getX(), mt.getY(), g, 0, mt.getDirect());
        } else {

        }
        //draw bullet
        for(int i = 0; i < mt.bs.size(); i++){
            Bullet b = mt.bs.get(i);
            if (b!= null && b.isLive){
                g.draw3DRect(b.x, b.y, 5, 15, false);
            }
            if (b.isLive == false){
                mt.bs.remove(b);
            }
        }
        for(int i= 0; i < bombs.size(); i++){
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6)
            {	g.drawOval(bomb.x - 20, bomb.y - 25, 40, 50);

            } else if (bomb.life > 3){
                g.drawLine(bomb.x - 20, bomb.y - 25, bomb.x + 20, bomb.y + 25);
                g.drawLine(bomb.x - 20, bomb.y + 25, bomb.x + 20, bomb.y - 25);
            } else {
                g.draw3DRect(bomb.x - 20, bomb.y - 25, 1, 1, false);
                g.draw3DRect(bomb.x + 20, bomb.y - 25, 1, 1, false);
                g.draw3DRect(bomb.x - 20, bomb.y + 25, 1, 1, false);
                g.draw3DRect(bomb.x + 20, bomb.y + 25, 1, 1, false);

            }
            bomb.lifeDown();
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        for (int i = 0; i < ets.size(); i++){
            EnemyTank et = ets.get(i);
            if (et.isLive){
                this.drawTank(et.getX(), et.getY(), g, et.getColor(), et.getDirect());
                for(int j = 0; j < et.bs.size(); j++){
                    Bullet eb = et.bs.get(j);
                    if (eb.isLive){
                        g.draw3DRect(eb.x, eb.y, 1, 1, false);
                    }
                    else {
                        et.bs.remove(eb);
                    }
                }

            } else {
                ets.remove(et);
            }
        }

        //this.DrawTank(mt2.getX(), mt2.getY(), g, 1, 0);
    }
    public void hitTank(Tank et,Bullet b){
        switch (et.direct){
            case 0:
            case 2:
                if (b.x < et.x + 20 && b.x > et.x - 20 && b.y > et.y - 25 && b.y < et.y + 25){
                    b.isLive = false;
                    et.isLive = false;
                    mt.isLive = false;
                    Bomb bomb = new Bomb(et.x,et.y);
                    bombs.add(bomb);
                }
            case 1:
            case 3:
                if(b.x < et.x + 25 && b.x > et.x - 25 && b.y > et.y - 20 && b.y < et.y + 20){
                    b.isLive = false;
                    et.isLive = false;
                    mt.isLive = false;
                    Bomb bomb = new Bomb(et.x,et.y);
                    bombs.add(bomb);

                }
        }
    }
    public void hitMyTangk(){
        for (int i = 0; i < ets.size(); i++){
            EnemyTank et = ets.get(i);
            for(int j = 0; j < et.bs.size(); j++){
                Bullet b = et.bs.get(j);
                this.hitTank(mt, b);
            }
        }
    }
    public void hitEnemyTank(){
        for (int i = 0; i < mt.bs.size(); i++){
            Bullet b = mt.bs.get(i);
            if (b.isLive){
                for(int j = 0; j < ets.size(); j++){
                    EnemyTank et = ets.get(j);
                    if (et.isLive){
                        this.hitTank(et, b);
                    }
                }
            }
        }
    }
    public void drawTank(int x,int y,Graphics g,int color,int direct){
        switch (color){
            case 0:
                g.setColor(Color.BLUE);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
        }
        switch (direct){

            case 0://up
                g.fill3DRect(x - 20, y - 25, 10, 50, false);
                g.fill3DRect(x + 10, y - 25, 10, 50, false);
                g.fill3DRect(x - 10, y - 15, 20, 30, false);
                g.fillOval(x - 10, y - 10, 20, 20);
                g.drawLine(x, y, x, y - 30);
                break;

            case 1://right
                g.fill3DRect(x - 25, y - 20, 50, 10, false);
                g.fill3DRect(x - 25, y + 10, 50, 10, false);
                g.fill3DRect(x - 15, y - 10, 30, 20, false);
                g.fillOval(x - 10, y - 10, 20, 20);
                g.drawLine(x, y, x + 30, y);
                break;

            case 2://down
                g.fill3DRect(x - 20, y - 25, 10, 50, false);
                g.fill3DRect(x + 10, y - 25, 10, 50, false);
                g.fill3DRect(x - 10, y - 15, 20, 30, false);
                g.fillOval(x - 10, y - 10, 20, 20);
                g.drawLine(x, y, x, y + 30);
                break;

            case 3://left
                g.fill3DRect(x - 25, y - 20, 50, 10, false);
                g.fill3DRect(x - 25, y + 10, 50, 10, false);
                g.fill3DRect(x - 15, y - 10, 30, 20, false);
                g.fillOval(x - 10, y - 10, 20, 20);
                g.drawLine(x, y, x - 30, y);
                break;

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            this.mt.direct = 0;
            this.mt.movingUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_D){
            this.mt.direct = 1;
            this.mt.movingRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_S){
            this.mt.direct = 2;
            this.mt.movingDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
            this.mt.direct = 3;
            this.mt.movingLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_J){

            if(this.mt.bs.size() < 5){
                this.mt.shoot();}
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try {Thread.sleep(100);}
            catch (InterruptedException e){
                e.printStackTrace();
            }

            this.hitEnemyTank();
            this.hitMyTangk();

            for(int i = 0; i < ets.size(); i++){
                EnemyTank et = ets.get(i);
                if (et.isLive){
                    if (et.bs.size() < 1){
                        Bullet b = null;
                        switch (et.direct){
                            case 0:
                                b = new Bullet(et.x,et.y - 30,0);
                                et.bs.add(b);
                                break;
                            case 1:
                                b = new Bullet(et.x + 30,et.y,1);
                                et.bs.add(b);
                                break;
                            case 2:
                                b = new Bullet(et.x,et.y + 30,2);
                                et.bs.add(b);
                                break;
                            case 3:
                                b = new Bullet(et.x - 30,et.y,3);
                                et.bs.add(b);
                                break;
                        }
                        Thread t = new Thread(b);
                        t.start();
                    }
                }
            }
            this.repaint();
        }
    }
}
