package com.lms.utils.constants;

/**
 * Created by bhushan on 16/5/17.
 */
public class UrlMappingConstant {
    /*Common urls*/
    public static final String CREATE_PATH = "/create";
    public static final String EDIT_PATH = "/edit/{id}";
    public static final String DELETE_PATH = "/delete/{uuid}";
    public static final String INDEX_PATH = "/index";
    public static final String SAVE_PATH = "/save";
    public static final String UPDATE_PATH = "/update";
    public static final String ERROR_PATH = "/error";
    public static final String HOME_PATH = "/";
    public static final String VIEW_PATH = "/view/{uuid}";

    /*USER_PATH CONTROLLER*/
    public static final String USER_PATH = "/user";
    public static final String USER_HOME_PATH = "/home";
    public static final String USER_PLAN = "/userplan";
    public static final String FORGET_PASSWORD_PATH = "/forgetPassword/{email:.+}";
    public static final String RESET_PASSWORD_BY_UUID_PATH = "/resetpassword/{uuid}";
    public static final String RESET_PASSWORD_PATH = "/resetpassword";

    /*BOOK_PATH CONTROLLER*/
    public static final String BOOK_PATH = "/book";
    public static final String BOOK_VIEW_BY_ID_PATH = "/view/{id}";

    /*CATEGORY_PATH CONTROLLER*/
    public static final String CATEGORY_PATH = "/category";

    /*Configuration Controller*/
    public static final String CONFIGURATION_IMAGECONFIGURATION_PATH = "/imageconfiguration";
    public static final String CONFIGURATION_UPDATE_STORAGE_TYPE_PATH = "/updatestoragetype";
    public static final String CONFIGURATION_TEMPLATE_PATH = "/template";
    public static final String CONFIGURATION_EDIT_TEMPLATE_PATH = "/editTemplate/{id}";
    public static final String CONFIGURATION_VIEW_TEMPLATE_PATH = "/viewTemplate/{id}";
    public static final String CONFIGURATION_UPDATE_TEMPLATE_PATH = "/updateTemplate";

    /*Invitation Controller*/
    public static final String INVITATION_BASE_PATH = "invite";
    public static final String INVITATION_INVITE_USER_PATH = "/inviteUser";
    public static final String INVITATION_ACCEPT_INVITATION_BY_TOKEN_PATH = "/accept/{token}";
    public static final String INVITATION_ACCEPT_INVITATION_PATH = "/accept";
    public static final String INVITATION_URL = String.format("%s%s", INVITATION_BASE_PATH, INVITATION_ACCEPT_INVITATION_PATH);

    /*Membership Controller*/
    public static final String MEMBERSHIP_BASE_PATH = "membership";
    public static final String MEMBERSHIP_VIEW_PATH = "/view/{id}";

    /*MembershipPlan Controller*/
    public static final String MEMBERSHIP_PLAN_BASE_PATH = "/plan";
    public static final String MEMBERSHIP_PLAN_PURCHASE_PATH = "/purchase/{membershipPlanId}";

    /*Payment Controller*/
    public static final String PAYMENT_BASE_PATH = "/payment";
    public static final String PAYMENT_PAY_PATH = "/pay";

    /*Issue Book Controller*/
    public static final String ISSUE_BOOK__BASE_PATH = "/issuebook";
    public static final String VALIDATE_USER_FOR_ASSIGN_BOOK_PATH = "/validateUser";
    public static final String ASSIGN_BOOK_PATH = "/assign";


}
