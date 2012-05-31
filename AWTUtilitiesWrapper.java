import java.awt.GraphicsConfiguration;
import java.awt.Shape;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

//comment test

public class AWTUtilitiesWrapper
{
    private static Class<?> awtUtilitiesClass;
    private static Class<?> translucencyClass;
    private static Method mSetWindowShape,  mSetWindowOpacity,  mSetWindowOpaque;
    public static Object PERPIXEL_TRANSPARENT,  TRANSLUCENT,  PERPIXEL_TRANSLUCENT;

    static void init()
    {
        try
        {
            awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            translucencyClass = Class.forName("com.sun.awt.AWTUtilities$Translucency");

            if (translucencyClass.isEnum())
            {
                Object[] kinds = translucencyClass.getEnumConstants();

                if (kinds != null)
                {
                    PERPIXEL_TRANSPARENT = kinds[0];
                    TRANSLUCENT = kinds[1];
                    PERPIXEL_TRANSLUCENT = kinds[2];
                }
            }

            mSetWindowShape = awtUtilitiesClass.getMethod("setWindowShape", Window.class, Shape.class);
            mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
            mSetWindowOpaque = awtUtilitiesClass.getMethod("setWindowOpaque", Window.class, boolean.class);
        }
        catch (Exception ex){}
    }

    static
    {
        init();
    }

    private static void set(Method method, Window window, Object value)
    {
        if (awtUtilitiesClass == null || method == null)
        {
            return;
        }
        try
        {
            method.invoke(null, window, value);
        }
        catch (Exception ex) {}
    }

    public static void setWindowShape(Window window, Shape shape)
    {
        set(mSetWindowShape, window, shape);
    }

    public static void setWindowOpacity(Window window, float opacity)
    {
        set(mSetWindowOpacity, window, Float.valueOf(opacity));
    }

    public static void setWindowOpaque(Window window, boolean opaque)
    {
        set(mSetWindowOpaque, window, Boolean.valueOf(opaque));
    }
}
