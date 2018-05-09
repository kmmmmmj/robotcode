package org.roboid.android;

import kr.robomation.peripheral.SmartPen;
import kr.robomation.physical.AlbertSchool;
import kr.robomation.physical.UoAlbert;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class RobotFactory
{
	static AbstractRobot create(int modelCode, int index)
	{
		switch(modelCode)
		{
		case AlbertSchool.CODE:
			return new AlbertSchoolRobot(index);
		case UoAlbert.CODE:
			return new UoAlbertRobot(index);
		case SmartPen.CODE:
			return new SmartPenRobot(index);
		}
		return null;
	}
}
