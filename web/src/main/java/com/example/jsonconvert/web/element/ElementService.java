package com.example.jsonconvert.web.element;

import com.example.jsonconvert.web.array.model.po.ArrayPO;
import com.example.jsonconvert.web.array.service.ArrayService;
import com.example.jsonconvert.web.base.service.BaseService;
import com.example.jsonconvert.web.objectprop.model.po.ObjectPropPO;
import com.example.jsonconvert.web.objectprop.service.ObjectPropService;
import com.example.jsonconvert.web.prop.model.po.PropPO;
import com.example.jsonconvert.web.prop.service.PropService;
import com.example.jsonconvert.web.propmapping.model.po.PropMappingPO;
import com.example.jsonconvert.web.propmapping.service.PropMappingService;
import com.example.jsonconvert.web.root.model.po.RootPO;
import com.example.jsonconvert.web.root.service.RootService;
import com.google.common.collect.Lists;
import org.example.jsonconvert.enums.DataType;
import org.example.jsonconvert.model.ArrayElement;
import org.example.jsonconvert.model.Element;
import org.example.jsonconvert.model.ObjectElement;
import org.example.jsonconvert.model.RootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author hongbo.pan
 * @date 2020/11/23
 */
@Service
public class ElementService extends BaseService {

    @Autowired
    private RootService rootService;

    @Autowired
    private ObjectPropService objectPropService;

    @Autowired
    private PropMappingService propMappingService;

    @Autowired
    private PropService propService;

    @Autowired
    private ArrayService arrayService;

    public RootElement buildRoot(Long rootId) {
        RootPO po = rootService.findById(rootId);
        RootElement rootElement = new RootElement();
        if (DataType.OBJECT.getType().equals(po.getType())) {
            rootElement.setPropType(DataType.OBJECT);
            rootElement.setObjectElement(buildObject(po.getObjectId()));
        } else {
            rootElement.setPropType(DataType.ARRAY);
            rootElement.setArrayElement(buildArray(po.getArrayId()));
        }
        return rootElement;
    }

    private ObjectElement buildObject(Long objectId) {
        List<ObjectPropPO> objectPropPOList =
                objectPropService.findByObjectId(objectId);
        ObjectElement objectElement = new ObjectElement();
        objectElement.setElements(objectPropPOList.stream().map(objectPropPO -> {
            PropMappingPO propMappingPO =
                    propMappingService.findByObjectIdAndSrcPropId(objectPropPO.getObjectId(), objectPropPO.getPropId());
            Long srcPropId = propMappingPO.getSrcPropId();
            Long targetPropId = propMappingPO.getTargetPropId();
            List<Long> ids = Lists.newArrayList(srcPropId, targetPropId);
            List<PropPO> propPOList = propService.findByIdIn(ids);
            Map<Long, PropPO> propPOMap = propPOList.stream().collect(Collectors.toMap(PropPO::getId, propPO -> propPO));
            PropPO srcProp = propPOMap.get(srcPropId);
            PropPO targetProp = propPOMap.get(targetPropId);
            Element element = new Element();
            element.setPropName(srcProp.getName());
            element.setPropType(DataType.getByType(srcProp.getType()));
            element.setTargetPropName(targetProp.getName());
            element.setTargetPropType(DataType.getByType(targetProp.getType()));
            element.setNullVerify(propMappingPO.getNullVerify());
            element.setRegularVerify(propMappingPO.getRegularVerify());
            element.setDefaultValue(propMappingPO.getDefaultValue());
            if (DataType.OBJECT.getType().equals(srcProp.getType())) {
                element.setObjectElement(buildObject(srcProp.getObjectId()));
            }
            if (DataType.ARRAY.getType().equals(srcProp.getType())) {
                element.setArrayElement(buildArray(srcProp.getArrayId()));
            }
            return element;
        }).collect(toList()));
        return objectElement;
    }

    private ArrayElement buildArray(Long arrayId) {
        ArrayPO arrayPO = arrayService.findById(arrayId);
        ArrayElement arrayElement = new ArrayElement();
        arrayElement.setPropType(DataType.getByType(arrayPO.getType()));
        if (DataType.OBJECT.getType().equals(arrayPO.getType())) {
            arrayElement.setObjectElement(buildObject(arrayPO.getObjectId()));
        }
        if (DataType.ARRAY.getType().equals(arrayPO.getType())) {
            arrayElement.setArrayElement(buildArray(arrayPO.getArrayId()));
        }
        return arrayElement;
    }
}
