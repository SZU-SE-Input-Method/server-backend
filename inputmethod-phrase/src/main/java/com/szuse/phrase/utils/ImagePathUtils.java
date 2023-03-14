package com.szuse.phrase.utils;

import org.springframework.boot.system.ApplicationHome;

public class ImagePathUtils {
    private static final ApplicationHome applicationHome= new ApplicationHome(ImagePathUtils.class);

    private ImagePathUtils(){}
    public static String getPath(){
        return applicationHome.getSource().getParentFile().toString();
    }
}
