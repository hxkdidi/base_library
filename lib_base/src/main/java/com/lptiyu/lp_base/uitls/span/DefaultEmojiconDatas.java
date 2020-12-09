package com.lptiyu.lp_base.uitls.span;


import com.lptiyu.lp_base.R;

public class DefaultEmojiconDatas {

    private static String[] emojis = new String[]{
            SmileUtils.ee_36,

    };

    private static int[] icons = new int[]{
            R.drawable.choose_on,
    };


    private static final Emojicon[] DATA = createData();

    private static Emojicon[] createData(){
        Emojicon[] datas = new Emojicon[icons.length];
        for(int i = 0; i < icons.length; i++){
            datas[i] = new Emojicon(icons[i], emojis[i], Emojicon.Type.NORMAL);
        }
        return datas;
    }

    public static Emojicon[] getData(){
        return DATA;
    }
}