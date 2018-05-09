/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Public\\github\\robotcode\\uoalbert\\src\\main\\aidl\\org\\roboid\\android\\ipc\\DataRequestCallback.aidl
 */
package org.roboid.android.ipc;
/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface DataRequestCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.roboid.android.ipc.DataRequestCallback
{
private static final java.lang.String DESCRIPTOR = "org.roboid.android.ipc.DataRequestCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.roboid.android.ipc.DataRequestCallback interface,
 * generating a proxy if needed.
 */
public static org.roboid.android.ipc.DataRequestCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.roboid.android.ipc.DataRequestCallback))) {
return ((org.roboid.android.ipc.DataRequestCallback)iin);
}
return new org.roboid.android.ipc.DataRequestCallback.Stub.Proxy(obj);
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
case TRANSACTION_getMotoringSimulacrum:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
byte[] _result = this.getMotoringSimulacrum(_arg0, _arg1);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_onExecute:
{
data.enforceInterface(DESCRIPTOR);
this.onExecute();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.roboid.android.ipc.DataRequestCallback
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
@Override public byte[] getMotoringSimulacrum(int modelCode, int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(modelCode);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_getMotoringSimulacrum, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void onExecute() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onExecute, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getMotoringSimulacrum = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onExecute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public byte[] getMotoringSimulacrum(int modelCode, int index) throws android.os.RemoteException;
public void onExecute() throws android.os.RemoteException;
}
