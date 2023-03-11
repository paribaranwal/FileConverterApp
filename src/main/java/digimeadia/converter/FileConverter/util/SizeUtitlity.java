package digimeadia.converter.FileConverter.util;

public class SizeUtitlity {
    private static final int KB = 1024;
    private static final int MB = 1024 * 1024;
    private static String convertToSize(long size, Integer unit) {
        String value = String.format("%.2f", Double.valueOf((size > 0 ? Double.valueOf(size) / unit : 0)));
        switch(unit) {
            case KB: return value + "KB";
            case MB: return value + "MB";
            default:
                throw new IllegalStateException("Unexpected value: " + unit);
        }
    }
    public static String getSizeInMB(long bytes) {
        return convertToSize(bytes, MB);
    }

    public static String getSizeInKB(Long bytes) {
        return convertToSize(bytes, KB);
    }
}
