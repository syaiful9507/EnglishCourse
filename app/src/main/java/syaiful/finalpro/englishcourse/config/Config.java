package syaiful.finalpro.englishcourse.config;

/**
 * Created by syaiful9508 on 05/07/17.
 */

public class Config {


    //Fix Configuration APIs ...
    public static final String SERVER_APIs      = "http://192.168.43.203/EnglishAPIs/";
    public static final String URL_LISTCOURSE   = Config.SERVER_APIs + "Listcourse.php";
    public static final String URL_LISTTENSE    = Config.SERVER_APIs + "ListTense.php";
    public static final String URL_DETAIL_TENSE = Config.SERVER_APIs + "DetailTense.php?id_tense=";
    public static final String URL_LISTCONTENT  = Config.SERVER_APIs + "Listcontent.php?id_ct=";
    public static final String URL_FORMULA      = Config.SERVER_APIs + "Formula.php?id_tense=";
    public static final String IMAGE            = Config.SERVER_APIs + "image/";

    //TAG support of all file API
    public static final String TAG_ID_CATEGORY  = "id";
    public static final String TAG_TITLE        = "title";
    public static final String TAG_IMAGE        = "image";
    public static final String TAG_CONTENT      = "content";
    public static final String TAG_SUBTITLE     = "subtitle";
    public static final String TAG_TITLE_FRM    = "titlefrm";
    public static final String TAG_SUB1         = "sub1";
    public static final String TAG_SUB2         = "sub2";
    public static final String TAG_SUB3         = "sub3";

    //TAG ListContent.php


}
