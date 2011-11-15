package client.item;

//Fully Renamed - gamerx - 15.11.11

import client.model.Model;
import client.animation.Entity;

/**
 * Used for item stacks.
 *
 * Class30_Sub2_Sub4_sub2
 * @author gamerx
 */
public final class Item extends Entity {
    
    public int itemID;
    public int itemCount;
    
    public Item() {
    }

    /**
     * Gets differing models for different amounts of items.
     *
     * @return Model for the amount.
     */
    @Override
    public final Model getRotatedModel() {
        ItemDef itemDef = ItemDef.forID(itemID);
        return itemDef.getModelForAmount(itemCount);
    }
}
