package com.example.experiment2;

import android.widget.TextView;

public class ViewHolder {
    public TextView Tid;
    public TextView Tname;
    public TextView Tage;
    public TextView Tlength;

    ViewHolder(TextView Tid, TextView Tname, TextView Tage, TextView Tlength) {
        this.Tid = Tid;
        this.Tname = Tname;
        this.Tage = Tage;
        this.Tlength = Tlength;
    }

    public TextView getTid() {
        return Tid;
    }

    public void setTid(TextView tid) {
        Tid = tid;
    }

    public TextView getTname() {
        return Tname;
    }

    public void setTname(TextView tname) {
        Tname = tname;
    }

    public TextView getTage() {
        return Tage;
    }

    public void setTage(TextView tage) {
        Tage = tage;
    }

    public TextView getTlength() {
        return Tlength;
    }

    public void setTlength(TextView tlength) {
        Tlength = tlength;
    }


}
