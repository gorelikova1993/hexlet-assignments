package exercise;

// BEGIN
public class Segment {
    private Point pointBegin;
    private Point pointEnd;

    public Segment(Point pointBegin, Point pointEnd) {
        this.pointBegin = pointBegin;
        this.pointEnd = pointEnd;
    }

    public Point getBeginPoint() {
        return pointBegin;
    }

    public Point getEndPoint() {
        return pointEnd;
    }

    public Point getMidPoint() {
        int xC = (pointBegin.getX() + pointEnd.getX()) / 2;
        int yC = (pointBegin.getY() + pointEnd.getY()) / 2;
        return new Point(xC, yC);
    }
}
// END
