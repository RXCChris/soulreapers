/**
 * 
 */
package com.soulreapers.object.character;

import com.soulreapers.R;
import com.soulreapers.object.character.Remnant.RemnantType;

/**
 * @author chris
 *
 */
public class RemnantFactory {

//	public static Remnant create(Remnants pType) {
//		return new Remnant(pType.toString(), pType.toResId(), R.string.ic_e01);
//	}
	public static Remnant create(final int pID, final String pNameSuffix) {
		final int id = 200;
		final RemnantType type = RemnantType.BEAST;
		final String name = (pNameSuffix == null) ? "Chiroptera" : "Chiroptera" + " " + pNameSuffix;
		final int illustID = R.string.pc_e01;
		final int iconID = R.string.ic_e01;
		return new Remnant(id, name, illustID, iconID, type);
	}
	public static Remnant create(final int pRemnantID) {
		return create(pRemnantID, null);
	}
}
