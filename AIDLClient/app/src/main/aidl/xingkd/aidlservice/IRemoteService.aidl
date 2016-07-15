// IRemoteService.aidl
package xingkd.aidlservice;

// Declare any non-default types here with import statements

import xingkd.aidlservice.Person;
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
