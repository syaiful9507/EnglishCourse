package syaiful.finalpro.englishcourse.config;

/**
 * Created by syaiful9508 on 05/07/17.
 */

public class Config {

    public static final String SERVER_API = "http://192.168.43.203/APIs/";
    public static final String URL_SUBJECT = Config.SERVER_API + "Test.php";
    public static final String URL_GET_SUBJECT = Config.SERVER_API + "getTest.php?id=";
    public static final String URL_GET_CONTENT = Config.SERVER_API + "content.php?id=";
    public static final String TAG_VALUE = "value";
    public static final String Image = SERVER_API + "image/";
    public static final String TAG_ID = "id";
    public static final String TAG_SUBJECTS = "subjects";
    //public static final String TAG_CONTENT = "content";
    //public static final String TAG_IMAGE   = "image";

    //Fix Configuration APIs ...
    public static final String SERVER_APIs      = "http://200.200.200.232/EnglishAPIs/";
    public static final String URL_LISTCOURSE   = Config.SERVER_APIs + "Listcourse.php";
    public static final String URL_LISTTENSE    = Config.SERVER_APIs + "ListTense.php";
    public static final String URL_DETAIL_TENSE = Config.SERVER_APIs + "DetailTense.php?id_tense=";
    public static final String URL_LISTCONTENT  = Config.SERVER_APIs + "Listcontent.php?id_ct=";
    public static final String IMAGE            = Config.SERVER_APIs + "image/";

    //TAG support of all file API
    public static final String TAG_ID_CATEGORY  = "id";
    public static final String TAG_TITLE        = "title";
    public static final String TAG_IMAGE        = "image";
    public static final String TAG_CONTENT      = "content";

    //TAG ListContent.php


}
