package org.roboid.robot.impl;

import java.util.ArrayList;

import org.roboid.robot.Device;
import org.roboid.robot.DeviceDataChangedListener;
import org.roboid.robot.Roboid;
import org.roboid.robot.Robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public abstract class RobotImpl extends NamedElementImpl implements Robot
{
	private final int mUid;
	private final int mIndex;
	private int mIcon;
	private final ArrayList<RoboidImpl> mRoboids = new ArrayList<RoboidImpl>();
	
	protected RobotImpl(int uid, int index)
	{
		mUid = uid;
		mIndex = index;
	}
	
	int getUid()
	{
		return mUid;
	}
	
	@Override
	public int getIndex()
	{
		return mIndex;
	}

	@Override
	public int getIcon()
	{
		return mIcon;
	}
	
	protected void setIcon(int icon)
	{
		mIcon = icon;
	}

	// assume that no roboid is added after construction
	protected void addRoboid(Roboid roboid)
	{
		if((roboid instanceof RoboidImpl) == false) return;
		if(mRoboids.contains(roboid)) return;
		mRoboids.add((RoboidImpl)roboid);
	}
	
	@Override
	public Device findDeviceById(int deviceId)
	{
		Device device = null;
		for(RoboidImpl roboid : mRoboids)
		{
			device = roboid.findDeviceById(deviceId);
			if(device != null)
				return device;
		}
		return null;
	}

	@Override
	public void addDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(RoboidImpl roboid : mRoboids)
			roboid.addDeviceDataChangedListener(listener);
	}

	@Override
	public void removeDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(RoboidImpl roboid : mRoboids)
			roboid.removeDeviceDataChangedListener(listener);
	}
	
	@Override
	public void clearDeviceDataChangedListeners()
	{
		for(RoboidImpl roboid : mRoboids)
			roboid.clearDeviceDataChangedListeners();
	}
	
	protected void updateSensoryDeviceState()
	{
		for(RoboidImpl roboid : mRoboids)
			roboid.updateSensoryDeviceState();
	}
	
	protected void updateMotoringDeviceState()
	{
		for(RoboidImpl roboid : mRoboids)
			roboid.updateMotoringDeviceState();
	}
}
