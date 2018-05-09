package org.roboid.android;

import android.app.Activity;

import org.roboid.robot.Device;
import org.roboid.robot.DeviceDataChangedListener;
import org.roboid.robot.Robot;
import org.roboid.robot.World;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public abstract class RobotActivity extends Activity implements Connector.Callback, DeviceDataChangedListener
{
	@Override
	protected void onStart()
	{
		super.onStart();
		Connector.activate(getApplicationContext(), this);

    }


	@Override
	protected void onStop()
	{
		super.onStop();

		Connector.deactivate();

	}

	@Override
	public void onInitialized(World world)
	{
		world.addDeviceDataChangedListener(this);
	}

	@Override
	public void onActivated()
	{

	}

	@Override
	public void onDeactivated()
	{


	}

    public abstract void onInitialized(Robot robot);

    @Override
	public void onExecute()
	{
	}

	@Override
	public void onDisposed()
	{
		finish();
	}

	@Override
	public void onConnectionStateChanged(Robot robot, int state)
	{
	}

	@Override
	public void onDeviceDataChanged(Robot robot, Device device, Object values, long timestamp)
	{
	}
}
