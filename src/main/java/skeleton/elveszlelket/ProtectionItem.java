package skeleton.elveszlelket;

abstract class ProtectionItem extends Item {
    private int uses;

    public void decreaseUse() {
        uses--;
    }

    public int getUses() {
        return uses;
    }
}
