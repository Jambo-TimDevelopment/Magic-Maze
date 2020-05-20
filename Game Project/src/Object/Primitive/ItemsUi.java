package Object.Primitive;

import java.awt.*;

public class ItemsUi {
    private boolean focus;
    private Integer id = 0;
    private String tittle;
    private Color color;

    public ItemsUi(boolean focus, Integer id, String tittle){
        this.focus = focus;
        this.id = id;
        this.tittle = tittle;
        if(id == 0){
            color = Color.GREEN;
        }else if(id == 1){
            color = Color.orange;
        }else if(id == 2){
            color = Color.red;
        }else if(id == 3){
            color = Color.MAGENTA;
        }
    }

    public boolean getFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public Integer getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public Color getColor() {
        return color;
    }
}
