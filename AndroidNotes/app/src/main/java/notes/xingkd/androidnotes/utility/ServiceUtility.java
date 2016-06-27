package notes.xingkd.androidnotes.utility;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by xkd on 16-6-27.
 */
public class ServiceUtility {

    /**
     * Android5.0中service的intent一定要显性声明，当这样绑定的时候不会报错。
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;

        /**
         final Intent intent = new Intent();
         intent.setAction("com.example.user.firstapp.FIRST_SERVICE");
         final Intent eintent = new Intent(createExplicitFromImplicitIntent(this,intent));
         bindService(eintent,conn, Service.BIND_AUTO_CREATE);
         */
    }


}
