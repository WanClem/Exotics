package com.wan.exotics.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Wan Clem
 */
@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class ExoticObject implements Serializable, Parcelable {

    public JSONObject objectProperties = new JSONObject();

    //Utility String field to help parcel and un-parcel Object Properties
    private String objectPropertiesString;
    private String objectId;

    /**
     * A no argument constructor is needed for Kryo serialization
     **/
    public ExoticObject() {
        if (getObjectId() == null) {
            throw new RuntimeException("This Object must be assigned an Id. Please use setObjectId(String objectId) to assign an id to this object");
        }
    }

    protected ExoticObject(Parcel in) {
        objectPropertiesString = in.readString();
        if (StringUtils.isNotEmpty(objectPropertiesString)) {
            try {
                objectProperties = new JSONObject(objectPropertiesString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ExoticObject(String objectType) {
        put("_objectType", objectType);
        marshallProps();
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public JSONObject put(String key, int value) {
        try {
            objectProperties.put(key, value);
            marshallProps();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objectProperties;
    }

    public JSONObject put(String key, long value) {
        try {
            objectProperties.put(key, value);
            marshallProps();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objectProperties;
    }

    public JSONObject put(String key, double value) {
        try {
            objectProperties.put(key, value);
            marshallProps();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objectProperties;
    }

    public JSONObject put(String key, Object value) {
        try {
            objectProperties.put(key, value);
            marshallProps();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objectProperties;
    }

    public JSONObject copyFromMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            try {
                objectProperties.put(key, map.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        marshallProps();
        return objectProperties;
    }

    private void marshallProps() {
        this.objectPropertiesString = objectProperties.toString();
    }

    public String optString(String key) {
        return objectProperties.optString(key);
    }

    public long optLong(String key) {
        return objectProperties.optLong(key);
    }

    public int optInt(String key) {
        return objectProperties.optInt(key);
    }

    public double optDouble(String key) {
        return objectProperties.optDouble(key);
    }

    public Object opt(String key) {
        return objectProperties.opt(key);
    }

    public JSONArray optJSONArray(String key) {
        return objectProperties.optJSONArray(key);
    }

    public JSONObject optJSONObject(String key) {
        return objectProperties.optJSONObject(key);
    }

    public Iterator<String> keys() {
        return objectProperties.keys();
    }

    public JSONArray names() {
        return objectProperties.names();
    }

    public String toString() {
        return objectProperties.toString();
    }

    public String toString(int indentSpaces) throws JSONException {
        return objectProperties.toString(indentSpaces);
    }

    public JSONObject setObjectProperties(JSONObject objectProperties) {
        this.objectProperties = objectProperties;
        marshallProps();
        return objectProperties;
    }

    public static final Creator<ExoticObject> CREATOR = new Creator<ExoticObject>() {
        @Override
        public ExoticObject createFromParcel(Parcel in) {
            return new ExoticObject(in);
        }

        @Override
        public ExoticObject[] newArray(int size) {
            return new ExoticObject[size];
        }
    };

    public JSONObject remove(String key) {
        objectProperties.remove(key);
        return objectProperties;
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
        ExoticObject another = (ExoticObject) obj;
        String anotherObjectId = another.getObjectId();
        return getObjectId().equals(anotherObjectId);
    }

    @Override
    public int hashCode() {
        int result;
        result = getObjectId().hashCode();
        final String name = getClass().getName();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(objectPropertiesString);
    }

}
