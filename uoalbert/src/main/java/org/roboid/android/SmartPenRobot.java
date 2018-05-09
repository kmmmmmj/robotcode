package org.roboid.android;

import kr.robomation.peripheral.SmartPen;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class SmartPenRobot extends AbstractRobot
{
	SmartPenRobot(int index)
	{
		super(0x00600000, index, new SmartPenRoboid());
	}
	
	@Override
	public String getId()
	{
		return SmartPen.ID;
	}

	@Override
	public int getCode()
	{
		return SmartPen.CODE;
	}
}
