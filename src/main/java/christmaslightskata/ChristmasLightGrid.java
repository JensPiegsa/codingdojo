package christmaslightskata;

public class ChristmasLightGrid {

    boolean [][] lightGrid =  new boolean[1000][1000];

    public void turnLightOn(Rectangle rectangle) {
        for(int x = rectangle.x1(); x<= rectangle.x2(); x++){
            for(int y = rectangle.y1(); y<= rectangle.y2(); y++){
                lightGrid[x][y] = true;
            }
        }
    }

    public boolean isLightOn(Point point) {
        return lightGrid[point.x()][point.y()];
    }

    public void toggleLight(Rectangle rectangle) {
        for(int x = rectangle.x1(); x<= rectangle.x2(); x++){
            for(int y = rectangle.y1(); y<= rectangle.y2(); y++){
                lightGrid[x][y] = !lightGrid[x][y];
            }
        }
    }

    // TODO Consumer<Rectangle> ...
//    private void doSomethingWithLights(int x1, int y1, int x2, int y2, Consumer<Boolean> consumer){
//
//        for(int x=x1; x<=x2; x++){
//            for(int y=y1; y<=y2; y++){
//                lightGrid[x][y] = consumer.???;
//            }
//        }
//    }
}
