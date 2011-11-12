package client;

import client.animation.Animable;


final class Item extends Animable {
    
    public int ID;
    public int anInt1559;
    
    Item() {
    }
    
    @Override
    public final Model getRotatedModel() {
        ItemDef class8 = ItemDef.forID(ID);
        return class8.method201(anInt1559);
    }
}
