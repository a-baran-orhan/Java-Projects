public class Ork extends Character{
    private int healPoints;

    public Ork(String name) {
        super(name, 200, Constants.orkAP, Constants.orkMaxMove);
        this.healPoints = Constants.orkHealPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 200)
            this.setHp(200);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(200)";
    }
}
