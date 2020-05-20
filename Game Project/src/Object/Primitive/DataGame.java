package Object.Primitive;

import java.io.Serializable;

public class DataGame implements Serializable {
    public int[][] field;
    public  int SIZE_MAP_i;
    public  int SIZE_MAP_j;
    public int p_i;
    public int p_j;

    public DataGame(int[][] field, int SIZE_MAP_i, int SIZE_MAP_j){
        this.field = field;
        this.SIZE_MAP_i = SIZE_MAP_i;
        this.SIZE_MAP_j = SIZE_MAP_j;
        for(int i = 0; i <= SIZE_MAP_i; i++){
            for(int j = 0; j <= SIZE_MAP_j; j++){
                if(field[i][j] == 3){
                    this.p_i = i;
                    this.p_j = j;
                }
            }
        }
    }

   /* public void GetPlayer(){
        for(int i = 0; i <= SIZE_MAP_i; i++){
            for(int j = 0; j <= SIZE_MAP_j; j++){
                if(field[i][j] == 3){
                    this.p_i = i;
                    this.p_j = j;
                    System.out.println(" Координаты сохранения " + i + " " + j);
                }
            }
        }
    }*/

    public DataGame(){

    }
}

