package org.roboid.robot.impl;

import org.roboid.robot.Command;
import org.roboid.robot.DeviceType;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class StringCommandImpl extends StringMotoringDeviceImpl implements Command
{
	StringCommandImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object readLock, Object writeLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, readLock, writeLock, fireLock);
	}
	
	@Override
	public int getDeviceType()
	{
		return DeviceType.COMMAND;
	}
}
