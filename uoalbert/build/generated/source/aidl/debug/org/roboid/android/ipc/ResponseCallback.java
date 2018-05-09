/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\minjeong\\Desktop\\minjeong\\RobotSW\\SKproject\\robocode\\uoalbert\\src\\main\\aidl\\org\\roboid\\android\\ipc\\ResponseCallback.aidl
 */
package org.roboid.android.ipc;
/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface ResponseCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.roboid.android.ipc.ResponseCallback
{
private static final java.lang.String DESCRIPTOR = "org.roboid.android.ipc.ResponseCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.roboid.android.ipc.ResponseCallback interface,
 * generating a proxy if needed.
 */
public static org.roboid.android.ipc.ResponseCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.roboid.android.ipc.ResponseCallback))) {
return ((org.roboid.android.ipc.ResponseCallback)iin);
}
return new org.roboid.android.ipc.ResponseCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onResponse:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
android.os.Bundle _arg3;
if ((0!=data.readInt())) {
_arg3 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg3 = null;
}
this.onResponse(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.roboid.android.ipc.ResponseCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void onResponse(int command, int arg1, int arg2, android.os.Bundle bundle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(command);
_data.writeInt(arg1);
_data.writeInt(arg2);
if ((bundle!=null)) {
_data.writeInt(1);
bundle.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onResponse, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onResponse(int command, int arg1, int arg2, android.os.Bundle bundle) throws android.os.RemoteException;
}
