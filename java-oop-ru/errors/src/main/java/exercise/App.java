package exercise;

import java.text.DecimalFormat;

// BEGIN
public class App {
    public static void printSquare(Circle circle)  {
        try {
            double square = Math.ceil(circle.getSquare());
            DecimalFormat decimalFormat = new DecimalFormat("#.###");
            String result = decimalFormat.format(square);
            System.out.println(result);
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        }
        System.out.println("Вычисление окончено");
    }

    public static void main(String[] args) throws NegativeRadiusException {
        Point point = new Point(5, 7);
        Circle circle = new Circle(point, 4);
        App.printSquare(circle);

        Circle circle1 = new Circle(point, -2);
        App.printSquare(circle1);
    }
}
// END
