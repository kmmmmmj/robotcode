package org.roboid.robot.impl;

import org.roboid.robot.DataType;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class IntDeviceImpl extends DeviceImpl
{
	final int mMinValue;
	final int mMaxValue;
	private final Object mReadLock;
	private int[] mReadData;
	
	IntDeviceImpl(RoboidImpl parent, int id, String name, int dataSize, int minValue, int maxValue, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, fireLock);
		
		mMinValue = minValue;
		mMaxValue = maxValue;
		mReadLock = readLock;
		if(dataSize < 0) return;
		
		mReadData = new int[dataSize];
		fillInitialValue(mReadData, 0, dataSize);
	}
	
	void fillInitialValue(int[] data, int start, int end)
	{
		Object value = mInitialValue;
		if(value instanceof Integer)
		{
			int v = (Integer)value;
			for(int i = start; i < end; ++i)
				data[i] = v;
		}
		else if(value instanceof Float)
		{
			int v = (int)(float)(Float)value;
			for(int i = start; i < end; ++i)
				data[i] = v;
		}
		else if(value instanceof int[])
		{
			int[] v = (int[])value;
			int len = Math.min(end, v.length);
			if(start >= len)
			{
				for(int i = start; i < end; ++i)
					data[i] = 0;
			}
			else
			{
				System.arraycopy(v, start, data, start, len - start);
				for(int i = len; i < end; ++i)
					data[i] = 0;
			}
		}
		else if(value instanceof float[])
		{
			float[] v = (float[])value;
			int len = Math.min(end, v.length);
			if(start >= len)
			{
				for(int i = start; i < end; ++i)
					data[i] = 0;
			}
			else
			{
				for(int i = start; i < len; ++i)
					data[i] = (int)v[i];
				for(int i = len; i < end; ++i)
					data[i] = 0;
			}
		}
	}
	
	@Override
	public int getDataType()
	{
		return DataType.INTEGER;
	}
	
	@Override
	Object getReadData()
	{
		return mReadData;
	}
	
	@Override
	boolean putReadData(Object data)
	{
		if(data instanceof int[])
			return put((int[])data);
		else if(data instanceof float[])
			return putFloat((float[])data);
		else if(data instanceof Integer)
			return put((Integer)data);
		else if(data instanceof Float)
			return putFloat((Float)data);
		return false;
	}
	
	@Override
	public int read()
	{
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null || readData.length <= 0) return 0;
			return readData[0];
		}
	}

	@Override
	public int read(int index)
	{
		if(index < 0) return 0;
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null || index >= readData.length) return 0;
			return readData[index];
		}
	}

	@Override
	public int read(int[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null) return 0;
			len = Math.min(readData.length, dataLen);
			System.arraycopy(readData, 0, data, 0, len);
		}
		return len;
	}

	@Override
	public float readFloat()
	{
		return read();
	}

	@Override
	public float readFloat(int index)
	{
		return read(index);
	}

	@Override
	public int readFloat(float[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null) return 0;
			len = Math.min(readData.length, dataLen);
			for(int i = 0; i < len; ++i)
				data[i] = readData[i];
		}
		return len;
	}
	
	@Override
	boolean put(int data)
	{
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null || readData.length <= 0)
			{
				if(mDataSize < 0)
					readData = mReadData = new int[1];
				else
					return false;
			}
			if(data < mMinValue) data = mMinValue;
			else if(data > mMaxValue) data = mMaxValue;
			readData[0] = data;
			fire();
		}
		return true;
	}
	
	@Override
	boolean put(int index, int data)
	{
		if(index < 0) return false;
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(readData == null)
			{
				if(mDataSize < 0)
				{
					readData = mReadData = new int[index + 1];
					fillInitialValue(readData, 0, index);
				}
				else
					return false;
			}
			else if(index >= readData.length)
			{
				if(mDataSize < 0)
				{
					int[] newData = new int[index + 1];
					int len = readData.length;
					System.arraycopy(readData, 0, newData, 0, len);
					fillInitialValue(newData, len, index);
					readData = mReadData = newData;
				}
				else
					return false;
			}
			if(data < mMinValue) data = mMinValue;
			else if(data > mMaxValue) data = mMaxValue;
			readData[index] = data;
			fire();
		}
		return true;
	}
	
	@Override
	boolean put(int[] data)
	{
		if(data == null) return false;
		int dataLen = data.length;
		if(dataLen <= 0) return false;
		
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(mDataSize < 0)
			{
				if(readData == null || readData.length != dataLen)
					readData = mReadData = new int[dataLen];
			}
			if(readData == null) return false;
			int readLen = readData.length;
			if(readLen <= 0) return false;
			int len = Math.min(readLen, dataLen);
			int value, minValue = mMinValue, maxValue = mMaxValue;
			for(int i = 0; i < len; ++i)
			{
				value = data[i];
				if(value < minValue) value = minValue;
				else if(value > maxValue) value = maxValue;
				readData[i] = value;
			}
			fire();
		}
		return true;
	}
	
	@Override
	boolean putFloat(float data)
	{
		return put((int)data);
	}

	@Override
	boolean putFloat(int index, float data)
	{
		return put(index, (int)data);
	}

	@Override
	boolean putFloat(float[] data)
	{
		if(data == null) return false;
		int dataLen = data.length;
		if(dataLen <= 0) return false;
		
		synchronized(mReadLock)
		{
			int[] readData = mReadData;
			if(mDataSize < 0)
			{
				if(readData == null || readData.length != dataLen)
					readData = mReadData = new int[dataLen];
			}
			if(readData == null) return false;
			int readLen = readData.length;
			if(readLen <= 0) return false;
			int len = Math.min(readLen, dataLen);
			int value, minValue = mMinValue, maxValue = mMaxValue;
			for(int i = 0; i < len; ++i)
			{
				value = (int)data[i];
				if(value < minValue) value = minValue;
				else if(value > maxValue) value = maxValue;
				readData[i] = value;
			}
			fire();
		}
		return true;
	}
}
