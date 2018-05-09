package org.roboid.robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface Robot extends NamedElement
{
	public abstract String getId();
	public abstract int getCode();
	public abstract int getIndex();
	public abstract int getIcon();
	public abstract Device findDeviceById(int deviceId);
	public abstract void addDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void removeDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void clearDeviceDataChangedListeners();
}
