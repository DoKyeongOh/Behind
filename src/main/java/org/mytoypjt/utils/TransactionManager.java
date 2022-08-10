package org.mytoypjt.utils;

import java.util.HashMap;
import java.util.Map;

public class TransactionManager {

    Map<String, String> serviceNameMap;

    public TransactionManager() {
        serviceNameMap = new HashMap<String, String>();
    }

    public void entryService(String packageName){
        System.out.println("this is origin");
    }



}
