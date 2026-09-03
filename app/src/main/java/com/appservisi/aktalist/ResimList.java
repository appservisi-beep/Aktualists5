package com.appservisi.aktalist;

import java.util.ArrayList;

public class ResimList extends ArrayList {
    String resimkonum;
    String resimid;

    public ResimList( String resimkonum, String resimid) {

        this.resimkonum = resimkonum;
        this.resimid = resimid;
    }

    public String getResimkonum() {
        return resimkonum;
    }

    public String getResimid() {
        return resimid;
    }
}
