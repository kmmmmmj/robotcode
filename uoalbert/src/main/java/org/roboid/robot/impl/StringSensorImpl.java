package org.roboid.robot.impl;

import org.roboid.robot.DeviceType;
import org.roboid.robot.Sensor;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class StringSensorImpl extends StringDeviceImpl implements Sensor
{
	StringSensorImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, readLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.SENSOR;
	}
}
