package org.roboid.robot.impl;

import org.roboid.robot.DeviceType;
import org.roboid.robot.Sensor;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class IntSensorImpl extends IntDeviceImpl implements Sensor
{
	IntSensorImpl(RoboidImpl parent, int id, String name, int dataSize, int minValue, int maxValue, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, minValue, maxValue, initialValue, readLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.SENSOR;
	}
}
