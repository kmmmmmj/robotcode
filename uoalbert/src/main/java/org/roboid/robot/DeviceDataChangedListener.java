package org.roboid.robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface DeviceDataChangedListener
{
	public abstract void onDeviceDataChanged(Robot robot, Device device, Object values, long timestamp);
}
