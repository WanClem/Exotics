package com.wan.exotics.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.wan.exotics.constants.ExoticConstants;
import com.wan.exotics.loggers.ExoticsLogger;
import com.wan.exotics.models.AudioFile;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Wan Clem
 */

public class MediaUtils {

    public static String TAG = MediaUtils.class.getSimpleName();

    public static class MediaEntry {
        int bucketId;
        int imageId;
        long dateTaken;
        public String path;
        public int orientation;
        boolean isVideo;
        public String bucketName;
        public String fileName;
        public long fileSize;
        public long fileDuration;
        boolean selected;

        MediaEntry(int bucketId, int imageId, long dateTaken, String path, int orientation,
                   boolean isVideo, String bucketName, String fileName, long fileSize,
                   long fileDuration) {
            this.bucketId = bucketId;
            this.imageId = imageId;
            this.dateTaken = dateTaken;
            this.path = path;
            this.orientation = orientation;
            this.isVideo = isVideo;
            this.bucketName = bucketName;
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.fileDuration = fileDuration;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isSelected() {
            return selected;
        }

        @Override
        public int hashCode() {
            int result;
            result = path.hashCode();
            final String name = getClass().getName();
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            MediaEntry another = (MediaEntry) obj;
            return this.path.equals(another.path);
        }
    }

    public static ArrayList<AudioFile> getSortedMusicFiles(Context context) {

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
        };

        ArrayList<AudioFile> audioFiles = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    AudioFile audioFile = new AudioFile();

                    audioFile.setAudioId(cursor.getInt(0));
                    audioFile.setAuthor(cursor.getString(1));
                    audioFile.setTitle(cursor.getString(2));
                    audioFile.setPath(cursor.getString(3));
                    audioFile.setDuration((cursor.getLong(4)));
                    audioFile.setGenre(cursor.getString(5));
                    audioFiles.add(audioFile);
                }
            }
        } catch (Exception e) {
            ExoticsLogger.e("HOLLOUTUtils", "Error loading audio" + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        Collections.sort(audioFiles);
        return audioFiles;
    }

    public static ArrayList<MediaEntry> getSortedPhotos(Context context) {
        ArrayList<MediaEntry> photoEntriesSorted = new ArrayList<>();
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                cursor = MediaStore.Images.Media.query(context.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ExoticConstants.projectionPhotos, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");
                if (cursor != null) {
                    int imageIdColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                    int bucketIdColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
                    int bucketNameColumn = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                    int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    int dateColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
                    int orientationColumn = cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);

                    while (cursor.moveToNext()) {

                        int imageId = cursor.getInt(imageIdColumn);
                        int bucketId = cursor.getInt(bucketIdColumn);
                        String bucketName = cursor.getString(bucketNameColumn);
                        String path = cursor.getString(dataColumn);
                        long dateTaken = cursor.getLong(dateColumn);
                        int orientation = cursor.getInt(orientationColumn);

                        if (path == null || path.length() == 0) {
                            continue;
                        }

                        MediaEntry newMediaEntry = new MediaEntry(bucketId, imageId, dateTaken, path, orientation, false, bucketName, null, 0, 0);
                        photoEntriesSorted.add(newMediaEntry);

                    }

                }

            }

        } catch (Throwable e) {
            ExoticsLogger.e(TAG, e.getMessage());
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    ExoticsLogger.e(TAG, e.getMessage());
                }
            }
        }
        return photoEntriesSorted;
    }

    public static ArrayList<MediaEntry> getSortedVideos(Context context) {

        ArrayList<MediaEntry> sortedVideoEntries = new ArrayList<>();

        Cursor cursor = null;

        try {

            if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                cursor = MediaStore.Images.Media.query(context.getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI, ExoticConstants.projectionVideo, null, null, MediaStore.Video.Media.DATE_TAKEN + " DESC");

                if (cursor != null) {
                    int imageIdColumn = cursor.getColumnIndex(MediaStore.Video.Media._ID);
                    int bucketIdColumn = cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID);
                    int bucketNameColumn = cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
                    int dataColumn = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                    int dateColumn = cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN);
                    int fileNameColumn = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
                    int fileSizeColumn = cursor.getColumnIndex(MediaStore.Video.Media.SIZE);
                    int fileDuration = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);

                    while (cursor.moveToNext()) {

                        int videoId = cursor.getInt(imageIdColumn);
                        int bucketId = cursor.getInt(bucketIdColumn);
                        String bucketName = cursor.getString(bucketNameColumn);
                        String path = cursor.getString(dataColumn);
                        long dateTaken = cursor.getLong(dateColumn);
                        int orientation = 0;
                        String fileName = cursor.getString(fileNameColumn);
                        long fileSize = cursor.getLong(fileSizeColumn);
                        long durationOfFile = cursor.getLong(fileDuration);

                        if (path == null || path.length() == 0) {
                            continue;
                        }
                        MediaEntry newMediaEntry = new MediaEntry(bucketId, videoId, dateTaken, path, orientation, false, bucketName, fileName, fileSize, durationOfFile);
                        sortedVideoEntries.add(newMediaEntry);
                    }
                }
            }
        } catch (Throwable e) {
            ExoticsLogger.e(TAG, e.getMessage());

        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    ExoticsLogger.e(TAG, e.getMessage());
                }
            }
        }
        return sortedVideoEntries;
    }

}
