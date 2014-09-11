package com.bft.utils;

import java.util.Random;

public class RandInt {

	public static Integer randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
