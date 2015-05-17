/**
 * 
 */
package com.soulreapers.object.character;

import com.soulreapers.R;

/**
 * @author chris
 *
 */
public class RemnantFactory {
	public enum RemnantType {
		CHIROPTERA {
			@Override
			public int toResId() {
				// TODO Auto-generated method stub
				return R.string.pc_e01;
			}
			@Override
			public String toString() {
				return "Chiroptera";
			}
		};
		abstract public int toResId();
	}
	public static Remnant create(RemnantType pType) {
		return new Remnant(pType.toString(), pType.toResId(), R.string.ic_e01);
	}
}
