package thread;

public class Hero {
    public String name;
    public float hp;

    public int damage;

    public void attackHero(Hero h) {
//        try {
//            Thread.sleep(300);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        h.hp -= damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }

    public boolean isDead() {
        //Return true if the character's hp is greater than 0, otherwise return false
        return !(this.hp > 0);
    }

    public synchronized void recover() {
        hp = hp+1;
    }

    public void hurt() {
        synchronized (this) {
            hp = hp-1;
        }
    }
}
