package com.rasmusvk;

import com.rasmusvk.Rute;

/**
 * Created by Rasmusvk on 30.03.2017.
 */
public class SortRute extends Rute {

    public SortRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    public char tilTegn(){
        return '#';
    }

    public char utskrift() {
        return '#';
    }
}
