#define LOG_TAG "TimerClient"

#include <utils/Log.h>

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>

#include "CallbackService.h"
#include "ITimerService.h"

using namespace android;

int main()
{
    // LOGD("TimerClient is starting");
    android_printLog(8, "aaa", "TimerClient is starting");

    sp<ProcessState> proc(ProcessState::self());
    ProcessState::self()->startThreadPool();

    sp<ITimerService> service = interface_cast<ITimerService>(defaultServiceManager()->getService(String16("timer")));
    // LOGD("Successfully get timer service");

    sp<CallbackService> callback(new CallbackService);
    sp<IBinder> binder(callback.get());

    // LOGD("Request to registerCallback, timeout = 30");
    android_printLog(8, "aaa", "Request to registerCallback, timeout = 30");
    service->registerCallback(binder, 30);

    {
        Mutex::Autolock _l(callback->mLock);
        // LOGD("Wait callback to be executed");
        android_printLog(8, "aaa", "Wait callback to be executed");
        callback->mCondition.wait(callback->mLock);
    }
    android_printLog(8, "aaa", "Callback has already been executed. Ready to exit");
    // LOGD("Callback has already been executed. Ready to exit");

    return 0;
}
