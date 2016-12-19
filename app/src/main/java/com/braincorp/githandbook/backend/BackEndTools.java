package com.braincorp.githandbook.backend;

import android.content.Context;

import java.util.ArrayList;

/**
 * Back end tools class
 * Created by ALan Camargo - December 2016
 */
public class BackEndTools
{

    /**
     * Converts a resource ID to its key
     * @param context - the context
     * @param resId - the resource ID
     * @return key
     */
    public static String convertResourceIdToStringKey(Context context, int resId)
    {
        return context.getResources().getResourceName(resId);
    }

    /**
     * Converts a string
     * @param context - the context
     * @param str - the string to be converted
     * @return resource key
     */
    public static int getStringResourceKey(Context context, String str)
    {
        return context.getResources().getIdentifier(str, "string",
                                                    context.getPackageName());
    }

    //public static ArrayList<String>

}
