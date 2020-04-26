package Object;

public class Player extends Point{
    private Integer CHECK_POINTS;
    private Integer FINISH;
    public Maze field;
    private int score;
    private EndPoint finish;

    public Player(int i, int j, Maze field) {
        super(i, j);
        CHECK_POINTS = CheckPoint.POINTS_VALUE;
        FINISH = EndPoint.FINISH_POINT;
        setScore(0);
        field.setField(i, j, 3);
        this.field = field;
        finish = new EndPoint(field);
    }

    private void checkScore(int ki, int kj) {
        if(field.getField()[i + ki][j + kj] == CHECK_POINTS) {
            setScore(getScore() + 1);
        }
    }

    private boolean checkFinish(int ki, int kj) {
        if(field.getField()[i + ki][j + kj] == FINISH) {
            return true;
        }else {
            return false;
        }
    }

    public boolean moveLeft() {
        if(j - 1 >= 0 && field.getField()[i][j - 1] != 1) {
            checkScore(0, -1);
            boolean isFinish = checkFinish(0, -1);
            field.setField(i,j - 1, 3);
            field.setField(i, j, 2);
            j--;
            return isFinish;
        }else {
            return false;
        }

    }

    public boolean moveRight() {

        if(j + 1 <= field.SIZE_MAP && field.getField()[i][j + 1] != 1) {
            checkScore(0, 1);
            boolean isFinish = checkFinish(0, 1);
            field.setField(i,j + 1, 3);
            field.setField(i, j, 2);
            j++;
            return isFinish;
        }else {
            return false;
        }

    }

    public boolean moveUp() {
        if(i - 1 >= 0 && field.getField()[i - 1][j] != 1) {
            checkScore(-1, 0);
            boolean isFinish = checkFinish(-1, 0);
            field.setField(i - 1, j, 3);
            field.setField(i, j, 2);
            i--;
            return isFinish;
        }else {
            return false;
        }
    }

    public boolean moveDown() {
        if(i + 1 <= field.SIZE_MAP && field.getField()[i + 1][j] != 1) {
            checkScore(1, 0);
            boolean isFinish = checkFinish(1, 0);
            field.setField(i + 1,j, 3);
            field.setField(i, j, 2);
            i++;
            return isFinish;
        }else {
            return false;
        }

    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
