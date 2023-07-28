package com.example.educationnotes.bca.fourthsem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FourthPDFListAdapter extends ArrayAdapter<PdfFile>
{
    private Context context;

    public FourthPDFListAdapter(Context context, List<com.example.educationnotes.bca.fourthsem.PdfFile> pdfFiles) {
        super(context, 0, pdfFiles);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView txtFileName = convertView.findViewById(android.R.id.text1);
        com.example.educationnotes.bca.fourthsem.PdfFile pdfFile = getItem(position);
        if (pdfFile != null) {
            txtFileName.setText(pdfFile.getName());
        }

        return convertView;
    }
}
class PdfFile {
    private String name;
    private String url;

    public PdfFile() {
        // Default constructor required for Firebase
    }

    public PdfFile(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
