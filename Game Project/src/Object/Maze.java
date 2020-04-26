package Object;
import java.util.Stack;

public class Maze {
	private int[][] field = new int[100][100];
	public int SIZE_MAP;
	public int COUNT_CHEK_POINTS;
	private Stack<Point> way = new Stack<Point>();
	private CheckPoint chekPoints;

	public Maze(int size_map, int count_points){
		SIZE_MAP = size_map;
		if(SIZE_MAP > 48) {
			SIZE_MAP /= 2;
		}
		COUNT_CHEK_POINTS = count_points;
		for(int i = 0; i <= SIZE_MAP; i++){
			for(int j = 0; j <= SIZE_MAP; j++){
				if(i % 2 == 1 && j % 2 == 1){
					field[i][j] = 0;
				}else {
					field[i][j] = 1;
				}
			}
		}

		generateNewMaze(1, 1);
		createCycle();
		setChekPoints(new CheckPoint(COUNT_CHEK_POINTS, this));
	}

	public void generateNewMaze(int startI, int startJ){
		way.push(new Point(startI, startJ));
		EndPoint endPoint = new EndPoint(this);
		field[startI][startJ] = 2;
		while (!way.empty()){
			if(isDeadEnd(way.peek())){
				way.pop();
				endPoint.lenthWay--;
				continue;
			}
			int direction = (int)(Math.random() * 4);
			switch (direction){

				case 0:{
					if(isLeft(way.peek())){
						toLeft(way.peek());
						endPoint.searchMaxLenth(way.peek());
					}
					break;
				}

				case 1:{
					if(isUp(way.peek())){
						toUp(way.peek());
						endPoint.searchMaxLenth(way.peek());
					}
					break;
				}

				case 2:{
					if(isRight(way.peek())){
						toRight(way.peek());
						endPoint.searchMaxLenth(way.peek());
					}
					break;
				}

				case 3:{
					if(isDown(way.peek())){
						toDown(way.peek());
						endPoint.searchMaxLenth(way.peek());
					}
					break;
				}

				default:{
					System.out.println(" Return Default!!!\n What is It???");
				}
			}


		}

		endPoint.createEndPoint(field);
	}

	public void createCycle() {
		if(SIZE_MAP == 48) {
			int k = 0;
			while(k <= 48){
				int i = (int) (Math.random() * SIZE_MAP);
				int j = (int) (Math.random() * SIZE_MAP);
				if(i != 0 && j != 0) {
					System.out.println("i = " + i + " j = " + j);
					if(i >= 1 && i < SIZE_MAP && j >= 1 && j < SIZE_MAP && field[i][j] == 1
							&& (field[i + 1][j] == 1 && field[i - 1][j] == 1 || field[i][j - 1] == 1 && field[i][j + 1] == 1)) {
						k++;
						field[i][j] = 2;
					}
				}
			}
		}
	}

	public String toString(){
		String s = new String();
		for(int i = 0; i <= SIZE_MAP; i++){
			for(int j = 0; j <= SIZE_MAP; j++){
				s = s + field[i][j] + "  ";
			}
			s = s + "\n";
		}
		return s;
	}

	private boolean isDeadEnd(Point dot){
		return !(isLeft(dot) || isRight(dot) || isUp(dot) || isDown(dot));
	}

	private boolean isLeft(Point dot){
		int j = dot.j;
		int i = dot.i;
		return  j - 2 >= 0 && field[i][j - 2] == 0;
	}

	private boolean isRight(Point dot){
		int j = dot.j;
		int i = dot.i;
		return j + 2 <= SIZE_MAP && field[i][j + 2] == 0;
	}

	private boolean isUp(Point dot){
		int j = dot.j;
		int i = dot.i;
		return i - 2 >= 0 && field[i - 2][j] == 0;
	}

	private boolean isDown(Point dot){
		int j = dot.j;
		int i = dot.i;
		return i + 2 <= SIZE_MAP  && field[i + 2][j] == 0;
	}

	private void toLeft(Point dot){
		int j = dot.j;
		int i = dot.i;
		way.push(new Point(i, j - 2));
		field[i][j - 1] = 2;
		field[i][j - 2] = 2;
	}

	private void toRight(Point dot){
		int j = dot.j;
		int i = dot.i;
		way.push(new Point(i, j + 2));
		field[i][j + 1] = 2;
		field[i][j + 2] = 2;
	}

	private void toUp(Point dot){
		int j = dot.j;
		int i = dot.i;
		way.push(new Point(i - 2, j ));
		field[i - 1][j] = 2;
		field[i - 2][j] = 2;
	}

	private void toDown(Point dot){
		int j = dot.j;
		int i = dot.i;
		way.push(new Point(i + 2, j));
		field[i + 1][j] = 2;
		field[i + 2][j] = 2;
	}

	public int[][] getField(){
		return field;
	}

	public boolean setField(int i, int j, int value) {
		if(i <= SIZE_MAP && i >= 0 && j <= SIZE_MAP && j >= 0) {
			try {
				field[i][j] = value;
			}catch(Exception e){
				return false;
			}
			return true;
		}else {
			return false;
		}
	}

	public CheckPoint getChekPoints() {
		return chekPoints;
	}

	public void setChekPoints(CheckPoint chekPoints) {
		this.chekPoints = chekPoints;
	}
}


