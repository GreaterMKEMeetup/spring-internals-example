/*
 * Copyright (C) Consumer Agent Portal, LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by phil, December 08, 2017
 */

package com.mkejug.spring.webargumentresolver;

public class PersonalizationToken {

    String uid;
    String sid;

    public PersonalizationToken() {
        //Default constructor
    }

    public PersonalizationToken(String uid) {
        this.uid = uid;
    }

    public PersonalizationToken(String uid, String sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
