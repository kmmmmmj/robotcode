package org.roboid.robot.impl;

import org.roboid.robot.DataType;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class StringDeviceImpl extends DeviceImpl
{
	private final Object mReadLock;
	private String[] mReadData;
	
	StringDeviceImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object readLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, fireLock);
		
		mReadLock = readLock;
		if(dataSize < 0) return;
		
		mReadData = new String[dataSize];
		fillInitialValue(mReadData, 0, dataSize);
	}
	
	void fillInitialValue(String[] data, int start, int end)
	{
		Object value = mInitialValue;
		if(value instanceof String)
		{
			String v = (String)value;
			for(int i = start; i < end; ++i)
				data[i] = v;
		}
		else if(value instanceof String[])
		{
			String[] v = (String[])value;
			int len = Math.min(end, v.length);
			if(start >= len)
			{
				for(int i = start; i < end; ++i)
					data[i] = "";
			}
			else
			{
				for(int i = start; i < len; ++i)
					data[i] = v[i];
				for(int i = len; i < end; ++i)
					data[i] = "";
			}
		}
	}
	
	@Override
	public int getDataType()
	{
		return DataType.STRING;
	}
	
	@Override
	Object getReadData()
	{
		return mReadData;
	}
	
	@Override
	boolean putReadData(Object data)
	{
		if(data instanceof String[])
			return putString((String[])data);
		else if(data instanceof String)
			return putString((String)data);
		return false;
	}
	
	@Override
	public String readString()
	{
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(readData == null || readData.length <= 0) return "";
			return readData[0];
		}
	}

	@Override
	public String readString(int index)
	{
		if(index < 0) return "";
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(readData == null || index >= readData.length) return "";
			return readData[index];
		}
	}

	@Override
	public int readString(String[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(readData == null) return 0;
			len = Math.min(readData.length, dataLen);
			for(int i = 0; i < len; ++i)
				data[i] = readData[i];
		}
		return len;
	}
	
	@Override
	boolean putString(String data)
	{
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(readData == null || readData.length <= 0)
			{
				if(mDataSize < 0)
					readData = mReadData = new String[1];
				else
					return false;
			}
			readData[0] = data;
			fire();
		}
		return true;
	}

	@Override
	boolean putString(int index, String data)
	{
		if(index < 0) return false;
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(readData == null)
			{
				if(mDataSize < 0)
				{
					readData = mReadData = new String[index + 1];
					fillInitialValue(readData, 0, index);
				}
				else
					return false;
			}
			else if(index >= readData.length)
			{
				if(mDataSize < 0)
				{
					String[] newData = new String[index + 1];
					int len = readData.length;
					for(int i = 0; i < len; ++i)
						newData[i] = readData[i];
					fillInitialValue(newData, len, index);
					readData = mReadData = newData;
				}
				else
					return false;
			}
			readData[index] = data;
			fire();
		}
		return true;
	}

	@Override
	boolean putString(String[] data)
	{
		if(data == null) return false;
		int dataLen = data.length;
		if(dataLen <= 0) return false;
		
		synchronized(mReadLock)
		{
			String[] readData = mReadData;
			if(mDataSize < 0)
			{
				if(readData == null || readData.length != dataLen)
					readData = mReadData = new String[dataLen];
			}
			if(readData == null) return false;
			int readLen = readData.length;
			if(readLen <= 0) return false;
			int len = Math.min(readLen, dataLen);
			for(int i = 0; i < len; ++i)
				readData[i] = data[i];
			fire();
		}
		return true;
	}
}
