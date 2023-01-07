package chessutils;

public class Move {
    private Position from;
    private Position to;
    public Move(String from,String to){
        int fromX = from.charAt(0) - 'a' + 1;
        int fromY = Integer.parseInt(from.substring(1, 2));
        int toX = to.charAt(0) - 'a' + 1;
        int toY = Integer.parseInt(to.substring(1, 2));
        this.from = Position.of(fromX,fromY);
        this.to = Position.of(toX,toY);
    }
    public Move(Position from, Position to){
        this.from = from;
        this.to = to;
    }
    public int fromY(){
        return from.getY();
    }
    public int fromX(){
        return from.getX();
    }
    public int toY(){
        return to.getY();
    }
    public int toX(){
        return to.getX();
    }
}
