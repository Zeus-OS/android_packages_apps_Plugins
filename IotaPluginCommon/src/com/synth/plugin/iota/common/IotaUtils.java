/*
 * Copyright (C) 2020 The Potato Open Sauce Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.synth.plugin.iota.common;

import android.annotation.ColorInt;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.content.pm.PackageManager.NameNotFoundException;
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
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.os.Message;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.potatoproject.plugin.volume.common.*;

import java.text.NumberFormat;
import java.util.Objects;

public class IotaUtils {

      public static final String TAG = "IotaUtils";

      public static int getVolumeAlignment(Context sysuiContext) {
          return Settings.System.getIntForUser(sysuiContext.getContentResolver(), Settings.System.VOLUME_PANEL_ALIGNMENT, 1, UserHandle.USER_CURRENT);
      }

      public static boolean getHideRinger(Context sysuiContext) {
          return Settings.System.getIntForUser(sysuiContext.getContentResolver(), Settings.System.SYNTHOS_HIDE_RINGER_VOLUMEPANEL, 1, UserHandle.USER_CURRENT) == 1;
      }

      public static boolean getHideExtended(Context sysuiContext) {
          return Settings.System.getIntForUser(sysuiContext.getContentResolver(), Settings.System.SYNTHOS_HIDE_EXTENDED_VOLUMEPANEL, 0, UserHandle.USER_CURRENT) == 1;
      }

      public static int getDimensionFromVariable(Context sysuiContext, String var) {
          final String variable  =  var;
          int intVariable = Settings.System.getIntForUser(sysuiContext.getContentResolver(),
                  variable, 20, UserHandle.USER_CURRENT);

          switch (intVariable) {
              case 0:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_0"));
              case 1:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_1"));
              case 2:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_2"));
              case 3:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_3"));
              case 4:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_4"));
              case 5:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_5"));
              case 6:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_6"));
              case 7:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_7"));
              case 8:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_8"));
              case 9:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_9"));
              case 10:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_10"));
              case 11:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_11"));
              case 12:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_12"));
              case 13:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_13"));
              case 14:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_14"));
              case 15:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_15"));
              case 16:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_16"));
              case 17:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_17"));
              case 18:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_18"));
              case 19:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_19"));
              case 20:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_20"));
              case 21:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_21"));
              case 22:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_22"));
              case 23:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_23"));
              case 24:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_24"));
              case 25:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_25"));
              case 26:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_26"));
              case 27:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_27"));
              case 28:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_28"));
              case 29:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_29"));
              case 30:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_30"));
              case 31:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_31"));
              case 32:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_32"));
              case 33:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_33"));
              case 34:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_34"));
              case 35:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_35"));
              case 36:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_36"));
              case 37:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_37"));
              case 38:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_38"));
              case 39:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_39"));
              case 40:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_40"));
              case 41:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_41"));
              case 42:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_42"));
              case 43:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_43"));
              case 44:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_44"));
              case 45:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_45"));
              case 46:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_46"));
              case 47:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_47"));
              case 48:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_48"));
              case 49:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_49"));
              case 50:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_50"));
              case 51:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_51"));
              case 52:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_52"));
              case 53:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_53"));
              case 54:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_54"));
              case 55:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_55"));
              case 56:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_56"));
              case 57:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_57"));
              case 58:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_58"));
              case 59:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_59"));
              case 60:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_60"));
              case 61:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_61"));
              case 62:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_62"));
              case 63:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_63"));
              case 64:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_64"));
              case 65:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_65"));
              case 66:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_66"));
              case 67:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_67"));
              case 68:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_68"));
              case 69:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_69"));
              case 70:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_70"));
              case 71:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_71"));
              case 72:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_72"));
              case 73:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_73"));
              case 74:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_74"));
              case 75:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_75"));
              case 76:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_76"));
              case 77:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_77"));
              case 78:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_78"));
              case 79:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_79"));
              case 80:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_80"));
              case 81:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_81"));
              case 82:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_82"));
              case 83:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_83"));
              case 84:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_84"));
              case 85:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_85"));
              case 86:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_86"));
              case 87:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_87"));
              case 88:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_88"));
              case 89:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_89"));
              case 90:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_90"));
              case 91:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_91"));
              case 92:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_92"));
              case 93:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_93"));
              case 94:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_94"));
              case 95:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_95"));
              case 96:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_96"));
              case 97:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_97"));
              case 98:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_98"));
              case 99:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_99"));
              case 100:
                  return (int) sysuiContext.getResources().getDimension(getDimension(sysuiContext, "volume_panel_padding_100"));
          }

          return 0;
      }

      public static int getDimension(Context context, String resName) {
          int resId = 0;
          try {
              Resources res = context.getPackageManager().getResourcesForApplication("com.android.systemui");
              resId = res.getIdentifier(resName, "dimen", "com.android.systemui");
          } catch (NameNotFoundException e) {
              e.printStackTrace();
          }
          return resId;
      }

      public static void setPaddingLocation(Context sysuiContext, boolean leftVolume, boolean isLandscape, boolean isHorizontal, WindowManager.LayoutParams window, ViewGroup dialog, ViewGroup mediaCard) {

          boolean mLeftVolumeRocker = leftVolume;

          try {
              WindowManager.LayoutParams lp = window;
              switch (getVolumeAlignment(sysuiContext)) {
                  case 0:
                      lp.gravity = (isHorizontal ? Gravity.CENTER_HORIZONTAL : (mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT)) | Gravity.TOP;
                      break;
                  case 1:
                  default:
                      lp.gravity = (isHorizontal ? Gravity.CENTER_HORIZONTAL : (mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT)) | Gravity.CENTER_VERTICAL;
                      break;
                  case 2:
                      lp.gravity = (isHorizontal ? Gravity.CENTER_HORIZONTAL : (mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT)) | Gravity.BOTTOM;
                      break;
              }
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
          }

          try {
              ViewGroup mDialogView = dialog;
              LinearLayout.LayoutParams dlp = (LinearLayout.LayoutParams) mDialogView.getLayoutParams();
              switch (getVolumeAlignment(sysuiContext)) {
                  case 0:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.TOP;
                      break;
                  case 1:
                  default:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                      break;
                  case 2:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.BOTTOM;
                      break;
              }
              dlp.setMargins(0,
                            (getDimensionFromVariable(sysuiContext, Settings.System.SYNTHOS_VOLUME_PANEL_PADDING_TOP) * (isLandscape ? 1 : 3)),
                            0,
                            (getDimensionFromVariable(sysuiContext, Settings.System.SYNTHOS_VOLUME_PANEL_PADDING_BOTTOM) * (isLandscape ? 1 : 3)));
              mDialogView.setLayoutParams(dlp);
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
          }

          try {
              ViewGroup mDialogView = dialog;
              FrameLayout.LayoutParams dlp = (FrameLayout.LayoutParams) mDialogView.getLayoutParams();
              switch (getVolumeAlignment(sysuiContext)) {
                  case 0:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.TOP;
                      break;
                  case 1:
                  default:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                      break;
                  case 2:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.BOTTOM;
                      break;
              }
              dlp.setMargins(0,
                            (getDimensionFromVariable(sysuiContext, Settings.System.SYNTHOS_VOLUME_PANEL_PADDING_TOP) * (isLandscape ? 1 : 3)),
                            0,
                            (getDimensionFromVariable(sysuiContext, Settings.System.SYNTHOS_VOLUME_PANEL_PADDING_BOTTOM) * (isLandscape ? 1 : 3)));
              mDialogView.setLayoutParams(dlp);
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
          }

          try {
              LinearLayout.LayoutParams dlp = (LinearLayout.LayoutParams) mediaCard.getLayoutParams();
              switch (getVolumeAlignment(sysuiContext)) {
                  case 0:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.TOP;
                      break;
                  case 1:
                  default:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                      break;
                  case 2:
                      dlp.gravity = mLeftVolumeRocker ? Gravity.LEFT : Gravity.RIGHT | Gravity.BOTTOM;
                      break;
              }
              mediaCard.setLayoutParams(dlp);
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
          }

      }

      public static void setBackgroud(Context context, View[] views, Drawable[] defaultDrawables, String[] defaultDrawablesNames) {
          boolean roundSystem = Settings.System.getIntForUser(context.getContentResolver(), Settings.System.VOLUME_DIALOG_ROUNDED_SYSTEM, 1, UserHandle.USER_CURRENT) == 1;
          if (roundSystem) {
              setDefaultBackground(context, views, defaultDrawables);
          } else {
              setRoundedBackground(context, views, defaultDrawables, defaultDrawablesNames);
          }
      }

      public static void setDefaultBackground(Context context, View[] views, Drawable[] defaultDrawables) {
          for (int i = 0; i < views.length; i++) {
              views[i].setBackground(defaultDrawables[i]);
          }
      }

      public static void setRoundedBackground(Context context, View[] views, Drawable[] defaultDrawables, String[] defaultDrawablesNames) {
          float roundValue = (float) Settings.System.getIntForUser(context.getContentResolver(), Settings.System.VOLUME_DIALOG_ROUNDED_VALUE, 36, UserHandle.USER_CURRENT);
          boolean gradientStroke = Settings.System.getIntForUser(context.getContentResolver(), Settings.System.VOLUME_DIALOG_GRADIENT_STROKE, 1, UserHandle.USER_CURRENT) == 1;
          boolean roundDefault = Settings.System.getIntForUser(context.getContentResolver(), Settings.System.VOLUME_DIALOG_ROUNDED_SYSTEM, 1, UserHandle.USER_CURRENT) == 1;
          TypedValue tValue = new TypedValue();
          context.getTheme().resolveAttribute(android.R.attr.dialogCornerRadius, tValue, true);
          float roundSystem = (float) tValue.data;
          float lastRound = roundValue;
          int colorDrawable;

          try {
              for (int i = 0; i < views.length; i++) {
                  if (defaultDrawablesNames[i].equals("rounded_bg_full")) {
                      TypedValue value = new TypedValue();
                      context.getTheme().resolveAttribute(android.R.attr.colorBackgroundFloating, value, true);
                      colorDrawable = value.data;
                      float[] round = {lastRound, lastRound, lastRound, lastRound, lastRound, lastRound, lastRound, lastRound};
                      views[i].setBackground(createGradientStrokeDrawable(context, colorDrawable, round, gradientStroke));
                  } else {
                      TypedValue value = new TypedValue();
                      context.getTheme().resolveAttribute(android.R.attr.panelColorBackground, value, true);
                      colorDrawable = value.data;
                      float[] round = {0, 0, 0, 0, lastRound, lastRound, lastRound, lastRound};
                      views[i].setBackground(createGradientStrokeDrawable(context, colorDrawable, round, gradientStroke));
                  }
              }
          } catch (Exception e) {
              Log.d(TAG, e.getMessage());
          }
      }

      public static Drawable createGradientStrokeDrawable(Context context, int color, float[] round, boolean stroke) {
          if (stroke) {
              return createGradientStrokeDrawable(context, color, round);
          } else {
              return createRoundShapeDrawable(context, color, round);
          }
      }

      public static LayerDrawable createGradientStrokeDrawable(Context context, int color, float[] round) {
          GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                                                                  new int[] {context.getResources().getColor(com.android.internal.R.color.accent_color_synth)
                                                                            ,context.getResources().getColor(com.android.internal.R.color.accent_color_synth)});
          gradientDrawable.setCornerRadii(round);

          LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] {gradientDrawable, createRoundShapeDrawable(context, color, round)});
          layerDrawable.setLayerInset(1, 2, 2, 2, 2);
          return layerDrawable;
      }

      public static ShapeDrawable createRoundShapeDrawable(Context context, int color, float[] round) {
          RoundRectShape rrS = new RoundRectShape(round, null, null);
          ShapeDrawable shapeDrawable = new ShapeDrawable(rrS);
          shapeDrawable.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_ATOP));
          return shapeDrawable;
      }

      public static void hideThings(Context sysuiContext, View mRinger) {
          hideThings(sysuiContext, mRinger, null, null);
      }

      public static void hideThings(Context sysuiContext, View mRinger, View mExpandRowsView) {
          hideThings(sysuiContext, mRinger, mExpandRowsView, null);
      }

      public static void hideThings(Context sysuiContext, View mRinger, View mExpandRowsView, View mBackgroundThings) {
          if (mRinger != null && getHideRinger(sysuiContext)) {
              mRinger.setVisibility(View.GONE);
          } else if (mRinger != null && !getHideRinger(sysuiContext)) {
              mRinger.setVisibility(View.VISIBLE);
          }
          if (mExpandRowsView != null && getHideExtended(sysuiContext)) {
              mExpandRowsView.setVisibility(View.GONE);
          } else if (mExpandRowsView != null && !getHideExtended(sysuiContext)) {
              mExpandRowsView.setVisibility(View.VISIBLE);
          }
          if (mBackgroundThings != null && getHideRinger(sysuiContext) && getHideExtended(sysuiContext)) {
              mBackgroundThings.setVisibility(View.GONE);
          } else if (mBackgroundThings != null && (!getHideRinger(sysuiContext) || !getHideExtended(sysuiContext)))  {
              mBackgroundThings.setVisibility(View.VISIBLE);
          }
      }
}
