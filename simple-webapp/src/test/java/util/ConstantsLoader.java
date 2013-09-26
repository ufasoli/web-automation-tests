package util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 26.09.13
 * Time: 09:22
 */
public class ConstantsLoader {

    private static ConstantsLoader instance = null;
    private Properties constants;


    private ConstantsLoader() {
        final String propertiesFileName = "values.properties";

        constants = new Properties();

        InputStream inputStream = ConstantsLoader.class.getClassLoader().getResourceAsStream(propertiesFileName);


        if (inputStream == null) {

            throw new RuntimeException(String.format("Property file %s. cannot be found in the classpath", propertiesFileName));

        }


        try {
            constants.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(String.format("An error occurred while loading the properties file : %s. Error : %s", propertiesFileName, e.getMessage()));
        }


    }

    public static ConstantsLoader loader() {

        if (instance == null) {
            instance = new ConstantsLoader();
        }

        return instance;
    }

    public Properties getConstants() {
        return constants;
    }
}
