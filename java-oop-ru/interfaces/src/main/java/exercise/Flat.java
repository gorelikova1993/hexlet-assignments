package exercise;

// BEGIN
public class Flat implements Home{
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area + balconyArea;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
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
