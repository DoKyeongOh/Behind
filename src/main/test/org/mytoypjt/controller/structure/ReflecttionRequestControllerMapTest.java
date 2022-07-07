package org.mytoypjt.controller.structure;

import org.junit.jupiter.api.Test;
import org.mytoypjt.controller.IndexController;
import org.mytoypjt.controller.structure.annotations.RequestMapping;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ReflecttionRequestControllerMapTest {

    Class aClass = IndexController.class;

    @Test
    void examineMethods() {
        Object o = null;

        for (Method m : aClass.getDeclaredMethods()) {
            RequestMapping rm = m.getAnnotation(RequestMapping.class);
            System.out.println("url : " + rm.uri() + " method : " + rm.method() + "\nfunction name : " + m.getName());

            assertEquals(rm.uri(), "/");
        }
    }
}