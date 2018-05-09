package org.roboid.robot.impl;

import org.roboid.robot.DataType;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class FloatDeviceImpl extends DeviceImpl
{
	final float mMinValue;
	final float mMaxValue;
	private final Object mReadLock;
	private float[] mReadData;
	
	FloatDeviceImpl(RoboidImpl parent, int id, String name, int dataSize, float minValue, float maxValue, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, fireLock);
		
		mMinValue = minValue;
		mMaxValue = maxValue;
		mReadLock = readLock;
		if(dataSize < 0) return;
		
		mReadData = new float[dataSize];
		fillInitialValue(mReadData, 0, dataSize);
	}
	
	void fillInitialValue(float[] data, int start, int end)
	{
		Object value = mInitialValue;
		if(value instanceof Float)
		{
			float v = (Float)value;
			for(int i = start; i < end; ++i)
				data[i] = v;
		}
		else if(value instanceof Integer)
		{
			float v = (Integer)value;
			for(int i = start; i < end; ++i)
				data[i] = v;
		}
		else if(value instanceof float[])
		{
			float[] v = (float[])value;
			int len = Math.min(end, v.length);
			if(start >= len)
			{
				for(int i = start; i < end; ++i)
					data[i] = 0.0f;
			}
			else
			{
				System.arraycopy(v, start, data, start, len - start);
				for(int i = len; i < end; ++i)
					data[i] = 0.0f;
			}
		}
		else if(value instanceof int[])
		{
			int[] v = (int[])value;
			int len = Math.min(end, v.length);
			if(start >= len)
			{
				for(int i = start; i < end; ++i)
					data[i] = 0.0f;
			}
			else
			{
				for(int i = start; i < len; ++i)
					data[i] = v[i];
				for(int i = len; i < end; ++i)
					data[i] = 0.0f;
			}
		}
	}
	
	@Override
	public int getDataType()
	{
		return DataType.FLOAT;
	}
	
	@Override
	Object getReadData()
	{
		return mReadData;
	}
	
	@Override
	boolean putReadData(Object data)
	{
		if(data instanceof float[])
			return putFloat((float[])data);
		else if(data instanceof int[])
			return put((int[])data);
		else if(data instanceof Float)
			return putFloat((Float)data);
		else if(data instanceof Integer)
			return put((Integer)data);
		return false;
	}
	
	@Override
	public int read()
	{
		return (int)readFloat();
	}

	@Override
	public int read(int index)
	{
		return (int)readFloat(index);
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
			float[] readData = mReadData;
			if(readData == null) return 0;
			len = Math.min(readData.length, dataLen);
			for(int i = 0; i < len; ++i)
				data[i] = (int)readData[i];
		}
		return len;
	}

	@Override
	public float readFloat()
	{
		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(readData == null || readData.length <= 0) return 0.0f;
			return readData[0];
		}
	}

	@Override
	public float readFloat(int index)
	{
		if(index < 0) return 0.0f;
		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(readData == null || index >= readData.length) return 0.0f;
			return readData[index];
		}
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
			float[] readData = mReadData;
			if(readData == null) return 0;
			len = Math.min(readData.length, dataLen);
			System.arraycopy(readData, 0, data, 0, len);
		}
		return len;
	}
	
	@Override
	boolean put(int data)
	{
		return putFloat((float)data);
	}
	
	@Override
	boolean put(int index, int data)
	{
		return putFloat(index, (float)data);
	}
	
	@Override
	boolean put(int[] data)
	{
		if(data == null) return false;
		int dataLen = data.length;
		if(dataLen <= 0) return false;
		
		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(mDataSize < 0)
			{
				if(readData == null || readData.length != dataLen)
					readData = mReadData = new float[dataLen];
			}
			if(readData == null) return false;
			int readLen = readData.length;
			if(readLen <= 0) return false;
			int len = Math.min(readLen, dataLen);
			float value, minValue = mMinValue, maxValue = mMaxValue;
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
		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(readData == null || readData.length <= 0)
			{
				if(mDataSize < 0)
					readData = mReadData = new float[1];
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
	boolean putFloat(int index, float data)
	{
		if(index < 0) return false;
		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(readData == null)
			{
				if(mDataSize < 0)
				{
					readData = mReadData = new float[index + 1];
					fillInitialValue(readData, 0, index);
				}
				else
					return false;
			}
			else if(index >= readData.length)
			{
				if(mDataSize < 0)
				{
					float[] newData = new float[index + 1];
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
	boolean putFloat(float[] data)
	{
		if(data == null) return false;
		int dataLen = data.length;
		if(dataLen <= 0) return false;

		synchronized(mReadLock)
		{
			float[] readData = mReadData;
			if(mDataSize < 0)
			{
				if(readData == null || readData.length != dataLen)
					readData = mReadData = new float[dataLen];
			}
			if(readData == null) return false;
			int readLen = readData.length;
			if(readLen <= 0) return false;
			int len = Math.min(readLen, dataLen);
			float value, minValue = mMinValue, maxValue = mMaxValue;
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
}
