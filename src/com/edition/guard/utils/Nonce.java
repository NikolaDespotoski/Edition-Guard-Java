package com.edition.guard.utils;

import java.util.Random;

public class Nonce {
    private static Random sRandom = new Random();
    public static String getNextNonce(){
    	int n = (int) (1000000 + sRandom.nextFloat() * 9000000);
    	return Long.toString(n);
    }
}