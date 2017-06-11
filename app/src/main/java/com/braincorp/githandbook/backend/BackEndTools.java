package com.braincorp.githandbook.backend;

import android.content.Context;

/**
 * Back end tools class
 * Created by ALan Camargo - December 2016
 */
public class BackEndTools {

    /**
     * Converts a string
     * @param context - the context
     * @param str - the string to be converted
     * @return resource key
     */
    public static int getStringResourceKey(Context context, String str) {
        return context.getResources().getIdentifier(str, "string",
                                                    context.getPackageName());
    }

}
