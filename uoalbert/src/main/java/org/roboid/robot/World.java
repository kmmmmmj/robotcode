package org.roboid.robot;

import java.util.List;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface World extends NamedElement
{
	public abstract int countRobots();
	public abstract List<Robot> getRobots();
	public abstract Robot findRobotById(String modelId);
	public abstract Robot findRobotById(String modelId, int index);
	public abstract Robot findRobotByCode(int modelCode);
	public abstract Robot findRobotByCode(int modelCode, int index);
	public abstract Device findDeviceById(int deviceId);
	public abstract Device findDeviceById(int deviceId, int index);
	public abstract void addDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void removeDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void clearDeviceDataChangedListeners();
}
