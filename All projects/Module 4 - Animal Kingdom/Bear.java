import java.awt.*;

public class Giant extends Critter{
    int moves;
    String s;

    public Giant(){
        moves = 0;
    }

    //  Color.GRAY
    public Color getColor() {
        return Color.GRAY;
    }

    // "fee" for 6 moves, then "fie" for 6 moves, then "foe" for 6 moves, then "fum" for 6 moves, then
    // repeat.
    public String toString() {
        if (moves % 6 == 0) {
            if (moves % 24 == 0) {
                s = "fee";
            } else if (moves % 24 == 6) {
                s = "fie";
            } else if (moves % 24 == 12){
                s = "foe";
            } else {
                s = "fum";
            }
            return s;
        } else {
            return s;
        }
    }

    //  always infect if an enemy is in front, otherwise hop if possible, otherwise turn right.
    public Action getMove(CritterInfo info) {
        moves++;

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }
}
