package com.example.dsm_calendar.ui.activity;

import android.Manifest;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;

public class BannerRequireActivity extends AppCompatActivity implements View.OnClickListener{

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
                startActivityForResult(intent, PICK_FROM_ALBUM);
                break;
            case R.id.button_bannerrequire_buttons_requirebanner:
                selectedImage.getDrawable();
                bannerContents.getText().toString();
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
}
