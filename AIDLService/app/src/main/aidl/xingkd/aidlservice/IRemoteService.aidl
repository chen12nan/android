// IRemoteService.aidl
package xingkd.aidlservice;

// 自定义类型，需要导入
import xingkd.aidlservice.Person;
// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int add(int a, int b);
     // Person 是自定义类型，因此需要注明方向
     Person getPerson(in Person person);
     void setName(in Person person, String name);
}
