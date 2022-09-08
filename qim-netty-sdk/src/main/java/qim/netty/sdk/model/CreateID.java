package qim.netty.sdk.model;

import java.util.Random;

public class CreateID {

    public static void main(String[] args) {

        long ti=System.currentTimeMillis();

        System.out.println("20位值：" + Long.valueOf(ti << 32)+"L");

    }
}
