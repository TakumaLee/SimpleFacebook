package com.github.takumalee.simplefacebook.Builder;

import android.os.Bundle;

import com.github.takumalee.simplefacebook.entities.Fields;
import com.github.takumalee.simplefacebook.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Nijugon on 2015/6/13.
 */
public class FieldsBuilder {
    private ArrayList<Fields> fields;

    public FieldsBuilder() {

    }

    public Bundle buildReadMeByBundle(ArrayList<Fields> fields) {
        String stringchain = "";
        for (Fields f : fields) {
            if (f == null) {
                continue;
            }
            stringchain.concat(f.toString()).concat(",");
        }
        String result = (String) stringchain.subSequence(0, stringchain.indexOf(stringchain.length() - 1));
        Utils.instance.logClaz(this, "result:" + result);
        Bundle parameters = new Bundle();
        parameters.putString("fields", result);
        return parameters;
    }
}
