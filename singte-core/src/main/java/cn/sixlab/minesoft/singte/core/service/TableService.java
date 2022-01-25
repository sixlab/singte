package cn.sixlab.minesoft.singte.core.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.sixlab.minesoft.singte.core.common.annotation.StColumn;
import cn.sixlab.minesoft.singte.core.common.annotation.StTable;
import cn.sixlab.minesoft.singte.core.common.config.BaseDao;
import cn.sixlab.minesoft.singte.core.common.config.BaseModel;
import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.common.vo.StModelColumn;
import cn.sixlab.minesoft.singte.core.common.vo.StModelTable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class TableService {

    public BaseDao getDao(String tableName) {
        char firstChar = tableName.charAt(0);
        tableName = Character.toLowerCase(firstChar) + tableName.substring(1);
        return SpringUtil.getBean(tableName + "Dao");
    }

    public BaseModel newModel(String tableName) {
        try {
            Class<?> clz = Class.forName("cn.sixlab.minesoft.singte.core.models." + tableName);

            BaseModel nextInfo = (BaseModel) clz.newInstance();

            nextInfo.setStatus(StConst.YES);
            nextInfo.setCreateTime(new Date());

            return nextInfo;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StModelTable getTableInfo(String tableName) {
        StModelTable tableInfo = new StModelTable();
        tableInfo.setTableName(tableName);

        String clzName = "cn.sixlab.minesoft.singte.core.models." + tableName;
        try {
            Class<?> clz = Class.forName(clzName);

            StTable annotation = clz.getAnnotation(StTable.class);

            if (null != annotation) {
                tableInfo.setTitle(annotation.title());
                tableInfo.setReloadUri(annotation.reloadUri());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    public List<StModelColumn> getColumns(String tableName, boolean skipHidden) {
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

                    if ("hidden".equals(type) && skipHidden) {
                        continue;
                    }

                    StModelColumn column = new StModelColumn();
                    column.setColumnName(field.getName());

                    if (StrUtil.isEmpty(type)) {
                        Class<?> fieldType = field.getType();
                        if (CharSequence.class.isAssignableFrom(fieldType)) {
                            type = "input";
                        } else if (Number.class.isAssignableFrom(fieldType)) {
                            type = "input";
                        }
                    }
                    column.setType(type);

                    column.setText(annotation.text());
                    column.setCssClass(annotation.cssClass());
                    column.setPlaceholder(annotation.placeholder());
                    column.setDefaultVal(annotation.defaultVal());
                    column.setOrder(annotation.order());

                    columnList.add(column);
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        columnList.sort(Comparator.comparingInt(StModelColumn::getOrder));

        return columnList;
    }

    public BaseModel getParams(String tableName, HttpServletRequest request) {
        BaseModel params = null;
        try {
            Class<?> clz = Class.forName("cn.sixlab.minesoft.singte.core.models." + tableName);
            params = (BaseModel) clz.newInstance();
            Field[] fieldList = clz.getDeclaredFields();

            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                StColumn annotation = field.getAnnotation(StColumn.class);
                if (null != annotation) {
                    String type = annotation.type();
                    String name = field.getName();
                    String val = request.getParameter(name);
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
