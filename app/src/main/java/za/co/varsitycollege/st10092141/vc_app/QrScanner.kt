package za.co.varsitycollege.st10092141.vc_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


class QrScanner : Fragment() {

    private lateinit var scanButton: Button
    private lateinit var scanResultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_qr_scanner, container, false)

        scanButton = view.findViewById(R.id.btnScanQr)
        scanResultTextView = view.findViewById(R.id.tvScanResult)

        // Set click listener for the scan button
        scanButton.setOnClickListener {
            startQrScanner()
        }

        return view
    }

    private fun startQrScanner() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a QR code")
        integrator.setCameraId(0) // Use the back camera
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                // Scan failed or canceled
                scanResultTextView.text = "Scan cancelled"
            } else {
                // Scan succeeded
                scanResultTextView.text = "Scan result: ${result.contents}"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}