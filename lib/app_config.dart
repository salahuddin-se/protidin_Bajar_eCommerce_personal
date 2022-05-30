var this_year = DateTime.now().year.toString();

class AppConfig {
  static String copyright_text = "@ SYSABLE " + this_year; //this shows in the splash screen
  static String app_name = "Protidin"; //this shows in the splash screen
  static String purchase_code = "e61f6cbb-bf4f-4df0-bfc7-8b2632b513db"; //enter your purchase code for the app from codecanyon
  //static String purchase_code = ""; //enter your purchase code for the app from codecanyon

  //configure this
  static const bool http = false;

  //configure this
  //static const DOMAIN_PATH = "192.168.0.104/ecommerce_demo_three"; //localhost
  //static const DOMAIN_PATH = "demo.activeitzone.com/ecommerce_flutter_demo"; //inside a folder
  static const DOMAIN_PATH = "v1.protidin.com.bd"; // directly inside the public folder

  //do not configure these below
  static const String API_ENDPATH = "api/v2";
  static const String PUBLIC_FOLDER = "public";
  static const String PROTOCOL = /* http ?*/ "http://" /* : "http://"*/;
  static const String RAW_BASE_URL = "${PROTOCOL}${DOMAIN_PATH}";
  static const String BASE_URL = "${RAW_BASE_URL}/${API_ENDPATH}";

  //configure this if you are using amazon s3 like services
  //give direct link to file like http://[[bucketname]].s3.ap-southeast-1.amazonaws.com/
  //otherwise do not change anythink
  static const String BASE_PATH = "${RAW_BASE_URL}/${PUBLIC_FOLDER}/";
  //static const String BASE_PATH = "http://tosoviti.s3.ap-southeast-2.amazonaws.com/";
}
