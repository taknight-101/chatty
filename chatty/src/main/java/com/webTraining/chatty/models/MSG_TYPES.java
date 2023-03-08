package com.webTraining.chatty.models;

public enum MSG_TYPES {
    TEXT ,
    VIDEO,
    AUDIO,
    PHOTO,
    URL ,
     NOT_IMPLEMENTED ;

    public static MSG_TYPES fromInteger(int x) {
        switch(x) {
            case 0:
                return TEXT;
            case 1:
                return VIDEO;
        }
        return null;
    }

}
