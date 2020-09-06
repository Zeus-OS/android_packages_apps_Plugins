/*
* Copyright (C) 2020 SynthOS
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.synth.plugin.iota.common;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.UserHandle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.synth.plugin.iota.common.R;

import com.android.internal.graphics.palette.Palette;
import com.android.internal.graphics.ColorUtils;

public class SynthMusic extends RelativeLayout implements Palette.PaletteAsyncListener {
    private static final boolean DEBUG = true;
    private static final String TAG = "SynthMusic";

    public Context mContext;
    private MediaMetadata mMediaMetadata;
    public MediaController mMediaController;
    private final Handler mHandler = new Handler();

    public CharSequence mMediaTitle;
    public CharSequence mMediaArtist;
    public Drawable mMediaArtwork;
    public boolean mMediaIsVisible;

    public TextView mTitle;
    public TextView mArtist;
    public ImageView mArtwork;
    public int shadow;
    public int colorArtwork;
    public int colorTextIcons;

    private ImageButton mPrevious;
    private ImageButton mPlayPause;
    private ImageButton mNext;

    private SynthMusic mBackground;

    private final MediaController.Callback mMediaListener = new MediaController.Callback() {
        @Override
        public void onPlaybackStateChanged(PlaybackState state) {
            super.onPlaybackStateChanged(state);
            dispatchUpdateMediaMetaData();
        }

        @Override
        public void onMetadataChanged(MediaMetadata metadata) {
            super.onMetadataChanged(metadata);
            mMediaMetadata = metadata;
            dispatchUpdateMediaMetaData();
        }
    };

    public SynthMusic(Context context) {
        this(context, null);
    }

    public SynthMusic(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SynthMusic(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SynthMusic(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (DEBUG) Log.d(TAG, "New Instance");
    }

    public void initDependencies(MediaController mediaController, Context context) {
        try {
            mContext = context;
            mMediaController = mediaController;
            mMediaMetadata = mMediaController.getMetadata();
            mMediaController.registerCallback(mMediaListener);
            dispatchUpdateMediaMetaData();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void resize(Context context, int dialogHeight, int fullWidth, boolean vertical, boolean horizontal) {

        boolean full = Settings.System.getIntForUser(context.getContentResolver(), Settings.System.SYNTHOS_MEDIA_CARD_VOLUME_PANEL_FULL_WIDTH, 0, UserHandle.USER_CURRENT) == 1;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();

        int fullWidthDP = ((int) displayMetrics.widthPixels) - fullWidth - 96;
        int marginVertical = vertical ? 48 : 0;
        int marginHorizontal = horizontal ? 48 : 0;

        lp.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical);

        try {
            if (full) {
                lp.height = dialogHeight;
                lp.width = fullWidthDP;
                setLayoutParams(lp);
            } else {
                lp.height = dialogHeight;
                lp.width = lp.height;
                setLayoutParams(lp);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        setClipToOutline(true);

    }

    private int getMediaControllerPlaybackState(MediaController controller) {
        if (controller != null) {
            final PlaybackState playbackState = controller.getPlaybackState();
            if (playbackState != null) {
                return playbackState.getState();
            }
        }
        return PlaybackState.STATE_NONE;
    }

    private boolean getPlaybackStateIsEqual(@PlaybackState.State int state) {
        if (mMediaController != null) {
            return state == getMediaControllerPlaybackState(mMediaController);
        } else {
            return false;
        }
    }

    private void dispatchUpdateMediaMetaData() {
        @PlaybackState.State int state = getMediaControllerPlaybackState(mMediaController);
        onMetadataOrStateChanged(mMediaMetadata, state, mMediaController);
    }

    public void onMetadataOrStateChanged(MediaMetadata metadata, @PlaybackState.State int state, MediaController mediaController) {

          CharSequence title = metadata == null ? null : metadata.getText(
                  MediaMetadata.METADATA_KEY_TITLE);
          CharSequence artist = metadata == null ? null : metadata.getText(
                  MediaMetadata.METADATA_KEY_ARTIST);
          Bitmap artwork = metadata == null ? null : metadata.getBitmap(
                  MediaMetadata.METADATA_KEY_ALBUM_ART);
          Drawable d = new BitmapDrawable(mContext.getResources(), artwork);

          mMediaController = mediaController;
          mMediaTitle = title;
          mMediaArtist = artist;
          mMediaArtwork = d;

          update();
    }

    public void update() {
        updateObjects();
        updateButtons();
        updateViews();
        updateIconPlayPause();
    }

    public void updateIconPlayPause() {
        try {
            if ( !(getPlaybackStateIsEqual(PlaybackState.STATE_PLAYING)) ) {
                mPlayPause.setImageResource(R.drawable.ic_play_arrow_white);
            } else {
                mPlayPause.setImageResource(R.drawable.ic_pause_white);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            mPrevious.setVisibility(View.GONE);
            mPlayPause.setVisibility(View.GONE);
            mNext.setVisibility(View.GONE);
        }
    }

    public void updateObjects() {
        if (mTitle == null || mArtist == null || mArtwork == null || mPrevious == null || mPlayPause == null || mNext == null) {
            mArtwork = findViewById(R.id.artwork);
            mTitle = (TextView) findViewById(R.id.title);
            mArtist = (TextView) findViewById(R.id.artist);
            mPrevious = findViewById(R.id.button_previous);
            mPlayPause = findViewById(R.id.button_play_pause);
            mNext = findViewById(R.id.button_next);
        }
    }

    public void updateButtons() {
        mPrevious.setOnClickListener(v -> {
            skipTrackPrevious();
        });

        mPlayPause.setOnClickListener(v -> {
            playPauseTrack();
        });

        mNext.setOnClickListener(v -> {
            skipTrackNext();
        });
    }

    public void updateViews() {

        boolean show = Settings.System.getIntForUser(mContext.getContentResolver(),
                Settings.System.SYNTHOS_MUSIC_VOLUME_PANEL_TEXT, 1, UserHandle.USER_CURRENT) != 0;

        try {
            if ((mMediaController != null || mMediaMetadata != null) && mMediaTitle != null && mMediaArtist != null && mMediaArtwork != null) {
                mTitle.setText(mMediaTitle.toString());
                mTitle.setSelected(true);
                mArtist.setText(mMediaArtist.toString());
                mArtist.setSelected(true);

                mArtwork.setImageDrawable(mMediaArtwork);

                if (mMediaMetadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART) != null) {
                    Palette.generateAsync((mMediaMetadata.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART)), this);
                }

                setVisibility(show ? View.VISIBLE : View.GONE);

            } else {
                setVisibility(View.GONE);
            }
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
              setVisibility(View.GONE);
          }

        mArtwork.setClipToOutline(true);

   }

   @Override
   public void onGenerated(Palette palette) {

        shadow = 115;
        colorArtwork = Color.BLACK;
        colorTextIcons = Color.WHITE;

        colorTextIcons = palette.getLightVibrantColor(colorTextIcons);
        colorArtwork = ColorUtils.setAlphaComponent(palette.getDarkVibrantColor(colorArtwork), shadow);

        mArtwork.setColorFilter(colorArtwork, Mode.SRC_ATOP);
        mTitle.setTextColor(colorTextIcons);
        mArtist.setTextColor(colorTextIcons);
        mPrevious.setColorFilter(colorTextIcons);
        mPlayPause.setColorFilter(colorTextIcons);
        mNext.setColorFilter(colorTextIcons);
   }

    public void skipTrackNext() {
        if (PlaybackState.STATE_PLAYING ==
                getMediaControllerPlaybackState(mMediaController)) {
            mMediaController.getTransportControls().skipToNext();
        }
    }

    public void skipTrackPrevious() {
        if (PlaybackState.STATE_PLAYING ==
                getMediaControllerPlaybackState(mMediaController)) {
            mMediaController.getTransportControls().skipToPrevious();
        }
    }

    public void playPauseTrack() {
        if (PlaybackState.STATE_PLAYING ==
                getMediaControllerPlaybackState(mMediaController)) {
            mMediaController.getTransportControls().pause();
        } else {
            mMediaController.getTransportControls().play();
        }
    }

}
