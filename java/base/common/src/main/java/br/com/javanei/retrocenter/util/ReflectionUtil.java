package br.com.javanei.retrocenter.util;

import br.com.javanei.retrocenter.common.UnknownAttributeException;
import br.com.javanei.retrocenter.common.UnknownTagException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    public static void setValueByReflection(Object to, String attr, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String mName = "set" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
        Method m = to.getClass().getMethod(mName, value.getClass());
        m.invoke(to, value);
    }

    public static void setValueByAttribute(Object to, Node attr) {
        String aName = attr.getNodeName();
        String value = attr.getTextContent().trim();
        try {
            setValueByReflection(to, aName, value);
        } catch (Exception ex) {
            throw new UnknownAttributeException(attr.getNodeName());
        }
    }

    public static void setValueByAttributes(Object to, NamedNodeMap attrs) {
        if (attrs != null) {
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                setValueByAttribute(to, attr);
            }
        }
    }

    public static void setValueByAttributes(Object to, Attributes attrs) {
        if (attrs != null) {
            for (int j = 0; j < attrs.getLength(); j++) {
                String qName = attrs.getQName(j);
                String value = attrs.getValue(j);
                try {
                    setValueByReflection(to, qName, value);
                } catch (Exception ex) {
                    throw new UnknownAttributeException(qName);
                }
            }
        }
    }

    public static void setValueByNodeContent(Object to, Node node) {
        if (node.getNodeName().equals("#text")) {
            return;
        }
        try {
            ReflectionUtil.setValueByReflection(to, node.getNodeName().trim(), node.getTextContent().trim());
        } catch (Exception e) {
            throw new UnknownTagException(node.getNodeName());
        }
    }
}
