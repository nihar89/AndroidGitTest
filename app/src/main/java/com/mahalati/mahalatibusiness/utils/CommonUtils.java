package com.mahalati.mahalatibusiness.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mahalati.mahalatibusiness.ActivitySplash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by Mahlati on 02/11/16.
 */

public class CommonUtils {

    public static final String SF_LANGUAGE = "SF_LANGUAGE";
    public static final String SF_USER_TYPE = "SF_USER_TYPE";
    public static final String SF_USER_ID = "SF_USER_ID";
    public static final String SF_DELIVERY_CONFIRM = "SF_DELIVERY_CONFIRM";
    public static final String ORDER_ID = "order_Id";
    public static String str_notification = "str_no";

    public static final String SF_NOTIFICATION = "SF_NOTIFICATION";

    public static final String SHOP_NAME = "SHOP_NAME";

    public static final String SF_SHOP_MY_PROFILE = "SF_SHOP_MY_PROFILE";
    public static final String SF_BRANCH_MY_PROFILE = "SF_BRANCH_MY_PROFILE";
    public static final String SF_SHIP_DELI_PROFILE = "SF_SHIP_DELI_PROFILE";

    public static final String SF_SESSION_ID = "SF_SESSION_ID";
    public static final String SF_OTP = "SF_OTP";
    public static final String SF_USER_MOBILE = "SF_USER_MOBILE";
    public static final String SF_IS_USER_LOGGED = "SF_IS_USER_LOGGED";
    public static final String SF_DEVICE_TOKEN = "SF_DEVICE_TOKEN";
    public static final String SF_LOCATION_TRACK_START = "SF_LOCATION_TRACK_START";
    public static final String SF_CATEGORY_DATA = "SF_CATEGORY_DATA";
    public static final String SF_CITIES = "SF_CITIES";
    public static final String SF_NOTIFICATION_COUNT = "SF_NOTIFICATION_COUNT";

    public static final String SF_IS_LOCATION_PERMIT_1 = "SF_IS_LOCATION_PERMIT_1";

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    public static String SHARED_PREF_NAME = "SHARED_PREFS_MAHLATIAPP";
    public static String selectedDate;
    public static String selectedColor;

    public static final String SF_RATE_SHOP = "SF_RATE_SHOP";
    public static final String SF_NOT_RECEIVE = "SF_NOT_RECEIVE";

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    /**
     * Sets {@link android.content.SharedPreferences} object.
     *
     * @param p_context activity context
     */
    public static void setSharedPreference(Context p_context) {
        if (Constant.m_sharedPreference == null)
            Constant.m_sharedPreference = p_context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    /**
     * Initializes {@link android.content.SharedPreferences.Editor}.
     *
     * @param p_context activity context
     */
    public static void setEditor(Context p_context) {

        setSharedPreference(p_context);
        if (Constant.m_sharedPrefEditor == null)
            Constant.m_sharedPrefEditor = Constant.m_sharedPreference.edit();
    }


    /**
     * Adds {@link Integer} value of <code>p_spKey</code> into {@link android.content.SharedPreferences}.
     * If value of <code>p_spKey</code> is already set then overrides its previous value.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     */
    public static void setIntSharedPref(Context p_context, String p_spKey, Integer p_value) {
        setEditor(p_context);
        Constant.m_sharedPrefEditor.putInt(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
        Constant.m_sharedPrefEditor.apply();
    }

    public static void updatePreferences1() {
        // TODO Auto-generated method stub
        SharedPreferences.Editor editor = Constant.m_sharedPrefEditor.clear();
        editor.clear();
        editor.commit();
    }

    /**
     * Adds {@link String} value of <code>p_spKey</code> into {@link android.content.SharedPreferences}.
     * If value of <code>p_spKey</code> is already set then overrides its previous value.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     */
    public static void setStringSharedPref(Context p_context, String p_spKey, String p_value) {

        setEditor(p_context);
        Constant.m_sharedPrefEditor.putString(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
    }

    public static void setBooleanPref(Context p_context, String p_spKey, boolean p_value) {
        setEditor(p_context);
        Constant.m_sharedPrefEditor.putBoolean(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
    }


    /**
     * Method to request focus of view
     *
     * @param p_view
     */
    public static void requestFocus(View p_view, Context p_Context) {
        if (p_view.requestFocus()) {
            ((Activity) p_Context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * Gets {@link Integer} value of <code>p_spKey</code> from {@link android.content.SharedPreferences}.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     * @return {@link Integer} value of <code>p_spKey</code>
     */
    public static int getIntSharedPref(Context p_context, String p_spKey, int p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getInt(p_spKey, p_value);
    }


    /**
     * Gets {@link String} value of <code>p_spKey</code> from {@link android.content.SharedPreferences}.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     * @return {@link String} value of <code>p_spKey</code>
     */
    public static String getStringSharedPref(Context p_context, String p_spKey, String p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getString(p_spKey, p_value);
    }

    public static boolean getBooleanSharedPref(Context p_context, String p_spKey, boolean p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getBoolean(p_spKey, p_value);
    }

    /**
     * Method to validate time,date and title
     *
     * @return true false
     */
    public static boolean validateForEmpty(EditText p_editText, Context p_context, TextInputLayout p_layout, String p_string) {
        if (p_editText.getText().toString().trim().isEmpty()) {
            p_layout.setError(p_string);
            requestFocus(p_editText, p_context);
            return false;
        } else {
            p_layout.setErrorEnabled(false);
        }
        return true;
    }


    public static boolean validateForEmpty(EditText p_editText, Context p_context, String p_string) {
        if (p_editText.getText().toString().trim().isEmpty()) {
            p_editText.setError(p_string);
            requestFocus(p_editText, p_context);
            return false;
        } else {
            p_editText.setError(null);
            p_editText.clearFocus();
        }
        return true;
    }

    public static boolean validateForEmptyMobile(EditText p_editText, Context p_context, String p_string) {
        if (p_editText.getText().toString().trim().isEmpty() || p_editText.getText().toString().trim().length() < 8) {
            p_editText.setError(p_string);
            requestFocus(p_editText, p_context);
            return false;
        } else {
            p_editText.setError(null);
        }

        return true;
    }

    public static boolean validateForEmptyPassword(EditText p_editText, Context p_context, String p_string, String mString) {
        if (p_editText.getText().toString().trim().isEmpty()) {
            p_editText.setError(p_string);
            requestFocus(p_editText, p_context);
            return false;
        } else if (p_editText.getText().toString().trim().length() < 6) {
            p_editText.setError(mString);
            requestFocus(p_editText, p_context);
            return false;
        } else {
            p_editText.setError(null);
        }
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static Bitmap handleSamplingAndRotationBitmap(String selectedImage) {
        int MAX_HEIGHT = 400;
        int MAX_WIDTH = 400;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImage, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap img = BitmapFactory.decodeFile(selectedImage, options);
        if (selectedImage != null)
            img = rotateImageIfRequired(img, selectedImage);
        return img;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, String selectedImage) {

        try {
            ExifInterface ei = new ExifInterface(selectedImage);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static String saveImage(Context context, Bitmap bitmap) {
        String filePah = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName();
        if (!new File(filePah).exists()) {
            new File(filePah).mkdirs();
        }

        String filename = File.separator + "mahalati_img" + Calendar.getInstance().getTimeInMillis() + ".jpg";
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePah + filename);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
            return filePah + filename;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePah + File.separator + filename;
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return false; // It's a light color
        } else {
            return true; // It's a dark color
        }
    }

    public static int getContrastColor(int bgColor) {
        return Color.rgb(255 - Color.red(bgColor),
                255 - Color.green(bgColor),
                255 - Color.blue(bgColor));
    }

    public static void triggerRebirth(Context context) {
        Intent intent = new Intent(context, ActivitySplash.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        Runtime.getRuntime().exit(0);
    }

    public static void triggerRebirth1(Context context) {
        Intent intent = new Intent(context, ActivitySplash.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        Runtime.getRuntime().exit(0);
    }

    /*@NonNull
    private Part prepareFilePart(Context context,String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return Part.createFormData(partName, file.getName(), requestFile);
    }*/

    public static JsonArray sortArray(String jsonArrStr, final String key) {
        try {
            JSONArray jsonArr = new JSONArray(jsonArrStr);
            JSONArray sortedJsonArray = new JSONArray();

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArr.length(); i++) {
                jsonValues.add(jsonArr.getJSONObject(i));
            }
            Collections.sort(jsonValues, new Comparator<JSONObject>() {
                final String KEY_NAME = key;

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    } catch (JSONException e) {
                        //do something
                    }

                    return valA.compareTo(valB);
                    //if you want to change the sort order, simply use the following:
                    //return -valA.compareTo(valB);
                }
            });

            for (int i = 0; i < jsonArr.length(); i++) {
                sortedJsonArray.put(jsonValues.get(i));
            }

            JsonParser parser = new JsonParser();
            JsonElement tradeElement = parser.parse(sortedJsonArray.toString());
            return tradeElement.getAsJsonArray();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void putCityList(Context context, ArrayList<String> cities) {
        Gson gson = new Gson();
        String json = gson.toJson(cities);
        setStringSharedPref(context, CommonUtils.SF_CITIES, json);
    }

    public static ArrayList<String> getCityList(Context context) {
        Gson gson = new Gson();
        String json = getStringSharedPref(context, CommonUtils.SF_CITIES, null);
        Type type = new TypeToken<List<String>>() {
        }.getType();
        ArrayList<String> strings = gson.fromJson(json, type);
        return strings;
    }
}
