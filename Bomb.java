public class Bomb {
    int x = 0;
    int y = 0;
    boolean isLive = true;
    int life = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            this.isLive = false;
        }
    }
}
