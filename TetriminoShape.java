import java.awt.*;


public enum TetriminoShape {
	
	/**code modified : @see <Brooke Bullek (2016) TShape source code 	 * (Version 1.0) [Source code].https://github.com/DTV96Calibre/		 * Tetris/blob/master/src/tetris model/TShape.java >
	 */
	

I_BLOCK(new Point[]{new Point(0, 0), new Point(-1, 0), new Point(1, 0),
             new Point(2, 0)}, "cyan"),
J_BLOCK(new Point[]{new Point(0, 0), new Point(-1, 0), new Point(-1, 1),
             new Point(1, 0)}, "blue"),
L_BLOCK(new Point[]{new Point(0, 0), new Point(-1, 0), new Point(1, 0),
             new Point(1, 1)}, "orange"),
O_BLOCK(new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, -1),
             new Point(1, -1)}, "yellow"),
S_BLOCK(new Point[]{new Point(0, 0), new Point(-1, 0), new Point(0, 1),
             new Point(1, 1)}, "green"),
T_BLOCK(new Point[]{new Point(0, 0), new Point(-1, 0), new Point(1, 0),
             new Point(0, 1)}, "magenta"),
Z_BLOCK(new Point[]{new Point(0, 0), new Point(0, 1), new Point(1, 0),
             new Point(-1, 1)}, "red");

/**
* Array of Points rather than array of Blocks (to reduce dependencies)
*/
private final Point[] tetrisLocations;

/**
* The color of type of Tetrimino
*/
private final String color;

/**
* Constructs a new TetriminoShape instance
*
* @param minoLocations Block locations
* @param color The color of this TetriminoShape instance
*/
TetriminoShape(Point[] minoLocations, String color) {
this.color = color;
this.tetrisLocations = minoLocations;
}

/* Getters and setters */
public Point[] getTetrisLocations() {
return tetrisLocations;
}

public String getColor() {
return color;
}
/* End of getters and setters */
}

