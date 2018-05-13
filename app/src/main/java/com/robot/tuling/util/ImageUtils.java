package com.robot.tuling.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class ImageUtils {
    public static final File compressJPEGFile(File file, int quality) throws IOException {
        File compressedFile = new File(file.getParent(), "c_" + file.getName());
        if (!compressedFile.exists()) {
            return null;
        }
        OutputStream outputStream = null;
        outputStream = Objects.requireNonNull(new FileOutputStream(compressedFile));
        Bitmap orignalBitmap = BitmapFactory.decodeFile(file.getPath());
        orignalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        outputStream.close();
        return compressedFile;
    }
}
