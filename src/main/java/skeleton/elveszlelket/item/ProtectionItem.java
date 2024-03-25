package skeleton.elveszlelket.item;

abstract class ProtectionItem extends Item {
    
    protected int uses;
    
    public ProtectionItem() {
    }

    /**
     * A maradék használatok számát leviszi 1-el ha a használatok száma nem 0
     */
    public void decreaseUse() {
        if(uses > 0)
            uses--;
    }

    
    /** 
     * @return int A maradék használatok számát adja vissza
     */
    public int getUses() {
        return uses;
    }
}
