package dev;

/**
 * author: midVictor
 * date: on 2016/12/19
 * description:
 */

public class Test {


    public static void main(String args[]) {

//        int i = 0;
//        int num = ++i * i++;

        float f= (float) .5;

        System.err.println(f);

        AAAAA aaaaa =new AAAAA();

        System.err.println(aaaaa instanceof inter);

    }
}

class AAAAA implements  inter{

}

interface inter{

}
