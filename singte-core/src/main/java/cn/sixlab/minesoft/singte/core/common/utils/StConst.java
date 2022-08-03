package cn.sixlab.minesoft.singte.core.common.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Locale;

public class StConst {
    public static final String DEPLOY_DATE = DateUtil.format(new Date(), "yyyyMMddHHmmss");

    public static final String YES = "1";
    public static final String No = "0";

    public final static String ST_VERSION   = "st_version";
    public final static String ST_INIT   = "st_init";

    public final static String ST_WX_NAME   = "st_wx_name";
    public final static String ST_WX_QR     = "st_wx_qr";
    public final static String ST_WX_SHOP_QR     = "st_wx_shop_qr";

    public final static String ST_GITHUB_NAME     = "st_github_name";
    public final static String ST_GITHUB_LINK     = "st_github_link";
    public final static String ST_GITHUB_QR     = "st_github_qr";

    public final static String ST_GITEE_NAME     = "st_gitee_name";
    public final static String ST_GITEE_LINK     = "st_gitee_link";
    public final static String ST_GITEE_QR     = "st_gitee_qr";

    public static final String ST_SITE_NAME = "st_site_name";
    public static final String ST_SITE_SUBTITLE = "st_site_subtitle";

    public static final String ST_SITE_LOGO = "st_site_logo";
    public static final String ST_SITE_LOGO_BLOCK = "st_site_logo_block";

    public static final String ST_COPY_YEAR = "st_copyright_year";
    public static final String ST_ICP       = "st_icp";

    public static final String SERVER_SH_PATH = "server_sh_path";

    public static final String WX_APP_ID = "wx_app_id";
    public static final String WX_APP_TOKEN = "wx_app_token";
    public static final String WX_APP_SECRET = "wx_app_secret";

    public static final String DT_APP_KEY = "dt_app_key";
    public static final String DT_APP_SECRET = "dt_app_secret";

    public static final long SECONDS_1      = 1;
    public static final long SECONDS_MIN_1  = 60;
    public static final long SECONDS_MIN_2  = SECONDS_MIN_1 * 2;
    public static final long SECONDS_MIN_3  = SECONDS_MIN_1 * 3;
    public static final long SECONDS_MIN_4  = SECONDS_MIN_1 * 4;
    public static final long SECONDS_MIN_5  = SECONDS_MIN_1 * 5;
    public static final long SECONDS_MIN_10 = SECONDS_MIN_1 * 10;
    public static final long SECONDS_MIN_15 = SECONDS_MIN_1 * 15;
    public static final long SECONDS_MIN_20 = SECONDS_MIN_1 * 20;
    public static final long SECONDS_MIN_25 = SECONDS_MIN_1 * 25;
    public static final long SECONDS_MIN_30 = SECONDS_MIN_1 * 30;
    public static final long SECONDS_MIN_35 = SECONDS_MIN_1 * 35;
    public static final long SECONDS_MIN_90 = SECONDS_MIN_1 * 90;
    public static final long SECONDS_HOUR_1 = SECONDS_MIN_1 * 60;
    public static final long SECONDS_HOUR_2 = SECONDS_MIN_1 * 60 * 2;
    public static final long SECONDS_YEAR_1 = SECONDS_MIN_1 * 365 * 24 * 60;

    public final static String ST_PUBLISH_WILL = "0";
    public final static String ST_PUBLISH_DID = "1";

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final Locale DEFAULT_LOCALE = Locale.CHINESE;
}
