package com.lptiyu.lp_base.uitls;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

/**
 * 多媒体管理类，主要用于多个音频连续播放
 * Created by 11298 on 2017/6/29.
 */

public class MediaPlayerManager implements MediaPlayer.OnCompletionListener {
    //    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioManager audioManager;
    private AudioManager mgr;
    private Context context;
    private MediaPlayer mediaPlayer;
    private int[] resIds;
    private int index = 0;
    private float leftVolume;
    private float rightVolum;
    private Handler handler;
    private MediaPlayerManager.OnPlayingListener listener;
    private boolean isStartRunSound;

    public MediaPlayerManager(Context context) {
        this.context = context;
        if (mgr == null) {
            mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        if (audioManager == null) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        leftVolume = getVolume();
        rightVolum = leftVolume;
        handler = new Handler(Looper.getMainLooper());
//        onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//            @Override
//            public void onAudioFocusChange(int focusChange) {
//                switch (focusChange) {
//                    case AudioManager.AUDIOFOCUS_GAIN:
//                        // Resume playback or Raise it back to normal
//                        LogUtils.i("获得Audio Focus");
//                        break;
//                    case AudioManager.AUDIOFOCUS_LOSS:
//                        // Stop playback
////                                            am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
////                                            am.abandonAudioFocus(afChangeListener);
//                        LogUtils.i("失去了Audio Focus，并将会持续很长的时间");
//                        break;
//                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
//                        // Pause playback
//                        LogUtils.i("暂时失去Audio Focus，并会很快再次获得");
//                        break;
//                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
//                        // Lower the volume
//                        LogUtils.i("暂时失去Audio Focus，但是可以继续播放，不过要在降低音量");
//                        break;
//                }
//            }
//        };
    }


    public boolean isStartRunSound() {
        return isStartRunSound;
    }

    public void setStartRunSound(boolean startRunSound) {
        isStartRunSound = startRunSound;
    }

    public void setPlayingListener(MediaPlayerManager.OnPlayingListener listener) {
        this.listener = listener;
    }

    public void setSoundResource(int[] resIds) {
        this.resIds = resIds;
        index = 0;
    }

    public float getLeftVolume() {
        return this.leftVolume;
    }

    public void setLeftVolume(float leftVolume) {
        this.leftVolume = leftVolume;
    }

    public float getRightVolum() {
        return this.rightVolum;
    }

    public void setRightVolum(float rightVolum) {
        this.rightVolum = rightVolum;
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        if (index < resIds.length) {
            mediaPlayer = MediaPlayer.create(context, resIds[index++]);
            if (listener != null) {
                listener.onPlaying(resIds.length - index);
            }
        } else {
            if (listener != null) {
                listener.onComplete();
            }
            return;
        }
        if (mediaPlayer == null) {
            if (listener != null) {
                listener.onComplete();
            }
            return;
        }
        mediaPlayer.setVolume(leftVolume, rightVolum);
        controlSystemMusic(mediaPlayer.getDuration());
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (isStartRunSound) {
            if (handler == null) {
                if (listener != null) {
                    listener.onComplete();
                }
                return;
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    play();
                }
            }, 300);
        } else {
            play();
        }
    }

    /**
     * 获取音量大小
     *
     * @return
     */
    private float getVolume() {
        //设置音量
        if (mgr == null) {
            return 0f;
        }
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return streamVolumeCurrent * 10 / streamVolumeMax;
    }

    /**
     * 播放音效时如果正在播放音乐，则降低音乐的音量，音效播放完毕后再恢复音量
     *
     * @param soundDuration
     */
    private void controlSystemMusic(int soundDuration) {
//        if (audioManager == null) {
//            return;
//        }
//        boolean vIsActive = audioManager.isMusicActive();
//        if (vIsActive) {
//            int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
//                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
//            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                LogUtils.i("申请Audio Focus成功");
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (audioManager != null) {
//                            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//                        }
//                    }
//                }, soundDuration);
//            } else if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
//                LogUtils.i("申请Audio Focus失败");
//            } else {
//                LogUtils.i("申请Audio Focus结果未知");
//            }
//        }
    }

    /**
     * 释放对音频管理的控制
     */
    public void loseAudioControll() {
//        if (audioManager != null && onAudioFocusChangeListener != null) {
//            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//        }
//        onAudioFocusChangeListener = null;
        audioManager = null;
    }

    public interface OnPlayingListener {
        void onComplete();

        void onPlaying(int index);
    }
}
