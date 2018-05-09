package org.roboid.robot.impl;

import org.roboid.robot.DeviceType;
import org.roboid.robot.Effector;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class FloatEffectorImpl extends FloatMotoringDeviceImpl implements Effector
{
	FloatEffectorImpl(RoboidImpl parent, int id, String name, int dataSize, float minValue, float maxValue, Object initialValue, Object readLock, Object writeLock, Object fireLock)
	{
		super(parent, id, name, dataSize, minValue, maxValue, initialValue, readLock, writeLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.EFFECTOR;
	}
}
