package org.roboid.robot.impl;

import org.roboid.robot.DeviceType;
import org.roboid.robot.Event;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class FloatEventImpl extends FloatDeviceImpl implements Event
{
	FloatEventImpl(RoboidImpl parent, int id, String name, int dataSize, float minValue, float maxValue, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, minValue, maxValue, initialValue, readLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.EVENT;
	}
}
