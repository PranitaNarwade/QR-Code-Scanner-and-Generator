package com.example.qrcodescanner;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrcodescanner.databinding.FragmentGenerateCodeBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateCodeFragment extends Fragment {

    private FragmentGenerateCodeBinding binding;

    public GenerateCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGenerateCodeBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.btGenerateQr.setOnClickListener((View v)->{
            binding.imgQr.setImageBitmap(null);
            String url = binding.etUrl.getText().toString().trim();
            if(url.isEmpty()){
                Toast.makeText(getContext(), R.string.enter_url, Toast.LENGTH_SHORT).show();
                return;
            }

            if(!url.startsWith("https://"))
                url = "https://"+url;

            if(isValidURL(url))
                generateQRCode(url);
            else
                Toast.makeText(getContext(), R.string.not_valid_url_msg, Toast.LENGTH_SHORT).show();

        });
    }

    public static boolean isValidURL(String urlString) {
        return urlString != null && Patterns.WEB_URL.matcher(urlString).matches();
    }

    private void generateQRCode(String text)
    {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(text, BarcodeFormat.QR_CODE, 400, 400);
            binding.imgQr.setImageBitmap(bitmap);
            binding.etUrl.setText("");
        }
        catch (WriterException e) {
            e.printStackTrace();
        }
    }
}