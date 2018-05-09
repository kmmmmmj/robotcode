package org.roboid.android;

import kr.robomation.physical.UoAlbert;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class UoAlbertRobot extends AbstractRobot
{
	UoAlbertRobot(int index)
	{
		super(0x00700000, index, new UoAlbertRoboid());
	}
	
	@Override
	public String getId()
	{
		return UoAlbert.ID;
	}

	@Override
	public int getCode()
	{
		return UoAlbert.CODE;
	}
}
