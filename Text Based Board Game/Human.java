public class Human extends Character{
    public Human(String name) {
        super(name, 100, Constants.humanAP, Constants.humanMaxMove);
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 100)
            this.setHp(100);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(100)";
    }
}
