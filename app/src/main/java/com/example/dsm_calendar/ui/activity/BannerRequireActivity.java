package com.example.dsm_calendar.ui.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.BannerRquireContract;
import com.example.dsm_calendar.data.BannerRequireRepository;
import com.example.dsm_calendar.presenter.BannerRequirePresenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class BannerRequireActivity extends AppCompatActivity implements BannerRquireContract.View, View.OnClickListener{

    private ImageButton offButton;
    private ImageView selectedImage;
    private Button selectImageButton;
    private TextView selectedTextView;
    private EditText bannerContents;
    private Button bannerRequireCancel;
    private Button bannerRequire;

    private final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    private String path;

    private BannerRequirePresenter presenter = new BannerRequirePresenter(this, new BannerRequireRepository(this));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerrequire);

        tedPermission();

        offButton = findViewById(R.id.button_bannerrequire_off);
        selectedImage = findViewById(R.id.iv_bannerrequire_selected);
        selectImageButton = findViewById(R.id.button_bannerrequire_addimage);
        selectedTextView = findViewById(R.id.tv_bannerrequire_selected);
        bannerContents = findViewById(R.id.et_bannerrequire_edits_contents);
        bannerRequireCancel = findViewById(R.id.button_bannerrequire_buttons_cancel);
        bannerRequire = findViewById(R.id.button_bannerrequire_buttons_requirebanner);

        offButton.setOnClickListener(this);
        selectImageButton.setOnClickListener(this);
        selectedTextView.setOnClickListener(this);
        bannerRequireCancel.setOnClickListener(this);
        bannerRequire.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_ALBUM) {
            if (resultCode != RESULT_CANCELED){
                Uri selectedImageUri = data.getData();
                Cursor cursor = null;
                try {
                    String[] proj = {MediaStore.Images.Media.DATA};
                    assert selectedImageUri != null;
                    cursor = getContentResolver().query(selectedImageUri, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    tempFile = new File(cursor.getString(column_index));
                    path = cursor.getString(column_index).substring(cursor.getString(column_index).lastIndexOf("/")+1);
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                setImage();
            }
        }
    }

    @Override
    public void showMessageForBannerRequireSuccess() {
        Toast.makeText(this, "Banner Require Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForBannerRequireFail(String message) {
        Toast.makeText(this, "Banner Require Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bannerrequire_off:
            case R.id.button_bannerrequire_buttons_cancel:
                finish();
                break;
            case R.id.button_bannerrequire_addimage:
            case R.id.tv_bannerrequire_selected:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_FROM_ALBUM);
                } catch (ActivityNotFoundException e){

                }
                break;
            case R.id.button_bannerrequire_buttons_requirebanner:
                if (isContentFilled()){
                    presenter.onRequireClicked(bannerContents.getText().toString(), tempFile.getAbsolutePath(), "", "");
                } else {
                    Toast.makeText(this, "내용이 전부 채워지지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void tedPermission(){
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        };

        TedPermission.with(this).setPermissionListener(permissionListener)
                .setRationaleMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setDeniedMessage("사진 및 파일을 저장하기 위하여 접근 권한이 필요합니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void setImage(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        selectedImage.setImageBitmap(originalBm);
        selectedTextView.setText(path);
    }

    private boolean isContentFilled(){
        return tempFile.getAbsoluteFile() != null && bannerContents.getText().toString().equals("");
    }
}
