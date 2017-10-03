import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
        Vector<Bullet> bs = new Vector<Bullet>();
        Vector<EnemyTank> ets = new Vector<EnemyTank>();

        public EnemyTank(int x, int y){
            super(x, y);
        }
        public void setEnemyTank(Vector<EnemyTank> ee){
            this.ets = ee;
        }
        public boolean isTouchOtherTank(){
            boolean b = false;
            switch (this.direct){
                case 0:
                    for(int i = 0;i < ets.size();i++){
                        EnemyTank et = ets.get(i);
                        if (et != this){
                            helper(et, b);
                        }
                    }
                    break;
                case 2:
                    for(int i = 0;i < ets.size();i++){
                        EnemyTank et = ets.get(i);
                        if(et!= this){
                            helper(et, b);
                        }
                    }
                    break;
                case 1:
                    for(int i=0;i<ets.size();i++){
                        EnemyTank et=ets.get(i);
                        if(et!=this){
                            helper(et, b);
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<ets.size();i++){
                        EnemyTank et=ets.get(i);
                        if(et!=this){
                            helper(et, b);
                        }
                    }
                    break;
            }

            return b;

        }
        public void helper(EnemyTank et, boolean b){
            if (et.direct == 0 || et.direct == 2){
                if (this.x - 20 >= et.x - 20 && this.x - 20 <= et.x + 20 &&
                        this.y - 25 >= et.y - 25 && this.y - 25 <= et.y + 25){
                    b = true;
                }
                if (this.x + 20 >= et.x - 20 && this.x + 20 <= et.x + 20 &&
                        this.y - 25 >= et.y - 25 && this.y - 25 <= et.y + 25){
                    b = true;
                }
            }
            if (et.direct == 1 || et.direct == 3) {
                if (this.x - 20 >= et.x - 25 && this.x - 20 <= et.x + 25
                        && this.y - 25 >= et.y - 20 && this.y - 25 <= et.y + 20) {
                    b = true;
                }
                if (this.x + 20 >= et.x - 25 && this.x + 20 <= et.x + 25 &&
                        this.y - 25 >= et.y - 20 && this.y - 25 <= et.y + 20) {
                    b = true;
                }
            }
        }
        public void run(){
            while (true){
                try {Thread.sleep(50);}
                catch (Exception e){
                    e.printStackTrace();
                }
                switch (this.direct){
                    //up
                    case 0:
                        for(int i = 0;i < 30;i++){

                            if (y > 25 && !this.isTouchOtherTank()){y-= speed;}

                            try {Thread.sleep(50);} catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        break;

                    //right
                    case 1:
                        for(int i = 0; i < 30; i++){
                            if (x < 780 && !this.isTouchOtherTank()){x+= speed;}
                            try {Thread.sleep(50);} catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        break;
                    //down
                    case 2:
                        for (int i = 0; i < 30; i++){
                            if (y < 575 && !this.isTouchOtherTank()){y+= speed;}
                            try {Thread.sleep(50);} catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        break;
                    //left
                    case 3:
                        for(int i = 0; i < 30; i++){
                            if (x > 20 && !this.isTouchOtherTank()){x-= speed;}
                            try {Thread.sleep(50);} catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        break;

                }
                this.direct = (int)(Math.random() * 4);
                if (this.isLive == false){
                    break;
                }
            }
        }
    }

