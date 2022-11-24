package co.edu.uniquindio.ingesis;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

class ShapeTest {

    @Test
    void exercise() {
        List<Shape> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            switch (i%3){
                case 0: list.add(new Circle((float) (Math.random()*i))); break;
                case 1: list.add(new Square((float) (Math.random()*i))); break;
                case 2: list.add(new Triangle((float) (Math.random()*i), (float) (Math.random()*i))); break;
            }
        }

        for (Shape shape : list) {
            System.out.println(shape.computeArea());
        }

        System.out.println("solved...");

        List<Square> sq = list.stream().map(l -> l.getClass().equals(new Square(1f))).collect(Collectors.toList());
        DoubleSummaryStatistics summaryStatistics = sq.stream().mapToDouble(s -> s.computeArea()).summaryStatistics();

        System.out.println("sum area values " + summaryStatistics.getSum());
        System.out.println("max area values " + summaryStatistics.getMax());
        System.out.println("min area values " + summaryStatistics.getMin());
        System.out.println("average area values " + summaryStatistics.getAverage());

    }
}