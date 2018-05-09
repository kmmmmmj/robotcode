package org.roboid.android;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import org.roboid.android.ipc.ClientInterface;
import org.roboid.android.ipc.DataRequestCallback;
import org.roboid.android.ipc.ResponseCallback;
import org.roboid.robot.Robot;
import org.roboid.robot.World;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public final class Connector
{
	private static final int STATE_NONE = 0;
	private static final int STATE_QUIT = 5;
	
	private static final int MSG_CONNECTION_STATE = 1;

	public static final HardwareWorld mWorld = new HardwareWorld();
	private final ArrayList<AbstractRobot> mRobots = new ArrayList<AbstractRobot>();
	private WeakReference<Context> mContext;
	Callback mCallback;
	private int mCount;
	
	private ClientInterface mClientInterface;
	private final ServiceConnection mServiceConnection = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder)
		{
			mClientInterface = ClientInterface.Stub.asInterface(binder);
			try
			{
				mClientInterface.registerSystemCallback(mMotoringDataRequestCallback, mResponseCallback);
				mClientInterface.request(RoboidIntent.REQUEST_HARDWARE_LIST, 0, 0, null);
			} catch (Exception e)
			{
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			mClientInterface = null;
		}
	};
	private final DataRequestCallback.Stub mMotoringDataRequestCallback = new DataRequestCallback.Stub()
	{
		@Override
		public byte[] getMotoringSimulacrum(int modelCode, int index) throws RemoteException
		{
			AbstractRobot robot = (AbstractRobot)mWorld.findRobotByCode(modelCode, index);
			if(robot == null) return null;
			return robot.encodeMotoringSimulacrum();
		}

		@Override
		public void onExecute() throws RemoteException
		{
			for(AbstractRobot robot : mRobots)
				robot.updateSensoryDeviceState();
			if(mCallback != null)
				mCallback.onExecute();
			for(AbstractRobot robot : mRobots)
				robot.updateMotoringDeviceState();
		}
	};
	private final ResponseCallback.Stub mResponseCallback = new ResponseCallback.Stub()
	{
		@Override
		public void onResponse(int command, int arg1, int arg2, Bundle bundle) throws RemoteException
		{
			switch(command)
			{
			case RoboidIntent.REQUEST_HARDWARE_LIST:
				{
					if(bundle == null) return;
					
					int[] codes = bundle.getIntArray(RoboidIntent.EXTRA_MODEL_CODE);
					int[] indices = bundle.getIntArray(RoboidIntent.EXTRA_HARDWARE_INDEX);
					int[] icons = bundle.getIntArray(RoboidIntent.EXTRA_HARDWARE_ICON);
					String[] names = bundle.getStringArray(RoboidIntent.EXTRA_HARDWARE_NAME);
					
					if(codes == null || indices == null || icons == null || names == null) return;
					int len = Math.min(codes.length, indices.length);
					len = Math.min(len, icons.length);
					len = Math.min(len, names.length);
					
					HardwareWorld world = mWorld;
					ArrayList<AbstractRobot> robots = mRobots;
					world.clearDeviceDataChangedListeners();
					world.clearRobots();
					robots.clear();
					
					AbstractRobot robot;
					Callback callback = mCallback;
					for(int i = 0; i < len; ++i)
					{
						robot = RobotFactory.create(codes[i], indices[i]);
						robot.setIcon(icons[i]);
						robot.setName(names[i]);
						world.addRobot(robot);
						robots.add(robot);
					}
					
					if(callback != null)
						callback.onInitialized(world);
					
					ClientInterface clientInterface = mClientInterface;
					if(clientInterface != null)
					{
						try
						{
							for(AbstractRobot r : robots)
								r.activate(clientInterface);
							if(callback != null)
								callback.onActivated();
						} catch (Exception e)
						{
						}
					}
				}
				break;
			}
		}
	};
	private BroadcastReceiver mBR;
	private final MsgHandler mMsgHandler;
	private static Connector INSTANCE = new Connector();
	
	public interface Callback
	{
		public abstract void onInitialized(World world);
		public abstract void onActivated();
		public abstract void onDeactivated();
		public abstract void onExecute();
		public abstract void onDisposed();
		public abstract void onConnectionStateChanged(Robot robot, int state);
	}
	
	private Connector()
	{
		mCount = 0;
		Looper looper;
		if((looper = Looper.myLooper()) != null)
		{
			mMsgHandler = new MsgHandler(looper);
		}
		else if((looper = Looper.getMainLooper()) != null)
		{
			mMsgHandler = new MsgHandler(looper);
		}
		else
			mMsgHandler = new MsgHandler();
	}
	
	public static boolean activate(Context context, Callback callback)
	{
		if(context == null || callback == null) return false;
		INSTANCE.setContext(context);
		INSTANCE.setCallback(callback);
		return INSTANCE.doActivate();
	}
	
	public static void deactivate()
	{
		INSTANCE.doDeactivate();
	}
	
	Context getContext()
	{
		if(mContext == null) return null;
		return mContext.get();
	}
	
	private void setContext(Context context)
	{
		Context applicationContext = context.getApplicationContext();
		if(applicationContext == null)
			mContext = new WeakReference<Context>(context);
		else
			mContext = new WeakReference<Context>(applicationContext);
	}
	
	private void setCallback(Callback callback)
	{
		mCallback = callback;
	}
	
	private boolean doActivate()
	{
		if(++mCount > 1) return true;
		Context context = getContext();
		if(context == null) return false;
		
		registerBroadcast(context);
		
		try
		{
			Intent intent = new Intent(RoboidIntent.ACTION_HARDWARE);
			intent.setPackage(RoboidIntent.PACKAGE_LAUNCHER);
			intent.addCategory(RoboidIntent.CATEGORY_HARDWARE);
			intent.putExtra(RoboidIntent.EXTRA_PACKAGE_NAME, context.getPackageName());
			context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	private void registerBroadcast(Context context)
	{
		if(mBR != null) return;
		mBR = new BroadcastReceiver()
		{
			@Override
			public void onReceive(Context context, Intent intent)
			{
				String action = intent.getAction();
				if(RoboidIntent.ACTION_CONNECTION_STATE.equals(action))
				{
					if(mMsgHandler != null)
					{
						Message msg = mMsgHandler.obtainMessage(MSG_CONNECTION_STATE);
						msg.obj = intent;
						msg.sendToTarget();
					}
				}
			}
		};
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(RoboidIntent.ACTION_CONNECTION_STATE);
		context.registerReceiver(mBR, intentFilter);
	}
	
	private void doDeactivate()
	{
		if(--mCount > 0) return;
		
		if(mMsgHandler != null)
			mMsgHandler.removeCallbacksAndMessages(null);
		
		disconnect();
		
		if(mBR != null)
		{
			try
			{
				Context context = getContext();
				if(context != null)
					context.unregisterReceiver(mBR);
			} catch (Exception e)
			{
			}
			mBR = null;
		}
		mCallback = null;
	}
	
	void disconnect()
	{
		ClientInterface clientInterface = mClientInterface;
		if(clientInterface != null)
		{
			try
			{
				clientInterface.unregisterSystemCallback(mMotoringDataRequestCallback, mResponseCallback);
				for(AbstractRobot robot : mRobots)
					robot.deactivate(clientInterface);
				if(mCallback != null)
					mCallback.onDeactivated();
			} catch (Exception e)
			{
			}
		}
		
		try
		{
			mClientInterface = null;
			
			Context context = getContext();
			if(context != null)
				context.unbindService(mServiceConnection);
		} catch (Exception e)
		{
		}
		
		mWorld.clearDeviceDataChangedListeners();
		mWorld.clearRobots();
		mRobots.clear();
	}
	
	private class MsgHandler extends Handler
	{
		MsgHandler()
		{
			super();
		}
		
		MsgHandler(Looper looper)
		{
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg)
		{

			switch(msg.what)
			{
			case MSG_CONNECTION_STATE:
				{
					Intent intent = (Intent)msg.obj;
					if(intent == null) return;

					int state = intent.getIntExtra(RoboidIntent.EXTRA_CONNECTION_STATE, STATE_NONE);
					switch(state)
					{
					case STATE_NONE:
						break;
					case STATE_QUIT:
						{
							disconnect();
							Connector.Callback callback = mCallback;
							if(callback != null)
								callback.onDisposed();
						}
						break;
					default:
						{
							Connector.Callback callback = mCallback;
							if(callback != null)
							{
								int code = intent.getIntExtra(RoboidIntent.EXTRA_MODEL_CODE, -1);
								int index = intent.getIntExtra(RoboidIntent.EXTRA_HARDWARE_INDEX, -1);
								Robot robot = mWorld.findRobotByCode(code, index);
								if(robot != null)
									callback.onConnectionStateChanged(robot, state);
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
}
