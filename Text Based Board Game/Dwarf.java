public class Dwarf extends Character{
    public Dwarf(String name) {
        super(name, 120, Constants.dwarfAP, Constants.dwarfMaxMove);
    }

    @Override
    public void Heal() {
        this.setHp(this.getHp() + 10);
        if (this.getHp() > 120)
            this.setHp(120);
    }

    @Override
    public void Attack(Character opponent) {
        opponent.setHp(opponent.getHp() - this.getAp());
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getHp() + "\t" + "(120)";
    }
}
