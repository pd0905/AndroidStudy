package leavesc.hello.aidl_server;

import leavesc.hello.aidl_server.Parameter;

interface IOnOperationCompletedListener {

    void onOperationCompleted(in Parameter result);

    void onOperationCompleted01(in Parameter result);

    void onOperationCompletedRegister(boolean flag);

    void onOperationCompletedUnRegister(boolean flag);
}
