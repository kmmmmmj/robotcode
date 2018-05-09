package org.roboid.robot.impl;

import org.roboid.robot.DeviceType;
import org.roboid.robot.Effector;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class StringEffectorImpl extends StringMotoringDeviceImpl implements Effector
{
	StringEffectorImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object readLock, Object writeLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, readLock, writeLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.EFFECTOR;
	}
}
