package com.company;
import java.io.IOException;
import java.util.Scanner;

    class Perceptrons{
        private double a;                                       //宣告變數
        private double w1,w2,w3;
        private double t;
        private int num;
        private double in,sigmoid,dsigmoid,error;
        private String ty;

        Scanner sc = new Scanner(System.in);
            void train(double inputs[][],int outputs[],double a,double w1,double w2,double w3,double t,int num){


            this.a=a;                       //宣告阿法參數值
            this.w1=w1;                    //宣告w1權值
            this.w2=w2;                    //宣告w2權值
            this.w3=w3;                    //宣告w3權值
            this.t=t;
            this.num=num;


            for (int i = 0 ; i < num ; i++){                                             //開始訓練,調整參數
                for(int k = 0; k < inputs.length; k++) {
                    in = inputs[k][0]*w1 +inputs[k][1]*w2 +inputs[k][2]*w3 + t*-1;      //計算in值
                    sigmoid = 1/(1+Math.exp(-in));                                      //計算sigmoid值訓練開始 調整權值
                    dsigmoid = sigmoid*(1-sigmoid);                                    //計算dsigmoid值
                    error = outputs[k] - sigmoid ;
                    w1 = w1 + a*inputs[k][0]*error*dsigmoid;
                    w2 = w2 + a*inputs[k][1]*error*dsigmoid;
                    w3 = w3 + a*inputs[k][2]*error*dsigmoid;
                    t = t + a*-1*error*dsigmoid;
                    System.out.println("w1:"+w1+"\n"+"w2:"+w2+"\n"+"w3:"+w3+"\n"+"t:"+t+"\n");
                }
            }
                System.out.println("w1:"+(w1+1)+"\n"+"w2:"+(w2+1)+"\n"+"w3:"+(w3+1)+"\n"+"t:"+(t+1)+"\n");
            test(w1,w2,w3,t);
            }

            void test(double w1,double w2,double w3,double t){              //測試3個位元的各值
                System.out.println("輸入要測試的值:");
                ty = sc.next();

                switch (ty){
                    case "000":
                        System.out.println("output:"+(1/(1+Math.exp(0-(0*w1+0*w2+0*w3+t*-1)))));
                        break;
                    case "001":
                        System.out.println("output:"+(1/(1+Math.exp(0-(0*w1+0*w2+1*w3+t*-1)))));
                        break;
                    case "010":
                        System.out.println("output:"+(1/(1+Math.exp(0-(0*w1+1*w2+0*w3+t*-1)))));
                        break;
                    case "011":
                        System.out.println("output:"+(1/(1+Math.exp(0-(0*w1+1*w2+1*w3+t*-1)))));
                        break;
                    case "100":
                        System.out.println("output:"+(1/(1+Math.exp(0-(1*w1+0*w2+0*w3+t*-1)))));
                        break;
                    case "101":
                        System.out.println("output:"+(1/(1+Math.exp(0-(1*w1+0*w2+1*w3+t*-1)))));
                        break;
                    case "110":
                        System.out.println("output:"+(1/(1+Math.exp(0-(1*w1+1*w2+0*w3+t*-1)))));
                        break;
                    case "111":
                        System.out.println("output:"+(1/(1+Math.exp(0-(1*w1+1*w2+1*w3+t*-1)))));
                        break;
                }
            }

    }

    public class Main {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            Perceptrons p = new Perceptrons();

            double inputs[][] = {{0,0,1},{1,1,0},{0,0,0},{1,1,1},{1,0,1},{0,1,1}};
            int outputs[] = {0,1,0,1,1,1};
            double a=0,w1=0,w2=0,w3=0,t=0;
            int se,num;

            System.out.print("1.選擇亂數 2.自行輸入:");
                se = sc.nextInt();
                if(se==1){
                    a = (double) (Math.random());
                    System.out.println("a : "+a);
                    w1 = (double) (Math.random()*2-1);
                    System.out.println("w1 : "+w1);
                    w2 = (double) (Math.random()*2-1);
                    System.out.println("w2 : "+w2);
                    w3 = (double) (Math.random()*2-1);
                    System.out.println("w3 : "+w3);
                    t = (double) (Math.random()*2-1);
                    System.out.println("t : "+t);
                }

                else if(se==2) {
                    System.out.print("輸入a:");
                    a = sc.nextDouble();
                    System.out.print("輸入w1:");
                    w1 = sc.nextDouble();
                    System.out.print("輸入w2:");
                    w2 = sc.nextDouble();
                    System.out.print("輸入w3:");
                    w3 = sc.nextDouble();
                    System.out.print("輸入t:");
                    t = sc.nextDouble();
                }

                System.out.println("輸入次數:");
                    num = sc.nextInt();

            p.train(inputs,outputs,a,w1,w2,w3,t,num);
        }
    }
