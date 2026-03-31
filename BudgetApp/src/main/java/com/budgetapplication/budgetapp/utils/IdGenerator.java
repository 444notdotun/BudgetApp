package com.budgetapplication.budgetapp.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Component
public class IdGenerator {
    private static  final Map<Type,Generator> generators =  new HashMap<>();

    static {
        generators.put(Type.BUDGET,()-> "BUG-"+generateId());
        generators.put(Type.CATEGORY,()->"CAT-"+generateId());
        generators.put(Type.TEMPLATE,()-> "TEM-"+generateId());
        generators.put(Type.USER,()->"USER-"+generateId());
    }

    public static String generate(Type type){
       Generator generator = generators.get(type);
       return generator.generate();
    }
     public static String generateId(){
        return UUID.randomUUID().toString().substring(0,5);
     }
}
