/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.IEntity;

/**
 * @author chris
 *
 */
public interface IUserInterface extends IEntity {
	public void destroy();
	public float getX();
	public float getY();
	public float getWidth();
	public float getHeight();
}
