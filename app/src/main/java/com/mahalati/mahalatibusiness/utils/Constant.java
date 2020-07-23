package com.mahalati.mahalatibusiness.utils;

import android.content.SharedPreferences;

/**
 * Created by Mahlati on 29/12/16.
 */

public class Constant {

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.mahlati.mahlti";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";


    public static final String LOCATION_CITY = PACKAGE_NAME + ".LOCATION_CITY";
    public static final String LOCATION_STATE = PACKAGE_NAME + ".LOCATION_STATE";
    public static final String LOCATION_COUNTRY = PACKAGE_NAME + ".LOCATION_COUNTRY";
    public static final String LOCATION_ZIP = PACKAGE_NAME + ".LOCATION_ZIP";
    public static final String LOCATION_STREET = PACKAGE_NAME + ".LOCATION_STREET";
    public static final String LOCATION_DISTRICT = PACKAGE_NAME + ".LOCATION_DISTRICT";
    public static final String LOCATION_ADDRESSLINE0 = PACKAGE_NAME + ".LOCATION_ADDRESSLINE0";


    public static SharedPreferences m_sharedPreference;
    public static SharedPreferences.Editor m_sharedPrefEditor;

    public static final int NO_ACTION = 0;
    public static final int OPEN_FRAGMENT = 1;
    public static final int OPEN_KEYBOARD = 2;
    public static final int OPEN_ORDER_LIST = 3;
    public static final int OPEN_SIGN_IN = 4;
    public static final int FINISH_ACTIVITY = 5;
    public static final int RESUME_SCANNER = 6;
    public static final int OPEN_BRANCH_LIST = 7;
    public static final int OPEN_TRACK_ORDER = 8;
    public static final int OPEN_PRODUCT_LIST = 9;
    public static final int CLOSE_FRAGMENT = 10;
    public static final int OPEN_CART_LIST = 11;

    public static final int PERMISION_CAMERA = 12;
    public static final int PERMISION_STORAGE = 13;
    public static final int PERMISION_LOCATION = 14;
    public static final int PERMISION_SMS = 15;


    public static final int OPEN_HOME = 16;
    public static final int GO_BACK = 17;
    public static final int NO_OTHER = 18;

    public static final String SHOP_DATA= "shopData";
    public static final String DELIVERY_DATA= "deliveryData";

    public static final String USER_TYPE = "user_type";
    public static int SELECTED_USER;

    public static final int CUSTOMER_GUEST = 5;
    public static final int CUSTOMER = 1;
    public static final int DELIVERY = 3;
    public static final int SHOP = 2;
    public static final int SHIPMENT = 4;
    public static final int BRANCHUSER = 6;

    public static final int ENGLISH = 1;
    public static final int ARABIC = 2;

    //Shop classification  1=Store(Private),2=Establishment,3=Company(Family),4=Joint Store
    public static final String STORE = "1";
    public static final String ESTABLISHMENT = "2";
    public static final String COMPANY = "3";
    public static final String JOINT_STORE = "4";

    //shop visibility options   1=All,2=Customers,3=Shops,4=Deliveries
    public static final String ALL = "1";
    public static final String CUSTOMERS = "2";
    public static final String SHOPS = "3";
    public static final String DELIVERIES = "4";

    //shop payment method   //1=Cash Only,2=Credit Card Only,3=Credit Card / Cash

    public static final String CASH_ONLY = "1";
    public static final String CREDIT_CARD_ONLY = "2";
    public static final String CREDIT_CARD_CASH = "3";

    //shop fragment_delivery_registration_two  //1=Yes,2=No
    public static final String YES = "1";
    public static final String NO = "2";

    //Shipment user type //private or company

    public static final String PRIVATE_TYPE = "private";
    public static final String COMPANY_TYPE = "company";

    //Vehicle type

    public static final String VEHICLE_SHIPMENT = "shipment";
    public static final String VEHICLE_DELIVERY = "delivery";
    public static final String VEHICLE_PICK_UP_BY_ME = "pickupbyme";

    //offer discount type
    public static final String BUY_ONE_GET_ONE_FREE = "1";
    public static final String CONDITIONAL = "2";

    //shop type
    public static final String MAIN = "1";
    public static final String BRANCH = "2";


    //Search type
    public static final String SEARCH_ITEM = "1";
    public static final String SEARCH_SHOP = "2";

    //search condition
    public static final String SEARCH_NEW = "1";
    public static final String SEARCH_USED = "2";
    public static final String SEARCH_ALL = "3";

    //bid type
    public static final String FIXED = "fixed";
    public static final String BID = "bid";

    //discount type
    public static final String OFEFR = "offer";
    public static final String DISCOUNT = "discount";

    //Order status
    public static final String SHOP_CANCELED = "shop_canceled";
    public static final String WAIT_SHOP_APPROVAL = "wait_shop_approval";
    public static final String SHOP_CONFIRMED = "shop_confirmed";
    public static final String SHOP_REJECTED ="shop_rejected";
    public static final String SHOP_RETAINED ="shop_retained";


    //Delivery Status
    public static final String DELIVERY_CANCELED = "shop_canceled";
    public static final String DELIVERY_CONFIRMED = "confirmed";
    public static final String DELIVERY_PROCESS = "process";

    //Payment Status
    public static final String PAYMENT_PAID = "paid";
    public static final String PAYMENT_PROCESS = "process";
    public static final String PAYMENT_PENDING = "pending";

    public static final String BID_UPDATED = "updated";
    public static final String BID_PENDING = "pending";
    public static final String BID_ACCEPTED = "accepted";
    public static final String BID_CANCELED = "canceled";
    public static final String BID_REJECTED = "rejected";
    public static final String BID_PURCHASED = "purchased";
    public static final String BID_APPROVED = "approved";

    public static final String PICKUP_BY_ME ="pickupbyme";
    public static final String DELIVERY_BY_SHOP="deliverybyshop";
    public static final String DELIVERYBYSHOP="delivery_by_shop";
    public static final String BY_CASH ="by_cash";
    public static final String REJECT = "reject";


    public static final String WITH_ITEM_BILL = "with_item_bill";
    public static final String CASH_AFTER_DELIVERY = "cash_after_delivery";

    public static final String BY_SADAD = "by_sadad";
    public static final String BY_CREDITCARD = "by_CreditCard";

    public static final String CURRENT = "current";
    public static final String OTHER = "other";

    //public static final String CART_DATA = "cart_data";

    public static boolean needToUpdate = false;
    public static boolean needToUpdateSearch = false;
    public static boolean isChangeButton= false;


    public static boolean needToUpdateCartLogo= false;
    public static boolean needToAddCartCount= false;
    public static boolean needToUpdateCartCount= false;
    public static int countToUpdate;

    public static final int BARCODE_IN = 1;
    public static final int BARCODE_OUT = 2;

    public static boolean isCustomerFromLogin = false;

    public static final String MESSAGE = "MESSAGE";

    public static String DELID = "";
    public static String SHOPID = "";
    public static String GPSLAT = "";
    public static String GPSLONG = "";
}
