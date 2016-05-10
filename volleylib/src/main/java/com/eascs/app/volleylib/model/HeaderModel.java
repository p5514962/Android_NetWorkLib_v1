package com.eascs.app.volleylib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class HeaderModel implements Serializable, Parcelable {

    private static final long serialVersionUID = -7953600059271142578L;

    protected int state;

    protected String msg;

    protected String time;


    public HeaderModel() {
    }

    public HeaderModel(int state, String msg) {
        this(state, msg, null);
    }


    public HeaderModel(int state, String msg, String time) {
        super();
        this.state = state;
        this.msg = msg;
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HeaderModel{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(state);
        dest.writeString(msg);
        dest.writeString(time);
    }

    public static final Creator<HeaderModel> CREATOR = new Creator<HeaderModel>() {

        @Override
        public HeaderModel createFromParcel(Parcel source) {
            HeaderModel model = new HeaderModel();
            model.setState(source.readInt());
            model.setMsg(source.readString());
            model.setTime(source.readString());
            return model;
        }

        @Override
        public HeaderModel[] newArray(int size) {
            return new HeaderModel[size];
        }

    };

}
