package Object.Primitive;

import Object.Global.Maze;

import java.io.Serializable;

public  class Player extends Point implements Serializable {
    private static Integer FINISH;
    public static Maze field;

    public Player(int i, int j, Maze field) {
        super(i, j);
        FINISH = EndPoint.FINISH_POINT;
        field.setField(i, j, 3);
        this.field = field;
    }

    private boolean checkFinish(int ki, int kj) {
        if (field.getField()[i + ki][j + kj] == FINISH) {
            return true;
        } else {
            return false;
        }
    }

    public boolean moveLeft() {
        if (j - 1 >= 0 && field.getField()[i][j - 1] != 1) {
            boolean isFinish = checkFinish(0, -1);
            field.setField(i, j - 1, 3);
            field.setField(i, j, 2);
            j--;
            return isFinish;
        } else {
            return false;
        }

    }

    public boolean moveRight() {
        if (j + 1 <= field.SIZE_MAP_j && field.getField()[i][j + 1] != 1) {
            boolean isFinish = checkFinish(0, 1);
            field.setField(i, j + 1, 3);
            field.setField(i, j, 2);
            j++;
            return isFinish;
        } else {
            return false;
        }

    }

    public boolean moveUp() {
        if (i - 1 >= 0 && field.getField()[i - 1][j] != 1) {
            boolean isFinish = checkFinish(-1, 0);
            field.setField(i - 1, j, 3);
            field.setField(i, j, 2);
            i--;
            return isFinish;
        } else {
            return false;
        }
    }

    public boolean moveDown() {
        if (i + 1 <= field.SIZE_MAP_i && field.getField()[i + 1][j] != 1) {
            boolean isFinish = checkFinish(1, 0);
            field.setField(i + 1, j, 3);
            field.setField(i, j, 2);
            i++;
            return isFinish;
        } else {
            return false;
        }

    }
}
