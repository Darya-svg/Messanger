package ru.course.java.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Factory {

    private static Map<String, IMessage> myMap;

    private void init() {


    myMap =new HashMap<>();
       myMap.put("CheckIN",new

    CheckIN());
       myMap.put("SignIN",new

    SingIN());
}


   public IMessage getImplm(String header){
       init();
       return myMap.get(header);

   }
}
