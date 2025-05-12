package src.thread;

public class Hero {
    //定义英雄的名称
    public String name;
    //定义英雄的血量
    public float hp;

    //定义英雄的伤害值
    public int damage;

    //定义英雄攻击另一个英雄的方法
    public void attackHero(Hero h) {
//        try {
//            //让线程休眠300毫秒
//            Thread.sleep(300);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //攻击另一个英雄，减少其血量
        h.hp -= damage;
        //输出攻击信息
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        //判断另一个英雄是否死亡
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }

    //判断英雄是否死亡的方法
    public boolean isDead() {
        //Return true if the character's hp is greater than 0, otherwise return false
        return !(this.hp > 0);
    }

    public synchronized void recover() {
        hp = hp + 1;
        System.out.printf("%s 回血1点,增加血后，%s的血量是%.0f%n", name, name, hp);
        this.notify();
    }

    public  void hurt() {
        synchronized (this) {
            if (hp == 1) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            hp = hp - 1;
            System.out.printf("%s 减血1点,减少血后，%s的血量是%.0f%n", name, name, hp);
        }
    }
}
