package org.roboid.android;

import org.roboid.robot.DataType;
import org.roboid.robot.Device;
import org.roboid.robot.DeviceType;
import org.roboid.robot.Robot;

import kr.robomation.peripheral.SmartPen;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class SmartPenRoboid extends AbstractRoboid
{
	private int mReadSensoryFlag;
	private final int[][] mReadBuffer = new int[NUM_DEVICES][];
	
	private static final int UID = 0x00600000;
	private static final int NUM_DEVICES = 4;
	private static final int MIN_SENSORY_SIMULACRUM_SIZE = 6;
	
	private static final int DEVICE_INDEX_SIGNAL_STRENGTH = 0;
	private static final int DEVICE_INDEX_BATTERY = 1;
	private static final int DEVICE_INDEX_OID = 2;
	private static final int DEVICE_INDEX_BUTTON = 3;
	
	private static final int SIMULACRUM_INDEX_SIGNAL_STRENGTH = 2;
	private static final int SIMULACRUM_INDEX_BATTERY = 3;
//	private static final int SIMULACRUM_INDEX_OID_ID = 4;
	private static final int SIMULACRUM_INDEX_OID = 5;
	
	private static final int DATA_SIZE_SIGNAL_STRENGTH = 1;
	private static final int DATA_SIZE_BATTERY = 1;
	private static final int DATA_SIZE_OID = 1;
	private static final int DATA_SIZE_BUTTON = 1;
	
	private static final int MIN_SIGNAL_STRENGTH = -128;
	private static final int MIN_BATTERY = 0;
	private static final int MIN_OID = -1;
	private static final int MIN_BUTTON = 0;
	
	private static final int MAX_SIGNAL_STRENGTH = 0;
	private static final int MAX_BATTERY = 100;
	private static final int MAX_OID = 65535;
	private static final int MAX_BUTTON = 1;
	
	private static final int DEFAULT_SIGNAL_STRENGTH = 0;
	private static final int DEFAULT_BATTERY = 0;
	private static final int DEFAULT_OID = -1;
	private static final int DEFAULT_BUTTON = 0;
	
	private static final int MASK_OID = 0x10000000;
	private static final int MASK_BUTTON = 0x08000000;
	
//	private static final int DMP_ROBOID = 0x80;
//	private static final int DMP_SIGNAL_STRENGTH = 0x40;
//	private static final int DMP_BATTERY = 0x20;
	private static final int DMP_OID = 0x10;
	private static final int DMP_BUTTON = 0x08;
	
	private static final int DMP_INDEX_OID = 1;
	private static final int DMP_INDEX_BUTTON = 1;
	
	SmartPenRoboid()
	{
		super(UID, NUM_DEVICES);
		
		int[][] readBuffer = mReadBuffer;
		
		Device device = addDevice(DEVICE_INDEX_SIGNAL_STRENGTH, SmartPen.SIGNAL_STRENGTH, "SignalStrength", DeviceType.SENSOR, DataType.INTEGER, DATA_SIZE_SIGNAL_STRENGTH, MIN_SIGNAL_STRENGTH, MAX_SIGNAL_STRENGTH, DEFAULT_SIGNAL_STRENGTH);
		readBuffer[DEVICE_INDEX_SIGNAL_STRENGTH] = (int[])getReadData(device);
		
		device = addDevice(DEVICE_INDEX_BATTERY, SmartPen.BATTERY, "Battery", DeviceType.SENSOR, DataType.INTEGER, DATA_SIZE_BATTERY, MIN_BATTERY, MAX_BATTERY, DEFAULT_BATTERY);
		readBuffer[DEVICE_INDEX_BATTERY] = (int[])getReadData(device);
		
		device = addDevice(DEVICE_INDEX_OID, SmartPen.OID, "OID", DeviceType.EVENT, DataType.INTEGER, DATA_SIZE_OID, MIN_OID, MAX_OID , DEFAULT_OID);
		readBuffer[DEVICE_INDEX_OID] = (int[])getReadData(device);
		
		device = addDevice(DEVICE_INDEX_BUTTON, SmartPen.BUTTON, "Button", DeviceType.EVENT, DataType.INTEGER, DATA_SIZE_BUTTON, MIN_BUTTON, MAX_BUTTON, DEFAULT_BUTTON);
		readBuffer[DEVICE_INDEX_BUTTON] = (int[])getReadData(device);
	}
	
	@Override
	public String getId()
	{
		return SmartPen.ID;
	}
	
	@Override
	public String getName()
	{
		return "SmartPen";
	}
	
	@Override
	boolean decodeSensorySimulacrum(byte[] simulacrum)
	{
		if(simulacrum == null || simulacrum.length < MIN_SENSORY_SIMULACRUM_SIZE) return false;
		
		// simulacrum[0] : version
		// simulacrum[1] : dmp
		
		int flag = 0;
		synchronized(mReadLock)
		{
			int[][] buffer = mReadBuffer;

			// signal strength
			buffer[DEVICE_INDEX_SIGNAL_STRENGTH][0] = simulacrum[SIMULACRUM_INDEX_SIGNAL_STRENGTH]; // byte

			// battery
			buffer[DEVICE_INDEX_BATTERY][0] = simulacrum[SIMULACRUM_INDEX_BATTERY] & 0xff; // unsigned byte

			// oid
			int index = SIMULACRUM_INDEX_OID;
			if((simulacrum[DMP_INDEX_OID] & DMP_OID) != 0)
			{
				flag |= MASK_OID;
				int v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				buffer[DEVICE_INDEX_OID][0] = v;
			}

			// button
			++ index;
			if((simulacrum[DMP_INDEX_BUTTON] & DMP_BUTTON) != 0)
			{
				flag |= MASK_BUTTON;
				buffer[DEVICE_INDEX_BUTTON][0] = simulacrum[index++] & 0xff; // unsigned byte
			}
		}
		fire(DEVICE_INDEX_SIGNAL_STRENGTH);
		fire(DEVICE_INDEX_BATTERY);
		if((flag & MASK_OID) != 0)
			fire(DEVICE_INDEX_OID);
		if((flag & MASK_BUTTON) != 0)
			fire(DEVICE_INDEX_BUTTON);
		mReadSensoryFlag = flag;
		return true;
	}
	
	@Override
	void notifySensoryDeviceDataChanged(Robot robot, long timestamp)
	{
		notifyDeviceDataChanged(robot, DEVICE_INDEX_SIGNAL_STRENGTH, timestamp);
		notifyDeviceDataChanged(robot, DEVICE_INDEX_BATTERY, timestamp);
		if((mReadSensoryFlag & MASK_OID) != 0)
			notifyDeviceDataChanged(robot, DEVICE_INDEX_OID, timestamp);
		if((mReadSensoryFlag & MASK_BUTTON) != 0)
			notifyDeviceDataChanged(robot, DEVICE_INDEX_BUTTON, timestamp);
	}
}
