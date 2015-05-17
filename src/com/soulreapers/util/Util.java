/**
 * 
 */
package com.soulreapers.util;

import java.util.Random;

/**
 * @author chris
 *
 */
public class Util {
	private static final Random RAND = new Random();

	public static int random(int min, int max) {
		return RAND.nextInt((max - min) + 1) + min;
	}
}
