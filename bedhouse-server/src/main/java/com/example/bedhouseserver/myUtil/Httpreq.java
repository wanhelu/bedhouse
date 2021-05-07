package com.example.bedhouseserver.myUtil;

import javax.servlet.http.HttpServletRequest;

public class Httpreq {
    public static String getString(HttpServletRequest req, String name){
        String s=req.getParameter(name);
        if(s!=null&&s.equals("null"))s=null;
        return s;
    }
}
