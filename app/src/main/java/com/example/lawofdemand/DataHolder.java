package com.example.lawofdemand;

import com.quickblox.content.model.QBFile;

import java.util.List;

/**
 * Created by jaicksninan on 2/12/15.
 */
public class DataHolder {

    private static DataHolder dataHolder;
    private List<QBFile> qbFileList;

    public static synchronized DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }
    public void addQbFile(QBFile qbFile) {
        qbFileList.add(qbFile);
    }

}
