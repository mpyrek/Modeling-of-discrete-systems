import java.math.*;


public class Point {

    int type;
    Point next;
    boolean moved;
    int v;

    public void move() {
        if (this.type == 1 && this.moved == false) {

            if (this.v < 5) {
                this.v = this.v + 1;
            }

            int x = (int) (Math.random() * 3);
            if (x == 0) this.v = this.v -1;

            int distance = 0;
            Point temp = this;
            while (temp.next.type == 0 && distance < this.v) {
                distance++;
                temp = temp.next;
            }

            this.type = 0;
            this.moved = true;
            temp.moved=true;
            temp.type = 1;
            temp.v = this.v;
            this.v = 0;

        }
    }

    public void clicked() {
        this.type = 1;
        this.v = 0;
    }

    public void clear() {
        this.type = 0;
    }
}

