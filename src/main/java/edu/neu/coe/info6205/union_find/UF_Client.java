package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_Client {
    public static int count(int num){
        UF_HWQUPC obj = new UF_HWQUPC(num);

        int number = 0;
        Random random = new Random();
        while (obj.components() > 1){
            int a = random.nextInt(num);
            int b = random.nextInt(num);

            if(!obj.connected(a, b)){
                obj.union(a,b);
            }

            number++;
        }

        return number;
    }


    public static void main(String[] args){

        // Initial i = 128
        for(int i = 128; i < 1000000; i *= 2){
            int sum = 0;
            for(int j = 0; j < 10; j++){
                sum += count(i);
            }

            double m = sum / 10.0;
            int fn =  (int) (0.5 * i * Math.log(i));
            System.out.println("m: " + m + " n: " + i);

        }
    }
}