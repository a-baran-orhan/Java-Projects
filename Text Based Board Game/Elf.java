public class Elf extends Character{
    private int rangedAp;

    public Elf(String name) {
        super(name, 70, Constants.elfAP, Constants.elfMaxMove);
        this.rangedAp = Constants.elfRangedAP;
    }

    public int getRangedAp() {
        return rangedAp;
    }

    public void setRangedAp(int rangedAp) {
        this.rangedAp = rangedAp;
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 70)
            this.setHp(70);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public void RangedAttack(Character opponent){
        opponent.setHp(opponent.getHp() - this.getRangedAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(70)";
    }
}
