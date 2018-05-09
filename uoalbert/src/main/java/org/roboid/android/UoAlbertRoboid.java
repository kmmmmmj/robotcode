package org.roboid.android;

import kr.robomation.physical.UoAlbert;

import org.roboid.robot.DataType;
import org.roboid.robot.Device;
import org.roboid.robot.DeviceType;
import org.roboid.robot.Robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
final class UoAlbertRoboid extends AbstractRoboid
{
	private int mReadSensoryFlag;
	private int mReadMotoringFlag;
	private int mWriteMotoringFlag;
	private final byte[] mMotoringSimulacrum = new byte[1004];
	private final Object[] mReadBuffer = new Object[28];
	private final Object[] mWriteBuffer = new Object[15];
	
	UoAlbertRoboid()
	{
		super(0x00700000, 28);
		
		Object[] readBuffer = mReadBuffer;
		Object[] writeBuffer = mWriteBuffer;
		
		Device device = addDevice(0, UoAlbert.SPEAKER, "Speaker", DeviceType.EFFECTOR, DataType.INTEGER, 480, -32768, 32767, 0);
		readBuffer[0] = getReadData(device);
		writeBuffer[0] = getWriteData(device);
		
		device = addDevice(1, UoAlbert.VOLUME, "Volume", DeviceType.EFFECTOR, DataType.INTEGER, 1, 0, 300, 100);
		readBuffer[1] = (int[])getReadData(device);
		writeBuffer[1] = (int[])getWriteData(device);
		
		device = addDevice(2, UoAlbert.LIP, "Lip", DeviceType.EFFECTOR, DataType.INTEGER, 1, 0, 100, 0);
		readBuffer[2] = (int[])getReadData(device);
		writeBuffer[2] = (int[])getWriteData(device);
		
		device = addDevice(3, UoAlbert.LEFT_WHEEL, "LeftWheel", DeviceType.EFFECTOR, DataType.FLOAT, 1, -100.0f, 100.0f, 0.0f);
		readBuffer[3] = getReadData(device);
		writeBuffer[3] = getWriteData(device);
		
		device = addDevice(4, UoAlbert.RIGHT_WHEEL, "RightWheel", DeviceType.EFFECTOR, DataType.FLOAT, 1, -100.0f, 100.0f, 0.0f);
		readBuffer[4] = getReadData(device);
		writeBuffer[4] = getWriteData(device);
		
		device = addDevice(5, UoAlbert.LEFT_EYE, "LeftEye", DeviceType.EFFECTOR, DataType.INTEGER, 3, 0, 255, 0);
		readBuffer[5] = getReadData(device);
		writeBuffer[5] = getWriteData(device);

		device = addDevice(6, UoAlbert.RIGHT_EYE, "RightEye", DeviceType.EFFECTOR, DataType.INTEGER, 3, 0, 255, 0);
		readBuffer[6] = getReadData(device);
		writeBuffer[6] = getWriteData(device);
		
		device = addDevice(7, UoAlbert.BUZZER, "Buzzer", DeviceType.EFFECTOR, DataType.FLOAT, 1, 0.0f, 167772.15f, 0.0f);
		readBuffer[7] = getReadData(device);
		writeBuffer[7] = getWriteData(device);
		
		device = addDevice(8, UoAlbert.PULSE, "Pulse", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 65535, 0);
		readBuffer[8] = getReadData(device);
		writeBuffer[8] = getWriteData(device);
		
		device = addDevice(9, UoAlbert.NOTE, "Note", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 88, 0);
		readBuffer[9] = getReadData(device);
		writeBuffer[9] = getWriteData(device);
		
		device = addDevice(10, UoAlbert.SOUND, "Sound", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 127, 0);
		readBuffer[10] = getReadData(device);
		writeBuffer[10] = getWriteData(device);
		
		device = addDevice(11,  UoAlbert.BOARD_SIZE, "BoardSize", DeviceType.COMMAND,DataType.INTEGER, 2, 0, 40000, 0);
		readBuffer[11] = getReadData(device);
		writeBuffer[11] = getWriteData(device);
		
		device = addDevice(12, UoAlbert.CONFIG_LEFT_POWER, "ConfigLeftPower", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[12] = getReadData(device);
		writeBuffer[12] = getWriteData(device);
		
		device = addDevice(13, UoAlbert.CONFIG_RIGHT_POWER, "ConfigRightPower", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[13] = getReadData(device);
		writeBuffer[13] = getWriteData(device);
		
		device = addDevice(14, UoAlbert.CONFIG_RESOLUTION, "ConfigResolution", DeviceType.COMMAND, DataType.INTEGER, 1, 0, 15, 2);
		readBuffer[14] = getReadData(device);
		writeBuffer[14] = getWriteData(device);
		
		device = addDevice(15, UoAlbert.SIGNAL_STRENGTH, "SignalStrength", DeviceType.SENSOR, DataType.INTEGER, 1, -128, 0, 0);
		readBuffer[15] = getReadData(device);
		
		device = addDevice(16, UoAlbert.LEFT_PROXIMITY, "LeftProximity", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 255, 0);
		readBuffer[16] = getReadData(device);
		
		device = addDevice(17, UoAlbert.RIGHT_PROXIMITY, "RightProximity", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 255, 0);
		readBuffer[17] = getReadData(device);
		
		device = addDevice(18, UoAlbert.ACCELERATION, "Acceleration", DeviceType.SENSOR, DataType.INTEGER, 3, -32768, 32767, 0);
		readBuffer[18] = getReadData(device);
		
		device = addDevice(19, UoAlbert.POSITION, "Position", DeviceType.SENSOR, DataType.INTEGER, 2, -1, 39999, -1);
		readBuffer[19] = getReadData(device);
		
		device = addDevice(20, UoAlbert.LIGHT, "Light", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 65535, 0);
		readBuffer[20] = getReadData(device);
		
		device = addDevice(21, UoAlbert.TEMPERATURE, "Temperature", DeviceType.SENSOR, DataType.INTEGER, 1, -40, 88, 0);
		readBuffer[21] = getReadData(device);
		
		device = addDevice(22, UoAlbert.BATTERY, "Battery", DeviceType.SENSOR, DataType.INTEGER, 1, 0, 100, 0);
		readBuffer[22] = getReadData(device);
		
		device = addDevice(23, UoAlbert.TOUCH, "Touch", DeviceType.EVENT, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[23] = getReadData(device);
		
		device = addDevice(24, UoAlbert.OID, "OID", DeviceType.EVENT, DataType.INTEGER, 1, -1, 65535, -1);
		readBuffer[24] = getReadData(device);
		
		device = addDevice(25, UoAlbert.PULSE_COUNT, "PulseCount", DeviceType.EVENT, DataType.INTEGER, 1, 0, 65535, 0);
		readBuffer[25] = getReadData(device);
		
		device = addDevice(26, UoAlbert.WHEEL_STATE, "WheelState", DeviceType.EVENT, DataType.INTEGER, 1, 0, 1, 0);
		readBuffer[26] = getReadData(device);
		
		device = addDevice(27, UoAlbert.SOUND_STATE, "SoundState", DeviceType.EVENT, DataType.INTEGER, 1, -1, 1, 0);
		readBuffer[27] = getReadData(device);
	}
	
	@Override
	public String getId()
	{
		return UoAlbert.ID;
	}
	
	@Override
	public String getName()
	{
		return "UO Albert";
	}
	
	@Override
	protected void onMotoringDeviceWritten(Device device)
	{
		switch(device.getId())
		{
		case UoAlbert.SPEAKER:
			mWriteMotoringFlag |= 0x40000000;
			break;
		case UoAlbert.PULSE:
			mWriteMotoringFlag |= 0x00400000;
			break;
		case UoAlbert.NOTE:
			mWriteMotoringFlag |= 0x00200000;
			break;
		case UoAlbert.SOUND:
			mWriteMotoringFlag |= 0x00100000;
			break;
		case UoAlbert.BOARD_SIZE:
			mWriteMotoringFlag |= 0x00080000;
			break;
		case UoAlbert.CONFIG_LEFT_POWER:
			mWriteMotoringFlag |= 0x00040000;
			break;
		case UoAlbert.CONFIG_RIGHT_POWER:
			mWriteMotoringFlag |= 0x00020000;
			break;
		case UoAlbert.CONFIG_RESOLUTION:
			mWriteMotoringFlag |= 0x00010000;
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
			((int[])buffer[15])[0] = simulacrum[5]; // byte

			// left proximity
			((int[])buffer[16])[0] = simulacrum[6] & 0xff; // unsigned byte

			// right proximity
			((int[])buffer[17])[0] = simulacrum[7] & 0xff; // unsigned byte

			// acceleration
			int[] t = (int[])buffer[18];
			int v = simulacrum[8] << 8; // short
			v |= simulacrum[9] & 0xff;
			t[0] = v;
			v = simulacrum[10] << 8; // short
			v |= simulacrum[11] & 0xff;
			t[1] = v;
			v = simulacrum[12] << 8; // short
			v |= simulacrum[13] & 0xff;
			t[2] = v;
			
			// position
			t = (int[])buffer[19];
			v = simulacrum[14] << 24; // integer
			v |= (simulacrum[15] & 0xff) << 16;
			v |= (simulacrum[16] & 0xff) << 8;
			v |= simulacrum[17] & 0xff;
			t[0] = v;
			v = simulacrum[18] << 24; // integer
			v |= (simulacrum[19] & 0xff) << 16;
			v |= (simulacrum[20] & 0xff) << 8;
			v |= simulacrum[21] & 0xff;
			t[1] = v;
			
			// light
			v = (simulacrum[22] & 0xff) << 8; // unsigned short
			v |= simulacrum[23] & 0xff;
			((int[])buffer[20])[0] = v;

			// temperature
			((int[])buffer[21])[0] = simulacrum[24]; // byte
			
			// battery
			((int[])buffer[22])[0] = simulacrum[25] & 0xff; // unsigned byte

			// touch
			int index = 27;
			if((simulacrum[4] & 0x80) != 0)
			{
				flag |= 0x00000080;
				((int[])buffer[23])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}
			// oid
			++index;
			if((simulacrum[4] & 0x40) != 0)
			{
				flag |= 0x00000040;
				v = simulacrum[index++] << 24; // integer
				v |= (simulacrum[index++] & 0xff) << 16;
				v |= (simulacrum[index++] & 0xff) << 8;
				v |= simulacrum[index++] & 0xff;
				((int[])buffer[24])[0] = v;
			}
			
			// pulse count
			++index;
			if((simulacrum[4] & 0x20) != 0)
			{
				flag |= 0x00000020;
				v = (simulacrum[index++] & 0xff) << 8; // unsigned short
				v |= simulacrum[index++] & 0xff;
				((int[])buffer[25])[0] = v;
			}
			
			// wheel state
			++index;
			if((simulacrum[4] & 0x10) != 0)
			{
				flag |= 0x00000010;
				((int[])buffer[26])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}
			
			// sound state
			++index;
			if((simulacrum[4] & 0x08) != 0)
			{
				flag |= 0x00000008;
				((int[])buffer[27])[0] = simulacrum[index++]; // byte
			}
		}
		for(int i = 15; i < 23; ++i)
			fire(i);
		if((flag & 0x00000080) != 0)
			fire(23);
		if((flag & 0x00000040) != 0)
			fire(24);
		if((flag & 0x00000020) != 0)
			fire(25);
		if((flag & 0x00000010) != 0)
			fire(26);
		if((flag & 0x00000008) != 0)
			fire(27);
		mReadSensoryFlag = flag;
		return true;
	}
	
	@Override
	void notifySensoryDeviceDataChanged(Robot robot, long timestamp)
	{
		int flag = mReadSensoryFlag;
		
		for(int i = 15; i < 23; ++i)
			notifyDeviceDataChanged(robot, i, timestamp);
		if((flag & 0x00000080) != 0)
			notifyDeviceDataChanged(robot, 23, timestamp);
		if((flag & 0x00000040) != 0)
			notifyDeviceDataChanged(robot, 24, timestamp);
		if((flag & 0x00000020) != 0)
			notifyDeviceDataChanged(robot, 25, timestamp);
		if((flag & 0x00000010) != 0)
			notifyDeviceDataChanged(robot, 26, timestamp);
		if((flag & 0x00000008) != 0)
			notifyDeviceDataChanged(robot, 27, timestamp);
	}
	
	@Override
	protected boolean decodeMotoringSimulacrum(byte[] simulacrum)
	{
		if(simulacrum == null || simulacrum.length < 29) return false;
		
		int flag = 0;
		synchronized(mReadLock)
		{
			Object[] buffer = mReadBuffer;
			
			// speaker
			int v;
			int index = 5;
			if((simulacrum[1] & 0x40) != 0)
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
			v = simulacrum[index++] << 8; // short
			v |= simulacrum[index++] & 0xff;
			((float[])buffer[3])[0] = v * 0.1f;

			// right wheel
			v = simulacrum[index++] << 8; // short
			v |= simulacrum[index++] & 0xff;
			((float[])buffer[4])[0] = v * 0.1f;
			
			// left eye
			int[] t = (int[])buffer[5];
			t[0] = simulacrum[index++] & 0xff; // unsigned byte
			t[1] = simulacrum[index++] & 0xff; // unsigned byte
			t[2] = simulacrum[index++] & 0xff; // unsigned byte

			// right eye
			t = (int[])buffer[6];
			t[0] = simulacrum[index++] & 0xff; // unsigned byte
			t[1] = simulacrum[index++] & 0xff; // unsigned byte
			t[2] = simulacrum[index++] & 0xff; // unsigned byte

			// buzzer
			v = simulacrum[index++] << 24; // integer
			v |= (simulacrum[index++] & 0xff) << 16;
			v |= (simulacrum[index++] & 0xff) << 8;
			v |= simulacrum[index++] & 0xff;
			((float[])buffer[7])[0] = (float)(v / 100.0f);

			// pulse
			++ index;
			if((simulacrum[2] & 0x40) != 0)
			{
				flag |= 0x00400000;
				v = (simulacrum[index++] & 0xff) << 8; // unsigned short
				v |= simulacrum[index++] & 0xff;
				((int[])buffer[8])[0] = v;
			}

			// note
			++ index;
			if((simulacrum[2] & 0x20) != 0)
			{
				flag |= 0x00200000;
				((int[])buffer[9])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// sound
			++ index;
			if((simulacrum[2] & 0x10) != 0)
			{
				flag |= 0x00100000;
				((int[])buffer[10])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}

			// pad size
			++ index;
			if((simulacrum[2] & 0x08) != 0)
			{
				flag |= 0x00080000;
				t = (int[])buffer[11];
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
			
			// config left power
			++ index;
			if((simulacrum[2] & 0x04) != 0)
			{
				flag |= 0x00040000;
				((int[])buffer[12])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}
			
			// config right power
			++ index;
			if((simulacrum[2] & 0x02) != 0)
			{
				flag |= 0x00020000;
				((int[])buffer[13])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}
			
			// config resolution
			++ index;
			if((simulacrum[2] & 0x01) != 0)
			{
				flag |= 0x00010000;
				((int[])buffer[14])[0] = simulacrum[index++] & 0xff; // unsigned byte
			}
		}
		for(int i = 0; i < 8; ++i)
			fire(i);
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
		if((flag & 0x00020000) != 0)
			fire(13);
		if((flag & 0x00010000) != 0)
			fire(14);
		mReadMotoringFlag = flag;
		return true;
	}
	
	@Override
	void notifyMotoringDeviceDataChanged(Robot robot, long timestamp)
	{
		int flag = mReadMotoringFlag;
		
		for(int i = 0; i < 8; ++i)
			notifyDeviceDataChanged(robot, i, timestamp);
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
		if((flag & 0x00020000) != 0)
			notifyDeviceDataChanged(robot, 13, timestamp);
		if((flag & 0x00010000) != 0)
			notifyDeviceDataChanged(robot, 14, timestamp);
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
			simulacrum[1] = (byte)0xbf;
			simulacrum[2] = (byte)0x80;
			simulacrum[3] = (byte)0x00;
			simulacrum[4] = (byte)0x00;
			
			int index = 5;
			int v;
			int[] t;
			
			if((flag & 0x40000000) != 0)
			{
				simulacrum[1] |= 0x40;
				t = (int[])buffer[0];
				for(int i = 0; i < 480; ++i)
				{
					v = t[i];
					simulacrum[index++] = (byte)((v >> 8) & 0xff); // short
					simulacrum[index++] = (byte)(v & 0xff);
				}
			}
			
			// volume
			v = ((int[])buffer[1])[0];
			simulacrum[index++] = (byte)((v >> 8) & 0xff); // unsigned short
			simulacrum[index++] = (byte)(v & 0xff);
			
			// lip
			simulacrum[index++] = (byte)(((int[])buffer[2])[0] & 0xff); // unsigned byte

			// left wheel
			v = (int)(((float[])buffer[3])[0] * 10);
			simulacrum[index++] = (byte)((v >> 8) & 0xff); // short
			simulacrum[index++] = (byte)(v & 0xff);

			// right wheel
			v = (int)(((float[])buffer[4])[0] * 10);
			simulacrum[index++] = (byte)((v >> 8) & 0xff); // short
			simulacrum[index++] = (byte)(v & 0xff);
			
			// left eye
			t = (int[])buffer[5];
			simulacrum[index++] = (byte)(t[0] & 0xff); // unsigned byte
			simulacrum[index++] = (byte)(t[1] & 0xff);
			simulacrum[index++] = (byte)(t[2] & 0xff);

			// right eye
			t = (int[])buffer[6];
			simulacrum[index++] = (byte)(t[0] & 0xff); // unsigned byte
			simulacrum[index++] = (byte)(t[1] & 0xff);
			simulacrum[index++] = (byte)(t[2] & 0xff);

			// buzzer
			v = (int)(((float[])buffer[7])[0] * 100);
			simulacrum[index++] = (byte)((v >> 24) & 0xff); // integer
			simulacrum[index++] = (byte)((v >> 16) & 0xff);
			simulacrum[index++] = (byte)((v >> 8) & 0xff);
			simulacrum[index++] = (byte)(v & 0xff);

			// pulse
			simulacrum[index++] = 0;
			if((flag & 0x00400000) != 0)
			{
				simulacrum[2] |= 0x40;
				v = ((int[])buffer[8])[0];
				simulacrum[index++] = (byte)((v >> 8) & 0xff); // unsigned short
				simulacrum[index++] = (byte)(v & 0xff);
			}

			// note
			simulacrum[index++] = 0;
			if((flag & 0x00200000) != 0)
			{
				simulacrum[2] |= 0x20;
				simulacrum[index++] = (byte)(((int[])buffer[9])[0] & 0xff); // unsigned byte
			}

			// sound
			simulacrum[index++] = 0;
			if((flag & 0x00100000) != 0)
			{
				simulacrum[2] |= 0x10;
				simulacrum[index++] = (byte)(((int[])buffer[10])[0] & 0xff); // unsigned byte
			}

			// pad size
			simulacrum[index++] = 0;
			if((flag & 0x00080000) != 0)
			{
				simulacrum[2] |= 0x08;
				t = (int[])buffer[11];
				v = t[0];
				simulacrum[index++] = (byte)((v >> 24) & 0xff); // integer
				simulacrum[index++] = (byte)((v >> 16) & 0xff);
				simulacrum[index++] = (byte)((v >> 8) & 0xff);
				simulacrum[index++] = (byte)(v & 0xff);
				v = t[1];
				simulacrum[index++] = (byte)((v >> 24) & 0xff);
				simulacrum[index++] = (byte)((v >> 16) & 0xff);
				simulacrum[index++] = (byte)((v >> 8) & 0xff);
				simulacrum[index++] = (byte)(v & 0xff);
			}
			
			// config left power
			simulacrum[index++] = 0;
			if((flag & 0x00040000) != 0)
			{
				simulacrum[2] |= 0x04;
				simulacrum[index++] = (byte)(((int[])buffer[12])[0] & 0xff); // unsigned byte
			}
			
			// config right power
			simulacrum[index++] = 0;
			if((flag & 0x00020000) != 0)
			{
				simulacrum[2] |= 0x02;
				simulacrum[index++] = (byte)(((int[])buffer[13])[0] & 0xff); // unsigned byte
			}
			
			// config resolution
			simulacrum[index++] = 0;
			if((flag & 0x00010000) != 0)
			{
				simulacrum[2] |= 0x01;
				simulacrum[index++] = (byte)(((int[])buffer[14])[0] & 0xff); // unsigned byte
			}
			mWriteMotoringFlag = 0;
		}
		return simulacrum;
	}
}
