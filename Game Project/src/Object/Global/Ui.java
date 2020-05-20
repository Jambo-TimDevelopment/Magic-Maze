package Object.Global;

import Object.Primitive.ItemsUi;
import Object.Primitive.MyList;

public class Ui {
    MyList<ItemsUi> uiList;
    Integer countUiItems = 0;
    public int arr[] = new int[5];
    public int n = 0;

    public Ui() {
        ItemsUi items1 = new ItemsUi(true, 1, "1");
        ItemsUi items2 = new ItemsUi(false, 2, "2");
        ItemsUi items3 = new ItemsUi(false, 3, "3");

        uiList = new MyList<ItemsUi>();

        uiList.add(items1);
        countUiItems++;
        uiList.add(items2);
        countUiItems++;
        uiList.add(items3);
        countUiItems++;
    }

    public void print() {
       // System.out.println("->");
        while (uiList.hasNext()) {
            ItemsUi uiItem = uiList.next();
            System.out.println("->" + uiItem.getTittle());
            uiList = uiList.getNext();
        }

        while (uiList.hasPrev()) {
            ItemsUi uiItem = uiList.prev();
            try {
                System.out.println("<-" + uiItem.getTittle());
            }catch (Exception ex){ }
            uiList = uiList.getPrev();
        }
    }

    public void toLeft() {
        System.out.println("->");
        while (uiList.hasNext()) {
            ItemsUi uiItem = uiList.next();
            //Если мы еще не утонули в дерьме
            if(uiItem != null){
                //И если мы не по колено в дерьме
               if(uiItem.getFocus() && uiList.hasPrev()){
                   //Меняем фокусировку
                   uiItem.setFocus(false);
                   ItemsUi t = (ItemsUi) uiList.getPrev().getVar();
                   try {
                       t.setFocus(true);
                   }catch (Exception ex){ }
               }
            }
            System.out.println(uiList.getVar().getTittle());
            uiList = uiList.getNext();
        }

        while (uiList.hasPrev()) {
            System.out.println(uiList.getVar().getTittle());
            uiList = uiList.getPrev();
        }
    }

    public void toRight() {
        System.out.println("=>");
        while (uiList.hasNext()) {
            try{
                ItemsUi uiItem = uiList.prev();
                //Если мы еще не утонули в дерьме
                if(uiItem != null){
                    //И если мы не по колено в дерьме
                    if(uiItem.getFocus() && uiList.hasNext()){
                        //Меняем фокусировку
                        uiItem.setFocus(false);
                        ItemsUi t = (ItemsUi) uiList.getNext().getVar();
                        try {
                            t.setFocus(true);
                        }catch (Exception ex){ }
                    }
                }
            }catch (Exception ex){

            }

            uiList = uiList.getNext();
        }

        while (uiList.hasPrev()) {
            uiList = uiList.getPrev();
        }
    }

    public MyList<ItemsUi> getUiList() {
        return uiList;
    }

    public Integer getCountUiItems() {
        return countUiItems;
    }
}
