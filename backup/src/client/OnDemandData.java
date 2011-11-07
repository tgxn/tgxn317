package client;


public class OnDemandData extends NodeSub {
    
    int dataType;
    byte buffer[];
    int ID;
    boolean incomplete;
    int loopCycle;
    
    public OnDemandData() {
        incomplete = true;
    }
    
}
