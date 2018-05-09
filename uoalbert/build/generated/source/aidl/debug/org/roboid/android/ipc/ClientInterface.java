/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\minjeong\\Desktop\\minjeong\\robot\\SKproject\\robocode\\uoalbert\\src\\main\\aidl\\org\\roboid\\android\\ipc\\ClientInterface.aidl
 */
package org.roboid.android.ipc;
/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
public interface ClientInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.roboid.android.ipc.ClientInterface
{
private static final java.lang.String DESCRIPTOR = "org.roboid.android.ipc.ClientInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.roboid.android.ipc.ClientInterface interface,
 * generating a proxy if needed.
 */
public static org.roboid.android.ipc.ClientInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.roboid.android.ipc.ClientInterface))) {
return ((org.roboid.android.ipc.ClientInterface)iin);
}
return new org.roboid.android.ipc.ClientInterface.Stub.Proxy(obj);
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
case TRANSACTION_registerSystemCallback:
{
data.enforceInterface(DESCRIPTOR);
org.roboid.android.ipc.DataRequestCallback _arg0;
_arg0 = org.roboid.android.ipc.DataRequestCallback.Stub.asInterface(data.readStrongBinder());
org.roboid.android.ipc.ResponseCallback _arg1;
_arg1 = org.roboid.android.ipc.ResponseCallback.Stub.asInterface(data.readStrongBinder());
this.registerSystemCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_registerDataChangedCallback:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
org.roboid.android.ipc.DataChangedCallback _arg2;
_arg2 = org.roboid.android.ipc.DataChangedCallback.Stub.asInterface(data.readStrongBinder());
org.roboid.android.ipc.DataChangedCallback _arg3;
_arg3 = org.roboid.android.ipc.DataChangedCallback.Stub.asInterface(data.readStrongBinder());
this.registerDataChangedCallback(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterSystemCallback:
{
data.enforceInterface(DESCRIPTOR);
org.roboid.android.ipc.DataRequestCallback _arg0;
_arg0 = org.roboid.android.ipc.DataRequestCallback.Stub.asInterface(data.readStrongBinder());
org.roboid.android.ipc.ResponseCallback _arg1;
_arg1 = org.roboid.android.ipc.ResponseCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterSystemCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterDataChangedCallback:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
org.roboid.android.ipc.DataChangedCallback _arg2;
_arg2 = org.roboid.android.ipc.DataChangedCallback.Stub.asInterface(data.readStrongBinder());
org.roboid.android.ipc.DataChangedCallback _arg3;
_arg3 = org.roboid.android.ipc.DataChangedCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterDataChangedCallback(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_request:
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
int _result = this.request(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.roboid.android.ipc.ClientInterface
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
@Override public void registerSystemCallback(org.roboid.android.ipc.DataRequestCallback motoringDataRequestCallback, org.roboid.android.ipc.ResponseCallback responseCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((motoringDataRequestCallback!=null))?(motoringDataRequestCallback.asBinder()):(null)));
_data.writeStrongBinder((((responseCallback!=null))?(responseCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerSystemCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerDataChangedCallback(int modelCode, int index, org.roboid.android.ipc.DataChangedCallback sensoryDataChangedCallback, org.roboid.android.ipc.DataChangedCallback motoringDataChangedCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(modelCode);
_data.writeInt(index);
_data.writeStrongBinder((((sensoryDataChangedCallback!=null))?(sensoryDataChangedCallback.asBinder()):(null)));
_data.writeStrongBinder((((motoringDataChangedCallback!=null))?(motoringDataChangedCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerDataChangedCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterSystemCallback(org.roboid.android.ipc.DataRequestCallback motoringDataRequestCallback, org.roboid.android.ipc.ResponseCallback responseCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((motoringDataRequestCallback!=null))?(motoringDataRequestCallback.asBinder()):(null)));
_data.writeStrongBinder((((responseCallback!=null))?(responseCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterSystemCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterDataChangedCallback(int modelCode, int index, org.roboid.android.ipc.DataChangedCallback sensoryDataChangedCallback, org.roboid.android.ipc.DataChangedCallback motoringDataChangedCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(modelCode);
_data.writeInt(index);
_data.writeStrongBinder((((sensoryDataChangedCallback!=null))?(sensoryDataChangedCallback.asBinder()):(null)));
_data.writeStrongBinder((((motoringDataChangedCallback!=null))?(motoringDataChangedCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterDataChangedCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int request(int command, int arg1, int arg2, android.os.Bundle bundle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
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
mRemote.transact(Stub.TRANSACTION_request, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_registerSystemCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerDataChangedCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregisterSystemCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterDataChangedCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_request = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public void registerSystemCallback(org.roboid.android.ipc.DataRequestCallback motoringDataRequestCallback, org.roboid.android.ipc.ResponseCallback responseCallback) throws android.os.RemoteException;
public void registerDataChangedCallback(int modelCode, int index, org.roboid.android.ipc.DataChangedCallback sensoryDataChangedCallback, org.roboid.android.ipc.DataChangedCallback motoringDataChangedCallback) throws android.os.RemoteException;
public void unregisterSystemCallback(org.roboid.android.ipc.DataRequestCallback motoringDataRequestCallback, org.roboid.android.ipc.ResponseCallback responseCallback) throws android.os.RemoteException;
public void unregisterDataChangedCallback(int modelCode, int index, org.roboid.android.ipc.DataChangedCallback sensoryDataChangedCallback, org.roboid.android.ipc.DataChangedCallback motoringDataChangedCallback) throws android.os.RemoteException;
public int request(int command, int arg1, int arg2, android.os.Bundle bundle) throws android.os.RemoteException;
}
