package org.roboid.android.ipc;

import org.roboid.android.ipc.DataChangedCallback;
import org.roboid.android.ipc.DataRequestCallback;
import org.roboid.android.ipc.ResponseCallback;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
interface ClientInterface
{
	void registerSystemCallback(DataRequestCallback motoringDataRequestCallback, ResponseCallback responseCallback);
	void registerDataChangedCallback(int modelCode, int index, DataChangedCallback sensoryDataChangedCallback, DataChangedCallback motoringDataChangedCallback);
	void unregisterSystemCallback(DataRequestCallback motoringDataRequestCallback, ResponseCallback responseCallback);
	void unregisterDataChangedCallback(int modelCode, int index, DataChangedCallback sensoryDataChangedCallback, DataChangedCallback motoringDataChangedCallback);
	int request(int command, int arg1, int arg2, in Bundle bundle);
}
