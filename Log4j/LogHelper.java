/**
 * 实现按日期和大小分割日志的代码片段
 * https://blog.csdn.net/uukenyoyo/article/details/53669480?utm_source=itdadao&utm_medium=referral
 */

/**
 * 1. 字符串/整型 互转
 */
String a = String.valueOf(2);
int i = Integer.parseInt(a);

/**
 * 2. 向文件末尾添加内容
 */
BufferWriter out = null;
try {
    out = new BufferWriter(new FileWriter("filename", true));
    out.write("aString");
} catch(IOException e) {
    // error processing here
} finally {
    if (out != null) {
        out.close();
    }
}

/**
 * 3. 得到当前方法的名字
 */
String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

/**
 * 4. 转字符串到String
 */
java.util.Date = java.text.DateFormat.getDateInstance().parse(date String);
// 或者
SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
Date date = format.parse(myString);

/**
 * 5. 使用JDBC链接Oracle
 */
public class OracleJdbcTest {
    String driverClass = "oracle.jdbc.driver.OracleDriver";
    Connection con;
    
    public void init(FileInputString fs) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Properties props = new Properties();
        props.load(fs);
        String url = props.getProperties("db.url");
        String userName = props.getProperties("db.user");
        String password = props.getProperties("db.password");
        Class.forName(driverClass);
        con = DriverManager.getConnection(url, userName, password);
    }
    
    public void fetch() throws SQLException, IOException {
        PreparedStatement ps = con.prepareStatement("select SYSDATE from dual");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // do something
        }
        rs.close();
        ps.close();
    }
}

/**
 * 6. 把java.util.Date 转成 java.sql.Date
 */
java.util.Date utilDate = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

/**
 * 7. 使用NIO进行快速的文件拷贝
 */
public static void fileCopy(File in, File out) throws IOException {
    FileChannel inChannel = new FileInputStream(in).getChannel();
    FileChannel outChannel = new FileOutputStream(out).getChannel();
    try {
        // inChannel.transferTo(0, inChannel.size(), outChannel);
        // original -- apparently has trouble copying larges file on Windows
        
        // magic number for Windows, 64Mb - 32Kb
        int maxCount = (64 * 1024 * 1024) - (32 * 1024);
        long size = inChannel.size();
        long position = 0;
        while (position < size) {
            position += inChannel.transferTo(position, maxCount, outChannel);
        }
    }
    finally {
        if (inChannel != null) {
            inChannel.close();
        }
        if (outChannel != null) {
            outChannel.close();
        }
    }
}

