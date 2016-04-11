package jetools.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlConfUtils {

	@SuppressWarnings("unchecked")
	public static <T> T load(String path, Class<T> cls) throws Exception {
		JAXBContext context = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T)unmarshaller.unmarshal(new File(path));
	}
}
