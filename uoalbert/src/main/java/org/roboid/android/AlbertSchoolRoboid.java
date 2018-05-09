package org.roboid.android;

import kr.robomation.physical.AlbertSchool;

import org.roboid.robot.DataType;
import org.roboid.robot.Device;
import org.roboid.robot.DeviceType;
import org.roboid.robot.Robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class AlbertSchoolRoboid extends AbstractRoboid
{
	private int mReadSensoryFlag;
	private int mReadMotoringFlag;
	private int mWriteMotoringFlag;
	private final byte[] mMotoringSimulacrum = new byte[994];
	private final Object[] mReadBuffer = new Object[24];
	private final Object[] mWriteBuffer = new Object[13];
	
	AlbertSchoolRoboid()
	{
		super(0x00500000, 24);
		
		Object[] readBuffer = mReadBuffer;
		Object[] writeBuffer = mWriteBuffer;
		
		Device device = addDevice(0, AlbertSchool.SPEAKER, "Speaker", DeviceType.EFFECTOR, DataType.INTEGER, 480, -32768, 32767, 0);
		readBuffer[0] = getReadData(device);
		writeBuffer[0] = getWriteData(device);
		
		device = addDevice(1, AlbertSchool.VOLUME, "Volume", DeviceType.EFFECTOR, DataType.INTEGER, 1, 0, 300, 100);
		readBuffer[1] = (int[])getReadData(device);
		writeBuffer[1] = (int[])getWriteData(device);
		
		device = addDevice(2, AlbertSchool.LIP, "Lip", DeviceType.EFFECTOR, DataType.INTEGER, 1, 0, 100, 0);
		readBuffer[2] = (int[])getReadData(device);
		writeBuffer[2] = (int[])getWriteData(device);
		
		device = addDevice(3, AlbertSchool.LEFT_WHEEL, "LeftWheel", DeviceType.EFFECTOR, DataType.INTEGER, 1, -100, 100, 0);
		readBuffer[3] = getReadData(device);
		writeBuffer[3] = getWriteData(device);
		
		device = addDevice(4, AlbertSchool.RIGHT_WHEEL, "RightWheel", DeviceType.EFFECTOR, DataType.INTEGER, 1, -100, 100, 0);
		readBuffer[4] = getReadData(device);
		writeBuffer[4] = getWriteData(device);
		
		device = addDevice(5, AlbertSchool.BUZZER, "Buzzer", DeviceType.EFFECTOR, DataType.FLOAT, 1, 0.0f, 167772.15f, 0.0f);
		readBuffer[5] = getReadData(device);
		writeBuffer[5] = getWriteData(device);
		
		device = addDevice(6, AlbertSchool.TOPOLOGY, "Topology", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 15, 0);
		readBuffer[6] = getReadData(device);
		writeBuffer[6] = getWriteData(device);
		
		device = addDevice(7, AlbertSchool.LEFT_EYE, "LeftEye", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 7, 0);
		readBuffer[7] = getReadData(device);
		writeBuffer[7] = getWriteData(device);

		device = addDevice(8, AlbertSchool.RIGHT_EYE, "RightEye", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 7, 0);
		readBuffer[8] = getReadData(device);
		writeBuffer[8] = getWriteData(device);
		
		device = addDevice(9, AlbertSchool.NOTE, "Note", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 88, 0);
		readBuffer[9] = getReadData(device);
		writeBuffer[9] = getWriteData(device);
		
		device = addDevice(10, AlbertSchool.BODY_LED, "BodyLed", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[10] = getReadData(device);
		writeBuffer[10] = getWriteData(device);
		
		device = addDevice(11, AlbertSchool.FRONT_LED, "FrontLed", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[11] = getReadData(device);
		writeBuffer[11] = getWriteData(device);
		
		device = addDevice(12, AlbertSchool.BOARD_SIZE, "BoardSize", DeviceType.COMMAND, DataType.INTEGER, 2, 0, 40000, 0);
		readBuffer[12] = getReadData(device);
		writeBuffer[12] = getWriteData(device);
		
		device = addDevice(13, AlbertSchool.SIGNAL_STRENGTH, "SignalStrength", DeviceType.SENSOR, DataType.INTEGER, 1, -128, 0, 0);
		readBuffer[13] = getReadData(device);
		
		device = addDevice(14, AlbertSchool.LEFT_PROXIMITY, "LeftProximity", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 255, 0);
		readBuffer[14] = getReadData(device);
		
		device = addDevice(15, AlbertSchool.RIGHT_PROXIMITY, "RightProximity", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 255, 0);
		readBuffer[15] = getReadData(device);
		
		device = addDevice(16, AlbertSchool.ACCELERATION, "Acceleration", DeviceType.SENSOR, DataType.INTEGER, 3, -32768, 32767, 0);
		readBuffer[16] = getReadData(device);
		
		device = addDevice(17, AlbertSchool.POSITION, "Position", DeviceType.SENSOR, DataType.INTEGER, 2, -1, 39999, -1);
		readBuffer[17] = getReadData(device);
		
		device = addDevice(18, AlbertSchool.ORIENTATION, "Orientation", DeviceType.SENSOR, DataType.INTEGER, 1, -200, 180, -200);
		readBuffer[18] = getReadData(device);
		
		device = addDevice(19, AlbertSchool.LIGHT, "Light", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 65535, 0);
		readBuffer[19] = getReadData(device);
		
		device = addDevice(20, AlbertSchool.TEMPERATURE, "Temperature", DeviceType.SENSOR, DataType.INTEGER, 1, -40, 88, 0);
		readBuffer[20] = getReadData(device);
		
		device = addDevice(21, AlbertSchool.BATTERY, "Battery", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 100, 0);
		readBuffer[21] = getReadData(device);
		
		device = addDevice(22, AlbertSchool.FRONT_OID, "FrontOID", DeviceType.EVENT, DataType.INTEGER, 1, -1, 65535, -1);
		readBuffer[22] = getReadData(device);
		
		device = addDevice(23, AlbertSchool.BACK_OID, "BackOID", DeviceType.EVENT, DataType.INTEGER, 1, -1, 65535, -1);
		readBuffer[23] = getReadData(device);
	}
	
	@Override
	public String getId()
	{
		return AlbertSchool.ID;
	}
	
	@Override
	public String getName()
	{
		return "AlbertSchool";
	}
	
	@Override
	protected void onMotoringDeviceWritten(Device device)
	{
		switch(device.getId())
		{
		case AlbertSchool.SPEAKER:
			mWriteMotoringFlag |= 0x40000000;
			break;
		case AlbertSchool.TOPOLOGY:
			mWriteMotoringFlag |= 0x01000000;
			break;
		case AlbertSchool.LEFT_EYE:
			mWriteMotoringFlag |= 0x00800000;
			break;
		case AlbertSchool.RIGHT_EYE:
			mWriteMotoringFlag |= 0x00400000;
			break;
		case AlbertSchool.NOTE:
			mWriteMotoringFlag |= 0x00200000;
			break;
		case AlbertSchool.BODY_LED:
			mWriteMotoringFlag |= 0x00100000;
			break;
		case AlbertSchool.FRONT_LED:
			mWriteMotoringFlag |= 0x00080000;
			break;
		case AlbertSchool.BOARD_SIZE:
			mWriteMotoringFlag |= 0x00040000;
			break;
		}
	}
	
	@Override
	protected boolean decodeSensorySimulacrum(byte[] simulacrum)
	{
		if(simulacrum == null || simulacrum.length < 31) return false;
		
		int flag = 0;
		synchronized(mReadLock)
		{
			Object[] buffer = mReadBuffer;

			// signal strength
			((int[])buffer[13])[0] = simulacrum[6]; // byte

			// left proximity
			((int[])buffer[14])[0] = simulacrum[7] & 0xff; // unsigned byte

			// right proximity
			((int[])buffer[15])[0] = simulacrum[8] & 0xff; // unsigned byte

			// acceleration
			int[] t = (int[])buffer[16];
			int v = simulacrum[9] << 8; // short
			v |= simulacrum[10] & 0xff;
			t[0] = v;
			v = simulacrum[11] << 8; // short
			v |= simulacrum[12] & 0xff;
			t[1] = v;
			v = simulacrum[13] << 8; // short
			v |= simulacrum[14] & 0xff;
			t[2] = v;
			
			// position
			t = (int[])buffer[17];
			v = simulacrum[15] << 24; // integer
			v |= (simulacrum[16] & 0xff) << 16;
			v |= (simulacrum[17] & 0xff) << 8;
			v |= simulacrum[18] & 0xff;
			t[0] = v;
			v = simulacrum[19] << 24; // integer
			v |= (simulacrum[20] & 0xff) << 16;
			v |= (simulacrum[21] & 0xff) << 8;
			v |= simulacrum[22] & 0xff;
			t[1] = v;
			
			// orientation
			v = simulacrum[23] << 8; // short
			v |= simulacrum[24] & 0xff;
			((int[])buffer[18])[0] = v;

			// light
			v = (simulacrum[25] & 0xff) << 8; // unsigned short
			v |= simulacrum[26] & 0xff;
			((int[])buffer[19])[0] = v;

			// temperature
			((int[])buffer[20])[0] = simulacrum[27]; // byte
			
			// battery // unsigned byte
			((int[])buffer[21])[0] = simulacrum[28] & 0xff; // unsigned byte

			// front oid
			int index = 30;
			if((simulacrum[4] & 0x01) != 0)
			{
				flag |= 0x00000100;
				v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				((int[])buffer[22])[0] = v;
			}
			
			// back oid
			++index;
			if((simulacrum[5] & 0x80) != 0)
			{
				flag |= 0x00000080;
				v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				((int[])buffer[23])[0] = v;
			}
		}
		for(int i = 13; i < 22; ++i)
			fire(i);
		if((flag & 0x00000100) != 0)
			fire(22);
		if((flag & 0x00000080) != 0)
			fire(23);
		mReadSensoryFlag = flag;
		return true;
	}
	
	@Override
	void notifySensoryDeviceDataChanged(Robot robot, long timestamp)
	{
		for(int i = 13; i < 22; ++i)
			notifyDeviceDataChanged(robot, i, timestamp);
		if((mReadSensoryFlag & 0x00000100) != 0)
			notifyDeviceDataChanged(robot, 22, timestamp);
		if((mReadSensoryFlag & 0x00000080) != 0)
			notifyDeviceDataChanged(robot, 23, timestamp);
	}
	
	@Override
	protected boolean decodeMotoringSimulacrum(byte[] simulacrum)
	{
		if(simulacrum == null || simulacrum.length < 22) return false;
		
		int flag = 0;
		synchronized(mReadLock)
		{
			Object[] buffer = mReadBuffer;
			
			// speaker
			int v;
			int index = 6;
			if((simulacrum[2] & 0x40) != 0)
			{
				flag |= 0x40000000;
				int[] t = (int[])buffer[0];
				for(int i = 0; i < 480; ++i)
				{
					v = simulacrum[index++] << 8; // short
					v |= simulacrum[index++] & 0xff;
					t[i] = v;
				}
			}
			
			// volume
			v = (simulacrum[index++] & 0xff) << 8; // unsigned short
			v |= simulacrum[index++] & 0xff;
			((int[])buffer[1])[0] = v;
			
			// lip
			((int[])buffer[2])[0] = simulacrum[index++] & 0xff; // unsigned byte

			// left wheel
			((int[])buffer[3])[0] = simulacrum[index++]; // byte

			// right wheel
			((int[])buffer[4])[0] = simulacrum[index++]; // byte

			// buzzer
			v = simulacrum[index++] << 24; // integer
			v |= (simulacrum[index++] & 0xff) << 16;
			v |= (simulacrum[index++] & 0xff) << 8;
			v |= simulacrum[index++] & 0xff;
			((float[])buffer[5])[0] = (float)(v / 100.0f);

			// topology
			++ index;
			if((simulacrum[2] & 0x01) != 0)
			{
				flag |= 0x01000000;
				((int[])buffer[6])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// left eye
			++ index;
			if((simulacrum[3] & 0x80) != 0)
			{
				flag |= 0x00800000;
				((int[])buffer[7])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// right eye
			++ index;
			if((simulacrum[3] & 0x40) != 0)
			{
				flag |= 0x00400000;
				((int[])buffer[8])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// note
			++ index;
			if((simulacrum[3] & 0x20) != 0)
			{
				flag |= 0x00200000;
				((int[])buffer[9])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// body led
			++ index;
			if((simulacrum[3] & 0x10) != 0)
			{
				flag |= 0x00100000;
				((int[])buffer[10])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// front led
			++ index;
			if((simulacrum[3] & 0x08) != 0)
			{
				flag |= 0x00080000;
				((int[])buffer[11])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// pad size
			++ index;
			if((simulacrum[3] & 0x04) != 0)
			{
				flag |= 0x00040000;
				int[] t = (int[])buffer[12];
				v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				t[0] = v;
				v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				t[1] = v;
			}
		}
		for(int i = 0; i < 6; ++i)
			fire(i);
		if((flag & 0x01000000) != 0)
			fire(6);
		if((flag & 0x00800000) != 0)
			fire(7);
		if((flag & 0x00400000) != 0)
			fire(8);
		if((flag & 0x00200000) != 0)
			fire(9);
		if((flag & 0x00100000) != 0)
			fire(10);
		if((flag & 0x00080000) != 0)
			fire(11);
		if((flag & 0x00040000) != 0)
			fire(12);
		mReadMotoringFlag = flag;
		return true;
	}
	
	@Override
	void notifyMotoringDeviceDataChanged(Robot robot, long timestamp)
	{
		int flag = mReadMotoringFlag;
		
		for(int i = 0; i < 6; ++i)
			notifyDeviceDataChanged(robot, i, timestamp);
		if((flag & 0x01000000) != 0)
			notifyDeviceDataChanged(robot, 6, timestamp);
		if((flag & 0x00800000) != 0)
			notifyDeviceDataChanged(robot, 7, timestamp);
		if((flag & 0x00400000) != 0)
			notifyDeviceDataChanged(robot, 8, timestamp);
		if((flag & 0x00200000) != 0)
			notifyDeviceDataChanged(robot, 9, timestamp);
		if((flag & 0x00100000) != 0)
			notifyDeviceDataChanged(robot, 10, timestamp);
		if((flag & 0x00080000) != 0)
			notifyDeviceDataChanged(robot, 11, timestamp);
		if((flag & 0x00040000) != 0)
			notifyDeviceDataChanged(robot, 12, timestamp);
	}
	
	@Override
	protected byte[] encodeMotoringSimulacrum()
	{
		byte[] simulacrum = mMotoringSimulacrum;
		synchronized(mWriteLock)
		{
			Object[] buffer = mWriteBuffer;
			int flag = mWriteMotoringFlag;
			
			simulacrum[0] = (byte)0x00; // version
			simulacrum[1] = 0; // network id
			simulacrum[2] = (byte)0xbe;
			simulacrum[3] = (byte)0x00;
			simulacrum[4] = (byte)0x00;
			simulacrum[5] = (byte)0x00;
			
			int index = 6;
			int v;
			int[] t;
			
			if((flag & 0x40000000) != 0)
			{
				simulacrum[2] |= 0x40;
				t = (int[])buffer[0];
				for(int i = 0; i < 480; ++i)
				{
					v = t[i];
					simulacrum[index++] = (byte)((v >> 8) & 0xff);
					simulacrum[index++] = (byte)(v & 0xff);
				}
			}
			
			// volume
			v = ((int[])buffer[1])[0];
			simulacrum[index++] = (byte)((v >> 8) & 0xff);
			simulacrum[index++] = (byte)(v & 0xff);
			
			// lip
			simulacrum[index++] = (byte)(((int[])buffer[2])[0] & 0xff);

			// left wheel
			simulacrum[index++] = (byte)(((int[])buffer[3])[0] & 0xff);

			// right wheel
			simulacrum[index++] = (byte)(((int[])buffer[4])[0] & 0xff);

			// buzzer
			float[] ft = (float[])buffer[5];
			v = (int)(ft[0] * 100);
			simulacrum[index++] = (byte)((v >> 24) & 0xff);
			simulacrum[index++] = (byte)((v >> 16) & 0xff);
			simulacrum[index++] = (byte)((v >> 8) & 0xff);
			simulacrum[index++] = (byte)(v & 0xff);

			// topology
			simulacrum[index++] = 0;
			if((flag & 0x01000000) != 0)
			{
				simulacrum[2] |= 0x01;
				simulacrum[index++] = (byte)(((int[])buffer[6])[0] & 0xff);
			}

			// left eye
			simulacrum[index++] = 0;
			if((flag & 0x00800000) != 0)
			{
				simulacrum[3] |= 0x80;
				simulacrum[index++] = (byte)(((int[])buffer[7])[0] & 0xff);
			}

			// right eye
			simulacrum[index++] = 0;
			if((flag & 0x00400000) != 0)
			{
				simulacrum[3] |= 0x40;
				simulacrum[index++] = (byte)(((int[])buffer[8])[0] & 0xff);
			}

			// note
			simulacrum[index++] = 0;
			if((flag & 0x00200000) != 0)
			{
				simulacrum[3] |= 0x20;
				simulacrum[index++] = (byte)(((int[])buffer[9])[0] & 0xff);
			}

			// body led
			simulacrum[index++] = 0;
			if((flag & 0x00100000) != 0)
			{
				simulacrum[3] |= 0x10;
				simulacrum[index++] = (byte)(((int[])buffer[10])[0] & 0xff);
			}

			// front led
			simulacrum[index++] = 0;
			if((flag & 0x00080000) != 0)
			{
				simulacrum[3] |= 0x08;
				simulacrum[index++] = (byte)(((int[])buffer[11])[0] & 0xff);
			}
			
			// pad size
			simulacrum[index++] = 0;
			if((flag & 0x00040000) != 0)
			{
				simulacrum[3] |= 0x04;
				t = (int[])buffer[12];
				v = t[0];
				simulacrum[index++] = (byte)((v >> 24) & 0xff);
				simulacrum[index++] = (byte)((v >> 16) & 0xff);
				simulacrum[index++] = (byte)((v >> 8) & 0xff);
				simulacrum[index++] = (byte)(v & 0xff);
				v = t[1];
				simulacrum[index++] = (byte)((v >> 24) & 0xff);
				simulacrum[index++] = (byte)((v >> 16) & 0xff);
				simulacrum[index++] = (byte)((v >> 8) & 0xff);
				simulacrum[index++] = (byte)(v & 0xff);
			}
			mWriteMotoringFlag = 0;
		}
		return simulacrum;
	}
}
