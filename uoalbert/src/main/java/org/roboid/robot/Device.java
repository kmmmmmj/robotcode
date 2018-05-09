package org.roboid.robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface Device extends NamedElement
{
	public abstract int getId();
	public abstract int getDeviceType();
	public abstract int getDataType();
	public abstract int getDataSize();
	public abstract boolean e();
	public abstract int read();
	public abstract int read(int index);
	public abstract int read(int[] data);
	public abstract float readFloat();
	public abstract float readFloat(int index);
	public abstract int readFloat(float[] data);
	public abstract String readString();
	public abstract String readString(int index);
	public abstract int readString(String[] data);
	public abstract boolean write(int data);
	public abstract boolean write(int index, int data);
	public abstract int write(int[] data);
	public abstract boolean writeFloat(float data);
	public abstract boolean writeFloat(int index, float data);
	public abstract int writeFloat(float[] data);
	public abstract boolean writeString(String data);
	public abstract boolean writeString(int index, String data);
	public abstract int writeString(String[] data);
	public abstract void addDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void removeDeviceDataChangedListener(DeviceDataChangedListener listener);
	public abstract void clearDeviceDataChangedListeners();
}
