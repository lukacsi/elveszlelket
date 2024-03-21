package skeleton.elveszlelket;

abstract class Item {
    private String name;
    private ItemUseStrategy strategy;

    public abstract void use(Player player);
}
