import java.awt.*;

public class WhiteTiger extends Critter{
    boolean hasAffected;

    public WhiteTiger(){
        hasAffected = false;
    }

    // Always Color.WHITE.
    public Color getColor() {
        return Color.WHITE;
    }

    // "tgr" if it hasn’t infected another Critter yet, “TGR” if it has infected.
    public String toString() {
        if (hasAffected) {
            return "TGR";
        } else {
            return "tgr";
        }
    }

    // Same as a Tiger. Note: you’ll have to override this method to figure out if it has infected another
    // Critter.
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            hasAffected = true;
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL){
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME){
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }
}
