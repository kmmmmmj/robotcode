package org.roboid.android;

import org.roboid.robot.impl.RobotImpl;
import org.roboid.robot.impl.WorldImpl;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
  public final class HardwareWorld extends WorldImpl
{
	HardwareWorld()
	{
	}
	
	@Override
	protected void clearRobots()
	{
		super.clearRobots();
	}

	@Override
	protected void addRobot(RobotImpl robot)
	{
		super.addRobot(robot);
	}
}
