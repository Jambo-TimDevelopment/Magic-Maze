package Object;

public class EndPoint extends Point{

    public int lenthWay = 0;
    public int maxLenthWay = 0;
    public static int FINISH_POINT = 5;
    private Maze field;
    
    EndPoint(Maze field){
	super(0,0);
	this.field = field;
	createEndPoint(field.getField());
    }

    public void createEndPoint(int[][] gameField) {
	if(i + 1 == field.SIZE_MAP) {
	    gameField[i + 1][j] = FINISH_POINT;
	    this.i = i + 1;
	}else if(i - 1 == 0) {
	    gameField[i - 1][j] = FINISH_POINT;
	    this.i = i - 1;
	}else if(j - 1 == 0) {
	    gameField[i][j - 1] = FINISH_POINT;
	    this.j = j - 1;
	}else if(j + 1 == field.SIZE_MAP) {
	    gameField[i][j + 1] = FINISH_POINT;
	    this.j = j + 1;
	} 
    }

    public void searchMaxLenth(Point peek) {
	lenthWay++;
	if(peek.i + 1 == field.SIZE_MAP || peek.j + 1 == field.SIZE_MAP || peek.i - 1 == 0 || peek.i - 1 == 0) {
	    if(lenthWay > maxLenthWay) {
		maxLenthWay = lenthWay;
		i = peek.i;
		j = peek.j;
	    }
	}
    } 
}
