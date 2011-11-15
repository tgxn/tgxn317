package client.ondemand;

// Fully Renamed.

import client.node.NodeSub;

public class OnDemandData extends NodeSub {
    
    public int dataType;
    public byte buffer[];
    public int ID;
    public boolean incomplete;
    public int loopCycle;
    
    public OnDemandData() {
        incomplete = true;
    }
}
