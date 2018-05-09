package org.roboid.robot.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.roboid.robot.Device;
import org.roboid.robot.DeviceDataChangedListener;
import org.roboid.robot.Robot;
import org.roboid.robot.World;

import android.util.SparseArray;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public abstract class WorldImpl extends NamedElementImpl implements World
{
	private final SparseArray<SparseArray<RobotImpl>> mIndexUidMap = new SparseArray<SparseArray<RobotImpl>>();
	private final SparseArray<SparseArray<RobotImpl>> mCodeIndexMap = new SparseArray<SparseArray<RobotImpl>>();
	private final ArrayList<Robot> mRobots = new ArrayList<Robot>();
	private final List<Robot> mUnmodifiableRobots = Collections.unmodifiableList(mRobots);
	
	protected WorldImpl()
	{
		super("World");
	}
	
	private void addRobotToIndexUidMap(RobotImpl robot)
	{
		int index = robot.getIndex();
		int uid = robot.getUid();
		
		SparseArray<SparseArray<RobotImpl>> map = mIndexUidMap;
		SparseArray<RobotImpl> robots = map.get(index);
		if(robots == null)
		{
			robots = new SparseArray<RobotImpl>();
			map.put(index, robots);
		}
		robots.put(uid, robot);
	}
	
	private void addRobotToCodeIndexMap(RobotImpl robot)
	{
		int code = robot.getCode();
		int index = robot.getIndex();
		
		SparseArray<SparseArray<RobotImpl>> map = mCodeIndexMap;
		SparseArray<RobotImpl> robots = map.get(code);
		if(robots == null)
		{
			robots = new SparseArray<RobotImpl>();
			map.put(code, robots);
		}
		robots.put(index, robot);
	}
	
	protected void clearRobots()
	{
		mIndexUidMap.clear();
		mCodeIndexMap.clear();
		mRobots.clear();
	}
	
	protected void addRobot(RobotImpl robot)
	{
		if(robot == null) return;
		
		addRobotToIndexUidMap(robot);
		addRobotToCodeIndexMap(robot);
		
		if(mRobots.contains(robot) == false)
			mRobots.add(robot);
	}
	
	protected void updateSensoryDeviceState()
	{
		for(Robot robot : mRobots)
			((RobotImpl)robot).updateSensoryDeviceState();
	}
	
	protected void updateMotoringDeviceState()
	{
		for(Robot robot : mRobots)
			((RobotImpl)robot).updateMotoringDeviceState();
	}
	
	@Override
	public int countRobots()
	{
		return mRobots.size();
	}

	@Override
	public List<Robot> getRobots()
	{
		return mUnmodifiableRobots;
	}

	@Override
	public Robot findRobotById(String modelId)
	{
		return findRobotById(modelId, 0);
	}

	@Override
	public Robot findRobotById(String modelId, int index)
	{
		if(modelId == null) return null;
		
		SparseArray<SparseArray<RobotImpl>> map = mIndexUidMap;
		SparseArray<RobotImpl> robots = map.get(index);
		if(robots == null) return null;
		
		int sz = robots.size();
		Robot robot;
		for(int i = 0; i < sz; ++i)
		{
			robot = robots.valueAt(i);
			if(modelId.equals(robot.getId()))
				return robot;
		}
		return null;
	}

	@Override
	public Robot findRobotByCode(int modelCode)
	{
		return findRobotByCode(modelCode, 0);
	}

	@Override
	public Robot findRobotByCode(int modelCode, int index)
	{
		SparseArray<SparseArray<RobotImpl>> map = mCodeIndexMap;
		SparseArray<RobotImpl> robots = map.get(modelCode);
		if(robots == null) return null;
		
		return robots.get(index);
	}

	@Override
	public Device findDeviceById(int deviceId)
	{
		return findDeviceById(deviceId, 0);
	}

	@Override
	public Device findDeviceById(int deviceId, int index)
	{
		SparseArray<SparseArray<RobotImpl>> map = mIndexUidMap;
		SparseArray<RobotImpl> robots = map.get(index);
		if(robots == null) return null;
		
		int uid = deviceId & 0xfff00000;
		RobotImpl robot = robots.get(uid);
		if(robot == null) return null;
		
		return robot.findDeviceById(deviceId);
	}

	@Override
	public void addDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(Robot robot : mRobots)
			robot.addDeviceDataChangedListener(listener);
	}

	@Override
	public void removeDeviceDataChangedListener(DeviceDataChangedListener listener)
	{
		if(listener == null) return;
		for(Robot robot : mRobots)
			robot.removeDeviceDataChangedListener(listener);
	}
	
	@Override
	public void clearDeviceDataChangedListeners()
	{
		for(Robot robot : mRobots)
			robot.clearDeviceDataChangedListeners();
	}
}
