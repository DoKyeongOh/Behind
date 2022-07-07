package org.mytoypjt.controller.structure;

import org.mytoypjt.controller.structure.enums.MappingName;

public class RequestControllerMappingFactory {

    public static IRequestControllerMapping getMappingClass(MappingName mappingName){

        switch (mappingName) {
            case Properties: {
                return new PropertiesRequestControllerMapping();
            }
            case Annotation: {
                return new AnnotationRequestControllerMapping();
            }
        }

        return new PropertiesRequestControllerMapping();
    }






}
