public class Troll extends Character{
    public Troll(String name) {
        super(name, 150, Constants.trollAP, Constants.trollMaxMove);
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 150)
            this.setHp(150);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(150)";
    }
}
