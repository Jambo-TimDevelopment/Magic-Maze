package Panel;

import Object.Global.Ui;
import Object.Primitive.ItemsUi;
import Object.Primitive.MyList;

import javax.swing.*;
import java.awt.*;

public class DrawingUI extends JPanel {

    private Ui ui;

    DrawingUI(Ui ui) {
        this.ui = ui;
        //setBackground(Color.orange);
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        MyList<ItemsUi> list = ui.getUiList();
        int i = 0;
        int arr[] = new int[5];
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

            if(uiItem.getFocus()){
                arr[i] = 1;
            }else{
                arr[i] = 0;
            }

            //Переход к следуюшему элеметн
            list = list.getNext();
        }
        ui.n = i;

        while (list.hasPrev()) {
            list = list.getPrev();
        }
    }
}