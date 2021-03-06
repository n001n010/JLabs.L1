package L1.DemoClasses;

import java.util.Iterator;
import L1.Exceptions.GeometricException;

public class Polygon extends Polyline {
    public Polygon() {
        super();
    }

    private String figuringOut(Integer anglesAmount) throws GeometricException {

        if (anglesAmount < 3) {
            String exceptionMessage = "Yet not a polygon! Not enough angles: " 
                                    + anglesAmount;
            throw new GeometricException(exceptionMessage);
        }

        switch (anglesAmount) {
            case 3:  return (" [Triangle]");
            case 4:  return (" [Tetragon]");
            case 10: return (" [Hellofagon]");
        }
        return "";
    }

    @Override
    double calculatePerimeter() {
        return super.calculatePerimeter()+first.findDistanceToAnotherPoint(last);
    }
    
    @Override
    double calculateArea() {
        double area = 0;
        Iterator<Point> polygonItr = points.keySet().iterator();

        previous = polygonItr.next();
        first = previous;

        while (polygonItr.hasNext()) {
            next = polygonItr.next();
            area += ((previous.x + next.x) * (previous.y - next.y));
            previous = next;

            if (!polygonItr.hasNext()) area += ((previous.x + first.x) 
                                              * (previous.y - first.y));

        }

        if (area < 0) area *= -1;
        return 0.5 * area;
    }

    @Override
    public String getInformation() {
        try {
            return figuringOut(this.points.size()) + " with " + points.size() 
                                                   + " points: " + points;
        } catch (GeometricException e) {
            e.printStackTrace();
        }
        return " exception at getInformation()";
    }
}