/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\minjeong\\Documents\\RobotTeam3\\robotcode\\uoalbert\\src\\main\\aidl\\org\\roboid\\android\\ipc\\DataChangedCallback.aidl
 */
package org.roboid.android.ipc;
/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface DataChangedCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.roboid.android.ipc.DataChangedCallback
{
private static final java.lang.String DESCRIPTOR = "org.roboid.android.ipc.DataChangedCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.roboid.android.ipc.DataChangedCallback interface,
 * generating a proxy if needed.
 */
public static org.roboid.android.ipc.DataChangedCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.roboid.android.ipc.DataChangedCallback))) {
return ((org.roboid.android.ipc.DataChangedCallback)iin);
}
return new org.roboid.android.ipc.DataChangedCallback.Stub.Proxy(obj);
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
case TRANSACTION_onDataChanged:
{
data.enforceInterface(DESCRIPTOR);
byte[] _arg0;
_arg0 = data.createByteArray();
long _arg1;
_arg1 = data.readLong();
this.onDataChanged(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.roboid.android.ipc.DataChangedCallback
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
@Override public void onDataChanged(byte[] simulacrum, long timestamp) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(simulacrum);
_data.writeLong(timestamp);
mRemote.transact(Stub.TRANSACTION_onDataChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onDataChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onDataChanged(byte[] simulacrum, long timestamp) throws android.os.RemoteException;
}
