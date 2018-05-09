package org.roboid.robot;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface ConnectionState
{
	public static final int CONNECTING = 1;
	public static final int CONNECTED = 2;
	public static final int CONNECTION_LOST = 3;
	public static final int DISCONNECTED = 4;
}
