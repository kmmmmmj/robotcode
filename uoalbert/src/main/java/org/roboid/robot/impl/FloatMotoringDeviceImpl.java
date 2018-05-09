package org.roboid.robot.impl;

import org.roboid.robot.MotoringDevice;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class FloatMotoringDeviceImpl extends FloatDeviceImpl implements MotoringDevice
{
	private final Object mWriteLock;
	private float[] mWriteData;
	
	FloatMotoringDeviceImpl(RoboidImpl parent, int id, String name, int dataSize, float minValue, float maxValue, Object initialValue, Object readLock, Object writeLock, Object fireLock)
	{
		super(parent, id, name, dataSize, minValue, maxValue, initialValue, readLock, fireLock);
		
		mWriteLock = writeLock;
		if(dataSize < 0) return;
		
		mWriteData = new float[dataSize];
		fillInitialValue(mWriteData, 0, dataSize);
	}
	
	@Override
	Object getWriteData()
	{
		return mWriteData;
	}

	@Override
	public boolean write(int data)
	{
		return writeFloat((float)data);
	}

	@Override
	public boolean write(int index, int data)
	{
		return writeFloat(index, (float)data);
	}

	@Override
	public int write(int[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mWriteLock)
		{
			float[] writeData = mWriteData;
			if(mDataSize < 0)
			{
				if(writeData == null || writeData.length != dataLen)
					writeData = mWriteData = new float[dataLen];
			}
			if(writeData == null) return 0;
			int writeLen = writeData.length;
			if(writeLen <= 0) return 0;
			len = Math.min(writeLen, dataLen);
			float value, minValue = mMinValue, maxValue = mMaxValue;
			for(int i = 0; i < len; ++i)
			{
				value = data[i];
				if(value < minValue) value = minValue;
				else if(value > maxValue) value = maxValue;
				writeData[i] = value;
			}
			fire();
			notifyMotoringDeviceWritten();
		}
		return len;
	}

	@Override
	public boolean writeFloat(float data)
	{
		synchronized(mWriteLock)
		{
			float[] writeData = mWriteData;
			if(writeData == null || writeData.length <= 0)
			{
				if(mDataSize < 0)
					writeData = mWriteData = new float[1];
				else
					return false;
			}
			if(data < mMinValue) data = mMinValue;
			else if(data > mMaxValue) data = mMaxValue;
			writeData[0] = data;
			fire();
			notifyMotoringDeviceWritten();
		}
		return true;
	}

	@Override
	public boolean writeFloat(int index, float data)
	{
		if(index < 0) return false;
		synchronized(mWriteLock)
		{
			float[] writeData = mWriteData;
			if(writeData == null)
			{
				if(mDataSize < 0)
				{
					writeData = mWriteData = new float[index + 1];
					fillInitialValue(writeData, 0, index);
				}
				else
					return false;
			}
			else if(index >= writeData.length)
			{
				if(mDataSize < 0)
				{
					float[] newData = new float[index + 1];
					int len = writeData.length;
					System.arraycopy(writeData, 0, newData, 0, len);
					fillInitialValue(newData, len, index);
					writeData = mWriteData = newData;
				}
				else
					return false;
			}
			if(data < mMinValue) data = mMinValue;
			else if(data > mMaxValue) data = mMaxValue;
			writeData[index] = data;
			fire();
			notifyMotoringDeviceWritten();
		}
		return true;
	}

	@Override
	public int writeFloat(float[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mWriteLock)
		{
			float[] writeData = mWriteData;
			if(mDataSize < 0)
			{
				if(writeData == null || writeData.length != dataLen)
					writeData = mWriteData = new float[dataLen];
			}
			if(writeData == null) return 0;
			int writeLen = writeData.length;
			if(writeLen <= 0) return 0;
			len = Math.min(writeLen, dataLen);
			float value, minValue = mMinValue, maxValue = mMaxValue;
			for(int i = 0; i < len; ++i)
			{
				value = data[i];
				if(value < minValue) value = minValue;
				else if(value > maxValue) value = maxValue;
				writeData[i] = value;
			}
			fire();
			notifyMotoringDeviceWritten();
		}
		return len;
	}
}
