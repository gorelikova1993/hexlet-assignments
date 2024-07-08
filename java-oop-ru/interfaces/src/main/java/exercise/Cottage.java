package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public double getArea() {
        return area;
    }

    public String toString() {
        return floorCount + " этажный коттедж площадью " + getArea() + "  метров";
    }

    public int compareTo(Home anotherHome) {
        if (getArea() == anotherHome.getArea()) {
            return 0;
        }
        if (getArea() >= anotherHome.getArea()) {
            return 1;
        }
        return -1;
    }
}
// END
