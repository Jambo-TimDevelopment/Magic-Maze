package Object.Primitive;

import java.awt.*;

public class MyList<T> extends List {
    private MyList prev;
    private MyList next;
    T var = null;

    public MyList(){
        this.var = null;
        this.next = null;
        this.prev = null;
    }

    public MyList(T var, MyList next, MyList prev){
        this.var = var;
        this.next = next;
        this.prev = prev;
    }

    public T prev() {
        return (T) prev.getVar();
    }

    public T next() {
        return (T) next.getVar();
    }

    public boolean hasNext(){
        if(this.next == null){
            return false;
        }else{
            return true;
        }
    }

    public MyList getNext(){
        return next;
    }

    public MyList getPrev(){
        return prev;
    }

    public void setPrev(MyList prev){
        this.prev = prev;
    }

    public boolean hasPrev(){
        if(this.prev == null){
            return false;
        }else{
            return true;
        }
    }

    public void add(T var){
        MyList next = this.next;
        this.next = new MyList(var, next, this);
        try{
            this.next.getNext().setPrev(this.next);
        }catch (Exception ex){ }

    }

    public T getVar(){
        return var;
    }

    public void ptint(){
        while (this.hasNext()){
            System.out.println("->" + this.getVar().toString());
        }
    }
}
