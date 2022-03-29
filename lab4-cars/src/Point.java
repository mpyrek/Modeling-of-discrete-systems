import java.math.*;

//program pokazuje manerw wyprzedzania 

public class Point {
    int type;
    Point next;
    boolean moved = false;
    int v;
    Point prev;
    int iteration;
    Point neighbour;
    Boolean is_right;
    int maxV;
    public static Integer[] types = {0, 1, 2, 3, 5};

    public void move() {
        if (this.type != 0 && !this.moved && this.neighbour.type == 0) {
//            if (!this.is_right && this.check_condition_to_return()) {
//                System.out.println("wracaj");
//                this.neighbour.type = this.type;
//                this.neighbour.v = this.v -1;
//                this.neighbour.maxV = this.maxV;
//                this.neighbour.is_right = true;
//                this.type = 0;
//                this.moved = true;
//                this.neighbour.moved = true;

            if (this.is_right && this.overtake() && this.check_condition_to_overtake() && this.neighbour.type == 0) {
                this.neighbour.type = this.type;
                this.neighbour.v = this.v + 1;
                this.neighbour.maxV = this.maxV;
                this.neighbour.is_right = false;
                this.type = 0;
                this.moved = true;
                this.neighbour.moved = true;
                int distance = 0;
                Point temp = this;
                while (temp.next.type == 0 && distance < this.v) {
                    distance++;
                    temp = temp.next;
                }
                this.iteration = distance;
            }
        }


        if (this.moved == false && this.type != 0) {
            int distance = 0;
            Point temp = this;
            while (temp.next.type == 0 && distance < this.v) {
                distance++;
                temp = temp.next;
            }

            temp.type = this.type;
            this.type = 0;
            this.moved = true;
            temp.moved = true;
            temp.v = this.v;
            temp.maxV = this.maxV;
            this.v = 0;
        }
    }

    private boolean overtake() {
        int distance = 0;
        Point temp = this;
        while (temp.next.type == 0 && distance < this.v) {
            distance++;
            temp = temp.next;
        }
        return (distance < this.v);
    }

    private boolean check_condition_to_overtake() {
        boolean first_condition = (this.v <= this.maxV);
        boolean second_condition = true;
        boolean third_condition = true;
        boolean fourth_condition = true;

        Point check_back_r = this;
        int distance = 0;
        while (check_back_r.prev.type == 0 && distance <= 7) {
            check_back_r = check_back_r.prev;
            distance = distance + 1;
        }
        if (check_back_r.prev.type != 0) second_condition = (distance >= check_back_r.prev.maxV);


        Point check_back_neighbour = this.neighbour;
        distance = 0;

        while (check_back_neighbour.prev.type == 0 && distance <= 7) {
            check_back_neighbour = check_back_neighbour.prev;
            distance = distance + 1;
        }
        if (check_back_neighbour.prev.type != 0) third_condition = (distance >= check_back_neighbour.prev.maxV);


        Point check_next_neighbour = this.neighbour;
        distance = 0;

        while (check_next_neighbour.prev.type == 0 && distance <= 7) {
            check_next_neighbour = check_next_neighbour.prev;
            distance = distance + 1;
        }
        if (check_next_neighbour.prev.type != 0) fourth_condition = (distance >= this.v);


        return first_condition && second_condition && third_condition && fourth_condition;
    }


    private boolean check_condition_to_return() {
        boolean first_condition = true;
        boolean second_condition = true;
        boolean third_condition = true;

        Point check_back_r = this.neighbour;
        int distance = 0;
        while (check_back_r.prev.type == 0 && distance <= 7) {
            check_back_r = check_back_r.prev;
            distance = distance + 1;
        }
        if (check_back_r.prev.type != 0) first_condition = (distance > check_back_r.prev.maxV);

        Point check_back_l = this;
        distance = 0;
        while (check_back_l.prev.type == 0 && distance <= 7) {
            check_back_l = check_back_l.prev;
            distance = distance + 1;
        }
        if (check_back_l.prev.type != 0) second_condition = (distance <= check_back_l.prev.maxV);


        Point check_next_r = this.neighbour;
        distance = 0;
        while ((check_next_r.prev.type == 0 || check_next_r.prev.type == 5) && distance <= 7) {
            check_next_r = check_next_r.prev;
            distance = distance + 1;
        }
        if (check_next_r.prev.type != 0 && check_next_r.prev.type != 5) third_condition = (distance >= this.v);


        return first_condition && second_condition && third_condition;
    }


    public void clicked() {
        this.type = 0;
    }

    public void setMaxV() {
        if (this.type == 1) {
            this.maxV = 3;
            this.v = this.maxV;
        } else if (this.type == 2) {
            this.maxV = 5;
            this.v = this.maxV;
        } else if (this.type == 3) {
            this.maxV = 7;
            this.v = this.maxV;
        }
    }

    public void clear() {
        this.type = 0;
    }
}
