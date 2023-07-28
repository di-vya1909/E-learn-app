package com.example.educationnotes.bca.firstsem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.educationnotes.R;
import com.example.educationnotes.bca.BcaActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NotesUploadActivity extends AppCompatActivity {

    EditText notesPdf;
    Button uploadNotes, back;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    private static final int REQUEST_CODE = 1;
    private static final int FILE_SELECT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_upload);

        notesPdf = findViewById(R.id.notes_pdf_name);
        back = findViewById(R.id.back);
        uploadNotes = findViewById(R.id.upload_note_pdf);
        storageReference = FirebaseStorage.getInstance("gs://bca-2023.appspot.com").getReference();
        databaseReference = FirebaseDatabase.getInstance("https://bca-2023-default-rtdb.firebaseio.com/").getReference();

        uploadNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStoragePermission();
            }
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(NotesUploadActivity.this, BcaActivity.class);
            startActivity(intent);
        });
    }
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            } else {
                // Permission already granted
                selectPdf();
            }
        } else {
            // Permission automatically granted on devices running below Marshmallow
            selectPdf();
        }
    }

    private void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), FILE_SELECT_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                selectPdf();
            } else {
                // Permission denied
                Toast.makeText(this, "Storage permission required to select a PDF", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uploadPdfToFirebase(data.getData());
        }
    }

    private void uploadPdfToFirebase(Uri pdfUri) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        String pdf_name = notesPdf.getText().toString();
        StorageReference reference = storageReference.child("BCA/First/Notes/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(pdfUri)
                .addOnSuccessListener(taskSnapshot -> reference.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                    String pdfUrl = downloadUri.toString();
                    savePdfUrlToDatabase(pdfUrl, pdf_name);
                    progressDialog.dismiss();
                }))
                .addOnProgressListener(snapshot -> {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    progressDialog.setMessage("Upload " + (int) progress + "%");
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(NotesUploadActivity.this, "File upload failed", Toast.LENGTH_SHORT).show();
                });
    }
    private void savePdfUrlToDatabase(String pdfUrl, String pdfName) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://bca-2023-default-rtdb.firebaseio.com/").getReference("BCA/First-Sem/Notes");
        String key = databaseRef.push().getKey();

        if (key != null) {
            UploadPDF uploadpdf = new UploadPDF(pdfName, pdfUrl);
            databaseRef.child(key).setValue(uploadpdf)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(NotesUploadActivity.this, "PDF uploaded and URL saved in database", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(NotesUploadActivity.this, "Failed to save PDF URL in database", Toast.LENGTH_SHORT).show());
        }
    }
}