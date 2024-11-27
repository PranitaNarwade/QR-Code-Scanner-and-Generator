package com.example.qrcodescanner;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrcodescanner.databinding.FragmentScanCodeBinding;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import java.util.List;

public class ScanCodeFragment extends Fragment {

    private FragmentScanCodeBinding binding;
    private boolean flag = false;
    String res = "";
    public ScanCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScanCodeBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, 101);
        }

        binding.barcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                // When a QR code is scanned, show the result in a Toast message
                if (result != null) {
                        if(res.equals("") || !res.equals(result.getText()))
                        {
                            res = result.getText();
                            flag = true;
                            binding.txtResult.setText(result.getText());
                            Toast.makeText(getContext(), R.string.scan_success_msg,Toast.LENGTH_LONG).show();
                        }else {
                            flag = false;
                        }
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
//                Toast.makeText(getContext(), R.string.try_again_msg,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.barcodeScanner.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.barcodeScanner.resume();
    }

}