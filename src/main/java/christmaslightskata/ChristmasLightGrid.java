package christmaslightskata;

public class ChristmasLightGrid {

    boolean [][] lightGrid =  new boolean[1000][1000];

    public void turnLightOn(int x1, int y1, int x2, int y2) {
        for(int x=x1; x<=x2; x++){
            for(int y=y1; y<=y2; y++){
                lightGrid[x][y] = true;
            }
        }
    }

    public boolean isLightOn(int x, int y) {
        return lightGrid[x][y];
    }
}
