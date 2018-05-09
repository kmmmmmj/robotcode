package org.roboid.android;

import org.roboid.robot.Robot;
import org.roboid.robot.impl.RoboidImpl;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class AbstractRoboid extends RoboidImpl
{
	AbstractRoboid(int uid, int size)
	{
		super(uid, size);
	}
	
	void handleSensorySimulacrum(Robot robot, byte[] simulacrum, long timestamp)
	{
		if(decodeSensorySimulacrum(simulacrum))
			notifySensoryDeviceDataChanged(robot, timestamp);
	}
	
	void handleMotoringSimulacrum(Robot robot, byte[] simulacrum, long timestamp)
	{
		if(decodeMotoringSimulacrum(simulacrum))
			notifyMotoringDeviceDataChanged(robot, timestamp);
	}
	
	boolean decodeSensorySimulacrum(byte[] simulacrum)
	{
		return false;
	}
	
	void notifySensoryDeviceDataChanged(Robot robot, long timestamp)
	{
	}
	
	boolean decodeMotoringSimulacrum(byte[] simulacrum)
	{
		return false;
	}
	
	void notifyMotoringDeviceDataChanged(Robot robot, long timestamp)
	{
	}
	
	byte[] encodeMotoringSimulacrum()
	{
		return null;
	}
}
