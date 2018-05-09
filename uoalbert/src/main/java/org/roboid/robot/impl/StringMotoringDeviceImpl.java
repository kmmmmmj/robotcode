package org.roboid.robot.impl;

import org.roboid.robot.MotoringDevice;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class StringMotoringDeviceImpl extends StringDeviceImpl implements MotoringDevice
{
	private final Object mWriteLock;
	private String[] mWriteData;
	
	StringMotoringDeviceImpl(RoboidImpl parent, int id, String name, int dataSize, Object initialValue, Object readLock, Object writeLock, Object fireLock)
	{
		super(parent, id, name, dataSize, initialValue, readLock, fireLock);
		
		mWriteLock = writeLock;
		if(dataSize < 0) return;
		
		mWriteData = new String[dataSize];
		fillInitialValue(mWriteData, 0, dataSize);
	}
	
	@Override
	Object getWriteData()
	{
		return mWriteData;
	}

	@Override
	public boolean writeString(String data)
	{
		synchronized(mWriteLock)
		{
			String[] writeData = mWriteData;
			if(writeData == null || writeData.length <= 0)
			{
				if(mDataSize < 0)
					writeData = mWriteData = new String[1];
				else
					return false;
			}
			writeData[0] = data;
			fire();
			notifyMotoringDeviceWritten();
		}
		return true;
	}

	@Override
	public boolean writeString(int index, String data)
	{
		if(index < 0) return false;
		synchronized(mWriteLock)
		{
			String[] writeData = mWriteData;
			if(writeData == null)
			{
				if(mDataSize < 0)
				{
					writeData = mWriteData = new String[index + 1];
					fillInitialValue(writeData, 0, index);
				}
				else
					return false;
			}
			else if(index >= writeData.length)
			{
				if(mDataSize < 0)
				{
					String[] newData = new String[index + 1];
					int len = writeData.length;
					for(int i = 0; i < len; ++i)
						newData[i] = writeData[i];
					fillInitialValue(newData, len, index);
					writeData = mWriteData = newData;
				}
				else
					return false;
			}
			writeData[index] = data;
			fire();
			notifyMotoringDeviceWritten();
		}
		return true;
	}

	@Override
	public int writeString(String[] data)
	{
		if(data == null) return 0;
		int dataLen = data.length;
		if(dataLen <= 0) return 0;
		
		int len = 0;
		synchronized(mWriteLock)
		{
			String[] writeData = mWriteData;
			if(mDataSize < 0)
			{
				if(writeData == null || writeData.length != dataLen)
					writeData = mWriteData = new String[dataLen];
			}
			if(writeData == null) return 0;
			int writeLen = writeData.length;
			if(writeLen <= 0) return 0;
			len = Math.min(writeLen, dataLen);
			for(int i = 0; i < len; ++i)
				writeData[i] = data[i];
			fire();
			notifyMotoringDeviceWritten();
		}
		return len;
	}
}
