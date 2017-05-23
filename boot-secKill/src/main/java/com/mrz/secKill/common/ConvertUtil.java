package com.mrz.secKill.common;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.TypeUtils;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConvertUtil
{

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

	/**
	 * 支持的基本类型
	 */
	private static final List<Class<?>> primitiveClasses = new ArrayList<Class<?>>();

	static
	{
		primitiveClasses.add(boolean.class);
		primitiveClasses.add(Boolean.class);

		primitiveClasses.add(char.class);
		primitiveClasses.add(Character.class);

		primitiveClasses.add(byte.class);
		primitiveClasses.add(Byte.class);

		primitiveClasses.add(short.class);
		primitiveClasses.add(Short.class);

		primitiveClasses.add(int.class);
		primitiveClasses.add(Integer.class);

		primitiveClasses.add(long.class);
		primitiveClasses.add(Long.class);

		primitiveClasses.add(float.class);
		primitiveClasses.add(Float.class);

		primitiveClasses.add(double.class);
		primitiveClasses.add(Double.class);

		primitiveClasses.add(BigInteger.class);
		primitiveClasses.add(BigDecimal.class);

		primitiveClasses.add(String.class);
		primitiveClasses.add(Date.class);
		primitiveClasses.add(java.sql.Date.class);
		primitiveClasses.add(java.sql.Time.class);
		primitiveClasses.add(java.sql.Timestamp.class);
	}

	/**
	 * 是否支持传入的类型
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isSuport(Class<?> clazz)
	{
		return primitiveClasses.contains(clazz);
	}

	private static String castToString(Object obj)
	{
		if (null == obj)
		{
			return "";
		}

		if (obj.getClass() == Date.class)
		{
			return ((Date) obj).getTime() + "";
		}
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	private static <T> Schema<T> getSchema(Class<T> cls)
	{
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null)
		{
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null)
			{
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

	@SuppressWarnings("unchecked")
	public static <T> byte[] serialize(T obj)
	{
		Class<T> cls = (Class<T>) obj.getClass();

		// 如果是基本类型，则转换为string
		if (isSuport(cls))
		{
			return castToString(obj).getBytes();
		}

		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try
		{
			Schema<T> schema = getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		}
		catch (Exception e)
		{
			throw new IllegalStateException(e.getMessage(), e);
		}
		finally
		{
			buffer.clear();
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T castTo(Object obj, Class<T> clazz)
	{
		if (obj == null)
		{
			return null;
		}

		if (clazz == null)
		{
			throw new IllegalArgumentException("clazz is null");
		}

		if (clazz == obj.getClass())
		{
			return (T) obj;
		}

		if (clazz.isAssignableFrom(obj.getClass()))
		{
			return (T) obj;
		}

		if (clazz == boolean.class || clazz == Boolean.class)
		{
			return (T) TypeUtils.castToBoolean(obj);
		}

		if (clazz == byte.class || clazz == Byte.class)
		{
			return (T) TypeUtils.castToByte(obj);
		}

		if (clazz == short.class || clazz == Short.class)
		{
			return (T) TypeUtils.castToShort(obj);
		}

		if (clazz == int.class || clazz == Integer.class)
		{
			return (T) TypeUtils.castToInt(obj);
		}

		if (clazz == long.class || clazz == Long.class)
		{
			return (T) TypeUtils.castToLong(obj);
		}

		if (clazz == float.class || clazz == Float.class)
		{
			return (T) TypeUtils.castToFloat(obj);
		}

		if (clazz == double.class || clazz == Double.class)
		{
			return (T) TypeUtils.castToDouble(obj);
		}

		if (clazz == String.class)
		{
			return (T) TypeUtils.castToString(obj);
		}

		if (clazz == BigDecimal.class)
		{
			return (T) TypeUtils.castToBigDecimal(obj);
		}

		if (clazz == BigInteger.class)
		{
			return (T) TypeUtils.castToBigInteger(obj);
		}

		if (clazz == Date.class)
		{
			return (T) TypeUtils.castToDate(obj);
		}

		if (clazz == java.sql.Date.class)
		{
			return (T) TypeUtils.castToSqlDate(obj);
		}

		if (clazz == java.sql.Timestamp.class)
		{
			return (T) TypeUtils.castToTimestamp(obj);
		}

		if (Calendar.class.isAssignableFrom(clazz))
		{
			Date date = TypeUtils.castToDate(obj);
			Calendar calendar;
			if (clazz == Calendar.class)
			{
				calendar = Calendar.getInstance();
			}
			else
			{
				try
				{
					calendar = (Calendar) clazz.newInstance();
				}
				catch (Exception e)
				{
					throw new JSONException("can not cast to : " + clazz.getName(), e);
				}
			}
			calendar.setTime(date);
			return (T) calendar;
		}

		if (obj instanceof String)
		{
			String strVal = (String) obj;
			if (strVal.length() == 0)
			{
				return null;
			}
		}

		throw new JSONException("can not cast to : " + clazz.getName());
	}
	
	public static <T> T unserialize(byte[] data, Class<T> cls)
	{
		if (data == null || data.length == 0)
		{
			return null;
		}
		
		// 如果是基本类型，则转换为string
		if (isSuport(cls))
		{
			return castTo(new String(data), cls);
		}
		
		try
		{
			T message = cls.newInstance();
			Schema<T> schema = getSchema(cls);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		}
		catch (Exception e)
		{
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	public static <T> List<T> unserialize(List<byte[]> data, Class<T> clazz)
	{
		List<T> result = new ArrayList<T>();
		for (byte[] itemBytes : data)
		{
			result.add(unserialize(itemBytes, clazz));
		}
		return result;
	}

}
