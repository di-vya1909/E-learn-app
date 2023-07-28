package com.example.educationnotes.bca.firstsem;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.educationnotes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class eBooksFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private PdfListAdapter pdfListAdapter;
    private DatabaseReference databaseReference;

    public eBooksFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_notes, container, false);

        pdfListAdapter = new PdfListAdapter(requireContext(), new ArrayList<>());
        ListView listView = rootView.findViewById(R.id.myNotesPDFList);
        listView.setAdapter(pdfListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PdfFile pdfFile = pdfListAdapter.getItem(position);
                if (pdfFile != null) {
                    openPdfFile(pdfFile.getUrl());
                }
            }
        });

        // Initialize Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance("https://bca-2023-default-rtdb.firebaseio.com/").getReference().child("BCA").child("First-Sem").child("EBooks");
        retrievePdfFiles();

        return rootView;
    }

    private void retrievePdfFiles() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<PdfFile> pdfFiles = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PdfFile pdfFile = dataSnapshot.getValue(PdfFile.class);
                    if (pdfFile != null) {
                        pdfFiles.add(pdfFile);
                    }
                }

                pdfListAdapter.clear();
                pdfListAdapter.addAll(pdfFiles);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error if needed
            }
        });
    }

    private void openPdfFile(String pdfUrl) {
        Uri uri = Uri.parse(pdfUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(requireContext(), "No PDF viewer application found", Toast.LENGTH_SHORT).show();
        }
    }
}