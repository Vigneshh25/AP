package Tetris_Game;

import java.util.*;
                    // Create a java file in your ide and copy paste this whole code and Play the game..!
class S {
    public static void set(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        d1[0] = 2;d1[1] = 5;
        d2[0] = 2;d2[1] = 4;
        d3[0] = 3;d3[1] = 4;
        d4[0] = 3;d4[1] = 3;
    }

    public static boolean check(int d1[], int d2[], int d3[], int d4[], String a[][], boolean loop[]) {
        if (a[d1[0]][d1[1]].equals("  ") &&  a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ") &&  a[d4[0]][d4[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }

    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        return (a[d2[0]][d2[1] - 1].equals("  ") &&  a[d4[0]][d4[1] - 1].equals("  "));
    }

    public static void move_left(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }

    public static boolean check_right(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        return a[d1[0]][d1[1] + 1].equals("  ") && a[d3[0]][d3[1] + 1].equals("  ");
    }

    public static void move_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]){ // ---------
        if(d1[0] == d2[0]){
            if(a[d1[0]-1][d1[1]-2].equals("  ") && a[d2[0]][d2[1]-1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";

                d1[0]--; d1[1]-=2;
                d2[1]--;
                d3[0]-- ;d4[1]++;
                return true;
            }
        }else if(a[d1[0]+1][d1[1]+2].equals("  ") && a[d4[0]][d4[1]-1].equals("  ")){
            // take * from array
            a[d1[0]][d1[1]] = "  ";
            a[d2[0]][d2[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            // changing dots
            d1[0]++ ; d1[1]+=2;
            d2[1]++;
            d3[0]++;
            d4[1]--;
            return true;
        }
        return false;
    }
    public static void rotate(int d1[], int d2[], int d3[], int d4[],
                              String a[][]){
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], String a[][],boolean play[]) {
        if(d1[0] == d2[0]){
            if(a[d1[0]+1][d1[1]].equals("  ") && a[d3[0]+1][d3[1]].equals("  ") &&
                    a[d4[0]+1][d4[1]].equals("  ")){
                a[d1[0]++][d1[1]] = "  ";
                a[d2[0]++][d2[1]] = "  ";
                a[d3[0]++][d3[1]] = "  ";
                a[d4[0]++][d4[1]] = "  ";
                return true;
            }
        }else if(a[d2[0]+1][d2[1]].equals("  ") && a[d4[0]+1][d4[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[], String a[][] , boolean play[]) {
        while(play[0]){
            if(S.check_down(d1, d2, d3, d4, a, play)) S.move_down(d1, d2, d3, d4, a);
        }
    }
}

class Z {
    public static void set(int d1[], int d2[], int d3[], int d4[], String a[][]){
        d1[0] = 2; d1[1] = 4;
        d2[0] = 2; d2[1] = 5;
        d3[0] = 3; d3[1] = 5;
        d4[0] = 3; d4[1] = 6;
    }
    public static boolean check(int d1[], int d2[], int d3[], int d4[], String a[][],boolean loop[]){
        if (a[d1[0]][d1[1]].equals("  ") && a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ") && a[d4[0]][d4[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }
    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        return (a[d1[0]][d1[1] - 1].equals("  ") && a[d3[0]][d3[1] - 1].equals("  "));
    }
    public static void move_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        return (a[d2[0]][d2[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  "));
    }

    public static void move_right(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]){ // ---------
        if(d1[0] == d2[0]){
            if(a[d1[0]-1][d1[1]+2].equals("  ") && a[d2[0]][d2[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";

                d1[0]--; d1[1]+=2;
                d2[1]++ ;
                d3[0]-- ;
                d4[1]--;
                return true;
            }
        }else if(a[d1[0]+1][d1[1]-2].equals("  ") && a[d4[0]][d4[1]+1].equals("  ")){
            // take * from array
            a[d1[0]][d1[1]] = "  ";
            a[d2[0]][d2[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            // changing dots
            d1[0]++ ; d1[1]-=2;
            d2[1]--;
            d3[0]++ ;
            d4[1]++;
            return true;
        }
        return false;
    }
    public static void rotate(int d1[], int d2[], int d3[], int d4[],
                              String a[][]){
        // set *  in array
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], String a[][],boolean play[]) {
        if(d1[0] == d2[0]){
            if(a[d1[0]+1][d1[1]].equals("  ") && a[d3[0]+1][d3[1]].equals("  ") &&
                    a[d4[0]+1][d4[1]].equals("  ")){
                a[d1[0]++][d1[1]] = "  ";
                a[d2[0]++][d2[1]] = "  ";
                a[d3[0]++][d3[1]] = "  ";
                a[d4[0]++][d4[1]] = "  ";
                return true;
            }
        }else if(a[d2[0]+1][d2[1]].equals("  ") && a[d4[0]+1][d4[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[], String a[][] , boolean play[]) {
        while(play[0]){
            if(Z.check_down(d1, d2, d3, d4, a, play)) Z.move_down(d1, d2, d3, d4, a);
        }
    }
}

class L {
    public static void set(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        d1[0] = 2;d1[1] = 4;
        d2[0] = 3;d2[1] = 4;
        d3[0] = 4;d3[1] = 4;
        d4[0] = 4;d4[1] = 5;
    }
    public static boolean check(int d1[], int d2[], int d3[], int d4[], String a[][],boolean loop[]){
        if (a[d1[0]][d1[1]].equals("  ") && a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ") && a[d4[0]][d4[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }
    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] - 1].equals("  ") && a[d2[0]][d2[1] - 1].equals("  ")) && (
                    a[d3[0]][d3[1] - 1].equals("  ") || a[d4[0]][d4[1] - 1].equals("  "));
        }else return (a[d3[0]][d3[1] - 1].equals("  ") && a[d4[0]][d4[1] - 1].equals("  ")) ||
                (a[d1[0]][d1[1] - 1].equals("  ") && a[d4[0]][d4[1] - 1].equals("  "));
    }
    public static void move_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] + 1].equals("  ") && a[d2[0]][d2[1] + 1].equals("  ")) &&
                    (a[d3[0]][d3[1] + 1].equals("  ") || a[d4[0]][d4[1] + 1].equals("  "));
        }else return (a[d1[0]][d1[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  ")) ||
                (a[d3[0]][d3[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  "));
    }
    public static void move_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_left_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        if(d1[1] == d2[1] && d1[0] < d2[0]){
            if(a[d1[0]+1][d1[1]-1].equals("  ") &&
                    a[d3[0]-1][d3[1]+1].equals("  ") && a[d4[0]-2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]--;
                d3[0]--;d3[1]++;
                d4[0]-=2;
                return true;
            }
        } else if(d1[0] == d2[0] && d2[1] < d1[1]){
            if(a[d1[0]-1][d1[1]-1].equals("  ") &&
                    a[d3[0]+1][d3[1]+1].equals("  ") && a[d4[0]][d4[1]+2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]--;
                d3[0]++;d3[1]++;
                d4[1]+=2;
                return true;
            }
        } else if(d1[1]==d2[1] && d2[0]<d1[0]){
            if  (a[d1[0]-1][d1[1]+1].equals("  ") &&
                    a[d3[0]+1][d3[1]-1].equals("  ") && a[d4[0]+2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]++;
                d3[0]++;d3[1]--;
                d4[0]+=2;
                return true;
            }
        } else if(a[d1[0]+1][d1[1]+1].equals("  ") &&
                a[d3[0]-1][d3[1]-1].equals("  ") && a[d4[0]][d4[1]-2].equals("  ")) {
            a[d1[0]][d1[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            d1[0]++;
            d1[1]++;
            d3[0]--;
            d3[1]--;
            d4[1] -= 2;
            return true;
        }
        return false;
    }
    public static void left_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        if(d1[1] == d2[1] && d1[0] < d2[0]){
            if(a[d1[0]+1][d1[1]+1].equals("  ") &&
                    a[d3[0]-1][d3[1]-1].equals("  ") && a[d4[0]][d4[1]-2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]++;
                d3[0]--;d3[1]--;
                d4[1]-=2;
                return true;
            }
        } else if(d1[0] == d2[0] && d2[1] < d1[1]){
            if(a[d1[0]+1][d1[1]-1].equals("  ") &&
                    a[d3[0]-1][d3[1]+1].equals("  ") && a[d4[0]-2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]--;
                d3[0]--;d3[1]++;
                d4[0]-=2;
                return true;
            }
        } else if(d1[1]==d2[1] && d2[0]<d1[0]){
            if(a[d1[0]-1][d1[1]-1].equals("  ") &&
                    a[d3[0]+1][d3[1]+1].equals("  ") && a[d4[0]][d4[1]+2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]--;
                d3[0]++;d3[1]++;
                d4[1]+=2;
                return true;
            }
        }else if(a[d1[0]-1][d1[1]+1].equals("  ") &&
                a[d3[0]+1][d3[1]-1].equals("  ") && a[d4[0]+2][d4[1]].equals("  ")){
            a[d1[0]][d1[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            d1[0]--;d1[1]++;
            d3[0]++;d3[1]--;
            d4[0]+=2;
            return true;
        }
        return false;
    }
    public static void right_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], String a[][],boolean play[]) {
        if(d1[1] == d2[1]){
            if((a[d4[0]+1][d4[1]].equals("  ")) &&
                    (a[d3[0]+1][d3[1]].equals("  ") || a[d1[0]+1][d1[1]].equals("  "))){
                a[d1[0]++][d1[1]] = "  ";
                a[d2[0]++][d2[1]] = "  ";
                a[d3[0]++][d3[1]] = "  ";
                a[d4[0]++][d4[1]] = "  ";
                return true;
            }
        }else if((a[d1[0]+1][d1[1]].equals("  ") && a[d2[0]+1][d2[1]].equals("  "))
                && (a[d3[0]+1][d3[1]].equals("  ") || a[d4[0]+1][d4[1]].equals("  "))){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], String a[][]) {

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[], String a[][] , boolean play[]) {
        while(play[0]){
            if(L.check_down(d1, d2, d3, d4, a, play)) L.move_down(d1, d2, d3, d4, a);
        }
    }
}

class ML {
    public static void set(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        d1[0] = 2;d1[1] = 4;
        d2[0] = 3;d2[1] = 4;
        d3[0] = 4;d3[1] = 4;
        d4[0] = 4;d4[1] = 3;
    }
    public static boolean check(int d1[], int d2[], int d3[], int d4[], String a[][],boolean loop[]){
        if (a[d1[0]][d1[1]].equals("  ") && a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ") && a[d4[0]][d4[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }
    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] - 1].equals("  ") && a[d2[0]][d2[1] - 1].equals("  ")) && (
                    a[d3[0]][d3[1] - 1].equals("  ") || a[d4[0]][d4[1] - 1].equals("  "));
        }else return (a[d3[0]][d3[1] - 1].equals("  ") && a[d4[0]][d4[1] - 1].equals("  ")) ||
                (a[d1[0]][d1[1] - 1].equals("  ") && a[d4[0]][d4[1] - 1].equals("  "));
    }
    public static void move_left(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] + 1].equals("  ") && a[d2[0]][d2[1] + 1].equals("  ")) &&
                    (a[d3[0]][d3[1] + 1].equals("  ") || a[d4[0]][d4[1] + 1].equals("  "));
        }else return (a[d1[0]][d1[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  ")) ||
                (a[d3[0]][d3[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  "));
    }
    public static void move_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_left_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        if(d1[1] == d2[1] && d1[0] < d2[0]){
            if(a[d1[0]+1][d1[1]-1].equals("  ") && a[d3[0]-1][d3[1]+1].equals("  ")
                    && a[d4[0]][d4[1]+2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]--;
                d3[0]--;d3[1]++;
                d4[1]+=2;
                return true;
            }
        } else if(d1[0] == d2[0] && d2[1] < d1[1]){
            if(a[d1[0]-1][d1[1]-1].equals("  ") && a[d3[0]+1][d3[1]+1].equals("  ")
                    && a[d4[0]+2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]--;
                d3[0]++;d3[1]++;
                d4[0]+=2;
                return true;
            }
        } else if(d1[1]==d2[1] && d2[0]<d1[0]){
            if  (a[d1[0]-1][d1[1]+1].equals("  ") && a[d3[0]+1][d3[1]-1].equals("  ")
                    && a[d4[0]][d4[1]-2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]++;
                d3[0]++;d3[1]--;
                d4[1]-=2;
                return true;
            }
        } else if(a[d1[0]+1][d1[1]+1].equals("  ") && a[d3[0]-1][d3[1]-1].equals("  ")
                && a[d4[0]-2][d4[1]].equals("  ")){
            a[d1[0]][d1[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            d1[0]++;d1[1]++;
            d3[0]--;d3[1]--;
            d4[0]-=2;
            return true;
        }return false;
    }
    public static void left_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        if(d1[1] == d2[1] && d1[0] < d2[0]){
            if(a[d1[0]+1][d1[1]+1].equals("  ") &&
                    a[d3[0]-1][d3[1]-1].equals("  ") && a[d4[0]-2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]++;
                d3[0]--;d3[1]--;
                d4[0]-=2;
                return true;
            }
        } else if(d1[0] == d2[0] && d2[1] < d1[1]){
            if(a[d1[0]+1][d1[1]-1].equals("  ") && a[d3[0]-1][d3[1]+1].equals("  ")
                    && a[d4[0]][d4[1]+2].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]++;d1[1]--;
                d3[0]--;d3[1]++;
                d4[1]+=2;
                return true;
            }
        } else if(d1[1]==d2[1] && d2[0]<d1[0]){
            if(a[d1[0]-1][d1[1]-1].equals("  ") &&
                    a[d3[0]+1][d3[1]+1].equals("  ") && a[d4[0]+2][d4[1]].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                d1[0]--;d1[1]--;
                d3[0]++;d3[1]++;
                d4[0]+=2;
                return true;
            }
        } else if(a[d1[0]-1][d1[1]+1].equals("  ") &&
                a[d3[0]+1][d3[1]-1].equals("  ") && a[d4[0]][d4[1]-2].equals("  ")){
            a[d1[0]][d1[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            d1[0]--;d1[1]++;
            d3[0]++;d3[1]--;
            d4[1]-=2;
            return true;
        }return false;
    }
    public static void right_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], String a[][],boolean play[]) {
        if(d1[1] == d2[1]){
            if((a[d4[0]+1][d4[1]].equals("  ")) &&
                    (a[d3[0]+1][d3[1]].equals("  ") || a[d1[0]+1][d1[1]].equals("  "))){
                a[d1[0]++][d1[1]] = "  ";
                a[d2[0]++][d2[1]] = "  ";
                a[d3[0]++][d3[1]] = "  ";
                a[d4[0]++][d4[1]] = "  ";
                return true;
            }
        }else if((a[d1[0]+1][d1[1]].equals("  ") && a[d2[0]+1][d2[1]].equals("  "))
                && a[d3[0]+1][d3[1]].equals("  ") || a[d4[0]+1][d4[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[], String a[][] , boolean play[]) {
        while(play[0]){
            if(ML.check_down(d1, d2, d3, d4, a, play)) ML.move_down(d1, d2, d3, d4, a);
        }
    }
}

class T {

    public static void set(int d1[], int d2[], int d3[], int d4[], int d5[], String a[][]) {
        d1[0] = 2;d1[1] = 3;
        d2[0] = 2;d2[1] = 4;
        d3[0] = 2;d3[1] = 5;
        d4[0] = 3;d4[1] = 4;
        d5[0] = 4;d5[1] = 4;
    }

    public static boolean check(int d1[], int d2[], int d3[], int d4[], int d5[], String a[][], boolean loop[]) {
        if (!a[d1[0]][d1[1]].equals("* ") && !a[d2[0]][d2[1]].equals("* ") &&
                !a[d3[0]][d3[1]].equals("* ") && !a[d4[0]][d4[1]].equals("* ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            a[d5[0]][d5[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }

    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], int d5[], String a[][]) {
        if (d1[0] == d3[0]) {
            return (a[d4[0]][d4[1] - 1].equals("  ") && a[d5[0]][d5[1] - 1].equals("  ")) &&
                    (a[d1[0]][d1[1] - 1].equals("  ") || a[d3[0]][d3[1] - 1].equals("  "));
        } else return (a[d1[0]][d1[1]-1].equals("  ") && a[d3[0]][d3[1]-1].equals("  ")) &&
                (a[d2[0]][d2[1]-1].equals("  ") || a[d5[0]][d5[1]-1].equals("  "));
    }
    public static void move_left(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]){
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";
        a[d5[0]][d5[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        a[d5[0]][d5[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]) {
        if (d1[0] == d3[0]) {
            return (a[d4[0]][d4[1] + 1].equals("  ") && a[d5[0]][d5[1] + 1].equals("  ")) &&
                    (a[d1[0]][d1[1] + 1].equals("  ") || a[d3[0]][d3[1] + 1].equals("  "));
        } else return (a[d1[0]][d1[1]-1].equals("  ") && a[d3[0]][d3[1]-1].equals("  ")) &&
                (a[d2[0]][d2[1]+1].equals("  ") || a[d5[0]][d5[1]+1].equals("  "));
    }
    public static void move_right(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";
        a[d5[0]][d5[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        a[d5[0]][d5[1]] = "* ";
        main.print(a);
    }
    public static boolean check_left_rotate(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]) { // for E
        if(d1[1]<d3[1]){
            if(a[d1[0]+2][d1[1]].equals("  ") && a[d2[0]+1][d2[1]-1].equals("  ") && a[d5[0]-1][d5[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[0]+=2;
                d2[0]++;d2[1]--;
                d3[1]-=2;
                d5[0]--;d5[1]++;
                return true;
            }
        }else if(d1[1]>d3[1]){
            if(a[d1[0]-2][d1[1]].equals("  ") && a[d2[0]-1][d2[1]+1].equals("  ") && a[d5[0]+1][d5[1]-1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[0]-=2;
                d2[0]--;d2[1]++;
                d3[1]+=2;
                d5[0]++;d5[1]--;
                return true;
            }
        }
        else if(d1[0]<d3[0]){
            if(a[d1[0]][d1[1]-2].equals("  ") && a[d2[0]-1][d2[1]-1].equals("  ") && a[d5[0]+1][d5[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[1]-=2;
                d2[0]--;d2[1]--;
                d3[0]-=2;
                d5[0]++;d5[1]++;
                return true;
            }
        }
        else if(a[d1[0]][d1[1]+2].equals("  ") && a[d2[0]+1][d2[1]+1].equals("  ") && a[d5[0]-1][d5[1]-1].equals("  ")){
            a[d1[0]][d1[1]] = "  ";
            a[d2[0]][d2[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            a[d5[0]][d5[1]] = "  ";
            d1[1]+=2;
            d2[0]++;d2[1]++;
            d3[0]+=2;
            d5[0]--;d5[1]--;
            return true;
        }
        return false;
    }
    public static void left_rotate(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        a[d5[0]][d5[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right_rotate(int d1[], int d2[], int d3[], int d4[], int d5[], String a[][]) {
        if(d1[1]<d3[1]){
            if(a[d2[0]+1][d2[1]+1].equals("  ") && a[d3[0]+2][d3[1]].equals("  ") && a[d5[0]-1][d5[1]-1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[1]+=2;
                d2[0]++;d2[1]++;
                d3[0]+=2;
                d5[0]--;d5[1]--;
                return true;
            }
        }else if(d1[1]>d3[1]){
            if(a[d2[0]-1][d2[1]-1].equals("  ") && a[d3[0]-2][d3[1]].equals("  ") && a[d5[0]+1][d5[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[1]-=2;
                d2[0]--;d2[1]--;
                d3[0]-=2;
                d5[0]++;d5[1]++;
                return true;
            }
        }
        else if(d1[0]<d3[0]){
            if(a[d2[0]+1][d2[1]-1].equals("  ") && a[d3[0]][d3[1]-2].equals("  ") && a[d5[0]-1][d5[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d2[0]][d2[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                a[d4[0]][d4[1]] = "  ";
                a[d5[0]][d5[1]] = "  ";
                d1[0]+=2;
                d2[0]++;d2[1]--;
                d3[1]-=2;
                d5[0]--;d5[1]++;
                return true;
            }
        }
        else if(a[d2[0]-1][d2[1]+1].equals("  ") && a[d3[0]][d3[1]+2].equals("  ") && a[d5[0]+1][d5[1]-1].equals("  ")){
            a[d1[0]][d1[1]] = "  ";
            a[d2[0]][d2[1]] = "  ";
            a[d3[0]][d3[1]] = "  ";
            a[d4[0]][d4[1]] = "  ";
            a[d5[0]][d5[1]] = "  ";
            d1[0]-=2;
            d2[0]--;d2[1]++;
            d3[1]+=2;
            d5[0]++;d5[1]--;
            return true;
        }
        return false;
    }
    public static void right_rotate(int d1[], int d2[], int d3[], int d4[],int d5[],  String a[][]) { // for E
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        a[d5[0]][d5[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], int d5[], String a[][],boolean play[]) {
        if(d1[0] == d3[0]){
            if((a[d1[0]+1][d1[1]].equals("  ") && a[d3[0]+1][d3[1]].equals("  ")) &&
                    (a[d2[0]+1][d2[1]].equals("  ") || a[d5[0]+1][d5[1]].equals("  "))){
                a[d1[0]++][d1[1]] = "  ";
                a[d2[0]++][d2[1]] = "  ";
                a[d3[0]++][d3[1]] = "  ";
                a[d4[0]++][d4[1]] = "  ";
                a[d5[0]++][d5[1]] = "  ";
                return true;
            }
        }else if((a[d4[0]+1][d4[1]].equals("  ") && a[d5[0]+1][d5[1]].equals("  ")) &&
                (a[d1[0]+1][d1[1]].equals("  ") || a[d3[0]+1][d3[1]].equals("  "))){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            a[d5[0]++][d5[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], int d5[],  String a[][]) {

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        a[d5[0]][d5[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[],int d5[], String a[][] , boolean play[]) {
        while(play[0]){
            if(T.check_down(d1, d2, d3, d4, d5, a, play)) T.move_down(d1, d2, d3, d4, d5, a);
        }
    }
}

class I {
    public static void set(int d1[], int d2[], int d3[], String a[][]) {
        d1[0] = 2;d1[1] = 4;
        d2[0] = 3;d2[1] = 4;
        d3[0] = 4;d3[1] = 4;
    }

    public static boolean check(int d1[], int d2[], int d3[], String a[][], boolean loop[]) {
        if (a[d1[0]][d1[1]].equals("  ") && a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }

    public static boolean check_left(int d1[], int d2[], int d3[], String a[][]) {
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] - 1].equals("  ") && a[d2[0]][d2[1] - 1].equals("  ")
                    && a[d3[0]][d3[1] - 1].equals("  "));
        }else return (a[d1[0]][d1[1] - 1].equals("  ") || a[d3[0]][d3[1] - 1].equals("  "));
    }
    public static void move_left(int d1[], int d2[], int d3[], String a[][]) {
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        main.print(a);
    }
    public static boolean check_right(int d1[], int d2[], int d3[], String a[][]) {
        if(d1[1] == d3[1]){
            return (a[d1[0]][d1[1] + 1].equals("  ") && a[d2[0]][d2[1] + 1].equals("  ")
                    && a[d3[0]][d3[1] + 1].equals("  "));
        }else return (a[d1[0]][d1[1] + 1].equals("  ") || a[d3[0]][d3[1] + 1].equals("  "));
    }

    public static void move_right(int d1[], int d2[], int d3[], String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        main.print(a);
    }
    public static boolean check_rotate(int d1[], int d2[], int d3[], String a[][]){ // ---------
        if(d1[1] == d3[1]) {
            if (a[d1[0] + 1][d1[1] + 1].equals("  ") && a[d3[0] - 1][d3[1] - 1].equals("  ")) {
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                d1[0]++;d1[1]++;
                d3[0]--;d3[1]--;
                return true;
            }
        }
        else {
            if(a[d1[0]-1][d1[1]-1].equals("  ") && a[d3[0]+1][d3[1]+1].equals("  ")){
                a[d1[0]][d1[1]] = "  ";
                a[d3[0]][d3[1]] = "  ";
                d1[0]--;d1[1]--;
                d3[0]++;d3[1]++;
                return true;
            }
        }
        return false;
    }
    public static void rotate(int d1[], int d2[], int d3[], String a[][]){
        // set *  in array
        a[d1[0]][d1[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], String a[][],boolean play[]) {
        if(a[d1[0]+1][d1[1]].equals("  ") && a[d2[0]+1][d2[1]].equals("  ") &&
                a[d3[0]+1][d3[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            return true;
        }else if(a[d3[0]+1][d3[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], String a[][]) {
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], String a[][] , boolean play[]) {
        while(play[0]){
            if(I.check_down(d1, d2, d3, a, play)) I.move_down(d1, d2, d3, a);
        }
    }
}

class SQ                                                                                                                                      {
    public static void set(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        d1[0] = 2;d1[1] = 3;
        d2[0] = 2;d2[1] = 4;
        d3[0] = 3;d3[1] = 3;
        d4[0] = 3;d4[1] = 4;
    }

    public static boolean check(int d1[], int d2[], int d3[], int d4[], String a[][], boolean loop[]) {
        if (a[d1[0]][d1[1]].equals("  ") && a[d2[0]][d2[1]].equals("  ") &&
                a[d3[0]][d3[1]].equals("  ") && a[d4[0]][d4[1]].equals("  ")) {
            a[d1[0]][d1[1]] = "* ";
            a[d2[0]][d2[1]] = "* ";
            a[d3[0]][d3[1]] = "* ";
            a[d4[0]][d4[1]] = "* ";
            return true;
        }
        return loop[0] = false;
    }

    public static boolean check_left(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        return (a[d1[0]][d1[1] - 1].equals("  ") && a[d3[0]][d3[1] - 1].equals("  "));
    }

    public static void move_left(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]--] = "  ";
        a[d2[0]][d2[1]--] = "  ";
        a[d3[0]][d3[1]--] = "  ";
        a[d4[0]][d4[1]--] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }

    public static boolean check_right(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        return (a[d2[0]][d2[1] + 1].equals("  ") && a[d4[0]][d4[1] + 1].equals("  "));
    }

    public static void move_right(int d1[], int d2[], int d3[], int d4[], String a[][]){
        a[d1[0]][d1[1]++] = "  ";
        a[d2[0]][d2[1]++] = "  ";
        a[d3[0]][d3[1]++] = "  ";
        a[d4[0]][d4[1]++] = "  ";

        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static boolean check_rotate(int d1[], int d2[], int d3[], int d4[], String a[][]){ // ---------
        return true;
    }
    public static void rotate(int d1[], int d2[], int d3[], int d4[], String a[][]){
        main.print(a);
    }
    public static boolean check_down(int d1[], int d2[], int d3[], int d4[], String a[][],boolean play[]) {
        if(a[d3[0]+1][d3[1]].equals("  ") && a[d4[0]+1][d4[1]].equals("  ")){
            a[d1[0]++][d1[1]] = "  ";
            a[d2[0]++][d2[1]] = "  ";
            a[d3[0]++][d3[1]] = "  ";
            a[d4[0]++][d4[1]] = "  ";
            return true;
        }
        main.check_is_row_full(a, a.length , a[0].length);
        return play[0] = false;
    }
    public static void move_down(int d1[], int d2[], int d3[], int d4[], String a[][]) {
        a[d1[0]][d1[1]] = "* ";
        a[d2[0]][d2[1]] = "* ";
        a[d3[0]][d3[1]] = "* ";
        a[d4[0]][d4[1]] = "* ";
        main.print(a);
    }
    public static void drop(int d1[], int d2[], int d3[], int d4[], String a[][] , boolean play[]) {
        while(play[0]){
            if(SQ.check_down(d1, d2, d3, d4, a, play)) SQ.move_down(d1, d2, d3, d4, a);

        }
    }
}

class Play_Tetris {
    public static void play(int d1[], int d2[], int d3[], int d4[], int d5[],
                            String a[][], boolean loop[] , String shape){
        Scanner in = new Scanner(System.in);
        boolean play[] = new boolean[1];
        play[0] = true;
        while(play[0]){
            String button = in.next();
            switch (shape) {
                case "s":
                    switch (button) {
                        case "a":
                            if (S.check_left(d1, d2, d3, d4, a)) S.move_left(d1, d2, d3, d4, a);
                            break;
                        case "d":
                            if (S.check_right(d1, d2, d3, d4, a)) S.move_right(d1, d2, d3, d4, a);
                            break;
                        case "e":
                        case "q":
                            if (S.check_rotate(d1, d2, d3, d4, a)) S.rotate(d1, d2, d3, d4, a);
                            break;
                        case "s":
                            if (S.check_down(d1, d2, d3, d4, a, play)) S.move_down(d1, d2, d3, d4, a);
                            break;
                        case "x":
                            S.drop(d1, d2, d3, d4, a, play);
                            break;
                    }
                    break;
                case "z":
                    switch (button) {
                        case "a":
                            if (Z.check_left(d1, d2, d3, d4, a)) Z.move_left(d1, d2, d3, d4, a);
                            break;
                        case "d":
                            if (Z.check_right(d1, d2, d3, d4, a)) Z.move_right(d1, d2, d3, d4, a);
                            break;
                        case "e":
                        case "q":
                            if (Z.check_rotate(d1, d2, d3, d4, a)) Z.rotate(d1, d2, d3, d4, a);
                            break;
                        case "s":
                            if (Z.check_down(d1, d2, d3, d4, a, play)) Z.move_down(d1, d2, d3, d4, a);
                            break;
                        case "x":
                            Z.drop(d1, d2, d3, d4, a, play);
                            break;
                    }
                    break;
                case "l":
                    switch (button) {
                        case "a":
                            if (L.check_left(d1, d2, d3, d4, a)) L.move_left(d1, d2, d3, d4, a);
                            break;
                        case "d":
                            if (L.check_right(d1, d2, d3, d4, a)) L.move_right(d1, d2, d3, d4, a);
                            break;
                        case "q":
                            if (L.check_left_rotate(d1, d2, d3, d4, a)) L.left_rotate(d1, d2, d3, d4, a);
                            break;
                        case "e":
                            if (L.check_right_rotate(d1, d2, d3, d4, a)) L.right_rotate(d1, d2, d3, d4, a);
                            break;
                        case "s":
                            if (L.check_down(d1, d2, d3, d4, a, play)) L.move_down(d1, d2, d3, d4, a);
                            break;
                        case "x":
                            L.drop(d1, d2, d3, d4, a, play);
                            break;
                    }
                    break;
                case "ml":
                    switch (button) {
                        case "a":
                            if (ML.check_left(d1, d2, d3, d4, a)) ML.move_left(d1, d2, d3, d4, a);
                            break;
                        case "d":
                            if (ML.check_right(d1, d2, d3, d4, a)) ML.move_right(d1, d2, d3, d4, a);
                            break;
                        case "q":
                            if (ML.check_left_rotate(d1, d2, d3, d4, a)) ML.left_rotate(d1, d2, d3, d4, a);
                            break;
                        case "e":
                            if (ML.check_right_rotate(d1, d2, d3, d4, a)) ML.right_rotate(d1, d2, d3, d4, a);
                            break;
                        case "s":
                            if (ML.check_down(d1, d2, d3, d4, a, play)) ML.move_down(d1, d2, d3, d4, a);
                            break;
                        case "x":
                            ML.drop(d1, d2, d3, d4, a, play);
                            break;
                    }
                    break;
                case "t":
                    switch (button) {
                        case "a":
                            if (T.check_left(d1, d2, d3, d4, d5, a)) T.move_left(d1, d2, d3, d4, d5, a);
                            break;
                        case "d":
                            if (T.check_right(d1, d2, d3, d4, d5, a)) T.move_right(d1, d2, d3, d4, d5, a);
                            break;
                        case "q":
                            if (T.check_left_rotate(d1, d2, d3, d4, d5, a)) T.left_rotate(d1, d2, d3, d4, d5, a);
                            break;
                        case "e":
                            if (T.check_right_rotate(d1, d2, d3, d4, d5, a)) T.right_rotate(d1, d2, d3, d4, d5, a);
                            break;
                        case "s":
                            if (T.check_down(d1, d2, d3, d4, d5, a, play)) T.move_down(d1, d2, d3, d4, d5, a);
                            break;
                        case "x":
                            T.drop(d1, d2, d3, d4, d5, a, play);
                            break;
                    }
                    break;
                case "i":
                    switch (button) {
                        case "a":
                            if (I.check_left(d1, d2, d3, a)) I.move_left(d1, d2, d3, a);
                            break;
                        case "d":
                            if (I.check_right(d1, d2, d3, a)) I.move_right(d1, d2, d3, a);
                            break;
                        case "q":
                        case "e":
                            if (I.check_rotate(d1, d2, d3, a)) I.rotate(d1, d2, d3, a);
                            break;
                        case "s":
                            if (I.check_down(d1, d2, d3, a, play)) I.move_down(d1, d2, d3, a);
                            break;
                        case "x":
                            I.drop(d1, d2, d3, a, play);
                            break;
                    }
                    break;
                case "sq":
                    switch (button) {
                        case "a":
                            if (SQ.check_left(d1, d2, d3, d4, a)) SQ.move_left(d1, d2, d3, d4, a);
                            break;
                        case "d":
                            if (SQ.check_right(d1, d2, d3, d4, a)) SQ.move_right(d1, d2, d3, d4, a);
                            break;
                        case "e":
                        case "q":
                            if (SQ.check_rotate(d1, d2, d3, d4, a)) SQ.rotate(d1, d2, d3, d4, a);
                            break;
                        case "s":
                            if (SQ.check_down(d1, d2, d3, d4, a, play)) SQ.move_down(d1, d2, d3, d4, a);
                            break;
                        case "x":
                            SQ.drop(d1, d2, d3, d4, a, play);
                            break;
                    }
                    break;
            }
        }
    }
}

//*******************************MAIN METHOD*****************************

 class main {
    static int score = 0;

    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>(Arrays.asList("s","z","l","ml","t","i","sq"));
        int n = 18;
        int m = 10;
        String a[][] = new String[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i == 0||j == 0||i == n-1||j == m-1) a[i][j] = "* ";
                else a[i][j] = "  ";
            }
        }
        System.out.println(" q-rotate Left\n a-left\n s-down\n d-right\n e-rotate right\n x-drop to bottom");
        boolean loop[] = new boolean[1];
        loop[0] = true;
        String shape;
        int index= 0;
        // Take Shape "SQ" as example : d1-> * * <-d2
        //                              d3-> * * <-d4
        // At max , there are 5 stars(*) (See Shape "T")

        int d1[] = new int [2]; // d1[0] as row , d1[1] as col
        int d2[] = new int [2]; // d2[0] as row , d2[1] as col
        int d3[] = new int [2]; // d3[0] as row , d3[1] as col
        int d4[] = new int [2]; // d4[0] as row , d4[1] as col
        int d5[] = new int [2]; // d5[0] as row , d5[1] as col
        while(loop[0]){
            shape = list.get(index++);
            if(index == 7) index = 0;
            switch (shape) {
                case "s" : {
                    S.set(d1, d2, d3, d4, a);
                    if (S.check(d1, d2, d3, d4, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "z" : {
                    Z.set(d1, d2, d3, d4, a);
                    if (Z.check(d1, d2, d3, d4, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "l" : {
                    L.set(d1, d2, d3, d4, a);
                    if (L.check(d1, d2, d3, d4, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "ml" : {
                    ML.set(d1, d2, d3, d4, a);
                    if (ML.check(d1, d2, d3, d4, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "t" : {
                    T.set(d1, d2, d3, d4, d5, a);
                    if (T.check(d1, d2, d3, d4, d5, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "i" : {
                    I.set(d1, d2, d3, a);
                    if (I.check(d1, d2, d3, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
                case "sq" : {
                    SQ.set(d1, d2, d3, d4, a);
                    if (SQ.check(d1, d2, d3, d4, a, loop)) {
                        print(a);
                        Play_Tetris.play(d1, d2, d3, d4, d5, a, loop, shape);
                    }
                    break;
                }
            }
            if(!loop[0]){ // if loop is false display score
                System.out.println("***********!..Game Over..!************");
                System.out.println("********* YOUR SCORE IS :"+ score+" ***********");
            }
        }
    }
    public static void print(String a[][]){ // printing the matrix
        for(String row[] : a){
            for(String star : row) System.out.print(star+" ");
            System.out.println();
        }
    }
    public static void check_is_row_full(String a[][] , int n , int m){
        int max_star_in_a_row = 8; // in 0-indexed array , at max 8 star can be found if a row is filled with "*"
        int count ;
        boolean  loop = true;
        while(loop){
            loop = false; // next iteration while loop breaks
            // Finding whether a row is filled with "*" or not
            for(int i = 1;i<n-1;i++){
                count = 0;
                for(int j=1;j<m-1;j++){
                    if(a[i][j].equals("  ")) break;
                    else count++; // incrementing by one if a "*" found
                }
                if(count == max_star_in_a_row){ // A row found with full of "*"
                    loop = true; // next time while loop checks if a row  is full of "*" or not
                    for(int k = 1;k<m-1;k++) a[i][k] = "  ";
                    score+=100;
                    for(int j = i-1;j>=1;j--){
                        for(int k=m-2;k>=1;k--){
                            if(a[j][k].equals("* ")){
                                a[j+1][k] =a[j][k];
                                a[j][k] = "  ";
                            }
                        }
                    }
                }
            }
            if(!loop) break;
        }
    }
}






