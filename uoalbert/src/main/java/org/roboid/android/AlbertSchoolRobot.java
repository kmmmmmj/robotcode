package org.roboid.android;

import kr.robomation.physical.AlbertSchool;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class AlbertSchoolRobot extends AbstractRobot
{
	AlbertSchoolRobot(int index)
	{
		super(0x00500000, index, new AlbertSchoolRoboid());
	}
	
	@Override
	public String getId()
	{
		return AlbertSchool.ID;
	}

	@Override
	public int getCode()
	{
		return AlbertSchool.CODE;
	}
}
