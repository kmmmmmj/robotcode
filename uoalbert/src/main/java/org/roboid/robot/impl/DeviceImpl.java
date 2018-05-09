package org.roboid.robot.impl;

import java.util.ArrayList;

import org.roboid.robot.Device;
import org.roboid.robot.DeviceDataChangedListener;
import org.roboid.robot.Robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class DeviceImpl extends NamedElementImpl implements Device
{
	private final RoboidImpl mParent;
	private final int mId;
	final int mDataSize;
	final Object mInitialValue;
	private boolean mEvent;
	private boolean mFired;
	private final Object mFireLock;
	private final ArrayList<DeviceDataChangedListener> mDeviceDataChangedListeners = new ArrayList<DeviceDataChangedListener>();

	DeviceImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object fireLock)
	{
		super(name);
		
		mParent = parent;
		mId = id;
		mDataSize = dataSize;
		mInitialValue = initialValue;
		mFireLock = fireLock;
	}

	@Override
	public int getId()
	{
		return mId;
	}
	
	@Override
	public int getDataSize()
	{
		return mDataSize;
	}

	Object getWriteData()
	{
		return null;
	}

	abstract Object getReadData();
	abstract boolean putReadData(Object data);
	
	@Override
	public boolean e()
	{
		synchronized(mFireLock)
		{
			return mEvent;
		}
	}

	@Override
	public int read()
	{
		return 0;
	}

	@Override
	public int read(int index)
	{
		return 0;
	}

	@Override
	public int read(int[] data)
	{
		return 0;
	}

	@Override
	public float readFloat()
	{
		return 0.0f;
	}

	@Override
	public float readFloat(int index)
	{
		return 0.0f;
	}

	@Override
	public int readFloat(float[] data)
	{
		return 0;
	}
	
	@Override
	public String readString()
	{
		return "";
	}

	@Override
	public String readString(int index)
	{
		return "";
	}

	@Override
	public int readString(String[] data)
	{
		return 0;
	}
	
	@Override
	public boolean write(int data)
	{
		return false;
	}

	@Override
	public boolean write(int index, int data)
	{
		return false;
	}

	@Override
	public int write(int[] data)
	{
		return 0;
	}
	
	@Override
	public boolean writeFloat(float data)
	{
		return false;
	}

	@Override
	public boolean writeFloat(int index, float data)
	{
		return false;
	}

	@Override
	public int writeFloat(float[] data)
	{
		return 0;
	}

	@Override
	public boolean writeString(String data)
	{
		return false;
	}

	@Override
	public boolean writeString(int index, String data)
	{
		return false;
	}

	@Override
	public int writeString(String[] data)
	{
		return 0;
	}
	
	boolean put(int data)
	{
		return false;
	}
	
	boolean put(int index, int data)
	{
		return false;
	}
	
	boolean put(int[] data)
	{
		return false;
	}
	
	boolean putFloat(float data)
	{
		return false;
	}
	
	boolean putFloat(int index, float data)
	{
		return false;
	}
	
	boolean putFloat(float[] data)
	{
		return false;
	}
	
	boolean putString(String data)
	{
		return false;
	}
	
	boolean putString(int index, String data)
	{
		return false;
	}
	
	boolean putString(String[] data)
	{
		return false;
	}
	
	@Override
	public void addDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		synchronized(mDeviceDataChangedListeners)
		{
			if(mDeviceDataChangedListeners.contains(listener)) return;
			mDeviceDataChangedListeners.add(listener);
		}
	}
	
	@Override
	public void removeDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		synchronized(mDeviceDataChangedListeners)
		{
			mDeviceDataChangedListeners.remove(listener);
		}
	}
	
	@Override
	public void clearDeviceDataChangedListeners()
	{
		synchronized(mDeviceDataChangedListeners)
		{
			mDeviceDataChangedListeners.clear();
		}
	}
	
	void notifyDeviceDataChanged(Robot robot, long timestamp)
	{
		synchronized(mDeviceDataChangedListeners)
		{
			for(DeviceDataChangedListener listener : mDeviceDataChangedListeners)
				listener.onDeviceDataChanged(robot, this, getReadData(), timestamp);
		}
	}
	
	void fire()
	{
		synchronized(mFireLock)
		{
			mFired = true;
		}
	}
	
	void notifyMotoringDeviceWritten()
	{
		if(mParent != null)
			mParent.onMotoringDeviceWritten(this);
	}
	
	void updateDeviceState()
	{
		synchronized(mFireLock)
		{
			mEvent = mFired;
			mFired = false;
		}
	}
}
