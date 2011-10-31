
final class Item extends Animable {
    
    public int ID;
    public int anInt1559;
    
    Item() {
    }
    
    @Override
    public final Model getRotatedModel() {
        Class8 class8 = Class8.method198(ID);
        return class8.method201(anInt1559);
    }
}
