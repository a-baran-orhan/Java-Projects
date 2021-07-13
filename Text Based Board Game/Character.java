public class Character {

    private String name;
    private int hp;
    private int ap;
    private int maxMove;

    public Character(String name, int hp, int ap, int maxMove) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.maxMove = maxMove;

    }




    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp < 0)
            this.hp = 0;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getMaxMove() {
        return maxMove;
    }

    public void setMaxMove(int maxMove) {
        this.maxMove = maxMove;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkMaxMove(int move) {
        return move == maxMove;
    }

    public void Heal(){}

    public void Attack(Character opponent){}

    public void RangedAttack(Character opponent){}


    @Override
    public String toString() {
        return "Character{" + "name='" + name + '\'' + ", hp=" + hp + ", ap=" + ap + '}';
    }
}
