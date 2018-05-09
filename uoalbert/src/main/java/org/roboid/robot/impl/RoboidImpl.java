package org.roboid.robot.impl;

import java.util.ArrayList;

import org.roboid.robot.DataType;
import org.roboid.robot.Device;
import org.roboid.robot.DeviceDataChangedListener;
import org.roboid.robot.DeviceType;
import org.roboid.robot.Roboid;
import org.roboid.robot.Robot;
import org.roboid.robot.SensoryDevice;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public abstract class RoboidImpl extends NamedElementImpl implements Roboid
{
	private int mUid;
	private final ArrayList<RoboidImpl> mRoboids = new ArrayList<RoboidImpl>();
	private final ArrayList<DeviceImpl> mSensoryDevices = new ArrayList<DeviceImpl>();
	private final ArrayList<DeviceImpl> mMotoringDevices = new ArrayList<DeviceImpl>();
	private final DeviceImpl[] mDevices;
	protected final Object mReadLock = new Object();
	protected final Object mWriteLock = new Object();
	private final Object mFireLock = new Object();

	protected RoboidImpl(int uid, int size)
	{
		mUid = uid;
		mDevices = new DeviceImpl[size];
	}
	
	protected DeviceImpl addDevice(int index, int id, String name, int deviceType, int dataType, int dataSize, float minValue, float maxValue, Object initialValue)
	{
		if(index < 0 || index >= mDevices.length) return null;
		
		DeviceImpl device = null;
		switch(deviceType)
		{
		case DeviceType.SENSOR:
			switch(dataType)
			{
			case DataType.INTEGER:
				device = new IntSensorImpl(this, id, name, dataSize, (int)minValue, (int)maxValue, initialValue, mReadLock, mFireLock);
				break;
			case DataType.FLOAT:
				device = new FloatSensorImpl(this, id, name, dataSize, minValue, maxValue, initialValue, mReadLock, mFireLock);
				break;
			case DataType.STRING:
				device = new StringSensorImpl(this, id, name, dataSize, initialValue, mReadLock, mFireLock);
				break;
			}
			break;
		case DeviceType.EFFECTOR:
			switch(dataType)
			{
			case DataType.INTEGER:
				device = new IntEffectorImpl(this, id, name, dataSize, (int)minValue, (int)maxValue, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			case DataType.FLOAT:
				device = new FloatEffectorImpl(this, id, name, dataSize, minValue, maxValue, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			case DataType.STRING:
				device = new StringEffectorImpl(this, id, name, dataSize, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			}
			break;
		case DeviceType.EVENT:
			switch(dataType)
			{
			case DataType.INTEGER:
				device = new IntEventImpl(this, id, name, dataSize, (int)minValue, (int)maxValue, initialValue, mReadLock, mFireLock);
				break;
			case DataType.FLOAT:
				device = new FloatEventImpl(this, id, name, dataSize, minValue, maxValue, initialValue, mReadLock, mFireLock);
				break;
			case DataType.STRING:
				device = new StringEventImpl(this, id, name, dataSize, initialValue, mReadLock, mFireLock);
				break;
			}
			break;
		case DeviceType.COMMAND:
			switch(dataType)
			{
			case DataType.INTEGER:
				device = new IntCommandImpl(this, id, name, dataSize, (int)minValue, (int)maxValue, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			case DataType.FLOAT:
				device = new FloatCommandImpl(this, id, name, dataSize, minValue, maxValue, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			case DataType.STRING:
				device = new StringCommandImpl(this, id, name, dataSize, initialValue, mReadLock, mWriteLock, mFireLock);
				break;
			}
			break;
		}
		if(device != null)
		{
			mDevices[index] = device;
			if(device instanceof SensoryDevice)
				mSensoryDevices.add(device);
			else
				mMotoringDevices.add(device);
		}
		return device;
	}
	
	// assume that no roboid is added after construction
	protected void addRoboid(Roboid roboid)
	{
		if((roboid instanceof RoboidImpl) == false) return;
		if(mRoboids.contains(roboid)) return;
		mRoboids.add((RoboidImpl)roboid);
	}
	
	protected Object getReadData(Device device)
	{
		if((device instanceof DeviceImpl) == false) return null;
		return ((DeviceImpl)device).getReadData();
	}
	
	protected Object getWriteData(Device device)
	{
		if((device instanceof DeviceImpl) == false) return null;
		return ((DeviceImpl)device).getWriteData();
	}
	
	protected boolean putReadData(Device device, Object data)
	{
		if((device instanceof DeviceImpl) == false) return false;
		return ((DeviceImpl)device).putReadData(data);
	}
	
	@Override
	public Device findDeviceById(int deviceId)
	{
		int uid = deviceId & 0xfff00000;
		if(uid == mUid)
		{
			for(DeviceImpl device : mDevices)
			{
				if(device.getId() == deviceId)
					return device;
			}
			return null;
		}
		
		Device device = null;
		for(RoboidImpl roboid : mRoboids)
		{
			device = roboid.findDeviceById(deviceId);
			if(device != null)
				return device;
		}
		return null;
	}
	
	protected void fire(int index)
	{
		if(index < 0 || index >= mDevices.length) return;
		mDevices[index].fire();
	}
	
	protected void onMotoringDeviceWritten(Device device)
	{
	}
	
	protected void notifyDeviceDataChanged(Robot robot, int index, long timestamp)
	{
		if(index < 0 || index >= mDevices.length) return;
		mDevices[index].notifyDeviceDataChanged(robot, timestamp);
	}
	
	@Override
	public void addDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(Device device : mDevices)
		{
			if(device != null)
				device.addDeviceDataChangedListener(listener);
		}
		for(RoboidImpl roboid : mRoboids)
			roboid.addDeviceDataChangedListener(listener);
	}
	
	@Override
	public void removeDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(Device device : mDevices)
		{
			if(device != null)
				device.removeDeviceDataChangedListener(listener);
		}
		for(RoboidImpl roboid : mRoboids)
			roboid.removeDeviceDataChangedListener(listener);
	}
	
	@Override
	public void clearDeviceDataChangedListeners()
	{
		for(Device device : mDevices)
		{
			if(device != null)
				device.clearDeviceDataChangedListeners();
		}
		for(RoboidImpl roboid : mRoboids)
			roboid.clearDeviceDataChangedListeners();
	}
	
	protected void updateSensoryDeviceState()
	{
		for(DeviceImpl device : mSensoryDevices)
			device.updateDeviceState();
		for(RoboidImpl roboid : mRoboids)
			roboid.updateSensoryDeviceState();
	}
	
	protected void updateMotoringDeviceState()
	{
		for(DeviceImpl device : mMotoringDevices)
			device.updateDeviceState();
		for(RoboidImpl roboid : mRoboids)
			roboid.updateMotoringDeviceState();
	}
}
