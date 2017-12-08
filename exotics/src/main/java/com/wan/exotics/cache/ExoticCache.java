package com.wan.exotics.cache;

import android.content.Context;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.wan.exotics.interfaces.DoneCallback;
import com.wan.exotics.loggers.ExoticsLogger;
import com.wan.exotics.models.ExoticObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wan Clem
 */

public class ExoticCache {

    private static String TAG = ExoticCache.class.getSimpleName();

    private static Kryo getKryoInstance() {
        return new Kryo();
    }

    public static synchronized void serializeExotics(Context context, List<ExoticObject> tObjects, String serializableName) {
        Kryo kryo = getKryoInstance();
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(serializableName, Context.MODE_PRIVATE);
            Output messageOutPuts = new Output(fileOutputStream);
            kryo.writeObject(messageOutPuts, tObjects);
            messageOutPuts.close();
        } catch (IOException | KryoException | BufferOverflowException e) {
            e.printStackTrace();
            ExoticsLogger.e(TAG, e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public static synchronized void deserializeExotics(Context context, String fileName, DoneCallback<List<ExoticObject>> doneCallback) {
        Kryo kryo = getKryoInstance();
        try {
            Input input = new Input(context.openFileInput(fileName));
            ArrayList<ExoticObject> previouslySerializedChats = kryo.readObject(input, ArrayList.class);
            if (doneCallback != null) {
                doneCallback.done(previouslySerializedChats, null);
            }
            input.close();
        } catch (FileNotFoundException | KryoException | BufferUnderflowException e) {
            if (doneCallback != null) {
                doneCallback.done(null, null);
            }
            e.printStackTrace();
            ExoticsLogger.e(TAG, e.getMessage());
        }
    }

}
