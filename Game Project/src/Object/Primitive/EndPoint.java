package Object.Primitive;


import java.io.Serializable;

public class EndPoint extends Point implements Serializable {
    public int lenthWay = 0;
    public int maxLenthWay = 0;
    private int SIZE_MAP_i;
    private int SIZE_MAP_j;
    public static int FINISH_POINT = 5;
    //private Maze field;

    public EndPoint(int[][] field, int SIZE_MAP_i, int SIZE_MAP_j) {
        super(0, 0);
       // this.field = field;
        this.SIZE_MAP_i = SIZE_MAP_i;
        this.SIZE_MAP_j = SIZE_MAP_j;

        createEndPoint(field);
    }

    public void createEndPoint(int[][] gameField) {
        if (i + 1 == SIZE_MAP_i) {
            gameField[i + 1][j] = FINISH_POINT;
            this.i = i + 1;
        } else if (i - 1 == 0) {
            gameField[i - 1][j] = FINISH_POINT;
            this.i = i - 1;
        } else if (j - 1 == 0) {
            gameField[i][j - 1] = FINISH_POINT;
            this.j = j - 1;
        } else if (j + 1 == SIZE_MAP_j) {
            gameField[i][j + 1] = FINISH_POINT;
            this.j = j + 1;
        }
    }

    public void searchMaxLenth(Point peek) {
        lenthWay++;
        if (peek.i + 1 == SIZE_MAP_i || peek.j + 1 == SIZE_MAP_j || peek.i - 1 == 0 || peek.i - 1 == 0) {
            if (lenthWay > maxLenthWay) {
                maxLenthWay = lenthWay;
                i = peek.i;
                j = peek.j;
            }
        }
    }
}
