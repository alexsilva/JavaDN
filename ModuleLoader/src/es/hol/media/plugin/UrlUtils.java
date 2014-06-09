package es.hol.media.plugin;

/**
 * UrlUtils:
 */
public class UrlUtils {

    /**
     * Getting file name from url;
     * @param url string
     * @return file name
     */
    public static String getFileName(String url) {
        String fileName;

        int slashIndex = url.lastIndexOf("/");
        int qIndex = url.lastIndexOf("?");

        if (qIndex > slashIndex) {//if has parameters
            fileName = url.substring(slashIndex + 1, qIndex);
        } else {
            fileName = url.substring(slashIndex + 1);
        }
        return fileName;
    }
}
