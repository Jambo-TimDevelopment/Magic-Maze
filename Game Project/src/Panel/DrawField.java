package Panel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import Object.Maze;

public class DrawField extends JPanel{
	Maze maze;
	int[][] field;

	DrawField(Maze field){
		super();
		setBackground(Color.black);
		this.field = field.getField();
		maze = field;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i <= maze.SIZE_MAP; i++) {
			for(int j = 0; j <= maze.SIZE_MAP; j++) {
				if(field[i][j] == 1) {
					g.setColor(Color.orange);
					g.fillRect(j * 10, i * 10, 10, 10);
				}else if(field[i][j] == 3) {
					g.setColor(Color.CYAN);
					g.fillRect(j * 10, i * 10, 8, 8);
				}else if(field[i][j] == 4) {
					g.setColor(Color.green);
					g.fillRect(j * 10, i * 10, 8, 8);
				}else if(field[i][j] == 5) {
					g.setColor(Color.black);
					g.fillRect(j * 10, i * 10, 8, 8);
				}else if(field[i][j] == 6) {
					g.setColor(Color.MAGENTA);
					g.fillRect(j * 10, i * 10, 8, 8);
				}
			}
		}
	}

	public void setField(Maze maze) {
		this.field = maze.getField();
		this.maze = maze;
	}
}
