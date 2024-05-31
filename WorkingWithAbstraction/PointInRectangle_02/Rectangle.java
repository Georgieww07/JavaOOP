package WorkingWithAbstraction.PointInRectangle_02;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean contains(Point point){
        boolean isInX = point.getX() >= this.bottomLeft.getX() && point.getX() <= this.topRight.getX();
        boolean isInY = point.getY() >= this.bottomLeft.getY() && point.getY() <= this.topRight.getY();

        return isInX && isInY;
    }
}
