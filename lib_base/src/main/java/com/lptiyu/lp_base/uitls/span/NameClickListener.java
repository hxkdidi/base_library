package com.lptiyu.lp_base.uitls.span;

import android.content.Context;
import android.text.SpannableString;
import android.widget.Toast;


public class NameClickListener implements ISpanClick {

    private SpannableString userName;
    private String userId;
    private Context context;

    public NameClickListener(Context context, SpannableString name, String userid) {
        this.context = context;
        this.userName = name;
        this.userId = userid;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(context, userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
    }
}
