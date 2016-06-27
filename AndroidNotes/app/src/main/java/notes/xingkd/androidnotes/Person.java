package notes.xingkd.androidnotes;


/**
 * Created by xkd on 16-6-27.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xkd on 16-6-27.
 */
public class Person implements Parcelable {
    private String mName;
    private int mAge;

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    public Person(Parcel in)
    {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mAge);
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in) {
        mAge = in.readInt();
        mName = in.readString();
    }

    public String getName()
    {
        return mName;
    }

    public int getAge()
    {
        return mAge;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public void setAge(int age)
    {
        mAge = age;
    }
}
