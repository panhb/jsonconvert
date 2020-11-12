package example.convert;

import com.google.common.collect.Lists;
import org.example.jsonconvert.JsonConvert;
import org.example.jsonconvert.enums.DataType;
import org.example.jsonconvert.model.ArrayElement;
import org.example.jsonconvert.model.Element;
import org.example.jsonconvert.model.ObjectElement;
import org.example.jsonconvert.model.RootElement;

/**
 * @author hongbo.pan
 * @date 2020/11/10
 */
public class TestArray1 {
    public static void main(String[] args) {
        String json = "[[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}],[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]]";

        Element element = new Element();
        element.setPropName("id");
        element.setPropType(DataType.STRING);
        element.setTargetPropName("pkid");
        element.setTargetPropType(DataType.LONG);

        Element element2 = new Element();
        element2.setPropName("name");
        element2.setPropType(DataType.STRING);
        element2.setTargetPropName("haha");
        element2.setTargetPropType(DataType.STRING);
        element2.setNullVerify(false);


        ObjectElement oe = new ObjectElement();
        oe.setElements(Lists.newArrayList(element, element2));

        ArrayElement arrayElement = new ArrayElement();
        arrayElement.setPropType(DataType.OBJECT);
        arrayElement.setObjectElement(oe);

        ArrayElement arrayElement2 = new ArrayElement();
        arrayElement2.setPropType(DataType.ARRAY);
        arrayElement2.setArrayElement(arrayElement);

        RootElement root = new RootElement();
        root.setArrayElement(arrayElement2);

        try {
            System.out.println(JsonConvert.convertToString(json, root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
