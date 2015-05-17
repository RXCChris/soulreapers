/**
 * 
 */
package com.soulreapers.object.character;

import com.soulreapers.misc.Attributes;
import com.soulreapers.misc.Attributes.AttributeType;

/**
 * @author chris
 *
 */
public class Modifier {
	private final AttributeType mType;
	private final int mValue;

	/**
	 * 
	 */
	public Modifier(final AttributeType pType, final int pValue) {
		mType = pType;
		mValue = pValue;
	}

	public void apply(Attributes pAttribute) {
		pAttribute.increaseBonus(mType, mValue);
	}
}
