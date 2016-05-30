package com.eascs.app.network.https;

import java.io.InputStream;

/**
 * Created by KevinHo on 2016/5/29 0029.
 */
public class BksInfo {
    InputStream inputStream;
    String bksPwd;

    public BksInfo(InputStream inputStream,String bksPwd){
        this.inputStream = inputStream;
        this.bksPwd = bksPwd;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getBksPwd() {
        return bksPwd;
    }

}
