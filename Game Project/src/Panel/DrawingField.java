package Panel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

import Object.Global.Maze;
import Object.Global.Ui;
import Object.Primitive.ItemsUi;
import Object.Primitive.MyList;

public class DrawingField extends JPanel {
    Maze maze;
    Ui ui;
    int[][] field;
    private int width = 150;
    private int height = 150;

    DrawingField(Maze maze, Ui ui) {
        super();
        setBackground(Color.black);
        this.field = maze.getField();
        this.maze = maze;
        this.ui = ui;
        setSize();
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Game_Panel.isGame) {
            drawGame(g);
        }else{
            drawUi(g);
        }
    }



    public void setField(Maze maze) {
        this.field = maze.getField();
        this.maze = maze;
    }

    public void setSize() {
        float k = (float) 1.0;


        while ((int) (width * maze.SIZE_MAP_i * k) >= 700) {
            k -= 0.05;
        }
        width = (int) (width * k);
        height = (int) (height * k);
    }

    private void setImage(Graphics g, ItemsUi uiItem, int x){
        if (uiItem.getId() == 1) {
            ImageIcon icon = new ImageIcon("src/Assets/Level_1.png");
            Image level = icon.getImage();
            g.drawImage(level, x - 25, 350 - 50, null);

            if (uiItem.getFocus()) {
                icon = new ImageIcon("src/Assets/Focus.png");
                level = icon.getImage();
                g.drawImage(level, x - 25, 350 - 50, null);
            }
        } else if (uiItem.getId() == 2) {
            ImageIcon icon = new ImageIcon("src/Assets/Level_2.png");
            Image level = icon.getImage();
            g.drawImage(level, x - 25, 350 - 50, null);

            if (uiItem.getFocus()) {
                icon = new ImageIcon("src/Assets/Focus.png");
                level = icon.getImage();
                g.drawImage(level, x - 25, 350 - 50, null);
            }
        } else if (uiItem.getId() == 3) {
            ImageIcon icon = new ImageIcon("src/Assets/Level_3.png");
            Image level = icon.getImage();
            g.drawImage(level, x - 25, 350 - 50, null);

            if (uiItem.getFocus()) {
                icon = new ImageIcon("src/Assets/Focus.png");
                level = icon.getImage();
                g.drawImage(level, x - 25, 350 - 50, null);
            }
        }
    }

    private void drawGame(Graphics g){
        setSize();

        setBackground(Color.black);

        for (int i = 0; i <= maze.SIZE_MAP_i; i++) {
            for (int j = 0; j <= maze.SIZE_MAP_j; j++) {
                if (field[i][j] == 1) {
                    g.setColor(Color.orange);
                    ImageIcon icon = new ImageIcon("src/Assets/stone.png");
                    Image stoneImg = icon.getImage();
                    g.drawImage(stoneImg, j * width, i * height, width, height, null);
                    //g.fillRect(j * width, i * height, width, height);
                } else if (field[i][j] == 3) {
                    g.setColor(Color.CYAN);
                    g.fillRect(j * width, i * height, width, height);
                }
            }
        }
    }

    private void drawUi(Graphics g){
        setBackground(Color.white);
        MyList<ItemsUi> list = ui.getUiList();
        while (list.hasNext()) {


            //получаем следующий элемент
            ItemsUi uiItem = list.next();

            //Настройка локации пункта
            Integer count = ui.getCountUiItems();
            int x = 300 + (300 / (count - 1) * uiItem.getId());

            //Отрисовка картинки
            g.setColor(Color.black);

            setImage(g, uiItem, x);
            if (uiItem.getFocus()) {
                g.setColor(Color.GREEN);
            }

            //Переход к следуюшему элеметн
            list = list.getNext();
        }

        while (list.hasPrev()) {
            list = list.getPrev();
        }
    }
}
