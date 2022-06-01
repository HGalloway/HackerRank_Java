package Introduction;

import java.util.Scanner;

public class Standard_Input_Output {
    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);
        int[] Ints = new int[3];
        for (int i = 0; i < 3; i++) {
            Ints[i] = Scanner.nextInt();
        }
        Scanner.close();
        for (int x = 0; x < Ints.length; x++) {
            System.out.println(Ints[x]);
        }  
    }
}
