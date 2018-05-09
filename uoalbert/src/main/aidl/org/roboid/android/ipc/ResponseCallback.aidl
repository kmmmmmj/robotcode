package org.roboid.android.ipc;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
interface ResponseCallback
{
	void onResponse(int command, int arg1, int arg2, in Bundle bundle);
}
