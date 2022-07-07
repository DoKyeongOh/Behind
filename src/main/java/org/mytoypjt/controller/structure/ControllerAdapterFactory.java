package org.mytoypjt.controller.structure;

import org.mytoypjt.controller.structure.enums.MappingName;

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
