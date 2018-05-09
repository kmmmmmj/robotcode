package org.roboid.android;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
interface RoboidIntent
{
	public static final int REQUEST_HARDWARE_LIST = 0x01;
	public static final int RESPONSE_SUCCESS = 0;
	public static final int RESPONSE_ERROR_INVALID_REQUEST = -1;
	public static final int RESPONSE_ERROR_INVALID_CALLBACK = -2;
	
	public static final String PACKAGE_LAUNCHER = "org.smartrobot.android.ble.launcher";
	public static final String ACTION_HARDWARE = "org.smartrobot.intent.action.HARDWARE";
	public static final String CATEGORY_HARDWARE = "org.smartrobot.intent.category.HARDWARE";
	public static final String EXTRA_PACKAGE_NAME = "org.smartrobot.intent.extra.PACKAGE_NAME";
	
	public static final String ACTION_CONNECTION_STATE = "org.smartrobot.intent.action.CONNECTION_STATE";
	public static final String EXTRA_CONNECTION_STATE = "org.smartrobot.intent.extra.CONNECTION_STATE";
	
	public static final String EXTRA_MODEL_CODE = "org.smartrobot.intent.extra.MODEL_CODE";
	public static final String EXTRA_HARDWARE_INDEX = "org.smartrobot.intent.extra.HARDWARE_INDEX";
	public static final String EXTRA_HARDWARE_ICON = "org.smartrobot.intent.extra.HARDWARE_ICON";
	public static final String EXTRA_HARDWARE_NAME = "org.smartrobot.intent.extra.HARDWARE_NAME";
}
