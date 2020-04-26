package Object;

public class CheckPoint {

    public static final int POINTS_VALUE = 4;
    private Point[] AllChekPoints = new Point[100];

    CheckPoint(int count, Maze maze) {
	int[][] field = maze.getField();
	while(count != 0) {
	    int i = (int)(Math.random() * maze.SIZE_MAP);
	    int j = (int)(Math.random() * maze.SIZE_MAP);
	    if(field[i][j] == 2) {
		count--;
		field[i][j] = POINTS_VALUE;
		AllChekPoints[count] = new Point(i, j);

	    }
	}
    }
}
