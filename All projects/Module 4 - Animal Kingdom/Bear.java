import java.awt.*;

public class Bear extends Critter{
    boolean polar;
    int moves;

    public Bear(boolean polar){
        this.polar = polar;
        moves = 0;
    }

    public Color getColor(){
        if (polar){
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public String toString() {
        if (moves % 2 == 0){
            return "/";
        } else {
            return "\\";
        }
    }

    public Action getMove(CritterInfo info) {
        moves++;

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }
}
