package com.kcv.kcv;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class signinpage extends Activity implements View.OnClickListener {
    Spinner state;
    String[] SPINNERVALUES = { "States","Andaman and Nicobar Islands ","Andra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Chandigarh","Delhi","Daman and Diu","Dadra and Nagar Haveli","Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala","Lakshadweep", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa", "Pondicherry","Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telagana", "Tripura", "Uttaranchal", "Uttar Pradesh", "West Bengal"};
    String spinnervalue;
    private EditText firstnameEdittext, lastnameEdittext, emailEdittext, passEdittext, passAgainEdittext, birthdayEdittext, usernameEdittext, mobileno,age,address,taluka,district,pincode,village;
    private RadioGroup genderRadioGroup;
    private Button registerButton;
    private DatePickerDialog datePickerDialog;
    public static final String EXTRA_TEXT = "com.example.kcv.signinpage.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.kcv.signinpage.EXTRA_NUMBER";
    private Button btn;
    private ImageView imageview;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    private AwesomeValidation awesomeValidation;
    private Bitmap bitmap;
    private  String UPLOAD_URL ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinpage);
        state =(Spinner)findViewById(R.id.state);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        UPLOAD_URL = getString(R.string.url)+ "/PhotoUpload/upload.php";


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.layout,SPINNERVALUES );

        spinnerArrayAdapter.setDropDownViewResource(R.layout.layout);


        state.setAdapter(spinnerArrayAdapter);
        spinnervalue = state.getSelectedItem().toString();

        TextView textView = findViewById(R.id.textView2);

        String text = "Already have an account? Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(signinpage.this, Login.class);
                startActivity(intent);
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };


        ss.setSpan(clickableSpan1, 25, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        bindview();




        setViewActions();


        prepareDatePickerDialog();
        requestMultiplePermissions();

        btn = (Button) findViewById(R.id.btn);
        imageview = (ImageView) findViewById(R.id.iv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
    }

    private void bindview() {
        firstnameEdittext = (EditText) findViewById(R.id.firstname_edittext);
        lastnameEdittext = (EditText) findViewById(R.id.lastname_edittext);
        emailEdittext = (EditText) findViewById(R.id.email_edittext);
        usernameEdittext = (EditText) findViewById(R.id.username_edittext);
        passEdittext = (EditText) findViewById(R.id.password_edittext);
        mobileno = (EditText) findViewById(R.id.mobileno);
        passAgainEdittext = (EditText) findViewById(R.id.password_again_edittext);
        birthdayEdittext = (EditText) findViewById(R.id.birthday_edittext);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radiogroup);
        registerButton = (Button) findViewById(R.id.register_button);
        mobileno=(EditText)findViewById(R.id.mobileno);
        age = (EditText)findViewById(R.id.age);
        address=(EditText)findViewById(R.id.address);
        taluka=(EditText)findViewById(R.id.taluka);
        district=(EditText)findViewById(R.id.district);
        pincode=(EditText)findViewById(R.id.pincode);
        village =(EditText)findViewById(R.id.village);
    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(signinpage.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(signinpage.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(bitmap);
            saveImage(bitmap);
            Toast.makeText(signinpage.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    public String uploadImage(){

        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(signinpage.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bmp = params[0];
                String uploadImage = getStringImage(bmp);

                HashMap<String,String> data = new HashMap<>();

                data.put("image", uploadImage);
                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }
        if(bitmap != null){
            UploadImage ui = new UploadImage();
            try {
                return ui.execute(bitmap).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            } catch (InterruptedException e) {

                e.printStackTrace();
                return null;
            }
        }else{
            return "Not Selected";
        }


    }
    public String getStringImage(Bitmap bmp){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    /**
                     * Method called whenever Android asks the application to inform the user of the need for the
                     * requested permissions. The request process won't continue until the token is properly used
                     *
                     * @param permissions The permissions that has been requested. Collections of values found in
                     *                    {@link Manifest.permission}
                     * @param token       Token used to continue or cancel the permission request process. The permission
                     */
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();

                    }




                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }





    private void setViewActions() {
        birthdayEdittext.setOnClickListener((View.OnClickListener) this);
        registerButton.setOnClickListener(this);
    }

    private void prepareDatePickerDialog() {

        Calendar calendar = Calendar.getInstance();


        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                birthdayEdittext.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) , calendar.get(Calendar.DAY_OF_MONTH));
    }


    private void showToastWithFormValues() {


        final String firstname = firstnameEdittext.getText().toString();
        final String lastname = lastnameEdittext.getText().toString();
        final String email = emailEdittext.getText().toString();
        String pass = passEdittext.getText().toString();
        String username = usernameEdittext.getText().toString();
        String passAgain = passAgainEdittext.getText().toString();
        final String birthday = birthdayEdittext.getText().toString();
        final String age1 = age.getText().toString();
        final String mobileno1 = mobileno.getText().toString();
        final String address1= address.getText().toString();
        final String taluka1 = taluka.getText().toString();
        final String district1= district.getText().toString();
        final String pincode1 = pincode.getText().toString();


        RadioButton selectedRadioButton = (RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender = selectedRadioButton == null ? "" : selectedRadioButton.getText().toString();


//        if (!firstname.equals("") && !lastname.equals("") && !email.equals("") && !pass.equals("") && !passAgain.equals("") && !birthday.equals("") && !gender.equals("") && !username.equals("") && !mobileno1.equals("") && !spinnervalue.equals("") && !taluka1.equals("") && !district1.equals("")&& !pincode1.equals("")) {
//
//            awesomeValidation.addValidation(this, R.id.firstname_edittext, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
//            if (awesomeValidation.validate()) {
//                awesomeValidation.addValidation(this, R.id.lastname_edittext, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
//                if (awesomeValidation.validate()) {
//
//                    awesomeValidation.addValidation(this, R.id.birthday_edittext, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.nameerror);
//                    if (awesomeValidation.validate()) {
//                        awesomeValidation.addValidation(this, R.id.age, Range.closed(13, 60), R.string.ageerror);
//                        if (awesomeValidation.validate()) {
//                            awesomeValidation.addValidation(this, R.id.email_edittext, "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", R.string.nameerror);
//                            if (awesomeValidation.validate()) {
//                                awesomeValidation.addValidation(this, R.id.mobileno, "[0-9]{10}", R.string.nameerror);
//                                if (awesomeValidation.validate()){
//                                    awesomeValidation.addValidation(this, R.id.pincode, "[0-9]{6}", R.string.nameerror);
//                                    if (awesomeValidation.validate()) {
//
//                                        awesomeValidation.addValidation(this, R.id.username_edittext, "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,15}$", R.string.nameerror);
//
//                                        if (awesomeValidation.validate()) {
//
//
//                                            if (pass.equals(passAgain)) {
//
//
////                                                    registerButton.setOnClickListener(new View.OnClickListener() {
////                                                        @Override
////                                                        public void onClick(View v) {
////                                                            openActivity2();
////                                                        }
////
////                                                        private void openActivity2() {
////
////                                                            Intent intent = new Intent(signinpage.this, Login.class);
////                                                            intent.putExtra("firstname", firstname);
////                                                            intent.putExtra("lastname", lastname);
////                                                            intent.putExtra("email", email);
////                                                            intent.putExtra("birthdate", birthday);
////                                                            intent.putExtra("age", age1);
////                                                            intent.putExtra("mobileno", mobileno1);
////                                                            intent.putExtra("address", address1);
////                                                            intent.putExtra("taluka", taluka1);
////                                                            intent.putExtra("district", district1);
////                                                            intent.putExtra("pincode", pincode1);
////                                                            intent.putExtra("state", spinnervalue);
////
////
////                                                            try {
////                                                                register(intent);
////                                                            } catch (JSONException e) {
////                                                                e.printStackTrace();
////                                                            }
////
////                                                        }
////                                                    });
//                                                } else {
//                                                    Toast.makeText(this, getResources().getString(R.string.passwords_must_be_the_same), Toast.LENGTH_SHORT).show();
//                                                }
//                                        } else {
//                                            Toast.makeText(getApplicationContext(), "pls enter less than 15 character in user name", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }else {
//                                        Toast.makeText(getApplicationContext(), "Please enter valid pincode", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(getApplicationContext(), "  Please enter valid Email id", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(getApplicationContext(), " Age is not valid", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), "  Please enter valid birthdate", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    Toast.makeText(getApplicationContext(), "  Lastname is not valid", Toast.LENGTH_SHORT).show();
//                }
//            }else {
//                Toast.makeText(getApplicationContext(), "  Firstname is not valid", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, getResources().getString(R.string.no_field_can_be_empty), Toast.LENGTH_SHORT).show();
//        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2();
            }

            private void openActivity2() {

                ProgressDialog loading;
                loading = ProgressDialog.show(signinpage.this, "Uploading...", null,true,true);
                Intent intent = new Intent(signinpage.this, Login.class);
//                intent.putExtra("firstname", firstname);
//                intent.putExtra("lastname", lastname);
//                intent.putExtra("email", email);
//                intent.putExtra("birthdate", birthday);
//                intent.putExtra("age", age1);
//                intent.putExtra("mobileno", mobileno1);
//                intent.putExtra("address", address1);
//                intent.putExtra("taluka", taluka1);
//                intent.putExtra("district", district1);
//                intent.putExtra("pincode", pincode1);
//                intent.putExtra("state", spinnervalue);


                try {
                    register(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    loading.dismiss();
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_edittext:
                datePickerDialog.show();
                break;
            case R.id.register_button:
                showToastWithFormValues();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void register(Intent i) throws JSONException {
            int loginState = 0;



        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton genderRadio = (RadioButton) findViewById(selectedGenderId);
        String gender = genderRadio.getTag().toString();



        AttemptLogin attemptLogin= new AttemptLogin(signinpage.this);
            JSONObject json = null;
            try {
                //uploading image and register form data
                String a =  uploadImage();
                if(a.equals("Error") || a.equals("Not Selected")){
                    a = "";
                }
                json = attemptLogin.execute(usernameEdittext.getText().toString(),
                        passEdittext.getText().toString(),
                        emailEdittext.getText().toString(),
                        firstnameEdittext.getText().toString(),
                        lastnameEdittext.getText().toString(),
                        a,
                        mobileno.getText().toString(),
                        gender,
                        birthdayEdittext.getText().toString(),
                        state.getSelectedItem().toString(),
                        district.getText().toString(),
                        taluka.getText().toString(),
                        village.getText().toString(),
                        pincode.getText().toString()).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(json!= null){
                loginState = Integer.parseInt(json.getString("success"));
                if(loginState == 1){
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_SHORT).show();
                }
            }


    }
}
