import java.awt.*;


public class Tiger extends Critter{
    int moves;
    char c;

    public Tiger(){
        moves = 0;
    }

    // Randomly picks one of three colors (Color.RED, Color.GREEN, Color.BLUE) and uses that color for three
    // moves, then randomly picks one of those colors again for the next three moves, then randomly picks
    // another one of those colors for the next three moves, and so on.
    public Color getColor() {
        if (moves % 3 == 0) {
            int rand = (int) (Math.random() * 10) % 3;

            if (rand == 0) {
                c = 'r';
                return Color.RED;
            } else if (rand == 1) {
                c = 'g';
                return Color.GREEN;
            } else {
                c = 'b';
                return Color.BLUE;
            }
        } else {
            switch (c) {
                case 'r':   return Color.RED;
                case 'g':   return Color.GREEN;
                case 'b':   return Color.BLUE;
                default:    return null;
            }
        }
    }

    // "TGR"
    public String toString() {
        return "TGR";
    }

    // always infect if an enemy is in front, otherwise if a wall is in front or to the right, then turn left, otherwise if
    // a fellow Tiger is in front, then turn right, otherwise hop.
    public Action getMove(CritterInfo info) {
        moves++;

        if (info.getFront() == Neighbor.OTHER) {
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
