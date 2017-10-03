public class Bullet implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 2;
    boolean isLive = true;
    public Bullet(int x, int y, int direct){
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void run(){
        while (true){
            try{
                Thread.sleep(10);}
            catch (Exception e){
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y-= speed;
                    break;
                case 1:
                    x+= speed;
                    break;
                case 2:
                    y+= speed;
                    break;
                case 3:
                    x-= speed;
                    break;
            }
            if(x < 0 || x > 800 || y < 0 || y > 600){
                this.isLive = false;
                break;
            }
        }
    }
}
