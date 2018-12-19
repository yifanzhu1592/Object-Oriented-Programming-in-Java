import java.awt.*;

public class NinjaCat extends Critter{
    public NinjaCat(){

    }

    //  Color.PINK
    public Color getColor(){
        return Color.pink;
    }

    //  "Meow"
    public String toString() {
        return "Meow";
    }

    //  always infect whether there is an enemy facing you in front of you, otherwise hop if possible, otherwise turn left.
    public Action getMove(CritterInfo info) {
        if (info.frontThreat()) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }
}
