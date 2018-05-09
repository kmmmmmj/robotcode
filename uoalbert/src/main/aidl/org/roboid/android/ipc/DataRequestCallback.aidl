package org.roboid.android.ipc;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
interface DataRequestCallback
{
	byte[] getMotoringSimulacrum(int modelCode, int index);
	void onExecute();
}
