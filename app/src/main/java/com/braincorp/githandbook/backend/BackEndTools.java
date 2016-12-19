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
    private static String convertResourceIdToStringKey(Context context, int resId)
    {
        return context.getResources().getResourceName(resId);
    }

    //public static ArrayList<String>

}
