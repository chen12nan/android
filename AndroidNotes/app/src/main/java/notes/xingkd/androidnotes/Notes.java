package notes.xingkd.androidnotes;

/**
 * Created by xkd on 16-6-20.
 */
public class Notes {

    public static void intent()
    {
        /**
         1、Intent() 空构造函数
         2、Intent(Intent o) 拷贝构造函数
         3、Intent(String action) 指定action类型的构造函数
         4、Intent(String action, Uri uri) 指定Action类型和Uri的构造函数，URI主要是结合程序之间的数据共享ContentProvider
         5、Intent(Context packageContext, Class<?> cls) 传入组件的构造函数，也就是上文提到的
         6、Intent(String action, Uri uri, Context packageContext, Class<?> cls) 前两种结合体
         */
    }

    public static  void multiThread()
    {
        /**
         http://www.cr173.com/html/19165_1.html

         据我所知android提供了以下几种方法，用于实现后台线程与UI线程的交互。
         1、handler
         2、Activity.runOnUIThread(Runnable)
         3、View.Post(Runnable)
         4、View.PostDelayed(Runnabe,long)
         5、AsyncTask
         */
    }

    public static void java_jni()
    {
        /**
         * http://arescaiser.iteye.com/blog/804639
         * gcc -fPIC -shared -I /usr/local/java/jdk1.7.0_79/include/linux
         * -I /usr/local/java/jdk1.7.0_79/include/ -I /usr/include -I ./  main.cpp -o libhello.so
         */
    }

    public static void studio_jni()
    {
        /**
         * http://blog.sina.com.cn/s/blog_ad64b8200102vnxl.html
         * 生成的函数是以包名开始的。结构： 包名_类名_函数名， 所以要进到包名的路径下。
         * 包名 xingkd.testjni;  则进入到和xingkd目录同级的目录下 javah
         * javah -d ../jni   xingkd.testjni.MainActivity
         *
         */
    }
    public static void android_mk()
    {
        /**
         * 1. Android.mk语法：

         首先看一个最简单的Android.mk的例子：
         LOCAL_PATH := $(call my-dir)

         include $(CLEAR_VARS)

         LOCAL_MODULE    := hello-jni
         LOCAL_SRC_FILES := hello-jni.c

         include $(BUILD_SHARED_LIBRARY)

         讲解如下：

         LOCAL_PATH := $(call my-dir)

         每个Android.mk文件必须以定义LOCAL_PATH为开始。它用于在开发tree中查找源文件。

         宏my-dir 则由Build System提供。返回包含Android.mk的目录路径。


         include $(CLEAR_VARS)

         CLEAR_VARS 变量由Build System提供。并指向一个指定的GNU Makefile，由它负责清理很多LOCAL_xxx.

         例如：LOCAL_MODULE, LOCAL_SRC_FILES, LOCAL_STATIC_LIBRARIES等等。但不清理LOCAL_PATH.

         这个清理动作是必须的，因为所有的编译控制文件由同一个GNU Make解析和执行，其变量是全局的。所以清理后才能避免相互影响。


         LOCAL_MODULE    := hello-jni

         LOCAL_MODULE模块必须定义，以表示Android.mk中的每一个模块。名字必须唯一且不包含空格。

         Build System会自动添加适当的前缀和后缀。例如，foo，要产生动态库，则生成libfoo.so. 但请注意：如果模块名被定为：libfoo.则生成libfoo.so. 不再加前缀。


         LOCAL_SRC_FILES := hello-jni.c

         LOCAL_SRC_FILES变量必须包含将要打包如模块的C/C++ 源码。

         不必列出头文件，build System 会自动帮我们找出依赖文件。

         缺省的C++源码的扩展名为.cpp. 也可以修改，通过LOCAL_CPP_EXTENSION。


         include $(BUILD_SHARED_LIBRARY)

         BUILD_SHARED_LIBRARY：是Build System提供的一个变量，指向一个GNU Makefile Script。

         它负责收集自从上次调用 include $(CLEAR_VARS)  后的所有LOCAL_XXX信息。并决定编译类型。



         BUILD_STATIC_LIBRARY：编译为静态库。
         BUILD_SHARED_LIBRARY ：编译为动态库
         BUILD_EXECUTABLE：编译为Native C可执行程序


         2. NDK Build System变量：

         NDK Build System 保留以下变量名：

         以LOCAL_  为开头的

         以PRIVATE_ ,NDK_ 或者APP_ 开头的名字。

         小写字母名字：如my-dir


         如果想要定义自己在Android.mk中使用的变量名，建议添加 ＭＹ＿　前缀。


         2.1: NDK提供的变量：

         此类GNU Make变量是NDK Build System在解析Android.mk之前就定义好了的。

         2.1.1：CLEAR_VARS：

         指向一个编译脚本。必须在新模块前包含之。

         include $(CLEAR_VARS)

         2.1.2：BUILD_SHARED_LIBRARY：

         指向一个编译脚本，它收集自从上次调用 include $(CLEAR_VARS)  后的所有LOCAL_XXX信息。

         并决定如何将你列出的Source编译成一个动态库。 注意，在包含此文件前，至少应该包含：LOCAL_MODULE and LOCAL_SRC_FILES 例如：

         include $(BUILD_SHARED_LIBRARY)

         2.1.3：BUILD_STATIC_LIBRARY：

         与前面类似，它也指向一个编译脚本，

         收集自从上次调用 include $(CLEAR_VARS)  后的所有LOCAL_XXX信息。

         并决定如何将你列出的Source编译成一个静态库。 静态库不能够加入到Project 或者APK中。但它可以用来生成动态库。

         LOCAL_STATIC_LIBRARIES and LOCAL_WHOLE_STATIC_LIBRARIES将描述之。

         include $(BUILD_STATIC_LIBRARY)

         2.1.4: BUILD_EXECUTABLE:
         与前面类似，它也指向一个编译脚本，收集自从上次调用 include $(CLEAR_VARS)  后的所有LOCAL_XXX信息。
         并决定如何将你列出的Source编译成一个可执行Native程序。

         include $(BUILD_EXECUTABLE)

         2.1.5：PREBUILT_SHARED_LIBRARY：
         把这个共享库声明为 “一个” 独立的模块。

         指向一个build 脚本，用来指定一个预先编译好多动态库。 与BUILD_SHARED_LIBRARY and BUILD_STATIC_LIBRARY不同，

         此时模块的LOCAL_SRC_FILES应该被指定为一个预先编译好的动态库，而非source file.  LOCAL_PATH := $(call my-dir)

         include $(CLEAR_VARS)
         LOCAL_MODULE := foo-prebuilt     # 模块名
         LOCAL_SRC_FILES := libfoo.so     # 模块的文件路径（相对于 LOCAL_PATH）

         include $(PREBUILT_SHARED_LIBRARY) # 注意这里不是 BUILD_SHARED_LIBRARY

         这个共享库将被拷贝到 $PROJECT/obj/local 和 $PROJECT/libs/<abi> (stripped)  主要是用在将已经编译好的第三方库

         使用在本Android Project中。为什么不直接将其COPY到libs/armabi目录呢？因为这样做缺陷很多。下一节再详细说明。

         2.1.6: PREBUILT_STATIC_LIBRARY: 预先编译的静态库。 同上。


         2.1.7: TARGET_ARCH: 目标ＣＰＵ架构名。如果为　“arm” 则声称ARM兼容的指令。与CPU架构版本无关。


         2.1.8: TARGET_PLATFORM:  目标平台的名字。


         2.1.9：TARGET_ARCH_ABI

         Name of the target CPU+ABI

         armeabi For ARMv5TE  armeabi-v7a
         2.1.10：TARGET_ABI

         2.2: NDK提供的功能宏：
         GNU　Make 提供的功能宏，只有通过类似： $(call function)   的方式来得到其值，它将返回文本化的信息。

         2.2.1: my-dir: $(call my-dir):
         返回最近一次include的Makefile的路径。通常返回Android.mk所在的路径。它用来作为Android.mk的开头来定义LOCAL_PATH.

         LOCAL_PATH := $(call my-dir)

         请注意：返回的是最近一次include的Makefile的路径。所以在Include其它Makefile后，再调用$(call my-dir)会返回其它Android.mk 所在路径。   例如：

         LOCAL_PATH := $(call my-dir)   ... declare one module   include $(LOCAL_PATH)/foo/Android.mk   LOCAL_PATH := $(call my-dir)   ... declare another module

         则第二次返回的LOCAL_PATH　为：$PATH/foo。 而非$PATH.


         2.2.2: all-subdir-makefiles:
         返回一个列表，包含'my-dir'中所有子目录中的Android.mk。
         例如： 结构如下：

         sources/foo/Android.mk   sources/foo/lib1/Android.mk   sources/foo/lib2/Android.mk

         在If sources/foo/Android.mk 中， include $(call all-subdir-makefiles)  那则自动include 了sources/foo/lib1/Android.mk and sources/foo/lib2/Android.mk。

         2.2.3：this-makefile：
         当前Makefile的路径。

         2.2.4：parent-makefile：
         返回include tree中父Makefile 路径。 也就是include 当前Makefile的Makefile Path。

         2.2.5：import-module：
         允许寻找并inport其它modules到本Android.mk中来。 它会从NDK_MODULE_PATH寻找指定的模块名。

         $(call import-module,<name>)

         2.3: 模块描述变量：
         此类变量用来给Build System描述模块信息。在'include $(CLEAR_VARS)' 和 'include $(BUILD_XXXXX)'之间。必须定义此类变量。

         include $(CLEAR_VARS) script用来清空这些变量。

         include $(BUILD_XXXXX)收集和使用这些变量。

         2.3.1: LOCAL_PATH:
         这个值用来给定当前目录。必须在Android.mk的开是位置定义之。
         例如：  LOCAL_PATH := $(call my-dir)   LOCAL_PATH不会被include $(CLEAR_VARS) 清理。

         2.3.2: LOCAL_MODULE:
         modules名。在include $(BUILD_XXXXX)之前，必须定义这个变量。此变量必须唯一且不能有空格。
         通常，由此变量名决定最终生成的目标文件名。

         2.3.3: LOCAL_MODULE_FILENAME:
         可选。用来override LOCAL_MODULE. 即允许用户重新定义最终生成的目标文件名。

         LOCAL_MODULE := foo-version-1  LOCAL_MODULE_FILENAME := libfoo

         2.3.4：LOCAL_SRC_FILES：
         为Build Modules而提供的Source 文件列表。不需要列出依赖文件。 注意：文件相对于LOCAL_PATH存放，
         且可以提供相对路径。 例如：

         LOCAL_SRC_FILES := foo.c               \ toto/bar.c

         2.3.5: LOCAL_CPP_EXTENSION:
         指出C++ 扩展名。(可选)

         LOCAL_CPP_EXTENSION := .cxx 从NDK R7后，可以写多个：

         LOCAL_CPP_EXTENSION := .cxx .cpp .cc

         2.3.6：LOCAL_CPP_FEATURES：
         可选。用来指定C++ features。

         LOCAL_CPP_FEATURES := rtti

         LOCAL_CPP_FEATURES := exceptions



         2.3.7：LOCAL_C_INCLUDES：
         一个可选的path列表。相对于NDK ROOT 目录。编译时，将会把这些目录附上。

         LOCAL_C_INCLUDES := sources/foo  LOCAL_C_INCLUDES := $(LOCAL_PATH)/../foo

         2.3.8: LOCAL_CFLAGS:
         一个可选的设置，在编译C/C++ source 时添加如Flags。
         用来附加编译选项。 注意：不要尝试在此处修改编译的优化选项和Debug等级。它会通过您Application.mk中的信息自动指定。
         也可以指定include 目录通过：LOCAL_CFLAGS += -I<path>。 这个方法比使用LOCAL_C_INCLUDES要好。因为这样也可以被ndk-debug使用。

         2.3.9: LOCAL_CXXFLAGS:

         LOCAL_CPPFLAGS的别名。

         2.3.10: LOCAL_CPPFLAGS:
         C++ Source 编译时添加的C Flags。这些Flags将出现在LOCAL_CFLAGS flags 的后面。


         2.3.11: LOCAL_STATIC_LIBRARIES:
         要链接到本模块的静态库list。(built with BUILD_STATIC_LIBRARY)


         2.3.12: LOCAL_SHARED_LIBRARIES:
         要链接到本模块的动态库。


         2.3.13：LOCAL_WHOLE_STATIC_LIBRARIES：

         静态库全链接。 不同于LOCAL_STATIC_LIBRARIES，类似于使用--whole-archive

         2.3.14：LOCAL_LDLIBS：

         linker flags。 可以用它来添加系统库。 如 -lz:

         LOCAL_LDLIBS := -lz

         2.3.15: LOCAL_ALLOW_UNDEFINED_SYMBOLS:

         2.3.16: LOCAL_ARM_MODE:
         缺省模式下，ARM目标代码被编译为thumb模式。每个指令16位。如果指定此变量为：arm。 则指令为32位。

         LOCAL_ARM_MODE := arm   其实也可以指定某一个或者某几个文件的ARM指令模式。

         2.3.17: LOCAL_ARM_NEON:
         设置为true时，会讲浮点编译成neon指令。这会极大地加快浮点运算(前提是硬件支持)
         只有targeting 为 'armeabi-v7a'时才可以。

         2.3.18：LOCAL_DISABLE_NO_EXECUTE：

         2.3.19: LOCAL_EXPORT_CFLAGS:
         定义这个变量用来记录C/C++编译器标志集合，
         并且会被添加到其他任何以LOCAL_STATIC_LIBRARIES和LOCAL_SHARED_LIBRARIES的模块的LOCAL_CFLAGS定义中

         LOCAL_SRC_FILES := foo.c bar.c.arm
         */
    }

    /**
     * aidl 自定义类型
     * http://www.cnblogs.com/Lefter/archive/2013/05/24/3097082.html
     *
     * 添加服务
     * http://www.2cto.com/kf/201501/370478.html
     */
    public static void aidlDataDirection()
    {
        /**
         * 对于非基本数据类型，也不是String和CharSequence类型的，需要有方向指示，
         * 包括in、out和inout，in表示由客户端设置，out表示由服务端设置，inout是两者均可设置。
         AIDL只支持接口方法，不能公开static变量。

         AIDL 编程中， 如果服务的aidl包是 a.b.c 那么client的aidl包也必须是 a.b.c (通过AndroidManifest.xml 修改）
         修改后组件的 android:name 属性值要跟着修改，因为当前的包是 a.b.c了，因此要改成从java下一级的包开始
        */

//         然后创建Person.aidl文件，注意这里的parcelable和原来实现的Parcelable 接口，开头的字母p一个小写一个大写：
//         package com.demo;
//         parcelable Person;
        /**
         对于实现AIDL接口，官方还提醒我们：
         1. 调用者是不能保证在主线程执行的，所以从一调用的开始就需要考虑多线程处理，以及确保线程安全；
         2. IPC调用是同步的。如果你知道一个IPC服务需要超过几毫秒的时间才能完成地话，你应该避免在Activity的主线程中调用。也就是IPC调用会挂起应用程序导致界面失去响应，这种情况应该考虑单独开启一个线程来处理。
         3. 抛出的异常是不能返回给调用者（跨进程抛异常处理是不可取的）
         */
    }
}
