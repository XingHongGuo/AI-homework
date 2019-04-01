package com.company;
import java.util.Scanner;


class Queens
{
    private int num;
    private int chessboard[][];
    private int temp[][];
    private int h[][];

    void initial(int num)
    {
        this.num = num;
        this.chessboard= new int[num][num];
        this.temp = new int [num][num];
        this.h = new int[num][num];

        for (int i = 0; i < num; ++i) {
            for (int j = 0; j < num; j++) {
                chessboard[i][j] = 0;
            }
        }

        for (int i = 0; i < num; i++) {
                int r = (int) (Math.random()*num);
                chessboard[i][r] = 1;

        }

    }

    int findCollision(int row,int col)
    {
        int count = 0 ;
        temp[row][col] = 1;
        for (int k = 0; k < num*num; k++) {
            if (temp[k/num][k%num] == 1) {
                for (int i = 0; i < num; i++) {                            // 同一列
                    if (i != k / num && temp[i][k % num] == 1)
                        count++;
                }
                for (int i = k/num, j = k%num; i < num && j < num; i++, j++) {    // 右下方
                    if (i != k / num && temp[i][j] == 1)
                        count++;
                }
                for (int i = k/num, j = k%num; i >= 0 && j >= 0; i--, j--) { // 左上方
                    if (i != k / num && temp[i][j] == 1)
                        count++;
                }
                for (int i = k/num, j = k%num; i < num && j >= 0; i++, j--) {  // 左下方
                    if (i != k / num && temp[i][j] == 1)
                        count++;
                }
                for (int i = k/num, j = k%num; i >= 0 && j < num; i--, j++) {  // 右上方
                    if (i != k / num && temp[i][j] == 1)
                        count++;
                }
            }
        }
        temp[row][col] = 0;
        return count/2;
    }

    boolean trains(){
        for (int trial = 0; trial <= 100; trial++) {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    temp[i][j] = chessboard[i][j];
                }
            }
            int minH = 9999, minX = 0, minY = 0, curState = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    for (int k = 0; k < num; k++)
                        temp[i][k] = 0;

                    h[i][j] = findCollision(i, j);

                    if (chessboard[i][j] == 1) {
                        curState = h[i][j];
                    }

                    if (h[i][j] < minH) {
                        minH = h[i][j];
                        minX = i;
                        minY = j;
                    }
                    for (int k = 0; k < num; k++)
                        temp[i][k] = chessboard[i][k];
                }
            }
            if (curState > minH) {
                for (int i = 0; i < num; i++)
                    chessboard[minX][i] = 0;
                chessboard[minX][minY] = 1;
            }


            if (check()) {
                print();
                return true;
            }
        }
            print();
            return false;
    }

     boolean check()
    {
        for (int i = 0; i < num; i++) {
            boolean flag = false;
            for (int j = 0; j < num; j++) {
                if (chessboard[i][j]==1 && h[i][j]==0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

     void print()
    {
        for (int i=0;i<num;++i){
            for(int j=0;j<num;j++){
                System.out.print(chessboard[i][j]+"  ");
            }
            System.out.println(" ");
        }
    }
}


public class HC {

    public static void main(String args[])
    {
        Queens q = new Queens();
        Scanner sc = new Scanner(System.in);
        int num;

        System.out.print("輸入幾乘幾的皇后:");
        num = sc.nextInt();

        boolean find = false;

        while (!find) {
            q.initial(num);
            find = q.trains();

        }
    }

}
