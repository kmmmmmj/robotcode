package org.roboid.android.ipc;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
interface DataChangedCallback
{
	void onDataChanged(in byte[] simulacrum, long timestamp);
}
