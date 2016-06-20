package notes.xingkd.androidnotes.contact;

/**
 * Created by lee on 16-6-19.
 */
public class Contact {

    private short mId;
    private String mName;
    private short mAge;
    private String mPhone;


    public Contact(String name, short age, String phone) {
        mId = 0;
        mName = name;
        mAge = age;
        mPhone = phone;
    }

    public Contact() {
        mId = 0;
        mName = "";
        mAge = 0;
        mPhone = "";
    }

    public void setId(short id)
    {
        mId = id;
    }
    public short getId()
    {
        return mId;
    }

    public void setAge(short age)
    {
        mAge = age;
    }

    public short getAge()
    {
        return mAge;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public  String getName()
    {
        return mName;
    }

    public void setPhone(String phone)
    {
        mPhone = phone;
    }

    public String getPhone()
    {
        return mPhone;
    }
}
