package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import cn.sixlab.minesoft.singte.core.common.vo.StModelColumn;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TableService {

    public BaseModel getModel(String tableName) {
        return new BaseModel();
    }

    public BaseDao getDao(String tableName) {
        char firstChar = tableName.charAt(0);
        tableName = Character.toLowerCase(firstChar) + tableName.substring(1);
        return SpringUtil.getBean(tableName + "Dao");
    }

    public List<StModelColumn> getColumns(String tableName) {
        String clzName = "cn.sixlab.minesoft.singte.core.models." + tableName;

        List<StModelColumn> columnList = new ArrayList<>();
        try {
            Field[] fieldList = Class.forName(clzName).getDeclaredFields();
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                StColumn annotation = field.getAnnotation(StColumn.class);
                if (null != annotation) {
                    String type = annotation.type();

                    if (!"hidden".equals(type)) {
                        StModelColumn column = new StModelColumn();
                        column.setName(field.getName());

                        if (StrUtil.isEmpty(type)) {
                            Class<?> fieldType = field.getType();
                            if (fieldType.equals(String.class)) {
                                type = "String";
                            } else if (fieldType.equals(Number.class)) {
                                type = "Number";
                            }
                        }
                        column.setType(type);

                        column.setCssClass(annotation.cssClass());
                        column.setPlaceholder(annotation.placeholder());
                        column.setDefaultVal(annotation.defaultVal());
                        column.setOrder(annotation.order());

                        columnList.add(column);
                    }
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        columnList.sort(Comparator.comparingInt(StModelColumn::getOrder));

        return columnList;
    }

    public BaseModel getParams(String tableName, HttpServerRequest request) {
        BaseModel params = null;
        try {
            String clzName = "cn.sixlab.minesoft.singte.core.models." + tableName;
            params = (BaseModel) Class.forName(clzName).newInstance();
            Field[] fieldList = Class.forName(clzName).getDeclaredFields();

            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                StColumn annotation = field.getAnnotation(StColumn.class);
                if (null != annotation) {
                    String type = annotation.type();
                    String name = field.getName();
                    String val = request.getParam(name);
                    if ("hidden".equals(type)) {
                        val = annotation.defaultVal();
                    }

                    Class<?> fieldType = field.getType();
                    field.set(params, Convert.convert(fieldType, val));
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return params;
    }

}
