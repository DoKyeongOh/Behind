package org.mytoypjt.controller.structure;

import org.mytoypjt.controller.structure.annotation.AnnotationControllerAdapter;
import org.mytoypjt.controller.structure.enums.MappingName;
import org.mytoypjt.controller.structure.properties.PropertiesControllerAdapter;

public class ControllerAdapterFactory {

    public static BaseControllerAdapter getControllerAdapter(MappingName mappingName){

        switch (mappingName) {
            case Properties: {
                return new PropertiesControllerAdapter();
            }

            case Annotation: {
                return new AnnotationControllerAdapter();
            }
        }

        return new PropertiesControllerAdapter();
    }
}
