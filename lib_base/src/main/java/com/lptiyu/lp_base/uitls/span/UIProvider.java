package com.lptiyu.lp_base.uitls.span;


import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UIProvider {
    private static final String TAG = UIProvider.class.getSimpleName();

    /**
     * the global EaseUI instance
     */
    private static UIProvider instance = null;

    private boolean showProgress = false;

    /**
     * 用来记录注册了eventlistener的foreground Activity
     */
    private List<Activity> activityList = Collections.synchronizedList(new ArrayList<Activity>());

    public void pushActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(0,activity);
        }
    }

    public void popActivity(Activity activity){
        activityList.remove(activity);
    }


    private UIProvider(){}

    /**
     * 获取EaseUI单实例对象
     * @return
     */
    public synchronized static UIProvider getInstance(){
        if(instance == null){
            instance = new UIProvider();
        }
        return instance;
    }

    /**
     * 表情信息提供者
     *
     */
    public interface EmojiconInfoProvider {
        /**
         * 根据唯一识别号返回此表情内容
         * @param emojiconIdentityCode
         * @return
         */
        Emojicon getEmojiconInfo(String emojiconIdentityCode);

        /**
         * 获取文字表情的映射Map,map的key为表情的emoji文本内容，value为对应的图片资源id或者本地路径(不能为网络地址)
         * @return
         */
        Map<String, Object> getTextEmojiconMapping();
    }

    private EmojiconInfoProvider emojiconInfoProvider;

    /**
     * 获取表情提供者
     * @return
     */
    public EmojiconInfoProvider getEmojiconInfoProvider(){
        return emojiconInfoProvider;
    }

    /**
     * 设置表情信息提供者
     * @param emojiconInfoProvider
     */
    public void setEmojiconInfoProvider(EmojiconInfoProvider emojiconInfoProvider){
        this.emojiconInfoProvider = emojiconInfoProvider;
    }

}