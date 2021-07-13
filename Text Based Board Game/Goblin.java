public class Goblin extends Character{
    public Goblin(String name) {
        super(name, 80, Constants.goblinAP, Constants.goblinMaxMove);
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 80)
            this.setHp(80);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(80)";
    }
}
