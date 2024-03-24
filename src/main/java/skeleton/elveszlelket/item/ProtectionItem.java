package skeleton.elveszlelket.item;

abstract class ProtectionItem extends Item {
    
    protected int uses;
    
    public ProtectionItem() {
    }
    public void decreaseUse() {
        uses--;
    }

    public int getUses() {
        return uses;
    }
}
