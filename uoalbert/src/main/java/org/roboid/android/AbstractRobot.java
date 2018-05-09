package org.roboid.android;

import org.roboid.android.ipc.ClientInterface;
import org.roboid.android.ipc.DataChangedCallback;
import org.roboid.robot.impl.RobotImpl;

import android.os.RemoteException;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class AbstractRobot extends RobotImpl
{
	private final AbstractRoboid mRoboid;
	
	private final DataChangedCallback.Stub mSensoryDataChangedCallback = new DataChangedCallback.Stub()
	{
		@Override
		public void onDataChanged(byte[] simulacrum, long timestamp) throws RemoteException
		{
			if(simulacrum != null && mRoboid != null)
				mRoboid.handleSensorySimulacrum(AbstractRobot.this, simulacrum, timestamp);
		}
	};
	private final DataChangedCallback.Stub mMotoringDataChangedCallback = new DataChangedCallback.Stub()
	{
		@Override
		public void onDataChanged(byte[] simulacrum, long timestamp) throws RemoteException
		{
			if(simulacrum != null && mRoboid != null)
				mRoboid.handleMotoringSimulacrum(AbstractRobot.this, simulacrum, timestamp);
		}
	};
	
	AbstractRobot(int uid, int index, AbstractRoboid roboid)
	{
		super(uid, index);
		
		mRoboid = roboid;
		addRoboid(roboid);
	}
	
	@Override
	protected void setIcon(int icon)
	{
		super.setIcon(icon);
	}

	void activate(ClientInterface clientInterface) throws RemoteException
	{
		if(clientInterface == null) return;
		clientInterface.registerDataChangedCallback(getCode(), getIndex(), mSensoryDataChangedCallback, mMotoringDataChangedCallback);
	}
	
	void deactivate(ClientInterface clientInterface) throws RemoteException
	{
		if(clientInterface == null) return;
		clientInterface.unregisterDataChangedCallback(getCode(), getIndex(), mSensoryDataChangedCallback, mMotoringDataChangedCallback);
	}
	
	@Override
	protected void updateSensoryDeviceState()
	{
		super.updateSensoryDeviceState();
	}
	
	@Override
	protected void updateMotoringDeviceState()
	{
		super.updateMotoringDeviceState();
	}
	
	byte[] encodeMotoringSimulacrum()
	{
		return mRoboid.encodeMotoringSimulacrum();
	}
}
