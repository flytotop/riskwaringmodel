package com.emsoft.riskwaring.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * CopyBean
 *
 * @author TangWeijie
 * @date 2019/2/18 15:21
 */
public class CopyBean {
    public static <U> U simpleCopy(Object object, Class<U> targetClass, String... ignoreProperty) {
        return Optional.of(object).map(v -> {
            U instance = null;
            try {

                instance = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(v, instance, ignoreProperty);

            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }

            return instance;
        }).get();
    }

    public static <U> U simpleCopy(Object object, Class<U> targetClass) {
        return Optional.of(object).map(v -> {
            U instance = null;
            try {
                instance = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(v, instance);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return instance;
        }).get();
    }

    public static void CopyNotNull(Object change, Object object) {
        String[] nullPropertyNames = getNullPropertyNames(change);
        BeanUtils.copyProperties(change, object, nullPropertyNames);

    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
